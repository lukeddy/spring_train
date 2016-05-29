package com.tangzq;

import com.tangzq.service.AdditionService;
import com.tangzq.service.impl.AdditionServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * 主方法类
 */
public class Server
{
    public static void main( String[] args )
    {
        int serverPort=8888;
        try {
            AdditionService.Processor<AdditionServiceImpl> myProcessor =  new AdditionService.Processor<AdditionServiceImpl>(new AdditionServiceImpl());

            TServerTransport serverTransport = new TServerSocket(serverPort);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(myProcessor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new
            // TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println(String.format("服务器启动{}...",serverPort));
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
