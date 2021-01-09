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
		//������Ⱥ
				//��ȡ�ļ�ϵͳ
				Configuration conf = new Configuration();
				
				//�������ü�Ⱥ��Ϣ ��ֵ��
				//�򱾻�Ϊ����hosts������"hdfs://hadoop161:9000"�޷�ʶ��Ҫ�ĳ�IP��ַ
				conf.set("fs.defaultFS","hdfs://192.168.12.161:9000");
				
				//��ȡHDFS�ͻ��˶���
				FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), conf, "hadoop");
				
				//��hdfs�ϴ���·��
				fs.mkdirs(new Path("/0200/asd"));
				//�ر���Դ���ͷ���Դ��
				fs.close();
				//��֤�����Ƿ����
				System.out.println("over");
	}
	
}
