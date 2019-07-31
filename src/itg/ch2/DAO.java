package itg.ch2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class DAO<T> {
    Class  clazz;
    public DAO() {
        System.out.println("'DAO's Constrctor...");
        System.out.println(this.getClass().getName());//结果是:itg.ch2.PersionDAO  --> this：父类构造方法中的this指的是子类对象，因为此时是PersonDAO对象在调用

        //获取this的父类DAO
        Class cla = this.getClass().getSuperclass();
        System.out.println(cla.getName());//结果是:itg.ch2.DAO --> 此时只能获的父类的类型名称，却不可以获得父类的泛型参数

        // 获取DAO类带泛型参数的子类

        Type type = this.getClass().getGenericSuperclass();
        System.out.println(type);//结果是: itg.ch2.DAO<itg.ch1.Persion> ，此时获得了泛型参数，然后就是把它提取出来

        //获取具体的泛型参数 DAO<T>
        if (type instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] arges = parameterizedType.getActualTypeArguments();
            System.out.println(Arrays.asList(arges));//结果是: [class itg.ch1.Persion]
            //得到的是一个数组，因为可能父类是多个泛型参数public class DAO<T,PK>{}
            if(arges != null && arges.length >0){
                Type arg = arges[0];
                System.out.println(arg);      //结果是：class itg.ch1.Persion
                //获得第一个参数
                if(arg instanceof Class){
                    clazz = (Class<T>) arg;
                    //把值赋给clazz字段
                }
            }
        }
    }
    T get(Integer id) {
        return null;
    }


    void save(T entity) {

    }
}
