import java.util.ArrayList;

public class Main3 {

    public static void main(String[] args) {

        int n = 1_000_0000;

        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++)
            testData.add(i);

        // Test AVL
        long start = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x : testData)
            avl.add(x, null);
        long end = System.nanoTime();
        double time = (end - start) / 1000000000.0;
        System.out.println("AVLTree: " + time + " s");

        // Test RBTree
        start = System.nanoTime();
        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x : testData)
            rbt.add(x, null);
        end = System.nanoTime();
        time = (end - start) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }
}
