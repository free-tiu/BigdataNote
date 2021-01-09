package com.bigdata.ct.producer.bean;

import com.bigdata.ct.common.bean.DataIn;
import com.bigdata.ct.common.bean.DataOut;
import com.bigdata.ct.common.bean.Producer;
import com.bigdata.ct.common.util.DateUtil;
import com.bigdata.ct.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 本地数据文件生产者
 */
public class LocalFileProducer implements Producer {

    //将in和out保存起来
    private DataIn in;
    private DataOut out;
    //volatile - 增强内存可见性
    private volatile Boolean flg = true;

    public void setIn(DataIn in) {
        this.in = in;
    }

    public void setOut(DataOut out) {
        this.out = out;
    }

    //生产数据
    public void produce() {

        try {
            //读取通讯录数据(靠in来读取) - 只读取一次
            List<Contact> contacts = in.read(Contact.class);
//            for (Contact contact : contacts) {
//                System.out.println(contact);
//            }
            //循环（每秒两条数据）
            while (flg){
                //从通讯录中随机查找2个电话号码（主叫和被叫 - 号码不能相同） - 多次读取
                //随机取两个数，这两个数当成索引，索引不同号码就不同
                int call1Index = new Random().nextInt(contacts.size());
                int call2Index = 1 ;
                while ( true ) {
                    call2Index = new Random().nextInt(contacts.size());
                    //判断是否相等
                    if ( call1Index == call2Index) {
                        break;
                    }
                }

                Contact call1 = contacts.get(call1Index);
                Contact call2 = contacts.get(call2Index);

                //生产随机的通话时间 - 多次读取
                String startDate = "20180101000000";
                String endDate = "20190101000000";

                //小写 h 是十二进制，大写 H 是二十四进制
                long startTime = DateUtil.parse(startDate,"yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate,"yyyyMMddHHmmss").getTime();

                //通话时间
                long calltime = startTime + (long)((endTime - startTime) * Math.random());

                //通话时间字符串
                String callTimeString = DateUtil.fomat(new Date(calltime),"yyyyMMddHHmmss");

                //生成随机的通话时长 - 多次读取
                String duretion = NumberUtil.format(new Random().nextInt( 3000 ),4);

                //生成通话记录 - 多次读取
                Calllog log = new Calllog(call1.getTel(),call2.getTel(),callTimeString,duretion);

                System.out.println(log);

                //将通话记录刷写到数据文件中 - 多次读取
                out.write(log);

                Thread.sleep(500);

            }
        } catch ( Exception e ) {
            //打印堆栈信息
            e.printStackTrace();
        }
    }

    /**
     * 关闭生产者资源(DataIn、DataOut)
     * @throws IOException
     */
    public void close() throws IOException {

        //in可能只用到一次，但out在不断输出，所以out在全部用完之后关闭
        if ( in != null ){
            in.close();
        }
        if ( out != null ){
            out.close();
        }
    }
}
