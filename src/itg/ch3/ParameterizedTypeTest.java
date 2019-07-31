package itg.ch3;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParameterizedTypeTest<T> {

    private List<T> list = null;
    private Set<T> set = null;
    private Map<Integer, String> map = null;
    private Map.Entry<String,Integer> mapEntry;

    public void test() throws NoSuchFieldException {
        Field field = ParameterizedTypeTest.class.getDeclaredField("list");
        System.out.println(field.getClass().getName());//java.lang.reflect.Field
        Type typeList = field.getGenericType();
        System.out.println(typeList.getClass().getName());//sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

        // 在ParameterizedType接口中，有3个方法，分别是getActualTypeArguments()、 getRawType()、 getOwnerType();
//        test_getActualTypeArguments();
//        test_getRawType();
        test_getOwnerType();

    }

    //1. getActualTypeArguments()获取泛型中的实际类型，可能会存在多个泛型，例如Map<K,V>,所以会返回Type[]数组；
    private void test_getActualTypeArguments() throws NoSuchFieldException {
        Field mapField = ParameterizedTypeTest.class.getDeclaredField("map");
        Type type = mapField.getGenericType();
        System.out.println(type.getClass().getName());//sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
        ParameterizedType parameterizedType = (ParameterizedType) type;
        /**
         * 值得注意的是，无论<>中有几层嵌套(List<Map<String,Integer>)，getActualTypeArguments()方法永远都是脱去最外层的<>(也就是List<>)，将口号内的内容（Map<String,Integer>）返回；
         * 我们经常遇到的List<T>，通过getActualTypeArguments()方法，得到的返回值是TypeVariableImpl对象，也就是TypeVariable类型(后面介绍);
         */
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            System.out.println("泛型类型 : " + t);//泛型类型 : class java.lang.Integer  泛型类型 : class java.lang.String
        }
    }


    //2.getRawType()获取声明泛型的类或者接口，也就是泛型中<>前面的那个值；
    private void test_getRawType() {
        try {
            Field mapField = ParameterizedTypeTest.class.getDeclaredField("map");
            Type type = mapField.getGenericType();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println(type.getClass().getName());//sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl

            Type rawType = parameterizedType.getRawType();
            System.out.println(rawType);//interface java.util.Map


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    //3. getOwnerType()此方法是获取泛型的拥有者，那么拥有者是个什么意思？，“拥有者”表示的含义--内部类的“父类”，
    // 通过getOwnerType()方法可以获取到内部类的“拥有者”；例如： Map  就是 Map.Entry<String,String>的拥有者；
    private void test_getOwnerType(){
        try {
            Field filedMapEntry = ParameterizedTypeTest.class.getDeclaredField("mapEntry");
            Type type = filedMapEntry.getGenericType();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type ownerType = parameterizedType.getOwnerType();
            System.out.println(ownerType);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



}
