package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sanfen on 2017/1/9.
 */
public class Service {

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(20000);
            Socket client = null;
            boolean flag = true;
            while (flag) {
                client = serverSocket.accept();
                System.out.println("与客户端连接成功");
                new Thread(new ServerThread(client)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
