package com.bigdata.ct.common.bean;

import java.io.Closeable;

//Closeable注意是java.io
public interface DataOut extends Closeable {

    public void setPath(String path);

    public void write( Object data ) throws Exception;
    public void write( String data ) throws Exception;
}
