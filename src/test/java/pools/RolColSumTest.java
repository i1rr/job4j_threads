package pools;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class RolColSumTest {
    @Test
    public void whenSyncIsTheSameAsASync() {
        int[][] arr = new int[50][50];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int o = 0; o < arr[i].length; o++) {
                arr[i][o] = r.nextInt(100);
            }
        }
        RolColSum rcs = new RolColSum();
        assertThat(rcs.sum(arr), is(rcs.asyncSum(arr)));
    }
}