package impl;

public class Fibo {

    public long fiboWithRecursion(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fiboWithRecursion(n - 1) + fiboWithRecursion(n - 2);
    }

    public long fiboIteration(int n) {
        long f0 = 0;
        long f1 = 1;
        for (int i = 1; i <= n; i++) {
            long f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f0;
    }
}
