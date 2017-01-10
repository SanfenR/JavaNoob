package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by sanfen on 2017/1/9.
 */
public class Client {
    public static void main(String[] args) {
        BufferedReader input = null;
        Socket client = null;
        try {
            //客户端请求与本机在20000端口建立tcp连接
            client = new Socket("127.0.0.1", 20000);
            client.setSoTimeout(5000);

            input = new BufferedReader(new InputStreamReader(System.in));

            PrintStream out = new PrintStream(client.getOutputStream());

            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));

            boolean flag = true;

            while (flag) {
                System.out.print("输入信息:");
                String str = input.readLine();

                out.println(str);
                if ("bye".equals(str)) {
                    flag = false;
                } else {
                    try {
                        //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                        String echo = buf.readLine();
                        System.out.println(echo);
                    } catch (SocketTimeoutException e) {
                        System.out.println("Time out, No response");
                    }

                }
            }

        } catch (IOException e) {

        } finally {
            try {
                if (input != null)
                    input.close();
                if (client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
