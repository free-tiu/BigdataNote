package com.bigdata.ct.producer.io;

import com.bigdata.ct.common.bean.Data;
import com.bigdata.ct.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地文件数据输入
 */
public class LocalFileDataIn implements DataIn {

    //加流,BufferedReader读取数据是按行读取的
    private BufferedReader reader = null;

    public LocalFileDataIn( String path){
        setPath(path);
    }

    public void setPath(String path){

        try {
            //reader字节流和stream字符不能流直接构建，需要转换流（InputStreamReader）进行转换将stream转换成reader
            //转换流中会涉及字符编码
            reader = new BufferedReader( new InputStreamReader(new FileInputStream(path),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object read() throws IOException {
        return null;
    }

    /**
     * 读取数据，返回数据集合
     * 封装方法
     * 使用了泛型约束，只对这个方法起作用
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {

        List<T> ts = new ArrayList<T>();

        try{
            //从数据文件中读取所有数据
            String line = null;
            while ((line = reader.readLine()) != null ) {
                //将数据转换为指定类型的对象，封装为集合返回
                T t = clazz.newInstance();
                t.setValue(line);
                ts.add(t);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
