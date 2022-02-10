/**
 * @version 0.0.5
 * @auther int_t(peanut_exe)
 *
 * server base
 */

package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerLauncher extends Thread {

    static LogManager log = new LogManager("FireStockServer");
    static int PORT = 56077;

    static ArrayList<Socket> list = new ArrayList<Socket>();
    static Socket socket = null;

    public ServerLauncher(Socket socket) {
        ServerLauncher.socket = socket;
        list.add(socket);
    }
    public void run() {
        try {
            System.out.println("server : " + socket.getInetAddress()
                    + " IP's client is connect");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            writer.print("connect to server. insert name$ ");

            String readValue;
            String name = null;
            boolean identify = false;

            while((readValue = reader.readLine()) != null ) {
                if(!identify) {
                    name = readValue;
                    identify = true;
                    writer.println("\n" + name + " is connect to server");
                    log.info(name + " is connect to server");
                    continue;
                }

                for(int i = 0; i<list.size(); i++) {
                    out = list.get(i).getOutputStream();
                    writer = new PrintWriter(out, true);
                    writer.println(name + " : " + readValue);
                    log.info(name + ": " + readValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            int socketPort = 56077;
            ServerSocket serverSocket = new ServerSocket(socketPort);
            System.out.println("socket : " + socketPort + " open to server");

            while(true) {
                Socket socketUser = serverSocket.accept();
                Thread thd = new ServerLauncher(socketUser);
                thd.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
