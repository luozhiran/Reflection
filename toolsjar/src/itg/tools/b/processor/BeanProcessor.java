package itg.tools.b.processor;

import itg.tools.b.Seriable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


@SupportedAnnotationTypes("itg.tools.b.Seriable")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class BeanProcessor extends AbstractProcessor {

    private Elements elementUtils;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // 元素操作的辅助类
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 获得被该注解声明的元素
        Set<? extends Element> elememts = roundEnv.getElementsAnnotatedWith(Seriable.class);
        TypeElement classElement = null;// 声明类元素
        List<VariableElement> fields = null;// 声明一个存放成员变量的列表
        // 存放二者
        Map<String, List<VariableElement>> maps = new HashMap<String, List<VariableElement>>();

        // 遍历
        for (Element ele : elememts) {
            // 判断该元素是否为类
            if (ele.getKind() == ElementKind.CLASS) {
                classElement = (TypeElement) ele;
                maps.put(classElement.getQualifiedName().toString(), fields = new ArrayList<VariableElement>());
            } else if (ele.getKind() == ElementKind.FIELD) // 判断该元素是否为成员变量
            {
                VariableElement varELe = (VariableElement) ele;
                // 获取该元素封装类型
                TypeElement enclosingElement = (TypeElement) varELe.getEnclosingElement();
                // 拿到key
                String key = enclosingElement.getQualifiedName().toString();
                fields = maps.get(key);
                if (fields == null) {
                    maps.put(key, fields = new ArrayList<VariableElement>());
                }
                fields.add(varELe);
            }
        }


        for (String key : maps.keySet()) {
            if (maps.get(key).size() == 0) {
                TypeElement typeElement = elementUtils.getTypeElement(key);
                List<? extends Element> allMembers = elementUtils.getAllMembers(typeElement);
                if (allMembers.size() > 0) {
                    maps.get(key).addAll(ElementFilter.fieldsIn(allMembers));
                }
            }
        }

        generateCodes(maps);

        return false;
    }

    private void generateCodes(Map<String, List<VariableElement>> maps) {
        File dir = new File("f://apt_test");
        if (!dir.exists())
            dir.mkdirs();
        // 遍历map
        for (String key : maps.keySet()) {
            // 创建文件
            File file = new File(dir, key.replaceAll("\\.", "_") + ".txt");
            try {
                /**
                 * 编写json文件内容
                 */
                FileWriter fw = new FileWriter(file);
                fw.append("{").append("class:").append("\"" + key + "\"")
                        .append(",\n ");
                fw.append("fields:\n {\n");
                List<VariableElement> fields = maps.get(key);

                for (int i = 0; i < fields.size(); i++) {
                    VariableElement field = fields.get(i);
                    fw.append("  ").append(field.getSimpleName()).append(":")
                            .append("\"" + field.asType().toString() + "\"");
                    if (i < fields.size() - 1) {
                        fw.append(",");
                        fw.append("\n");
                    }
                }
                fw.append("\n }\n");
                fw.append("}");
                fw.flush();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
