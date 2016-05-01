package ghcjvm.tests.runtime;

import org.junit.*;
import static org.junit.Assert.*;

import lljvm.runtime.Memory;


public class MemoryTests
{
    /**
     * emulated type sizes in bytes
     */
    private static final int LONG_SIZE = 8,
                             INT_SIZE = 4,
                             FLOAT_SIZE = 4,
                             DOUBLE_SIZE=8; 
    private Memory mem;  

    /**
    * whether to store on the stack or heap
    */
    enum Loc
    {
        STACK, HEAP
    }

    @Before
    public void setUp()
    {
        mem = Memory.getMemorySingleton();
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testStoreMaxLong()
    {
        assertStoreValue(Long.MAX_VALUE, Loc.STACK);
    }

    private void assertStoreValue(long value, Loc which)
    {
        final int arbitrary = 999;
        switch(which)
        {
        case STACK:
            //store 8-byte long
            int value_ptr = mem.storeStack(value);
            //store a 4-byte integer
            int next_ptr = mem.storeStack(arbitrary);
            //store another long
            int following_ptr = mem.storeStack(value / 2);

            //make sure the pointers are in the right order
            assertTrue(value_ptr > next_ptr);
            assertTrue(next_ptr > following_ptr);
            
            assertEquals(value, mem.load_i64(value_ptr));
            assertEquals(arbitrary, mem.load_i32(next_ptr));
            assertEquals(value / 2, mem.load_i64(following_ptr));

            break;
        case HEAP:

            break;
        default:
            fail("invalid enum type for Loc which");
        }
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
