package LC303;

import java.util.Arrays;

class NumArray {

    private int[] data, blocks;
    private int N;  // 元素总数
    private int B;  // 每组元素个数
    private int Bn; // 组数

    public NumArray(int[] nums) {

        N = nums.length;
        if (N == 0)  return;

        B = (int) Math.sqrt(N); // 下取整
        Bn = (N / B) + (((N % B) > 0) ? 1 : 0);

        data = Arrays.copyOf(nums, N);

        blocks = new int[Bn];
        for (int i = 0; i < N; i++)
            blocks[i / B] += nums[i];
    }

    public int sumRange(int left, int right) {

        if (left < 0 || left >= N || right < 0 || right >= N || left > right)
            return 0;

        int b_start = left / B, b_end = right / B;

        int res = 0;
        if (b_start == b_end) {
            for (int i = left; i <= right; i++)
                res += data[i];
            return res;
        }

        for (int i = left; i < (b_start + 1) * B; i++)
            res += data[i];
        for (int b = b_start + 1; b < b_end; b++)
            res += blocks[b];
        for (int i = b_end * B; i <= right; i++)
            res += data[i];

        return res;
    }
}

