package java.net.intt.stock.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ReadThread extends Thread {

    private BufferedReader br;
    private final InputThread t1;

    public ReadThread(Socket socket, InputThread t1) {
        this.t1 = t1;
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
                if (line.equals("")) {
                    System.out.print(ClientLauncher.ID + ":" + InetAddress.getLocalHost() + "$ ");
                    continue;
                }
                String[] lines = line.split("=");
                for (String s : lines) {
                    ClientLauncher.log.info(s);
                }
                System.out.print(ClientLauncher.ID + ":" + InetAddress.getLocalHost() + "$ ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        t1.stop();
        System.out.println();
        ClientLauncher.log.error("서버와의 연결이 끊겼습니다");
    }
}