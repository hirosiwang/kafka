package com.tech.consumer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyConsume {
    private static Logger   LOG = LoggerFactory.getLogger(MyConsume.class);

    public MyConsume() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "linux-1:9092,linux-1:9093,linux-1:9094");
        //设置不自动提交，自己手动更新offset
        properties.put("enable.auto.commit", "false");
        properties.put("auto.offset.reset", "earliest");//latest
        properties.put("zookeeper.connect", "linux-1:2181");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "testGroup2");
        properties.put("auto.commit.interval.ms", "1000");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //执行消费
        for (int i = 0; i < 1; i++) {
            executor.execute(new ConsumerThreadNew(new KafkaConsumer<String, String>(properties),
                    "my-replicated-topic", "消费者" + (i + 1)));
        }
    }
}