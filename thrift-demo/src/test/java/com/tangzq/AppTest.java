package com.tangzq;


import com.tangzq.service.AdditionService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

/**
 * 单元测试类
 */
public class AppTest
{

    @Test
    public void testClient()
    {
        System.out.println("模拟客户端");
        try {
            TTransport transport;
            transport = new TSocket("localhost", 9999);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);

            AdditionService.Client client = new AdditionService.Client(protocol);

            System.out.println("计算结果为:");
            System.out.println(client.add(123, 200));

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}
