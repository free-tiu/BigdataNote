package com.bigdata.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

//Closeable注意是java.io
public interface DataIn extends Closeable {

    public void setPath(String path);
    public Object read() throws IOException;

    /**
     * 相当于传进来什么类型【read(Class<T> clazz)】，就返回一个什么类型的集合【List<T>】
     * 为了使传进来的是封装成对象的数据，增加约束(<T extends Data> - 只有Data的子类可以传进来)
     * @param clazz
     * @return
     * @throws IOException
     */
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException;

}
