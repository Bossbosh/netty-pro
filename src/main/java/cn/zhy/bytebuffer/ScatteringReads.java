package cn.zhy.bytebuffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import static cn.zhy.util.ByteBufferUtil.*;

/**
 * 分散读取，有一个文本文件 words.txt,分别填充到3个ByteBuffer中
 */
public class ScatteringReads {


    public static void main(String[] args) {

        try (RandomAccessFile file = new RandomAccessFile("words.txt", "r")) {
            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);

            fileChannel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});

            buffer1.flip();
            buffer2.flip();
            buffer3.flip();

            debugAll(buffer1);
            debugAll(buffer2);
            debugAll(buffer3);


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

}
