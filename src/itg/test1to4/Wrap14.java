package itg.test1to4;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Wrap14<T extends SuperParamT, D > extends Super14<T> {

    private D d;

    private void createObj() {
        Class temp = getClass();
        getInstancedGenericTClass(temp);
    }


    public void test() {
        createObj();
    }


    private void getInstancedGenericTClass(Class z) {
        Type type = z.getGenericSuperclass();
        System.out.println(type);//itg.test1to4.Super14<T>
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        System.out.println(types[0]);
        if (types[0] instanceof Class) {
            Class tempClass = (Class) types[0];
            System.out.println("--->  "+tempClass);
        } else if (types[0] instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) types[0]).getRawType();
            System.out.println(rawType);
        }else {
            System.out.println("fsdfsdfdsfds");
        }

    }


    private List<String> list;

    private void get(){
        List<String> d = new ArrayList<>();
    }




}
