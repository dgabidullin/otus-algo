public class Power {

    public double powIter(double a, long N) {
        double result = 1;
        for (int i = 1; i <= N; i++) {
            result *= a;
        }
        return result;
    }

    public Double powMultiplication(double a, long N) {
        if (N == 0) return 1.0;
        double a1 = a;
        int j = 1;
        for (; j <= N / 2; j *= 2) {
            a *= a;
        }
        for (; j < N; j++) {
            a *= a1;
        }
        return a;
    }

    public double powLogN(double a, long N) {
        if (N == 0) return 1.0;
        double p = 1;
        for (; N > 1; N /= 2) {
            if (N % 2 == 1) {
                p *= a;
            }
            a *= a;
        }
        p *= a;
        return p;
    }
}
