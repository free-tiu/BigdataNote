package com.bigdata.ct.common.bean;

/**
 * 值对象接口
 * 只要数据跟取值有关系的都要实现这个接口
 * 要实现接口就要有一个获取值的方法 —— Value
 */
public interface Val {

    public void setvalue( Object val );
    //获取值
    public Object getvalue();

}
