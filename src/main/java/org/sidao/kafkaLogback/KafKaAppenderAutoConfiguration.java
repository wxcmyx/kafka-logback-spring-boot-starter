package org.sidao.kafkaLogback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.sidao.github.danielwegener.logback.kafka.KafkaAppender;
import org.sidao.github.danielwegener.logback.kafka.encoding.LayoutKafkaMessageEncoder;
import net.logstash.logback.fieldnames.ShortenedFieldNames;
import net.logstash.logback.layout.LogstashLayout;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Create by lixz
 */
@Configuration
@Import(KafkaAppenderProperties.class)
@ConditionalOnProperty(prefix = "kafka",name = "producer-config")
public class KafKaAppenderAutoConfiguration  {
    @Bean
    @ConditionalOnMissingBean
    public KafkaAppenderProperties kafkaAppenderProperties() {
        return new KafkaAppenderProperties();
}

    @Bean
    public Logger programmableAppender(KafkaAppenderProperties kafkaAppenderProperties ) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = null;
        Logger loggerd = null;
        if("root".equals(kafkaAppenderProperties.getModleType())){
             logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLoggerList().get(0);
        }else if("logger".equals(kafkaAppenderProperties.getModleType())){
              if(kafkaAppenderProperties.getLoggername()==null||"".equals(kafkaAppenderProperties.getLoggername())){
                  try {
                      throw  new Exception("缺少logname");
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
             logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(kafkaAppenderProperties.getLoggername());
              logger.setLevel(Level.INFO);
              logger.setAdditive(false);
        }else if("rootAndLogger".equals(kafkaAppenderProperties.getModleType())){
            if(kafkaAppenderProperties.getLoggername()==null||"".equals(kafkaAppenderProperties.getLoggername())){
                try {
                    throw  new Exception("缺少logname");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLoggerList().get(0);
            loggerd =((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(kafkaAppenderProperties.getLoggername());
            loggerd.setAdditive(false);
            loggerd.setLevel(Level.INFO);
        }else{
            logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLoggerList().get(0);
        }

        if(kafkaAppenderProperties.isEnable()){
            try {
                KafkaAppender<ILoggingEvent> kafkaAppender = new KafkaAppender();
                kafkaAppender.addProducerConfig(kafkaAppenderProperties.getProducerConfig());
                LayoutKafkaMessageEncoder kafkaMessageEncoder = new LayoutKafkaMessageEncoder();
                LogstashLayout layout = new LogstashLayout();
                layout.setIncludeContext(kafkaAppenderProperties.isIncludeContext());
                layout.setIncludeCallerData(kafkaAppenderProperties.isIncludeCallerData());
                layout.setFieldNames(new ShortenedFieldNames());
                layout.setCustomFields(kafkaAppenderProperties.getCustomFields());
                kafkaMessageEncoder.setLayout(layout);
                kafkaMessageEncoder.setContext(loggerContext);
                kafkaMessageEncoder.start();
                kafkaAppender.setTopic(kafkaAppenderProperties.getTopic());
                kafkaAppender.setEncoder(kafkaMessageEncoder);
                kafkaAppender.setContext(loggerContext);
                kafkaAppender.start();
                logger.addAppender(kafkaAppender);
                if (loggerd!=null){
                    loggerd.addAppender(kafkaAppender);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return logger;
    }


}
