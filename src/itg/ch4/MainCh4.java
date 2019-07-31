package itg.ch4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class MainCh4 {

    public static void main(String[] args) {

        try {
            Class cla = Class.forName("itg.ch4.Test");
            Constructor<Test> con = cla.getDeclaredConstructor(String.class);
            Test t = con.newInstance("测试内容");
            System.out.println(t.getTastName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
