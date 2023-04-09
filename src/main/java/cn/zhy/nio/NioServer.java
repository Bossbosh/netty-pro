package cn.zhy.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static cn.zhy.util.ByteBufferUtil.debugAll;

/**
 * 非阻塞模式下的单线程运行Server
 * * 非阻塞模式下，相关方法都会不会让线程暂停
 *   * 在 ServerSocketChannel.accept 在没有连接建立时，会返回 null，继续运行
 *   * SocketChannel.read 在没有数据可读时，会返回 0，但线程不必阻塞，可以去执行其它 SocketChannel 的 read 或是去执行 ServerSocketChannel.accept
 *   * 写数据时，线程只是等待数据写入 Channel 即可，无需等 Channel 通过网络把数据发送出去
 * * 但非阻塞模式下，即使没有连接建立，和可读数据，线程仍然在不断运行，白白浪费了 cpu
 */
@Slf4j
public class NioServer {


    public static void main(String[] args) throws IOException {

        //0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);


        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);  //非阻塞模式

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(9889));

        // 3. 连接的集合
        List<SocketChannel> channels = new ArrayList<>();

        while (true){

            // 4. accept建立与客户端连接，SocketChannel用来与客户端之间通信
            SocketChannel sc = ssc.accept();   //非阻塞，线程还会继续运行，如果没有连接建立，sc值为null
            if (sc != null) {
                log.debug("connected...{}",sc);
                sc.configureBlocking(false);  // 非阻塞模式
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                // 5. 接收客户端发送的数据
                int read = channel.read(buffer);  //非阻塞，线程仍然会继续运行，如果没有读到数据，read值返回 0
                if (read > 0) {
                    buffer.flip();
                    debugAll(buffer);
                    buffer.clear();
                    log.debug("after read......{}",channel);
                }
            }

        }



    }


}
