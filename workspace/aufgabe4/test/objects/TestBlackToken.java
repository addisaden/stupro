package objects;

import static objects.TestExamples.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBlackToken {
	private Token object;
	
	@Before
	public void setUp() {
		object = BlackToken.instance();
	}
	
	@Test
	public void isBlack() {
		assert(object.isBlack());
	}
	
    @Test
    public void unchanged() {
    	assertSame(object, object.nextGeneration(ts2));
    	assertSame(object, object.nextGeneration(ts3));
    	
    }
    
    @Test
    public void changed() {
    	assertEquals(W, object.nextGeneration(ts1));
    	assertEquals(W, object.nextGeneration(ts4));
    }
}
