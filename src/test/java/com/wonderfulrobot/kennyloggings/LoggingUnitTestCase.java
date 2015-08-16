package com.wonderfulrobot.kennyloggings;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.MuleTestUtils;
import org.mule.tck.junit4.FunctionalTestCase;

import com.wonderfulrobot.kennyloggings.KLog;


public class LoggingUnitTestCase extends FunctionalTestCase{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 * Tests that the logging module exists
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExistence() throws Exception {
		KLog log = new KLog();
		
		assertNotNull(log);
		
	}
	
	/**
	 * 
	 * Tests that all messages are passed through during processing
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMessagePassthrough() throws Exception {
		KLog log = new KLog();
		log.setMuleContext(muleContext);
		log.initialise();
		
		assertNotNull(log);
		
		MuleEvent testEvent = MuleTestUtils.getTestEvent("Test Data", muleContext);
		
		assertEquals(testEvent, log.process(testEvent));
	}
	
    @Override
    protected String[] getConfigFiles() {
        return new String[]{"./src/test/app/flow-test.xml"};
    }

}
