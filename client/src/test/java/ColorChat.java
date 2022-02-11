import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class ColorChat {

    protected static boolean quit = false;

    static LogManager log = new LogManager("Server");

    public static void main(String[] args) {
        while (!quit) {
            Scanner scn = new Scanner(System.in);
            System.out.print("Server IP: ");
            String arg = scn.next();
            try {
                Socket socket = new Socket(arg, 56077);

                t1Thread t1 = new t1Thread(socket);
                t2Thread t2 = new t2Thread(socket, t1, arg);
                System.out.print(arg + ":" + Inet4Address.getLocalHost() + "$ ");
                t1.start();
                t2.start();
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t1.stop();
                socket.close();
            } catch (IOException e) {
                log.error("서버의 해당 포트가 열려있지 않습니다");
            }
        }
    }
}
class t2Thread extends Thread {

    private BufferedReader br;
    private final t1Thread t1;
    private final String arg;

    public t2Thread(Socket socket, t1Thread t1, String arg) {
        this.t1 = t1;
        this.arg = arg;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.print(arg + ":" + InetAddress.getLocalHost() + "$ ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        t1.stop();
        System.out.println("\n서버와의 연결이 끊겼습니다");
    }
}
class t1Thread extends Thread {

    private PrintWriter pw;
    private final Socket socket;

    public t1Thread(Socket socket) {
        this.socket = socket;
        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String arg = null;
            try {
                arg = br.readLine();
            } catch (IOException e) {
            }
            String[] args = arg.split("\\s");

            if (args[0].equals("exit") || socket.isClosed()) {
                break;
            } else {
                pw.println(arg);
            }
        }
    }
}