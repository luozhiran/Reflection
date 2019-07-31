package itg.ch1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainCh1 {

    public static void main(String[] args) {
//        parseInstance();
//        parseClass();
        loadClass();
    }


    private static void parseClass() {
        Class<?> cla = Persion.class;
        Field[] fields = cla.getDeclaredFields();
        for (Field fel : fields) {
            System.out.println(fel.getName());
        }
    }


    private static void parseInstance() {
        Persion persion = new Persion();
        persion.setAge(100);
        persion.setName("reflect");
        persion.setSex(1);
        Class cla = persion.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field fld : fields) {
            System.out.println(fld.getName());
        }
    }

    private static void loadClass() {
        String classNamePath = "itg.ch1.Persion";
        try {
            Class cla = Class.forName(classNamePath);
            Field[] fields = cla.getDeclaredFields();
            for (Field fel : fields) {
                System.out.println(fel.getName());
            }
            System.out.println("");
            Method[] mths = cla.getDeclaredMethods();
            for (Method mth : mths) {
                System.out.println(mth.getName());
            }
            System.out.println("");
            Method method = cla.getDeclaredMethod("setName", String.class);
            System.out.println(method.getName());
            Persion persion = (Persion) cla.newInstance();
            method.invoke(persion, "exec invoke");
            System.out.println(persion.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
