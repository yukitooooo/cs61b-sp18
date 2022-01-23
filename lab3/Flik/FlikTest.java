import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {

    @Test
    public void testIsSameNumber() {
        int a = 500;
        int b = 128;
        int c = 500;
        assertFalse("500 != 128", Flik.isSameNumber(a, b));
        assertTrue("1 = 1", Flik.isSameNumber(a, c));
    }
}
