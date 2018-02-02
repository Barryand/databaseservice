import com.barry.test.Testtest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Barry on 2018/1/29.
 */
public class Testtesttest {
    @Test
    public void Ceshi() {
        Testtest testtest = new Testtest();
        String result = testtest.test();
        assertEquals("qqq", result);
    }
}
