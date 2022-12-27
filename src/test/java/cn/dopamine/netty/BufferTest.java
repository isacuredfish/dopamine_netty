package cn.dopamine.netty;

import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenzi.ma
 * @date 2022/12/27 20:52
 **/
@Slf4j
public class BufferTest {

    @Test
    public void bufferReadTest(){
        //创建channel
        try(FileChannel channel = new FileInputStream("data.text").getChannel()) {
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //由于buffer设置大小问题，需要循环读取
            int len = 0;
            List<Byte> list = new ArrayList<>();
            while (len != -1){
                //读取到buffer
               len = channel.read(buffer);
                //从Buffer输出
                //1.将buffer转为读模式
                buffer.flip();
               while (buffer.hasRemaining()){
                   //循环读取字节
                   byte b = buffer.get();
                   list.add(b);
               }
                //将buffer转为写模式
                buffer.clear();
            }
            log.info(new String(Bytes.toArray(list),"UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
