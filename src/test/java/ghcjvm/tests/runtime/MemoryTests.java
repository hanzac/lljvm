package ghcjvm.tests.runtime;

import org.junit.*;
import static org.junit.Assert.*;

import lljvm.runtime.Memory;

public class MemoryTests
{
    private Memory mem;  

    @Before
    public void setUp()
    {
        mem = Memory.getMemorySingleton();
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
        final Memory m = Memory.getMemorySingleton();
        assertNotNull(m);
        
        assertTrue(m instanceof Memory);
    }
}
