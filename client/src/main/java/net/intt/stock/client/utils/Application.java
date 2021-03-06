package net.intt.stock.client.utils;

import net.intt.stock.client.ClientLauncher;
import net.intt.stock.client.thread.InputThread;
import net.intt.stock.client.thread.OutputThread;
import net.intt.util.LogManager;
import org.jline.reader.LineReader;

import javax.sound.sampled.Line;
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
        private final LineReader reader = ClientLauncher.reader;

        public MainClient(Socket socket) throws IOException {
            this.br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            this.pw = new PrintWriter(
                    socket.getOutputStream(), true);
        }

        private String ID;

        public String login() throws Exception {
            System.out.print("login> ");
            String arg = reader.readLine();
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
            String return_ = br.readLine();
            String[] ar;
//            if (Integer.parseInt(return_ = br.readLine()) == 0) ID = args[1];
            if (return_ != null) if ((ar = return_.split("\\s"))[0].equals("login")) return_ = ar[1];
            else return "-4";
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
