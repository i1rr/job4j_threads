package pools;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RolColSumTest {
    @Test
    public void whenSyncIsTheSameAsASync() {
        int[][] arr = new int[100][100];
        Random r = new Random();
        for(int i = 0; i < arr.length; i++) {
            for (int o = 0; o < arr[i].length; o++) {
                arr[i][o] = r.nextInt(100);
            }
        }
        assertThat(RolColSum.sum(arr), is(RolColSum.asyncSum(arr)));
    }
}