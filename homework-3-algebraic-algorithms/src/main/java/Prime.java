public class Prime {

    public int Eratosthenes(int n) {
        int count = 0;
        boolean[] primes = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            primes[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p)
                    primes[i] = false;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                count++;
            }
        }
        return count;
    }

    public int countPrimesN2(long n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrimeN2(i)) {
                count++;
            }
        }
        return count;
    }

    public long countPrimes(int n) {
        if (n == 1) return 0;
        int count = 0;
        long[] primes = new long[n / 2 + 1];
        primes[count++] = 2;
        for (int i = 3; i <= n; i++) {
            if (isPrime(i, primes)) {
                primes[count++] = i;
            }
        }
        return count;
    }

    private boolean isPrime(long n, long[] primes) {
        long sqrtN = (long) Math.sqrt(n);
        for (int i = 0; primes[i] <= sqrtN; i++) {
            if (n % primes[i] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrimeN2(long n) {
        int d = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                d++;
            }
        }
        return d == 2;
    }
}
