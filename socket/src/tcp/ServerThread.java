package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by sanfen on 2017/1/10.
 */
public class ServerThread implements Runnable {
    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        PrintStream out = null;
        BufferedReader buf = null;
        try {
            out = new PrintStream(client.getOutputStream());
            buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;
            while (flag) {
                String str = buf.readLine();
                if (str == null || "".equals(str)) {
                    flag = false;
                } else {
                    if ("bye".equals(str)) {
                        flag = false;
                    } else {
                        out.println("echo:" + str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
