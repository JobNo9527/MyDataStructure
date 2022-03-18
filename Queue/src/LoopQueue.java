/**
 * @author 将晖
 */


/*
    注意循环队列中的空间问题：
        多出一个空间，为了就是循环，使得头尾相连

    遍历循环队列的两种方法需要复习：
        一种是从front开始到tail
        另一种是从0开始到size
 */

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];  //多一个空间，用于头尾相连
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {

        //扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        //tail++;
        tail = (tail + 1) % data.length;    //循环队列的体现
        size++;
    }

    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--; //bug1:漏添加了该步骤，出现缩容失败，size计数失败；假设在不知道哪错误时，应按思路思考：首先时size为什么++ --，没--成功的话，就有dequeue的方法可能错误，再逐一排查

        //缩容，第一个条件是懒惰方案，第二个条件是防止当只有一个元素的数组进行缩容时，导致数组的下标出现-1的情况
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return ret;
    }

    @Override
    public E getFront() {

        if (isEmpty())
            throw new IllegalArgumentException("Queue is Empty");
        return data[front];
    }

    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {    //遍历循环队列方式1
            //newData[0] = data[front] -> newData[i] = data[i+front] -> 防止数组越界 -> newData[i] = data[(i + front) % data.length];
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d ,capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {     //遍历循环队列方式2
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }

        res.append("] tail");

        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
