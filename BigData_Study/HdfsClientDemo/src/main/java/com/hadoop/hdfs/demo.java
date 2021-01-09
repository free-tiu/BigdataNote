package com.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class demo {
	public static void main(String[] args) throws IOException, Exception, URISyntaxException {
		//操作集群
				//获取文件系统
				Configuration conf = new Configuration();
				
				//设置配置集群信息 键值对
				//因本机为配置hosts，所以"hdfs://hadoop161:9000"无法识别，要改成IP地址
				conf.set("fs.defaultFS","hdfs://192.168.12.161:9000");
				
				//获取HDFS客户端对象
				FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), conf, "hadoop");
				
				//在hdfs上创建路径
				fs.mkdirs(new Path("/0200/asd"));
				//关闭资源（释放资源）
				fs.close();
				//验证程序是否结束
				System.out.println("over");
	}
	
}
