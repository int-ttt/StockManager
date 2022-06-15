package net.intt.stock.client.Utils;

import net.intt.stock.client.ClientLauncher;
import net.intt.stock.client.Thread.InputThread;
import org.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Application {

    private static final LogManager log = ClientLauncher.log;

    public static Socket ServerJoin(int port, BufferedReader br) throws IOException {
        String arg = br.readLine();
        return new Socket(arg, port);
    }

    public static class MainClient {

        private final BufferedReader br;
        private final PrintWriter pw;

        public MainClient(Socket socket) throws IOException {
            this.br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            this.pw = new PrintWriter(
                    socket.getOutputStream());
        }

        public int login() throws IOException {
            BufferedReader client = new BufferedReader(
                    new InputStreamReader(System.in));

            String arg = client.readLine();
            String[] args = arg.split("\\s");

            if (args[0].equals("help") ||
                    args[0].equals("?") ||
                    args.length < 3) {
                return -1;
            }

            switch (args[0]) {
                case "login" ->
                        pw.println("^login " + args[1] + " " + args[2]);

                case "signup" ->
                        pw.println("^signup " + args[1] + " " + args[2]);
                case "quit", "leave" -> {
                    return -2;
                }
            }
            return br.read();
        }

        public int client() throws IOException {
            Thread t = new Thread(new InputThread());
            while (true) {
                if (InputThread.isQuit()) break;
            }
            return 0;
        }
    }
}
