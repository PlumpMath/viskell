package nl.utwente.viskell.haskell.type;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunTypeTest {
    @Test
    public final void toHaskellTypeTest() {
        final Type integer = Type.con("Integer");
        final Type function = Type.fun(integer, integer);
        assertEquals("Integer -> Integer", function.prettyPrint());
    }
}
