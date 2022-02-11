package net.intt.stock.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String arg = null;
            while ((arg = br.readLine()) != null) {
                String[] args =  arg.split("\\s");
                if (args[0].equals("moo")) {
                    pw.println("                 (__)#\n" +
                            "                 (oo)#\n" +
                            "           /------\\/#\n" +
                            "          / |    ||#\n" +
                            "         *  /\\---/\\#\n" +
                            "            ~~   ~~");
                    System.out.println("                 (__)\n" +
                            "                 (oo)\n" +
                            "           /------\\/\n" +
                            "          / |    ||\n" +
                            "         *  /\\---/\\\n" +
                            "            ~~   ~~");
                } else {
                    for (String s : args) {
                        pw.println(s);
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
