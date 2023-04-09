package cn.zhy.bytebuffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 将多个 buffer 的数据填充至 channel
 */
public class GatheringWrites {


    public static void main(String[] args) {

        try (RandomAccessFile file = new RandomAccessFile("words2.txt", "rw")) {

            ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("123");
            ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("456");
            ByteBuffer buffer3 = StandardCharsets.UTF_8.encode("你好！");

            file.getChannel().write(new ByteBuffer[]{buffer1,buffer2,buffer3});


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }


}
