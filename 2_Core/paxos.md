
原子消息广播协议ZAB
cap
paxos
raft
zab
ZAB：
myid/serverID
zxid，
epoch
numID


Zookeeper的核心是原子广播，这个机制保证了各个server之间的同步。实现这个机制的协议叫做Zab协议。
Zab协议有两种模式：
恢复模式
广播模式。
当服务启动或者在领导者崩溃后
Zab就进入了恢复模式，
当领导者被选举出来，且大多数server的完成了和leader的状态同步以后，恢复模式就结束了。
状态同步保证了leader和server具有相同的系统状态


广播模式需要保证proposal被按顺序处理，因此zk采用了递增的事务id号(zxid)来保证。
所有的提议  (proposal)都在被提出的时候加上了zxid。
实现中zxid是一个64为的数字，它高32位是epoch用来标识  leader关系是否改变，每次一个leader被选出来，它都会有一个新的epoch，低32位是个递增计数。



Paxos算法是基于消息传递且具有高度容错特性的一致性算法，是目前公认的
解决分布式一致性问题最有效的算法之一，
其解决的问题就是在分布式系统中如何就某个值（决议）达成一致。




