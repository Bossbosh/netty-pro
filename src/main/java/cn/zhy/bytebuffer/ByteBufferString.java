package cn.zhy.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static cn.zhy.util.ByteBufferUtil.*;

/**
 * ByteBuffer 与 String 互转
 */
public class ByteBufferString {


    public static void main(String[] args) {

        // 1. 字符串转为 ByteBuffer,该方式position不在起始位置
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(16);
        byteBuffer1.put("hello".getBytes());
        debugAll(byteBuffer1);

        // 2. Charset
        ByteBuffer byteBuffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(byteBuffer2);

        // 3. wrap
        ByteBuffer byteBuffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(byteBuffer3);

        // ByteBuffer转为字符串
        String str1 = StandardCharsets.UTF_8.decode(byteBuffer2).toString();
        System.out.println(str1);

        byteBuffer1.flip();
        String str2 = StandardCharsets.UTF_8.decode(byteBuffer1).toString();
        System.out.println(str2);

    }


}
