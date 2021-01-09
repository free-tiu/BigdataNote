package com.bigdata.ct.common.bean;

/**
 * 数据对象
 * implements - 接口实现
 * abstract - 抽象
 */

public abstract class Data implements Val {

    public String content;  //拿到数据

    public void setvalue(Object val) {
        content = (String) val;
    }

    public String getvalue() {
        return content;
    }

    public abstract void setValue(String line);
}
