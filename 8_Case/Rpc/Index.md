
    zookeeper作为Rpc注册中心



单机方案
    
    Service实现Remote接口
    绑定Host、IP、Uri和Remote
    启动Rpc服务
    
    客户端访问url
    
集群zookeeper方案

     客户端订阅Zookeeper目录（递归订阅） 
     Rpc服务启动时在Zookeeper创建节点存放url信息（注册）
     Zookeeper会将url信息通知客户端 
     分布式协调
     
     
     