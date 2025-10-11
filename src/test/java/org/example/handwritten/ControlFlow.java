package org.example.handwritten;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JQF.class)
public class ControlFlow {
    @Fuzz
    public void bar(int y, int z) {
        if (y > 100) {
            this.foo(y);
        }
        if (z > 3) {
            assertTrue(false);
        }
    }

    public void foo(int x) {
        if (x > 1) {
            x = 2 * x;
            if (x > 10) {
                assertTrue(false);
            }
        }
    }
}
