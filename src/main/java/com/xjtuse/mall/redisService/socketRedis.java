package com.xjtuse.mall.redisService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class socketRedis {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6379);
        System.out.println("Redis is connected!");
        //从系统读取
        BufferedReader out = new BufferedReader(new InputStreamReader(System.in));
        //向socket写入
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        while(true){
            String str = out.readLine();
            if("".equals(str)){
                break;
            }
            writer.println(str);
            writer.flush();
        }
        writer.close();
        socket.close();
    }

}
