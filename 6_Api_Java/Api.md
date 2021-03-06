package testZK;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperTest {
	
	private static final int SESSION_TIMEOUT = 30000;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperTest.class);
	
	private Watcher watcher =  new Watcher() {

		public void process(WatchedEvent event) {
			LOGGER.info("process : " + event.getType());
		}
	};
	
	private ZooKeeper zooKeeper;
	
	/**
	 *   zookeeper 
	 * @throws IOException
	 */
	@Before
	public void connect() throws IOException {
//		indicate : all servers 
		zooKeeper  = new ZooKeeper("192.168.133.19:2181,192.168.133.20:2181,192.168.133.21:2181", SESSION_TIMEOUT, watcher);
	}
	
	/**
	 *  关闭
	 */
	@After
	public void close() {
		try {
			zooKeeper.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建 znode 
	 *  1.CreateMode  
	 *  PERSISTENT
	 *  PERSISTENT_SEQUENTIAL
	 *  EPHEMERAL
	 *  EPHEMERAL_SEQUENTIAL
	 *  Access Control List: 访问控制列表
	 *  https://baike.baidu.com/item/ACL/362453?fr=aladdin
	 *  OPEN_ACL_UNSAFE: ANYONE CAN VISIT 
	 * <br>------------------------------<br>
	 */
	@Test
	public void testCreate() {
		String result = null;
		 try {
			 result = zooKeeper.create("/zk002", "zk002data-e".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			 Thread.sleep(30000);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("create result : {}", result);
	 }
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete() {
		 try {
			zooKeeper.delete("/zk001", -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
	}
	
	/**
	 *   ��ȡ���
	 */
	/**
	 * 
	 */
	@Test
	public void testGetData() {
		String result = null;
		 try {
			 byte[] bytes = zooKeeper.getData("/sxt", null, null);
			 result = new String(bytes);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("getdata result------------------------------------------------- : {}", result);
	}
	@Test
	public void testGetData01() throws Exception {
		String result = null;
		try {
			byte[] bytes = zooKeeper.getData("/zk001", null, null);
			result = new String(bytes);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			Assert.fail();
		}
		LOGGER.info("getdata result-----------------1------------------ : {}", result);
		
		Thread.sleep(30000);
		
		byte[] bytes;
		try {
			bytes = zooKeeper.getData("/zk001", null, null);
			result = new String(bytes);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("getdata result-----------------2-------------------- : {}", result);
		
		
		
		
		
	}
	
	/**
	 *   注册
	 */
	@Test
	public void testGetDataWatch() {
		String result = null;
		 try {
			 System.out.println("get:");
			 byte[] bytes = zooKeeper.getData("/zk001", new Watcher() {
				public void process(WatchedEvent event) {
					LOGGER.info("testGetDataWatch  watch : {}", event.getType());
					System.out.println("watcher ok");
					
				}
			 }, null);
			 result = new String(bytes);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 LOGGER.info("getdata result------------------------------------------ : {}", result);
		 
		 // wacth  NodeDataChanged
		 try {
			 System.out.println("set:");
			 zooKeeper.setData("/zk001", "testSetDataWAWWW".getBytes(), -1);
			 System.out.println("set:");
			 zooKeeper.setData("/zk001", "testSetDataWAWWW".getBytes(), -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 System.out.println("over");
	}
	
	/**
	 *    �жϽڵ��Ƿ����
	 *    �����Ƿ������Ŀ¼�ڵ㣬����� watcher ���ڴ��� ZooKeeperʵ��ʱָ���� watcher
	 */
	@Test
	public void testExists() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/zk001", false);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 LOGGER.info("exists result : {}", stat.getCzxid());
	}
	
	/**
	 *     ���ö�Ӧznode�µ����  ,  -1��ʾƥ�����а汾
	 */
	@Test
	public void testSetData() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.setData("/zk001", "testSetData".getBytes(), -1);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 LOGGER.info("exists result : {}", stat.getVersion());	
	}
	
	/**
	 *    �жϽڵ��Ƿ����, 
	 *    �����Ƿ������Ŀ¼�ڵ㣬����� watcher ���ڴ��� ZooKeeperʵ��ʱָ���� watcher
	 */
	@Test
	public void testExistsWatch1() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/zk001", true);
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 
		 try {
			zooKeeper.delete("/zk001", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *    �жϽڵ��Ƿ����, 
	 *    ���ü�����Ŀ¼�ڵ�� Watcher
	 */
	@Test
	public void testExistsWatch2() {
		Stat stat = null;
		 try {
			 stat = zooKeeper.exists("/zk002", new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					LOGGER.info("testExistsWatch2  watch : {}", event.getType());
				}
			 });
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
		 Assert.assertNotNull(stat);
		 
		 // ����watch �е�process����   NodeDataChanged
		 try {
			zooKeeper.setData("/zk002", "testExistsWatch2".getBytes(), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 // ���ᴥ��watch ֻ�ᴥ��һ��
		 try {
			zooKeeper.delete("/zk002", -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  ��ȡָ���ڵ��µ��ӽڵ�
	 */
	@Test
	public void testGetChild() {
		 try {
			 zooKeeper.create("/zk/001", "001".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			 zooKeeper.create("/zk/002", "002".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			 
			 List<String> list = zooKeeper.getChildren("/zk", true);
			for (String node : list) {
				LOGGER.info("fffffff {}", node);
			}
		} catch (Exception e) {
			 LOGGER.error(e.getMessage());
			 Assert.fail();
		}
	}
}
