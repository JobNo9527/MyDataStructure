import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "Set//pride-and-prejudice.txt";

        BSTSet<String> bstSet = new BSTSet<>();
        double time = testSet(bstSet, filename);
        System.out.println("BST Set: " + time + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        time = testSet(linkedListSet, filename);
        System.out.println("LinkedList Set: " + time + " s");

        System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        time = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time + " s");
    }
}
