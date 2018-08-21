package org.sidao.kafkaLogback;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Create by lixz
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({KafKaAppenderAutoConfiguration.class})
public @interface EnableKafkaAppenders {
}
