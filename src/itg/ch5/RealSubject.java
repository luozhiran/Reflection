package itg.ch5;

/**
 * 真实角色：实现了Subject的request()方法
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("From real subject.");
    }
}
