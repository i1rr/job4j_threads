package pools;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class RolColSumTest {
    @Test
    public void whenSyncIsTheSameAsASync() {
        int[][] arr = new int[1000][1000];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int o = 0; o < arr[i].length; o++) {
                arr[i][o] = r.nextInt(10);
            }
        }
        RolColSum rcs = new RolColSum();
        assertThat(rcs.sum(arr), is(rcs.asyncSum(arr)));
    }

    /**
     * 1 1 1
     * 2 2 2
     * 3 3 3
     */
    @Test
    public void whenSync() {
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int o = 0; o < 3; o++) {
                arr[i][o] = i + 1;
            }
        }
        RolColSum rcs = new RolColSum();

        RolColSum.Sums[] sumsArr = new RolColSum.Sums[3];
        sumsArr[0] = new RolColSum.Sums(3, 6);
        sumsArr[1] = new RolColSum.Sums(6, 6);
        sumsArr[2] = new RolColSum.Sums(9, 6);

        assertThat(rcs.sum(arr), is (sumsArr));
    }

    /**
     * 1 2 3
     * 1 2 3
     * 1 2 3
     */
    @Test
    public void whenASync() {
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int o = 0; o < 3; o++) {
                arr[o][i] = i + 1;
            }
        }
        RolColSum rcs = new RolColSum();

        RolColSum.Sums[] sumsArr = new RolColSum.Sums[3];
        sumsArr[0] = new RolColSum.Sums(6, 3);
        sumsArr[1] = new RolColSum.Sums(6, 6);
        sumsArr[2] = new RolColSum.Sums(6, 9);

        assertThat(rcs.asyncSum(arr), is (sumsArr));
    }
}