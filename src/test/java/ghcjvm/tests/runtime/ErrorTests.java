package ghcjvm.runtime.tests;

import org.junit.*;
import static org.junit.Assert.*;

import lljvm.runtime.Error;
import lljvm.runtime.Memory;

public class ErrorTests
{
    @Before
    public void setUp()
    {

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
}
