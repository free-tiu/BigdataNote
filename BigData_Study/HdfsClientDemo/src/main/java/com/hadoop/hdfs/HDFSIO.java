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
		
		//��ȡ�ļ�ϵͳ
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
		//����������
		FileInputStream fis = new FileInputStream(new File("d:/Hadoop/HDFS/nanzhou.txt"));
		//��ȡ�����
		FSDataOutputStream fos = fs.create(new Path("/huayu.txt"));
		//���ĶԿ�
		IOUtils.copyBytes(fis, fos, configuration);
		//�ر���Դ
		IOUtils.closeStream(fos);
		IOUtils.closeStream(fis);
		fs.close();
	}
}
