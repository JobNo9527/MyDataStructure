/**
 * @author 将晖
 */


public class LinkedListR<E> {

    private class Node {
        public E e;
        public LinkedListR.Node next;

        public Node(E e, LinkedListR.Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //不采用虚拟头结点方式，递归实现链表
    public Node head;
    public int size;

    public LinkedListR() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Index illegal.");
        }

        head = add(head, index, e);
        size++;
    }

    private Node add(Node node, int index, E e) {

        if (index == 0)
            return new Node(e, node);

        node.next = add(node, index - 1, e);
        return node;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size - 1, e);
    }

    public E get(int index) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get failed.Index illegal.");

        return get(head, index);
    }

    private E get(Node node, int index) {

        if (index == 0)
            return node.e;

        return (E) get(node.next, index - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Index illegal.");
        }

        set(head, index, e);
    }

    private void set(Node node, int index, E e) {

        if (index == 0) {
            node.e = e;
            return;
        }
        set(node.next, index - 1, e);
    }

    public boolean contains(E e) {
        return contains(head, e);
    }

    private boolean contains(Node node, E e) {

        if (node == null)
            return false;

        if (node.e.equals(e))
            return true;

        return contains(node.next, e);
    }

    /*
        Pair<K,V>
            一个方便的类来表示名称 - 键值对。
     */

    /*
    public E remove(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Index illegal.");
        }

        Pair<Node, E> res = remove(head, index);
        size--;
        head = res.getKey();
        return res.getValue();
    }

    private Pair<Node, E> remove(Node node, int index) {

        if (index == 0)
            return new Pair<>(node.next, node.e);
        Pair<Node, E> res = remove(node.next, index - 1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }
     */


    public void removeElement(E e) {
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e) {

        if (node == null)
            return null;

        node.next = removeElement(node.next, e);

        if (node.e.equals(e)) {
            size--;
            return node.next;
        }

        return node;
    }

    public String toString() {

        StringBuilder res = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListR<Integer> list = new LinkedListR<>();
        for (int i = 0; i < 2; i++) {
            list.addFirst(i);
            //System.out.print(list);
        }
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list);
        //System.out.println(list);
    }

}
