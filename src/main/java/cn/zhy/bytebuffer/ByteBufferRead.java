package cn.zhy.bytebuffer;

import java.nio.ByteBuffer;
import static cn.zhy.util.ByteBufferUtil.*;

public class ByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[]{'a','b','c','d'});

        buffer.flip();

        debugAll(buffer);

//        buffer.get(new byte[4]);
//        //rewind 从头开始读
//
//        buffer.rewind();
//
//        System.out.println((char)buffer.get());
//
//        debugAll(buffer);

        // mark & reset
        // mark做一个标记，记录position的位置，reset则是将position重置到mark的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark();   // 加标记，索引2的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        debugAll(buffer);
//        buffer.reset(); // 将标记重置到索引2
//        debugAll(buffer);
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());

        // get(i) 不会改变position的位置
        System.out.println((char)buffer.get(3));
        debugAll(buffer);


    }

}
