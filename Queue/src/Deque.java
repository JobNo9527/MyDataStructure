/**
 * @author 将晖
 */

/*
    双端队列
 */

public class Deque<E> {

    private E[] data;
    private int front, tail;
    private int size;   //记录队列中的元素

    public Deque(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void addLast(E e) {

        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public void addFront(E e) {

        if (size == getCapacity())
            resize(getCapacity() * 2);

        //确定需要添加的位置 -> 是front - 1 -> front = 0的情况怎么处理，不处理就出现下标不合法，处理的方案是这样的：让其指向最后一个空间，这样
        //相当于使改队列的头改变了指向位置；当其不为0的时候，就直接在-1的位置放新元素就可以了
        front = front == 0 ? data.length - 1 : front - 1;
        data[front] = e;
        size++;
    }

    public E removeFront() {

        if (isEmpty())
            throw new IllegalArgumentException("Cannot deque from an empty queue.");

        E ret = data[front];
        data[front] = null;
        size--;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E removeLast() {

        if (isEmpty())
            throw new IllegalArgumentException("Cannot deque from an empty queue.");

        tail = tail == 0 ? data.length - 1 : tail - 1;
        E ret = data[tail];
        data[tail] = null;
        size--;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    public E getFront() {

        if (isEmpty())
            throw new IllegalArgumentException("Queue is Empty.");

        return data[front];
    }

    public E getLast() {

        if (isEmpty())
            throw new IllegalArgumentException("Queue is Empty.");

        return data[tail];
    }

    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d ,capacity = %d",getSize(),getCapacity()));
        res.append(" front [");
        for (int i =0; i<size;i++){
            res.append(data[(i+1)% data.length]);

            if (i != size - 1)
                res.append(" ,");
        }

        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i < 16; i++) {

            if (i % 2 == 0)
                dq.addLast(i);
            else
                dq.addFront(i);

            System.out.println(dq);
        }

        System.out.println("------");

        for (int i = 0; !dq.isEmpty(); i++) {
            if (i%2==0)
                dq.removeFront();
            else
                dq.removeLast();

            System.out.println(dq);
        }

    }


}
