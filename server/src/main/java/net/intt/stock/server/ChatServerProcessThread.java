package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerProcessThread extends Thread{
    private String nickname = null;
    private Socket socket = null;
    List<PrintWriter> listWriters = null;
    LogManager log;

    public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters, LogManager log) {
        this.socket = socket;
        this.listWriters = listWriters;
        this.log = log;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffereedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while(true) {
                String request = buffereedReader.readLine();

                if( request == null) {
                    log.error("클라이언트로부터 연결 끊김");
                    doQuit(printWriter);
                    break;
                }

                String[] tokens = request.split(":");
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                }
                else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                }
                else if("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                }
            }
        }
        catch(IOException e) {
            log.info(this.nickname + "님이 채팅방을 나갔습니다.");
        }
    }

    private void doQuit(PrintWriter writer) {
        removeWriter(writer);

        String data = this.nickname + "님이 퇴장했습니다.";
        broadcast(data);
    }

    private void removeWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void doMessage(String data) {
        broadcast(this.nickname + ":" + data);
    }

    private void doJoin(String nickname, PrintWriter writer) {
        this.nickname = nickname;

        String data = nickname + "님이 입장하였습니다.";
        broadcast(data);

        // writer pool에 저장
        addWriter(writer);
    }

    private void addWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    private void broadcast(String data) {
        synchronized (listWriters) {
            for(PrintWriter writer : listWriters) {
                writer.println(data);
                writer.flush();
            }
        }
    }
}