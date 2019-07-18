

# 打开客户端

    zkCli.sh

# 创建

## 创建节点

语法

    create /path /data
    create /FirstZnode “Myfirstzookeeper-app"
 
特点    
    
    如果path存在，则创建失败
    
## 节点类型

    节点类型包括临时的、持久的、顺序的。默认是持久的。

持久

	客户端与zookeeper断开连接后，该节点依旧存在
 
临时
    
    当会话过期或客户端断开连接时，临时节点将被自动删除。

顺序

    顺序节点保证znode路径将是唯一的。
    ZooKeeper集合将向znode路径填充10位序列号。
    例如，znode路径 /myapp 将转换为/myapp0000000001，下一个序列号将为/myapp0000000002

临时顺序

	客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号
    
    
# 获取

## 获取节点

语法

    get /path 
    get /FirstZnode

效果

    [zk: localhost:2181(CONNECTED) 1] get /FirstZnode
    “Myfirstzookeeper-app"
    cZxid = 0x7f
    ctime = Tue Sep 29 16:15:47 IST 2015
    mZxid = 0x7f
    mtime = Tue Sep 29 16:15:47 IST 2015
    pZxid = 0x7f
    cversion = 0
    dataVersion = 0
    aclVersion = 0
    ephemeralOwner = 0x0
    dataLength = 22
    numChildren = 0

##  节点信息
  
    czid 创建事务id 
    mzid 修改事务id 
    pzid 子节点事务id 

# 查看

    ls
# 删除

    rmr
    
            
# 修改

    设置指定znode的数据。

语法
    
    set /path /data
    set /SecondZnode Data-updated

    

