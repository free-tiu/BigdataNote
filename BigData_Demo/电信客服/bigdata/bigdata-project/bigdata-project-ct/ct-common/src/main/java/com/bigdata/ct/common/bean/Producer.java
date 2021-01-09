package com.bigdata.ct.common.bean;

import java.io.Closeable;

/**
 * 生产者接口
 * 数据来源和输出不是写死的
 */
public interface Producer extends Closeable {

    //数据来源
    public void setIn(DataIn in);
    //数据的输出
    public void setOut(DataOut out);

    //生产数据
    public void produce();

}
