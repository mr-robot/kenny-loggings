package com.wonderfulrobot.kennyloggings;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;

import com.wonderfulrobot.kennyloggings.KLog;


public class LoggingHandlerTestCase extends FunctionalTestCase {
	
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
	//@Test
	public void testBasicConfiguration() throws Exception {
		KLog log = (KLog) muleContext.getRegistry().lookupObject(KLog.class);
		log.setMuleContext(muleContext);
		log.initialise();
		
		assertNotNull(log);
	}

	 protected String getConfigResources()
	    {
	        return "klog-namespace-config.xml";
	    }
	
    @Override
    protected String[] getConfigFiles() {
        return new String[]{"./src/test/app/flow-config-test.xml"};
    }
}
