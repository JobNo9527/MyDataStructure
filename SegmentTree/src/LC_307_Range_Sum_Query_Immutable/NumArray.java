package LC_307_Range_Sum_Query_Immutable;

/*
    相较于 303 多一个update的方法
 */

public class NumArray {

    public class SegmentTree<E> {

        private E[] tree;
        private E[] data;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {

            this.merger = merger;

            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++)
                data[i] = arr[i];

            tree = (E[]) new Object[4 * arr.length];
            buildSegmentTree(0, 0, data.length - 1);
        }

        // 在treeIndex的位置创建表示区间（l...r) 的线段树
        private void buildSegmentTree(int treeIndex, int l, int r) {

            // 终止条件
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }

            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2;  // 如果不这么做，数据很大的时候会造成整型溢出
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

            // merge 两个当前线段的信息来获得更大的，对应根的信息
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        public int getSize() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index >= data.length)
                throw new IllegalArgumentException("Index is illegal");
            return data[index];
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的左孩子节点的索引
        private int leftChild(int index) {
            return 2 * index + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的右孩子节点的索引
        private int rightChild(int index) {
            return 2 * index + 2;
        }

        // 返回区间 [queryL,queryR] 的值
        public E query(int queryL, int queryR) {

            if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
                throw new IllegalArgumentException("Index is illegal");

            return query(0, 0, data.length - 1, queryL, queryR);
        }

        // 在以treeID为根的线段树值[l..r] 的范围里，搜索区间[queryL ... queryR]的值
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {

            if (l == queryL && r == queryR)
                return tree[treeIndex];

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            if (queryL >= mid + 1)
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            else if (queryR <= mid)
                return query(leftTreeIndex, l, mid, queryL, queryR);

            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }

        // 将index位置的值，更新为e
        public void set(int index, E e) {
            if (index < 0 || index >= data.length)
                throw new IllegalArgumentException("Index is illegal");

            data[index] = e;
            set(0, 0, data.length - 1, index, e);
        }

        // 在以treeIndex为根的线段树中更新index的值为e
        private void set(int treeIndex, int l, int r, int index, E e) {

            //终止条件
            if (l == r) {
                tree[treeIndex] = e;
                return;
            }

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if (index >= mid + 1)
                set(rightTreeIndex, mid + 1, r, index, e);
            else
                set(leftTreeIndex, l, mid, index, e);

            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null)
                    res.append(tree[i]);
                else
                    res.append("null");

                if (i != tree.length - 1)
                    res.append(",");
            }
            res.append(']');

            return res.toString();
        }
    }

    public interface Merger<E> {
        E merge(E a, E b);
    }

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<>(data, Integer::sum);
        }
    }

    public void update(int index, int val) {

        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        segmentTree.set(index, val);
    }

    public int sumRange(int left, int right) {
        if (segmentTree == null)
            throw new IllegalArgumentException("Segment Tree is null");

        return segmentTree.query(left, right);
    }
}
