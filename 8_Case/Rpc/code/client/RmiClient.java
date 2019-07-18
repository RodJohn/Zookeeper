package demo.zookeeper.remoting.client;
 
import demo.zookeeper.remoting.common.HelloService;
import java.rmi.Naming;
 
public class RmiClient {
 
    public static void main(String[] args) throws Exception {
    	//schema host port  
        String url = "rmi://localhost:1099/demo.zookeeper.remoting.server.HelloServiceImpl";
        //proxy 
        HelloService helloService = (HelloService) Naming.lookup(url);
        String result = helloService.sayHello("Jack");
        System.out.println(result);
    }
}