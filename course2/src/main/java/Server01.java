import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
为啥我这个性能这么高.. 奇怪了
tyz@mbp16  ~/java > wrk  -t 1 -c 1 -d10s http://localhost:8081
Running 10s test @ http://localhost:8081
  1 threads and 1 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   359.99us    3.01ms  45.43ms   98.54%
    Req/Sec    11.02k     1.56k   12.56k    90.00%
  109794 requests in 10.03s, 7.12MB read
  Socket errors: connect 0, read 109784, write 10, timeout 0
Requests/sec:  10946.92
Transfer/sec:    726.94KB
 */

public class Server01 {
    public static void main(String[] args) throws IOException {
        System.out.println("server01");
        ServerSocket serverSocket = new ServerSocket(8081);
        int i = 0;
        while (true) {
            try {
                i += 1;
                Socket socket = serverSocket.accept();
                if (i % 1000 == 0) {
                    System.out.println("get connect " + i);
                }
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-TYpe:text/html");
            String body = "hello,8081";
            pw.println("Content-Length:" + body.getBytes().length);
            pw.println();
            pw.write(body);
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
