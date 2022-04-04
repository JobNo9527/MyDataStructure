public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        // lambda 真好用
        //SegmentTree<Integer> segTree = new SegmentTree<>(nums, Math::max);
        //SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> Math.max(a, b));
        //System.out.println(segTree);

        // lambda 真好用
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, Integer::sum);
        //SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(0, 5));
    }
}
