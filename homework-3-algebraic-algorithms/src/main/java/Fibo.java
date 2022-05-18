public class Fibo {

    public long fibo1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibo1(n - 1) + fibo1(n - 2);
    }

    public long fibo2(int n) {
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
