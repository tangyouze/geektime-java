import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
tyz@mbp16  ~/java > wrk  -t 1 -c 1 -d10s http://localhost:8082
Running 10s test @ http://localhost:8082
  1 threads and 1 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   237.09us    1.28ms  23.20ms   98.92%
    Req/Sec     5.47k   657.94     6.34k    76.24%
  54902 requests in 10.10s, 3.56MB read
  Socket errors: connect 0, read 54836, write 66, timeout 0
Requests/sec:   5435.85
Transfer/sec:    360.97KB


tyz@mbp16  ~/java > wrk  -t 4 -c 40 -d10s http://localhost:8082
Running 10s test @ http://localhost:8082
  4 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.96ms   16.93ms 162.13ms   95.12%
    Req/Sec     3.46k   750.70     8.47k    90.15%
  137175 requests in 10.07s, 8.90MB read
  Socket errors: connect 0, read 137168, write 6, timeout 0
Requests/sec:  13618.38
Transfer/sec:      0.88MB


# 在xms512m 的情况下 没啥区别
tyz@mbp16  ~/java > wrk  -t 4 -c 40 -d10s http://localhost:8082
Running 10s test @ http://localhost:8082
  4 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.84ms   16.41ms 157.17ms   95.29%
    Req/Sec     3.47k   769.92    10.83k    87.63%
  137261 requests in 10.07s, 8.90MB read
  Socket errors: connect 0, read 137240, write 20, timeout 0
Requests/sec:  13634.67
Transfer/sec:      0.88MB
 */

public class Server02 {
    public static void main(String[] args) throws IOException {
        System.out.println("server02");
        ServerSocket serverSocket = new ServerSocket(8082);
        int i = 0;
        while (true) {
            try {
                i += 1;
                Socket socket = serverSocket.accept();
                if (i % 1000 == 0) {
                    System.out.println("get connect " + i);
                }
                new Thread(() -> {
                    service(socket);
                }).start();
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
            String body = "hello,8082";
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
