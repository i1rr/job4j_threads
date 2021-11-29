package pools;

import emailservice.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindingIndexTest {

    @Test
    public void whenUserIsFound() {

        User[] arr = new User[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new User("Vasja" + i, "vasja" + i + "@gmail.com");
        }
        User vasja2500 = new User("Vasja2500", "vasja2500@gmail.com");

        assertThat(FindingIndex.find(arr, vasja2500), is(2500));
    }
}