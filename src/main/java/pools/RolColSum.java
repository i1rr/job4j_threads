package pools;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            int rowSum = 0;
            int colSum = 0;
            for (int cell = 0; cell < matrix[row].length; cell++) {
                rowSum += matrix[row][cell];
                colSum += matrix[cell][row];
            }
            result[row] = new Sums(rowSum, colSum);
        }
        return result;
    }

    public Sums[] asyncSum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = new Sums(0, 0);
        }

                CompletableFuture<Void> first = CompletableFuture.runAsync(
                        () -> {
                            for (int row = 0; row < matrix.length; row++) {
                                for (int cell = 0; cell < matrix[row].length; cell++) {
                                    rsl[row].colSum += matrix[cell][row];
                                }
                            }
                        });

                CompletableFuture<Void> second = CompletableFuture.runAsync(
                        () -> {
                            for (int row = 0; row < matrix.length; row++) {
                                for (int cell = 0; cell < matrix[row].length; cell++) {
                                   rsl[row].rowSum += matrix[row][cell];
                                }
                            }
                        });
                CompletableFuture.allOf(first, second).join();
        return rsl;
    }
}