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

		//MapReduce案例
		
		//key代表单词，value代表单词
		//1.获取配置信息及封装任务（获取job对象）
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		//2.设置jar加载路径(存储路径)
		job.setJarByClass(WordcountDriver.class);	//程序运行的jar包位置

		//3.设置map和reduce类(关联类)
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);

		//4.设置map输出数据的key和value类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//5.设置最终输出的kv类型
		//最总数据输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 6 设置输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));	//输入
		FileOutputFormat.setOutputPath(job, new Path(args[1]));	//输出

		//7.提交	waitForCompletion => 等待提交完成
		boolean result = job.waitForCompletion(true);		//true -> 提交完成会有打印信息	false -> 提交完成无打印信息
		System.exit(result ? 0 : 1);
	}
}