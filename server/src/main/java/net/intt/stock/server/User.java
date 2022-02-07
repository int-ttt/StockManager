package net.intt.stock.server;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class User {

    HashMap<String, DataOutputStream> clientmap = new HashMap<String,DataOutputStream>();

    public synchronized void AddClient(String name, Socket socket) {
        try {
            sendMsg(name+" 입장하셨습니다.","Server");
            clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
            System.out.println("채팅 참여 인원 : "+clientmap.size());
        } catch(Exception e) {}

    }
    public synchronized void RemoveClient(String name) {
        try {
            clientmap.remove(name);
            sendMsg(name + " 퇴장하셨습니다.", "Server");
            System.out.println("채팅 참여 인원 : "+clientmap.size());
        } catch(Exception e) {}
    }

    public synchronized void sendMsg(String msg, String name) throws Exception {
        Iterator iterator = clientmap.keySet().iterator();
        while(iterator.hasNext()) {
            String clientname =(String)iterator.next();
            clientmap.get(clientname).writeUTF(name + ":" + msg);
        }
    }
}