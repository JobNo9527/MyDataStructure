import java.util.Random;

/**
 * @author 将晖
 */


public class Main {

    //多态体现，用接口创建对象，这样的两个实现类都能传入参数
    private static double testQueue(Queue<Integer> q, int opCount) {
        //测试使用q运行opCount个enqueue和dequeue操作所需要的时间，单位：秒

        long start = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }
}
/*
ArrayQueue, time: 48.16472899 s         O(n^2)
LoopQueue, time: 0.019317181 s          O(n)
LinkedListQueue, time: 0.018602829 s    O(n)
 */
