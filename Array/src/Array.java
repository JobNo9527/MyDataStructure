/**
 * @author 将晖
 */

//泛形类
public class Array<E> {

    //保证该类封装性
    private E[] data;
    //数组有多少个元素，同时指向没有元素（空空间）的第一个空
    private int size;

    //带参构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参的构造函数，默认数组的容量是capacity=10
    public Array() {
        this(10);
    }

    //获取数组中的元素个数
    public int getSize() {
        return size;
    }

    //获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在第index个位置插入一个新元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if (size == data.length)
            resize(2 * data.length);

        //腾出index索引的空间，但index源处仍有源元素，但是index+1处有该源元素的副本，故可放心放入新元素
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    //向所有元素后添加一个新元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 获取index索引位置的元素，使用户无法获取到未被放置元素的空间，保证数据安全性
    public E get(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed! Index is illegal.");
        return data[index];
    }

    //修改index处的元素为e
    public void set(int index, E e) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed! Index is illegal.");
        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(E e) {

        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))//值比较而不是引用比较，所谓引用比较就是对象的地址比较
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回 -1
    public int find(E e) {

        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 查找到所有元素e，并返回存入到的数组
    //public int[] findAll(int e){
    //    return
    //}

    // 从数组中删除index位置的元素，返回删除的元素
    public E remove(int index) {

        //同时也可以判断数组是否为空，当你的index是0时，if的条件是真，抛出异常
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed! Index is illegal.");

        E ret = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--; //此时size还指着一个值，没有指着空空间的第一个空，改成泛形之后，size指着存在的东西，并不会被垃圾回收机制回收
        data[size] = null;  // loitering objects != memory leak 【闲逛的东西并不等于内存溢出】

        //减少到一定程度，减收空间,Eager方案(size == data.length / 2) -> Lazy方案(size == data.length / 4)，防止过于频繁地增加缩减的空间
        //data.length / 2 != 0这个是防止当数组容量capacity只有1时，resize数组容量到0，这显然是不可能的，加在if的条件判断，使其不进行resize即可
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;


    }

    // 从数组中删除第一个元素。返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后一个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    // 从数组中删除所有元素e的方法
    //public boolean removeElementAll(E e) {
    //    return true;
    //}


    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');

        return res.toString();
    }

    //动态数组的体现
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData; //使data指向newData，这样的
    }
}
