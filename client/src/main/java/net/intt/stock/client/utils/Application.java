package net.intt.stock.client.utils;

import net.intt.stock.client.ClientLauncher;
import net.intt.stock.client.thread.InputThread;
import net.intt.stock.client.thread.OutputThread;
import org.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Application {

    private static final LogManager log = ClientLauncher.log;

    public static Socket ServerJoin(int port, /*Scanner scn*/ String ip) throws IOException {
//        System.out.print("ip address$ ");
//        String arg = scn.nextLine();
        return new Socket(/*arg*/ ip, port);
    }

    public static class MainClient {

        private final BufferedReader br;
        private final PrintWriter pw;

        public MainClient(Socket socket) throws IOException {
            this.br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            this.pw = new PrintWriter(
                    socket.getOutputStream(), true);
        }

        private String ID;

        public String login() throws Exception {
            System.out.print("login> ");
            Scanner scn = new Scanner(System.in);
            String arg = scn.nextLine();
            String[] args = arg.split("\\s");
            if (args[0].equals("help") ||
                    args[0].equals("?") ||
                    args.length < 3) {
                return "-1";
            }
            switch (args[0]) {
                case "login", "signup" -> pw.println("^" + arg);
                case "quit", "leave" -> {
                    return "-2";
                }
            }
            String return_;
            if (Integer.parseInt(return_ = br.readLine()) == 0) ID = args[1];
            return return_;
        }

        public int client() throws IOException, InterruptedException {
            InputThread input = new InputThread(br, pw);
            OutputThread output = new OutputThread(br, pw);
            input.start();
            output.start();
            while (true) {
                if (!input.isStop() || !output.isStop()) break;
            }

            return input.getReturn();
        }

        public String getID() {
            return ID;
        }
    }

//    public static void main(String[] args) throws Exception {
//        Socket socket = Application.ServerJoin(56077, new Scanner(System.in));
//        Application.MainClient app = new MainClient(socket);
//        while (true) {
//            System.out.println(app.login());
//        }
//    }
}
