package cn.zhy.bytebuffer;

import java.nio.ByteBuffer;
import static cn.zhy.util.ByteBufferUtil.*;

public class ByteBufferReadWrite {


    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);

        debugAll(buffer);

        buffer.put(new byte[]{0x62,0x63,0x64});

        debugAll(buffer);

        buffer.flip();

        System.out.println(buffer.get());

        debugAll(buffer);

        //compact 方法，是把未读完的部分向前压缩，然后切换至写模式
        buffer.compact();

        debugAll(buffer);

        buffer.put(new byte[]{0x65,0x66});

        debugAll(buffer);
    }






}
