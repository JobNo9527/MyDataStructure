/**
 * @author 将晖
 */


public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);  //入队列
    E dequeue();    //出队列
    E getFront();   //查看队头元素
}
