public class SQRTDecomposition<E> {

    private E[] data, blocks;
    private int N;  //元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数
    private Merger<E> merger;

    public SQRTDecomposition(E[] arr, Merger<E> merger) {

        this.merger = merger;

        N = arr.length;
        if (N == 0) return;

        data = (E[]) new Object[N];
        for (int i = 0; i < N; i++)
            data[i] = arr[i];

        B = (int) Math.sqrt(N); // 下取整
        Bn = (N / B) + (((N % B) > 0) ? 1 : 0);

        blocks = (E[]) new Object[Bn];
        for (int i = 0; i < N; i++)
            if (i % B == 0)
                blocks[i / B] = data[i];
            else
                blocks[i / B] = merger.merge(blocks[i / B], data[i]);
    }

    // 区间查询
    public E queryRange(int x, int y) {

        if (x < 0 || x > N || y < 0 || y > N || x > y) return null;

        int b_start = x / B, b_end = y / B;

        // res 的初值是data[X]
        E res = data[x];
        if (b_start == b_end) {
            for (int i = x + 1; i <= y; i++)
                res = merger.merge(res, data[i]);
            return res;
        }

        for (int i = x + 1; i < (b_start + 1) * B; i++)
            res = merger.merge(res, data[i]);
        for (int b = b_start + 1; b < b_end; b++)
            res = merger.merge(res, blocks[b]);
        for (int i = b_end * B; i < y; i++)
            res = merger.merge(res, data[i]);

        return res;
    }

    public void update(int index, E val) {

        if (index < 0 || index >= N) return;

        int b = index / B;
        data[index] = val;

        blocks[b] = data[b * B];
        for (int i = b * B + 1; i < Math.min((b + 1) * B, N); i++)
            blocks[b] = merger.merge(blocks[b], data[i]);
    }
}
