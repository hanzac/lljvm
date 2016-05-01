package ghcjvm.runtime.tests;

import org.junit.*;
import static org.junit.Assert.*;

import lljvm.runtime.Error;
import lljvm.runtime.Memory;

public class ErrorTests
{
    private Error err;

    @Before
    public void setUp()
    {
        err = Error.getErrorSingleton(Memory.getMemorySingleton());
    }

    @After
    public void tearDown()
    {

    }


    /**
     * sanity check that the singleton method works
     */
    @Test
    public void testGetSingleton()
    {
        Error err = Error.getErrorSingleton(Memory.getMemorySingleton());
        assertNotNull(err);
        
        assertTrue(err instanceof Error);
    }

    @Test
    public void testSetErrno()
    {
        final int prevErrno = err.errno();
        err.errno(Integer.MAX_VALUE);

        //set errno to max value and check
        final int maxVal = err.errno();
        assertEquals(Integer.MAX_VALUE, maxVal);

        //set errno to min value and check
        err.errno(Integer.MIN_VALUE);
        final int minVal = err.errno();
        assertEquals(Integer.MIN_VALUE, minVal);

        //reset and check
        err.errno(prevErrno);
        assertEquals(err.errno(), prevErrno);
    }
}
