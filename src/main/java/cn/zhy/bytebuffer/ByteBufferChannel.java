package cn.zhy.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;



@Slf4j
public class ByteBufferChannel {

    public static void main(String[] args) {

        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw")) {

            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do{
                int length = fileChannel.read(buffer);
                log.info("读取到的字节数:{}",length);
                if (length  == -1){
                    break;
                }
                //切换 buffer 读模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    log.debug("{}",(char)buffer.get());
                }

                //切换 buffer 写模式
                buffer.clear();

            }while (true);


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
