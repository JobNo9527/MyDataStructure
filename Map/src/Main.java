import java.util.ArrayList;

public class Main {

    // 测试基于链表的映射（字典）
    private static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "Map//pride-and-prejudice.txt";

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time = testMap(bstMap, filename);
        System.out.println("BSTMap: " + time + " s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        time = testMap(linkedListMap, filename);
        System.out.println("LinkedListMap: " + time + " s");

        System.out.println();

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        time = testMap(avlMap, filename);
        System.out.println("AVLMap:" + time + " s");
    }
}
