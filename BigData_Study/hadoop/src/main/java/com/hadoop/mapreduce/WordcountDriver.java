package com.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordcountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		//MapReduce����
		
		//key�����ʣ�value������
		//1.��ȡ������Ϣ����װ���񣨻�ȡjob����
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		//2.����jar����·��(�洢·��)
		job.setJarByClass(WordcountDriver.class);	//�������е�jar��λ��

		//3.����map��reduce��(������)
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);

		//4.����map������ݵ�key��value����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//5.�������������kv����
		//�������������key��value����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 6 ������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));	//����
		FileOutputFormat.setOutputPath(job, new Path(args[1]));	//���

		//7.�ύ	waitForCompletion => �ȴ��ύ���
		boolean result = job.waitForCompletion(true);		//true -> �ύ��ɻ��д�ӡ��Ϣ	false -> �ύ����޴�ӡ��Ϣ
		System.exit(result ? 0 : 1);
	}
}