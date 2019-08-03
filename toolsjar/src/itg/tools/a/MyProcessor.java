package itg.tools.a;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

public class MyProcessor extends AbstractProcessor {

    //类名的前缀、后缀
    public static final String SUFFIX = "AutoGenerate";
    public static final String PREFIX = "My_";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        for (TypeElement typeElement : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(typeElement)) {
                //打印
                messager.printMessage(Diagnostic.Kind.WARNING, "Printing:" + e.toString());
                messager.printMessage(Diagnostic.Kind.WARNING, "Printing:" + e.getSimpleName());
                messager.printMessage(Diagnostic.Kind.WARNING, "Printing:" + e.getEnclosedElements().toString());
                //获取注解
                ApiAnnotation apiAnnotation = e.getAnnotation(ApiAnnotation.class);
                //获取元素名称并将其首字母大写
                String name = e.getSimpleName().toString();
                char c = Character.toUpperCase(name.charAt(0));
                name = String.valueOf(c + name.substring(1));
                //包裹注解元素的元素，也就是其父元素，比如注解了成员变量或者函数，其上层就是该类
                Element enclosingElement = e.getEnclosingElement();
                //获取父元素的的全类名，用来生成包名
                String enclosingQualifiedName;
                if (enclosingElement instanceof PackageElement) {
                    enclosingQualifiedName = ((PackageElement) enclosingElement).getQualifiedName().toString();
                } else {
                    enclosingQualifiedName = ((TypeElement) enclosingElement).getQualifiedName().toString();
                }

                //生产包名
                String generatePackageName = enclosingQualifiedName.substring(0, enclosingQualifiedName.lastIndexOf("."));
                //生成类名
                String genarateClassName = PREFIX + enclosingElement.getSimpleName() + SUFFIX;
                Writer w = null;
                try {
                    //创建Java 文件
                    JavaFileObject f = processingEnv.getFiler().createClassFile(genarateClassName);
                    w = f.openWriter();
                    PrintWriter pw = new PrintWriter(w);
                    pw.println("package " + generatePackageName + ";");
                    pw.println("\npublic class " + genarateClassName + " { ");
                    pw.println("\n    /** 打印值 */");
                    pw.println("    public static void print" + name + "() {");
                    pw.println("        // 注解的父元素: " + enclosingElement.toString());
                    pw.println("        System.out.println(\"代码生成的路径: " + f.toUri() + "\");");
                    pw.println("        System.out.println(\"注解的元素: " + e.toString() + "\");");
                    pw.println("        System.out.println(\"注解的版本: " + apiAnnotation.version() + "\");");
                    pw.println("        System.out.println(\"注解的作者: " + apiAnnotation.author() + "\");");
                    pw.println("        System.out.println(\"注解的日期: " + apiAnnotation.date() + "\");");
                    pw.println("    }");
                    pw.println("}");
                    pw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (w != null) {
                            w.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
        return true;
    }
}
