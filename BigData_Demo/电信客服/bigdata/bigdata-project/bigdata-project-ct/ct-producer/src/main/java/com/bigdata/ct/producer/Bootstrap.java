package com.bigdata.ct.producer;

import com.bigdata.ct.common.bean.Producer;
import com.bigdata.ct.producer.bean.LocalFileProducer;
import com.bigdata.ct.producer.io.LocalFileDataIn;
import com.bigdata.ct.producer.io.LocalFileDataOut;

/**
 * 启动对象
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {

        if ( args.length < 2 ){
            System.out.println("系统参数不正确,请按照指定格式传递：java -jar Produce.jar path1 path2");
            System.exit(1);
        }
        //构建生产者对象
        Producer producer = new LocalFileProducer();

        //contact.log-联系人、call.log-通话日志
/*        producer.setIn(new LocalFileDataIn("D:\\0-学习用\\C-上手实操\\大数据项目\\电信客服\\辅助文档\\contact.log"));
        producer.setOut(new LocalFileDataOut("D:\\0-学习用\\C-上手实操\\大数据项目\\电信客服\\辅助文档\\call.log"));
*/

        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));
        //生产对象
        producer.produce();

        //关闭生产者对象
        producer.close();

    }
}
