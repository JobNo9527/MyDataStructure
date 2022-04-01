package MaxHeap.JianzhiOffer_40;

/*
    用最大堆思想解决top K问题
 */
public class Solution {

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

        // 将传来的数组返回给成员变量
        public Array(E[] arr) {
            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            size = arr.length;
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


        public void swap(int i, int j) {

            if (i < 0 || i >= size || j < 0 || j >= size)
                throw new IllegalArgumentException("Index is illegal.");

            E t = data[i];
            data[i] = data[j];
            data[j] = t;
        }


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

    public class MaxHeap<E extends Comparable<E>> {

        private Array<E> data;

        public MaxHeap(int capacity) {
            data = new Array<E>(capacity);
        }

        public MaxHeap() {
            data = new Array<E>();
        }

        // 将传来的数组转换为堆的情况
        public MaxHeap(E[] arr) {
            data = new Array<>(arr);
            if (arr.length != 1) {
                for (int i = parent(arr.length - 1); i >= 0; i--)
                    siftDown(i);
            }
        }


        // 返回堆中的元素个数
        public int size() {
            return data.getSize();
        }

        // 返回一个布尔值，表示队长是否为空
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index) {
            if (index == 0)
                throw new IllegalArgumentException("index-0 doesn't have parent.");
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChild(int index) {
            return index * 2 + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return index * 2 + 2;
        }

        // 向堆中添加元素
        public void add(E e) {
            data.addLast(e);    // 无需考虑容量，已实现自动扩容功能
            siftUp(data.getSize() - 1);
        }

        private void siftUp(int k) {

            while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
                data.swap(k, parent(k));
                k = parent(k);  // 继续与现在位置的父亲节点大小比较
            }
        }

        // 看堆中的最大元素
        public E findMax() {
            if (data.getSize() == 0)
                throw new IllegalArgumentException("Can not findMax when heap is empty.");
            return data.get(0);
        }

        // 取出堆中最大元素
        public E extractMax() {

            // findMax中已实现堆空的情况
            E ret = findMax();

            data.swap(0, data.getSize() - 1);
            data.removeLast();
            siftDown(0);

            return ret;
        }

        private void siftDown(int k) {

            // 因为右孩子的索引比左孩子的索引还要大，如果左孩子的索引都越界了，右孩子的索引一定越界，也就是到了k是个叶子节点的时候了
            while (leftChild(k) < data.getSize()) {

                int j = leftChild(k);   // 此时j+1就是右孩子
                if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                    j = rightChild(k);  // j++;
                // data[j] 是leftChild 和 rightChild中的最大值

                // 下沉终止条件
                if (data.get(k).compareTo(data.get(j)) >= 0)
                    break;

                data.swap(k, j);
                k = j;  // k下沉进行下一轮比较
            }
        }

        // 取出队堆中最大的元素，并替换成元素e
        public E replace(E e) {
            E ret = findMax();
            data.set(0, e);
            siftDown(0);
            return ret;
        }
    }

    public interface Queue<E> {

        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();
    }

    public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

        private MaxHeap<E> maxHeap;

        public PriorityQueue(){
            maxHeap = new MaxHeap<E>();
        }

        @Override
        public int getSize() {
            return maxHeap.size();
        }

        @Override
        public boolean isEmpty() {
            return maxHeap.isEmpty();
        }

        @Override
        public E getFront() {
            return maxHeap.findMax();
        }

        @Override
        public void enqueue(E e) {
            maxHeap.add(e);
        }

        @Override
        public E dequeue() {
            return maxHeap.extractMax();
        }
    }


    public int[] getLeastNumbers(int[] arr, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++)
            pq.enqueue(arr[i]);

        for (int i = k; i < arr.length; i++)
            if (!pq.isEmpty() && arr[i] < pq.getFront()) {
                pq.dequeue();
                pq.enqueue(arr[i]);
            }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.dequeue();
        }
        return res;
    }
}
