import java.util.ArrayList;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) {

        int n = 1000_0000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++)
            testData.add(random.nextInt(Integer.MAX_VALUE));

        // Test BST
        long start = System.nanoTime();
        BST<Integer, Integer> bst = new BST<>();
        for (Integer x : testData)
            bst.add(x, null);
        long end = System.nanoTime();
        double time = (end - start) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        // Test AVL
        start = System.nanoTime();
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x : testData)
            avl.add(x, null);
        end = System.nanoTime();
        time = (end - start) / 1000000000.0;
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
