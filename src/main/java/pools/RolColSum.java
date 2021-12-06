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

    private Sums sumRowCell(int[][] matrix, int index) {
        Sums rsl = new Sums(0, 0);
        int rowSum = 0;
        int colSum = 0;
        for (int cell = 0; cell < matrix[index].length; cell++) {
            rowSum += matrix[index][cell];
            colSum += matrix[cell][index];
        }
        rsl.rowSum = rowSum;
        rsl.colSum = colSum;
        return rsl;
    }

    public Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = sumRowCell(matrix, i);
        }
        return result;
    }

    public Sums[] asyncSum(int[][] matrix) {
        CompletableFuture<Sums[]> rsl = CompletableFuture.supplyAsync(
                () -> {
                    Sums[] temp = new Sums[matrix.length];
                    for (int i = 0; i < matrix.length; i++) {
                        temp[i] = sumRowCell(matrix, i);
                    }
                    return temp;
                }
        );
        return rsl.join();
    }
}