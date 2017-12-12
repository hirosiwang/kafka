package com.tech.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConsumerThreadNew implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(ConsumerThreadNew.class);

    //KafkaConsumer kafka生产者
    private KafkaConsumer<String, String> consumer;

    //消费者名字
    private String name;

    //消费的topic组
    private List<String> topics;

    //构造函数
    public ConsumerThreadNew(KafkaConsumer<String, String> consumer, String topic, String name) {
        super();
        this.consumer = consumer;
        this.name = name;
        this.topics = Arrays.asList(topic);
    }

    @Override
    public void run() {
        consumer.subscribe(topics);
//        consumer.seek(consumer.partitionsFor(topics.get(0)).get(0), 0);
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
        consumer.commitSync();
        // 批量提交数量
        final int minBatchSize = 1;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("消费者的名字为:" + name + ",消费的消息为：" + record.value() + "， offset: " + record.offset());
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                //这里就是处理成功了然后自己手动提交
                consumer.commitSync();
                System.out.println("提交完毕");
                buffer.clear();
            }
        }
    }

}