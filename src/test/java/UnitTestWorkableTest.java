import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTestWorkableTest {
    @Test
     void testFalse(){
        assertFalse(false);
    }

    @Test
    void testTrue(){
        assertTrue(true);
    }
}
