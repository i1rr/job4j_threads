package cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddButIdExist() {
        Cache c = new Cache();
        c.add(new Base(1, 1));
        assertFalse(c.add(new Base(1, 2)));
    }

    @Test
    public void commonAdd() {
        Cache c = new Cache();
        assertTrue(c.add(new Base(1, 1)));
    }

    @Test
    public void whenUpdateSuccessful() {
        Cache c = new Cache();
        c.add(new Base(1, 1));
        Base upd = new Base(1, 1);
        upd.setName("abc");
        assertTrue(c.update(upd));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateUnsuccessful() {
        Cache c = new Cache();
        c.add(new Base(1, 2));
        Base upd = new Base(1, 1);
        c.update(upd);
    }
}