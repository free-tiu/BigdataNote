package com.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.hdfs.server.namenode.FSAclBaseTest;
import org.junit.Test;

public class HDFSClient {

	public static void main(String[] args) throws IOException {
		//操作集群
		//获取文件系统
		Configuration conf = new Configuration();
		
		//设置配置集群信息 键值对
		//因本机为配置hosts，所以"hdfs://hadoop161:9000"无法识别，要改成IP地址
		conf.set("fs.defaultFS","hdfs://192.168.12.161:9000");
		
		//拦截本机user用户改成集群上的hadoop用户
		//Run As>Run Configurations>Java Application下的HDFSClient>(x)=Arguments下的VM arguments 输入：-DHADOOP_USER_NAME=hadoop
		
		//获取HDFS客户端对象
		FileSystem fs = FileSystem.get(conf);
		//在hdfs上创建路径
		fs.mkdirs(new Path("/0200/abc"));
		//关闭资源（释放资源）
		fs.close();
		//验证程序是否结束
		System.out.println("over");
		
	}

	//文件上传
		@Test
		public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
			
			//获取对象
			Configuration conf = new Configuration();
			
			//优先级
			//conf.set("dfs.replication", "2");
			
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), conf, "hadoop");
			
			//执行上传API
			fs.copyFromLocalFile(new Path("d:/Hadoop/HDFS/zxc.txt"), new Path("/qiuyun.txt"));
			
			//关闭资源
			fs.close();
							
		}
			
		//文件下载
		@Test
		public void testCopyToLocalFile() throws IOException,InterruptedException,URISyntaxException{
			
			//1.获取文件系统
			Configuration configuration = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
			//2.执行下载操作
			//boolean delSrc 指是否将原文件删除
			//Path src 值要下载的文件路径
			//Path dst 指将文件下载到的路径
			//boolean useRawLocalFileSystem 是否开启文件校验
			fs.copyToLocalFile(false, new Path("/qiuyun.txt"),new Path("d:/Hadoop/HDFS/nanzhou.txt"),true);
			//3.关闭资源
			fs.close();
		}
		
		//文件夹删除
		@Test
		public void testDelete() throws IOException, InterruptedException, URISyntaxException {
			
			//1.获取文件系统
			Configuration configuration = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
			//2.执行删除
			fs.delete(new Path("/zxc.txt"),true);
			//3.关闭资源
			fs.close();
		}
		
		//文件名更改
		@Test
		public void testRename() throws IOException, InterruptedException, URISyntaxException {
			
			//获取文件系统
			Configuration configuration = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
			//修改文件名称
			fs.rename(new Path("/qiuyun.txt"), new Path("/nanzhou.txt"));
			//关闭资源
			fs.close();
		}
		
		//文件详情查看
		@Test
		public void testListFiles() throws IOException, InterruptedException, URISyntaxException {
			
			//获取文件系统
			Configuration configuration = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
			//获取文件详情
			RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
			while(listFiles.hasNext()) {
				LocatedFileStatus filestatus = listFiles.next();
				//输出详情
				//文件名称
				System.out.println(filestatus.getPath().getName());
				//文件长度
				System.out.println(filestatus.getLen());
				//文件权限
				System.out.println(filestatus.getPermission());
				//分组
				System.out.println(filestatus.getGroup());
				//获取存储信息
				BlockLocation[] blockLocations = filestatus.getBlockLocations();
				for (BlockLocation blockLocation : blockLocations) {
					//获取块存储的主机节点
					String[] hosts = blockLocation.getHosts();
					for (String host : hosts) {
						System.out.println(host);
					}
				}
				System.out.println("------------这是一条分割线-------------");
			}
			//关闭资源
			fs.close();
		}
		
		//文件和文件夹判断
		@Test
		public void testListStatus() throws IOException, InterruptedException, URISyntaxException {
			
			//获取文件配置信息
			Configuration configuration = new Configuration();
			FileSystem fs = FileSystem.get(new URI("hdfs://192.168.12.161:9000"), configuration, "hadoop");
			//判断是文件还是文件夹
			FileStatus[] lisFileStatus = fs.listStatus(new Path("/"));
			for (FileStatus fileStatus : lisFileStatus) {
				//如果是文件
				if (fileStatus.isFile()) {
					System.out.println("文件:"+fileStatus.getPath().getName());
				} else {
					System.out.println("文件夹:"+fileStatus.getPath().getName());
				}
			}
			//关闭资源
			fs.close();
		}
		
}
