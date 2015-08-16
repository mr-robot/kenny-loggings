/**
 * 
 */
package com.wonderfulrobot.kennyloggings.config;

import org.mule.config.spring.parsers.generic.ChildDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.wonderfulrobot.kennyloggings.KLog;

/**
 * @author beast
 *
 */
public class KLogNamespaceHandler extends NamespaceHandlerSupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	@Override
	public void init() {
		registerBeanDefinitionParser("logger", new ChildDefinitionParser("messageProcessor",KLog.class));
	}

}
