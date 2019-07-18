
    
    数据模型Znode
    目录结构 节点间具有父子关系
    层次的，目录型结构，便于管理逻辑关系
    节点znode而非文件file
    znode信息
    包含最大1MB的数据信息
    记录了Zxid等元数据信息
    节点类型
        
    Znode有两种类型，短暂的（ephemeral）和持久的（persistent）
    Znode支持序列SEQUENTIAL：leader
    短暂znode的客户端会话结束时，zookeeper会将该短暂znode删除，短暂znode不可以有子节点
    持久znode不依赖于客户端会话，只有当客户端明确要删除该持久znode时才会被删除
    Znode的类型在创建时确定并且之后不能再修改
    Znode有四种形式的目录节点
    PERSISTENT
    EPHEMERAL
    PERSISTENT_SEQUENTIAL
    EPHEMERAL_SEQUENTIAL
    
