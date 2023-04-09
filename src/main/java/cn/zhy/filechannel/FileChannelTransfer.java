package cn.zhy.filechannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileChannelTransfer {

    public static void main(String[] args) {

        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel()
        ) {
            long size = from.size();
            for (long left = size; left>0 ; ) {
                //transferTo ： 效率高，底层会利用操作系统的零拷贝进行优化，最多传2g数据

                //left 代表剩余还有多少字节
                left -= from.transferTo(size-left,left,to);
            }
            
            
            
        }catch (Exception ex){
            ex.printStackTrace();
        }







    }



}
