/**
 * @version 0.0.5
 * @auther int_t(peanut_exe)
 *
 * ㅋ
 */

package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLauncher extends Thread {

    static LogManager log = new LogManager("FireStockServer");

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(56077);
            System.out.println("socket : " + 56077 + " open to server");

            while(true) {
                Socket socket = serverSocket.accept();

                ServerThread thread = new ServerThread(socket);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;

    public ServerThread(Socket socket) {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.socket = socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
            String arg = null;
            while ((arg = br.readLine()) != null) {
                String[] args =  arg.split("\\s");
                if (args[0].equals("moo")) {
                    pw.write("                 (__)\n" +
                               "                 (oo)\n" +
                               "           /------\\/\n" +
                               "          / |    ||\n" +
                               "         *  /\\---/\\\n" +
                               "            ~~   ~~");
                    System.out.println("                 (__)\n" +
                                       "                 (oo)\n" +
                                       "           /------\\/\n" +
                                       "          / |    ||\n" +
                                       "         *  /\\---/\\\n" +
                                       "            ~~   ~~");
                } else {
                    for (String s : args) {
                        pw.write(s);
                        System.out.println(s);
                    }
                }
            }
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
