package itg.ch3;

import java.lang.reflect.*;
import java.util.List;

public class TypeVariableTest<T> {

    private List<T> list;
    private T t;

    /**
     * 3.TypeVariable
     * <p>
     * 泛型的类型变量，指的是List<T>、Map<K,V>中的T，K，V等值，
     * 实际的Java类型是TypeVariableImpl（TypeVariable的子类）；
     * 此外，还可以对类型变量加上extend限定，这样会有类型变量对应的上限；
     *
     * 在TypeVariable接口中，有3个方法，分别为getBounds()、getGenericDeclaration()、getName()；
     */
    public void test() throws NoSuchFieldException {
        Field field = TypeVariableTest.class.getDeclaredField("list");
        //获取该属性的实际类型：
        Type type = field.getGenericType();
        System.out.println(type);//java.util.List<T>
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        System.out.println(types[0].getClass().getName());//sun.reflect.generics.reflectiveObjects.TypeVariableImpl
//        test_getBounds();
//        test_getGenericDeclaration();
        test_getName();
    }

    /**
     * 获得该类型变量的上限，也就是泛型中extend右边的值；
     * 例如 List<T extends Number> ，Number就是类型变量T的上限；
     * 如果我们只是简单的声明了List<T>（无显式定义extends），那么默认为Object；
     *
     * 值得注意的是，类型变量的上限可以为多个，必须使用&符号相连接，例如 List<T extends Number & Serializable>；其中，& 后必须为接口；
     */
    private void test_getBounds(){

        try {
            Field field = TypeVariableTest.class.getDeclaredField("t");
            TypeVariable typeVariable = (TypeVariable) field.getGenericType();
            //获取类型变量T的上边界
            Type[] types = typeVariable.getBounds();
            for (Type t:types){
                System.out.println(t);//class java.lang.Object
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3.2 getGenericDeclaration
     * 获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest；
     */
    private void test_getGenericDeclaration(){
        try {
            Field field = TypeVariableTest.class.getDeclaredField("t");
            TypeVariable typeVariable = (TypeVariable) field.getGenericType();
            GenericDeclaration genericDeclaration =typeVariable.getGenericDeclaration();
            System.out.println(genericDeclaration);//class itg.ch3.TypeVariableTest
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 3.3 getName
     * 获取类型变量在源码中定义的名称；
     *
     * 说到TypeVariable类，就不得不提及Java-Type体系中另一个比较重要的接口---GenericDeclaration；
     * 含义为：声明类型变量的所有实体的公共接口；也就是说该接口定义了哪些地方可以定义类型变量（泛型）；
     *
     * 通过查看源码发现，GenericDeclaration下有三个子类，分别为Class、Method、Constructor；也就是说，我们定义泛型只能在一个类中这3个地方自定义泛型；
     */
    private void test_getName(){
        try {
            Field field = TypeVariableTest.class.getDeclaredField("t");
            TypeVariable typeVariable = (TypeVariable) field.getGenericType();
            System.out.println(typeVariable.getName());//class itg.ch3.TypeVariableTest
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
