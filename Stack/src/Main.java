import java.util.Random;

/**
 * @author 将晖
 */


public class Main {

    private static double testStack(Stack<Integer> stack, int opCount) {

        long start = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 10000000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");
    }
}
