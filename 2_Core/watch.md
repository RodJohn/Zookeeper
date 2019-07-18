    
    事件监听Watcher
    Watcher 在 ZooKeeper 是一个核心功能，Watcher 可以监控目录节点的数据变化以及子目录的变化，一旦这些状态发生变化，服务器就会通知所有设置在这个目录节点上的Watcher，从而每个客户端都很快知道它所  关注的目录节点的状态发生变化，而做出相应的反应
    可以设置观察的操作：exists,getChildren,getData
    可以触发观察的操作：create,delete,setData
    
    
