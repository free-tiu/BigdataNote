package com.bigdata.ct.consumer.bean;

import com.bigdata.ct.common.bean.Consumer;
import com.bigdata.ct.common.constant.Names;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * 通话日志消费者对象
 */
public class CalllogConsumer implements Consumer {

    //消费数据
    public void consume() {

        //创建配置对象
        Properties prop = new Properties();

        //获取flume采集的数据
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(prop);

        //关注主题
        consumer.subscribe(Arrays.asList(Names.TOPIC.getvalue()));

        //消费数据
        while ( true ){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            for (ConsumerRecord<String,String> consumerRecord : consumerRecords> {
                System.out.println(ConsumerRecord.value());

            }
        }

    }

    //古纳比资源
    public void close() throws IOException {

    }
}
