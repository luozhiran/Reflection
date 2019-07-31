package itg.ch3;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericArrayTypeTest<T> {
    /**
     * 2.GenericArrayTypeTest
     * 泛型数组类型，例如List<String>[] 、T[]等；
     * 在GenericArrayType接口中，仅有1个方法，就是getGenericComponentType()；
     */
    private T[] t;
    private List<String>[] listArray;


    public void test() throws NoSuchFieldException {
        Field fieldListArray = GenericArrayTypeTest.class.getDeclaredField("listArray");
        Type type = fieldListArray.getGenericType();
        System.out.println(type.getClass().getName());//sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
        test_getGenericComponentType();
    }


    /**
     * 2.1 getGenericComponentType
     * 返回泛型数组中元素的Type类型，即List<String>[] 中的
     * List<String>（ParameterizedTypeImpl）、T[] 中的T（TypeVariableImpl）；
     *
     * 值得注意的是，无论是几维数组，getGenericComponentType()方法都只会脱去最右边的[]，返回剩下的值；
     */
    private void test_getGenericComponentType(){
        try {
            Field field = GenericArrayTypeTest.class.getDeclaredField("listArray");
            Type type = field.getGenericType();
            GenericArrayType genericArrayType = (GenericArrayType) type;

            Type tp = genericArrayType.getGenericComponentType();
            System.out.println(tp);//java.util.List<java.lang.String>
            ParameterizedType pt = (ParameterizedType) tp;
           Type rawType = pt.getRawType();
           System.out.println(rawType);//interface java.util.List
            Type[] tys = pt.getActualTypeArguments();
          for (Type t:tys){
              System.out.println(t);//class java.lang.String
          }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
