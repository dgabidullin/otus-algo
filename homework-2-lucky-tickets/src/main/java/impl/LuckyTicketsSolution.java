package impl;

public class LuckyTicketsSolution {

    public long countLuckyTicketsWithDigit(int n) {
        isValidDigitNumber(n);

        long[] totals = new long[n * 9 + 1];
        long[] digits = new long[n * 9 + 1];
        long cntLuckyTickets = 0;
        int maxSum, maxTotal;
        int i, z;

        for (i = 0; i <= 9; i++) {
            totals[i] = 1;
        }

        for (int j = 2; j <= n; j++) {
            maxSum = j * 9;
            maxTotal = maxSum - 9;
            for (z = 0; z <= maxTotal; z++) {
                digits[z] = totals[z];
            }

            for (z = 0; z < maxSum; z++) {
                totals[z] = 0;
            }

            for (i = 0; i <= 9; i++) {
                for (z = 0; z <= maxTotal; z++) {
                    totals[z + i] += digits[z];
                }
            }
        }

        for (z = 0; z <= n * 9; z++) {
            cntLuckyTickets += totals[z] * totals[z];
        }
        return cntLuckyTickets;
    }

    private void isValidDigitNumber(int n) {
        if (n > 0 && n < 11) {
            return;
        }
        throw new IllegalArgumentException(String.format("Bad argument n=%s, valid invariant: n > 0 && n < 11", n));
    }
}
