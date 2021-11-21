import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CASCountTest {
    @Test
    public void whenIncrement10000times() throws InterruptedException {
        CASCount cc = new CASCount(0);
        Thread one = new Thread(
                () -> {
                    for (int i = 0; i < 2500; i++) {
                        cc.increment();
                    }
                }
        );

        Thread two = new Thread(
                () -> {
                    for (int i = 0; i < 2500; i++) {
                        cc.increment();
                    }
                }
        );

        Thread three = new Thread(
                () -> {
                    for (int i = 0; i < 2500; i++) {
                        cc.increment();
                    }
                }
        );

        Thread four = new Thread(
                () -> {
                    for (int i = 0; i < 2500; i++) {
                        cc.increment();
                    }
                }
        );
        one.start();
        two.start();
        three.start();
        four.start();
        two.join();
        one.join();
        three.join();
        four.join();
        assertThat(cc.get(), is(10000));
    }
}