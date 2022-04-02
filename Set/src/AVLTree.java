import java.util.ArrayList;

/*
    符合二分搜索树性质的AVLTree自平衡树
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {

        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {

        if (node == null)
            return true;

        int balancedFactor = getBalanceFactor(node);
        if (Math.abs(balancedFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 获得节点node的高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    //获得节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {

        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新Height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {

        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 向二分搜索树中添加新的元素(key,value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key,value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        // 递归终点
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        // 递归插入
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else    //key.compareTo(node.key) == 0
            node.value = value;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //if (Math.abs(balanceFactor) > 1)
        //    System.out.println("unbalanced : " + balanceFactor);

        // 平衡维护，不平衡节点（node）的左侧中左子树比右子树高，并且这个高度差的绝对值比1要大，这个树整体向左倾斜
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);   // 返回递归上一层，这次操作后，以node为根节点的树，即满足了平衡二叉树和又满足了二分搜索树的性质

        // 平衡维护，不平衡节点的右侧中左子树比右子树矮，并且这个高度差的绝对值比1要大，这个树整体向右倾斜
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // 平衡维护，不平衡节点的左侧中左子树比右子树矮，并且这个高度差的绝对值比1要大，这个树整体向左倾斜
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 将LR的情况转换成LL的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 平衡维护，不平衡节点的右侧中左子树比右子树高，并且这个高度差的绝对值比1要大，这个树整体向右倾斜
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 将RL的情况转换为RR的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else    //key.compareTo(node.key)>0
            return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 二分搜索树中删除元素为e的节点
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        // 如果没有这个键的话就删不了，返回空
        return null;
    }

    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {    // key.compareTo(node.key) == 0
            if (node.left == null) {    // 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {    // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //  待删除节点左右子树都不为空的情况
                //  找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //  用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        // 如果删除的节点是叶子结点（也就是没有高度，没有左右子树的节点）
        if (retNode == null)
            return null;

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护，不平衡节点（node）的左侧中左子树比右子树高，并且这个高度差的绝对值比1要大，这个树整体向左倾斜
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);   // 返回递归上一层，这次操作后，以node为根节点的树，即满足了平衡二叉树和又满足了二分搜索树的性质

        // 平衡维护，不平衡节点的右侧中左子树比右子树矮，并且这个高度差的绝对值比1要大，这个树整体向右倾斜
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // 平衡维护，不平衡节点的左侧中左子树比右子树矮，并且这个高度差的绝对值比1要大，这个树整体向左倾斜
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // 将LR的情况转换成LL的情况
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // 平衡维护，不平衡节点的右侧中左子树比右子树高，并且这个高度差的绝对值比1要大，这个树整体向右倾斜
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // 将RL的情况转换为RR的情况
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args) {

        System.out.println("pride-and-prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("AVLTree//pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words：" + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            // 判断删除后的整个AVLTree是否还符合平衡的性质和二分搜索树的性质
            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced())
                    throw new RuntimeException("Error");
            }
        }
        System.out.println();
    }
}
