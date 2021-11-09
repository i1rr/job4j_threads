package other;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void transferFrom1To2Amount100Is300() {
        User one = new User(1, 100);
        User two = new User(2, 200);
        UserStorage us = new UserStorage();
        us.add(one);
        us.add(two);
        assertThat(us.transfer(1, 2, 100), is(two.getBalance() == 300));
    }

    @Test
    public void whenNotEnoughBalance() {
        User one = new User(1, 100);
        User two = new User(2, 200);
        UserStorage us = new UserStorage();
        us.add(one);
        us.add(two);
        assertFalse(us.transfer(1, 2, 101));
    }

    @Test
    public void whenNoSuchUser() {
        User one = new User(1, 100);
        UserStorage us = new UserStorage();
        us.add(one);
        assertFalse(us.transfer(1, 2, 101));
    }
}