import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("RedBlackTree//pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Test RBTree
            long start = System.nanoTime();
            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for (String word : words)
                rbt.contains(word);

            long end = System.nanoTime();
            double time = (end - start) / 1000000000.0;
            System.out.println("RBTree :" + time + " s");
        }
    }
}