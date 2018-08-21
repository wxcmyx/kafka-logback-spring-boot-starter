**kafka-logback-spring-boot-starter**

**一、项目说明**

为简化logback日志输出到kafka和SpringBoot整合，本项目为SpringBoot和logback/kafka整合starter,引用kafka-logback-spring-boot-starter并配置相关参数即可使用

**二、配置说明**

强烈建议使用yml作为配置文件，以下也使用yml作为示例。目前该项目提供可配置的部分配置项如下：


kafka:<br>
&nbsp;&nbsp;&nbsp;&nbsp;是否使用 默认true<br>
&nbsp;&nbsp;&nbsp;&nbsp;enable: true<br>
&nbsp;&nbsp;&nbsp;&nbsp;设置kafka<br>
&nbsp;&nbsp;&nbsp;&nbsp;producerConfig: bootstrap.servers=。。。。。<br>
&nbsp;&nbsp;&nbsp;&nbsp;kafka的topic 必须填写<br>
&nbsp;&nbsp;&nbsp;&nbsp;topic: topic<br>
&nbsp;&nbsp;&nbsp;&nbsp;默认true<br>
&nbsp;&nbsp;&nbsp;&nbsp;includeContext: true<br>
&nbsp;&nbsp;&nbsp;&nbsp;默认true<br>
&nbsp;&nbsp;&nbsp;&nbsp;includeCallerData: true<br>
&nbsp;&nbsp;&nbsp;&nbsp;自定义字段<br>
&nbsp;&nbsp;&nbsp;&nbsp;customFields："{\"system\":\"omocms\"}"<br>
   
   
   
**三、启用注解**

 @EnableKafkaAppenders
   
   