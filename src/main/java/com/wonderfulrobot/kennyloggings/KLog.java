package com.wonderfulrobot.kennyloggings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mule.AbstractAnnotatedObject;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.context.MuleContextAware;
import org.mule.api.expression.ExpressionManager;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.util.StringUtils;

/**
 * 
 */

/**
 * @author mr_robot
 *
 */
public class KLog  extends AbstractAnnotatedObject implements MessageProcessor, Initialisable, MuleContextAware{


    protected transient Logger logger;

    protected String message;
    protected String category;
    protected String level = "DEBUG";

    protected MuleContext muleContext;
    protected ExpressionManager expressionManager;

    public void initialise() throws InitialisationException
    {
        initLogger();
        expressionManager = muleContext.getExpressionManager();
    }

    protected void initLogger()
    {
        if (category != null)
        {
            logger = LogManager.getLogger(category);
        }
        else
        {
            logger = LogManager.getLogger(KLog.class.getName());
        }
    }

    public MuleEvent process(MuleEvent event) throws MuleException
    {
        log(event);
        return event;
    }

    protected void log(MuleEvent event)
    {
        if (event == null)
        {
            logWithLevel(null);
        }
        else
        {
            if (StringUtils.isEmpty(message))
            {
                logWithLevel(event.getMessage());
            }
            else
            {
                LogLevel logLevel = LogLevel.valueOf(level);
                if (LogLevel.valueOf(level).isEnabled(logger)) 
                {
                    logLevel.log(logger, expressionManager.parse(message, event));
                }
            }
        }
    }

    protected void logWithLevel(Object object)
    {
        LogLevel logLevel = LogLevel.valueOf(level);
        if (logLevel.isEnabled(logger)) 
        {
            logLevel.log(logger, object);
        }
    }

    public void setMuleContext(MuleContext muleContext)
    {
        this.muleContext = muleContext;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setLevel(String level)
    {
        this.level = level.toUpperCase();
    }

    public enum LogLevel
    {
        ERROR 
        {
            @Override
            public void log(Logger logger, Object object) 
            {
                logger.error(object);
            }

            @Override
            public boolean isEnabled(Logger logger) 
            {
                return logger.isErrorEnabled();
            }
        },
        WARN 
        {
            @Override
            public void log(Logger logger, Object object) 
            {
                logger.warn(object);
            }
            
            @Override
            public boolean isEnabled(Logger logger) 
            {
                return logger.isWarnEnabled();
            }
        },
        INFO
        {
            @Override
            public void log(Logger logger, Object object) 
            {
                logger.info(object);
            }
            
            @Override
            public boolean isEnabled(Logger logger) 
            {
                return logger.isInfoEnabled();
            }
        },
        DEBUG
        {
            @Override
            public void log(Logger logger, Object object) 
            {
                logger.debug(object);
            }
            
            @Override
            public boolean isEnabled(Logger logger) 
            {
                return logger.isDebugEnabled();
            }
        },
        TRACE
        {
            @Override
            public void log(Logger logger, Object object) 
            {
                logger.trace(object);
            }
            
            @Override
            public boolean isEnabled(Logger logger) 
            {
                return logger.isTraceEnabled();
            }
        };
        
        public abstract void log(Logger logger, Object object);
        public abstract boolean isEnabled(Logger logger);
    }

}
