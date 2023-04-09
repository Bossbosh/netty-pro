package cn.zhy.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import static cn.zhy.util.ByteBufferUtil.*;

/**
 * 阻塞模式下的单线程服务器
 * * 阻塞模式下，相关方法都会导致线程暂停
 *   * ServerSocketChannel.accept 会在没有连接建立时让线程暂停
 *   * SocketChannel.read 会在没有数据可读时让线程暂停
 *   * 阻塞的表现其实就是线程暂停了，暂停期间不会占用 cpu，但线程相当于闲置
 * * 单线程下，阻塞方法之间相互影响，几乎不能正常工作，需要多线程支持
 */
@Slf4j
public class BioServer {


    public static void main(String[] args) throws IOException {

        //0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);


        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(9889));

        // 3. 连接的集合
        List<SocketChannel> channels = new ArrayList<>();

        while (true){
            log.debug("connecting......");
            // 4. accept建立与客户端连接，SocketChannel用来与客户端之间通信
            SocketChannel sc = ssc.accept();   //阻塞方法，线程停止运行
            log.debug("connected...{}",sc);
            channels.add(sc);

            for (SocketChannel channel : channels) {
                log.debug("before read......{}",channel);
                // 5. 接收客户端发送的数据
                channel.read(buffer);   // 阻塞方法，线程停止运行
                buffer.flip();
                debugAll(buffer);
                buffer.clear();
                log.debug("after read......{}",channel);
            }

        }



    }




}
