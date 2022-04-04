public class Main {

    public static void main(String[] args) {

        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SQRTDecomposition<Integer> sumSQRT = new SQRTDecomposition<>(arr, Math::max);
        Integer res = sumSQRT.queryRange(0, 5);
        System.out.println(res);
    }
}
