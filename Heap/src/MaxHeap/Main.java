package MaxHeap;

import java.util.Random;

public class Main {

    private static double testHeap(Integer[] testData, boolean isHeapify) {

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)  // 用heapify方式建立一个符合最大堆性质的堆
            maxHeap = new MaxHeap<>(testData);
        else {          // 不用heapify方式，通过构建一个空堆，然后数组中元素一个一个放入
            maxHeap = new MaxHeap<Integer>();
            for (int num : testData)
                maxHeap.add(num);
        }

        // 验证堆的合法性
        int n = testData.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();
        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int n = 10000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData,true);
        System.out.println("With heapify: " + time2 + " s");
    }
}
