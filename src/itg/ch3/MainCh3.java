package itg.ch3;

/**
 * Java-Type体系:
 * <p>
 * 原始类型(Class)
 * 参数化类型(ParameterizedType) 表示参数化类型，也就是泛型，例如List<T>、Set<T>等；
 * 数组类型(GenericArrayTypeTest)
 * 类型变量(TypeVariable)
 * 基本类型(Class)
 */
public class MainCh3 {

    public static void main(String[] args) {
//        ParameterizedTypeTest typeTest = new ParameterizedTypeTest();
//        try {
//            typeTest.test();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

//        GenericArrayTypeTest genericArrayTypeTest = new GenericArrayTypeTest();
//        try {
//            genericArrayTypeTest.test();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

        TypeVariableTest typeVariableTest = new TypeVariableTest();
        try {
            typeVariableTest.test();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }


}
