package com.example.mykafkalearn.real;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Author: cmy
 * @Date: Created in  2020/10/26 2:25 下午
 * @Description: 消费者
 */
@Component
public class KafkaConsumer2 {

    @KafkaListener(topics = {"${spring.kafka.consumer.topic}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            Object message = record.value();
            System.out.println(String.format("%s 我是分区: %s, topic: %s, key : %s, message : %s ", System.currentTimeMillis(), record.partition(), record.topic(), record.key(), message));
            Thread.sleep(5000);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("demo3 cmy_topic 消费失败。。。。");
        }
    }

//
//    @KafkaListener(containerGroup = "test", topicPartitions = {
//            @TopicPartition(topic = "my_topic_partitions", partitions = {"0"}),
//
//    })
//    public void onMessage0(ConsumerRecord record, Acknowledgment acknowledgment) {
//        System.out.println("我是分区 0：" + record.topic() + "-" + record.partition() + "-" + record.value());
//        acknowledgment.acknowledge();
//    }
//
//    @KafkaListener(containerGroup = "test", topicPartitions = {
//            @TopicPartition(topic = "my_topic_partitions", partitions = {"1", "2"})
//    })
//    public void onMessage1(ConsumerRecord record, Acknowledgment acknowledgment) {
//        System.out.println("我是分区 1：" + record.topic() + "-" + record.partition() + "-" + record.value());
//        acknowledgment.acknowledge();
//
//    }
//


}