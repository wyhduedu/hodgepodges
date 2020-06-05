package com.wy.hodgepodges.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class IOServer {

    public static void main(String[] args) throws IOException {
        // TODO 服务端处理客户端连接请求
        ServerSocket serverSocket = new ServerSocket(3333);
        // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    // 每一个新的连接都创建一个线程，负责读取数据

                    new Thread(() -> {

                        try {

                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                        }

                    }).start();
                } catch (IOException e) {
                }
            }
        }).start();
    }


    public static void main1(String[] args) throws IOException {

        // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，

        // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等

        Selector serverSelector = Selector.open();

        // 2. clientSelector负责轮询连接是否有数据可读

        Selector clientSelector = Selector.open();


        new Thread(() -> {

            try {

                // 对应IO编程中服务端启动

                ServerSocketChannel listenerChannel = ServerSocketChannel.open();

                listenerChannel.socket().bind(new InetSocketAddress(3333));

                listenerChannel.configureBlocking(false);

                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);


                while (true) {

                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms

                    if (serverSelector.select(1) > 0) {

                        Set<SelectionKey> set = serverSelector.selectedKeys();

                        Iterator<SelectionKey> keyIterator = set.iterator();


                        while (keyIterator.hasNext()) {

                            SelectionKey key = keyIterator.next();


                            if (key.isAcceptable()) {

                                try {

                                    // (1)

                                    // 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector

                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();

                                    clientChannel.configureBlocking(false);

                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);

                                } finally {

                                    keyIterator.remove();

                                }

                            }


                        }

                    }

                }

            } catch (IOException ignored) {

            }

        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {

                        Set<SelectionKey> set = clientSelector.selectedKeys();

                        Iterator<SelectionKey> keyIterator = set.iterator();


                        while (keyIterator.hasNext()) {

                            SelectionKey key = keyIterator.next();


                            if (key.isReadable()) {

                                try {

                                    SocketChannel clientChannel = (SocketChannel) key.channel();

                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                                    // (3) 面向 Buffer

                                    clientChannel.read(byteBuffer);

                                    byteBuffer.flip();

                                    System.out.println(

                                            Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());

                                } finally {

                                    keyIterator.remove();

                                    key.interestOps(SelectionKey.OP_READ);

                                }

                            }


                        }

                    }

                }

            } catch (IOException ignored) {

            }
        }).start();

    }

}
