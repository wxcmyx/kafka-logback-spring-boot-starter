package org.sidao.kafkaLogback;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Create by lixz
 * @Description:
 */
@ConfigurationProperties(prefix = "kafka")
public class KafkaAppenderProperties {
    private  boolean enable = true;
    private  boolean includeContext = true;
    private  boolean includeCallerData = true;
    private  String topic = "test";
    private  String producerConfig;
    private  String customFields;
    /**
     * 1.全局输出到kafka ：root
     * 2.指定某个logger 输出到kafka：logger
     * 3.指定logger输出kafka,并不向父级root传递,其他全局输出到kafka: rootAndLogger
     */
    private  String modleType = "root";
    /**
     * logger的name
     * 指定modelType 为logger 和 rootAndLogger时此项为必填
     */
    private  String loggername;

    public boolean isIncludeContext() {
        return includeContext;
    }

    public void setIncludeContext(boolean includeContext) {
        this.includeContext = includeContext;
    }

    public boolean isIncludeCallerData() {
        return includeCallerData;
    }

    public void setIncludeCallerData(boolean includeCallerData) {
        this.includeCallerData = includeCallerData;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProducerConfig() {
        return producerConfig;
    }

    public void setProducerConfig(String producerConfig) {
        this.producerConfig = producerConfig;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getModleType() {
        return modleType;
    }

    public void setModleType(String modleType) {
        this.modleType = modleType;
    }

    public String getLoggername() {
        return loggername;
    }

    public void setLoggername(String loggername) {
        this.loggername = loggername;
    }
}
