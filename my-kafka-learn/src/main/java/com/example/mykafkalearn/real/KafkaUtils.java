package com.example.mykafkalearn.real;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/27 1:54 下午
 * @Description:
 */
@Component
public class KafkaUtils {

    private static final Logger logger = LoggerFactory.getLogger(KafkaUtils.class);

    @Resource(name = "kafkaTemplate1")
    private KafkaProducer<String, String> kafkaTemplate1;

    public boolean sendMessage(String topicName, String value) {
        try {
            logger.info("发送topic消息体：{}", value);
            Future<RecordMetadata> res = kafkaTemplate1.send(new ProducerRecord<>(topicName, value));
            System.out.println(res);
            return true;
        }catch (Exception e){
            logger.error("发送topic消息体失败：{}", value);
            return false;
        }
    }

    public boolean sendMessage(String topicName, Map<Object, Object> value) {
        logger.info("发送topic消息体：{}", JSONObject.toJSONString(value));
        String array = JSONObject.toJSONString(value);
        Future<RecordMetadata> res = kafkaTemplate1.send(new ProducerRecord<>(topicName, array));
        System.out.println(res);
        return true;
    }


}