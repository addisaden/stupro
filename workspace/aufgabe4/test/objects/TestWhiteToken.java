package objects;

import static objects.TestExamples.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestWhiteToken {
    private Token object;
    
	@Before
	public void setUp() {
		object = WhiteToken.instance();
	}

	@Test
	public void isWhite() {
		assertFalse(object.isBlack());
	}
	
	@Test
	public void unchanged() {
        assertSame(object, object.nextGeneration(ts1));
		assertSame(object, object.nextGeneration(ts2));
		assertSame(object, object.nextGeneration(ts4));
	}

	@Test
	public void changed() {
		assertEquals(B, object.nextGeneration(ts3));
	}
}
