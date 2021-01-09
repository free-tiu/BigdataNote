## 电信客服项目

----

`使用框架：Flume、Kafka、HBase、Hadoop、zookeeper`

`如果没有以上框架先安装`

### **Kafka集群部署**`集群规划`

| 主机 | hadoop161 | hadoop162 | hadoop163 |
| :--: | :-------: | :-------: | :-------: |
| 分布 | zookeeper | zookeeper | zookeeper |
|      |   kafka   |   kafka   |   kafka   |



### **各框架启动命令**

> - **hadoop：**
>
>   - 启动集群：start-dfs.sh start
>   - 关闭集群：start-dfs.sh stop
>
> - **Kafka：**
>
>   - 启动集群：`依次在hadoop161、hadoop162、hadoop163节点上启动kafka`
>
>     **bin/kafka-server-start.sh config/server.properties &**
>
>   - 关闭集群：
>
>     **bin/kafka-server-stop.sh stop**
>
> - **Hbase：**`访问Hbase管理页面 — http://hadoop161:16010`
>
>   - 启动方式1：**bin/hbase-daemon.sh start master**
>
>     ​					**bin/hbase-daemon.sh start regionserver**
>
>   - 启动方式2：**bin/start-hbase.sh**
>
>   - 方式2对应的停止服务：**bin/stop-hbase.sh**
>
>   > **Hbase Shell操作**
>   >
>   > 进入HBase客户端命令行：**bin/hbase shell**
>   >
>   > 查看帮助命令：**hbase(main):001:0> help**
>   >
>   > 查看当前数据库中有哪些表：**hbase(main):002:0> list**
>
> - **zookeeper：**`需要三台机子分别启动`
>
>   - 启动 — **bin/zkServer.sh start**
>
>   - 查看状态 — **bin/zkServer.sh status**
>   - 关闭 — bin/zkServer.sh stop
>
>   >**客户端命令行操作**
>   >
>   >启动客户端：**bin/zkCli.sh**
>   >
>   >显示所有操作命令：**[zk: localhost:2181(CONNECTED) 1] help**
>   >
>   >查看当前znode中所包含的内容：**[zk: localhost:2181(CONNECTED) 0] ls / [zookeeper]**







#### **释义**

|     列名     |               解释               |
| :----------: | :------------------------------: |
|    call1     |         打第一个手机号码         |
|  call1_name  | 第一个手机号码人姓名`（非必须）` |
|    call2     |          第二个手机号码          |
|  call2_name  |       第二个手机号码人姓名       |
|  date_time   |          建立通话的时间          |
| date_time_ts |  建立童话的时间`（时间戳形式）`  |
|   duration   |       通话持续时间`（秒）`       |

###### **创建kafka主题**

**bin/kafka-topics.sh --zookeeper hadoop161:2181 --create --topic ct --partitions 3 --replication-factor 2**

- --zookeeper hadoop161:2181：zookeeper集群地址
- --create --topic：创建名为"ct"的主题
- --partitions 3：主题分区数
- --replication-factor 2：副本因子` 每个分区副本因子个数即每个分区有多少副本 `

**查询话题是否创建成功**

- /opt/module/kafka/bin/kafka-topics.sh --zookeeper hadoop161:2181 --list





`11有讲解`

### **生产者阶段**

**将在客户端编写的程序打包放到服务器中去：**

>  - 用idea将程序打包`y有main方法的则打包成可运行的`
>  - 点击需要打包的模块 => `ctrl+alt+shift+s`出现**Project Structure** => 点击**Atidacts** => ➕ => **JAR** ，**From module with dependencies**
>  - 出现**Create JAR from Modules**框框，在**Module**中选择需要打包的模块`(项目中是ct-producer)`
>  - **Main Class**选择启动类`(项目中是com.bigdata.ct.producer.Bootstarp)`，
>  - 在**JAR files from libraries**中选中 —— **copy to the output directory and link via manifest**
>  - **后在Directory for META-INF/MANIFEST.MF:**下的选择文件路径中选择**resources**
>  - 后点击🆗，后apply => 🆗
>
> 尝试运行：**Build** => **Build Artfact** => **ct-producer.jar** => **Build**
>
> 运行成功会在**Project**中出现一个**out**文件夹，一直打开，看到**ct-producer.jar**。之后上传到服务器中的**/opt/module/data**文件夹下，尝试运行：
>
> ​	输入`java -jar ct-producer.jar /opt/module/data/contact.log /opt/module/data/call.log`后运行，会在**data**文件夹下看到**call.log**文件的生成



### **数据采集**











#### **遇到的问题**

- 每次运行控制台打印出的通话日志前两个值都是null`第一次测试时控制台打印出的都是 Contact[null,null]`

  ​													 —— 暂时无解

- Xshell远程CentOS，输入 ll 报错 `-bash: ls: command not found`

  解决方法：

  ​	因为环境变量PATH被修改了    
  ​	只需在命令行执行    
  ​	`export PATH=/bin:/usr/bin:$PATH` 