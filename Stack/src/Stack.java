/**
 * @author 将晖
 */


public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e); //进栈
    E pop();    //出栈
    E peek();   //查看栈顶元素
}
