package com.hadoop.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class HDFSIO {

	@Test
	public void putFileToHDFS() throws IOException, InterruptedException, URISyntaxException {
		
		//获取文件系统
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
		//创建输入流
		FileInputStream fis = new FileInputStream(new File("d:/Hadoop/HDFS/nanzhou.txt"));
		//获取输出流
		FSDataOutputStream fos = fs.create(new Path("/huayu.txt"));
		//流的对拷
		IOUtils.copyBytes(fis, fos, configuration);
		//关闭资源
		IOUtils.closeStream(fos);
		IOUtils.closeStream(fis);
		fs.close();
	}
}
