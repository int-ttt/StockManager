package net.intt.stock.server;

import java.io.DataInputStream;
import java.net.Socket;

public class Receiver implements Runnable{

    Socket socket;
    DataInputStream in;
    String name;
    User user = new User();

    public Receiver(User user,Socket socket) throws Exception
    {
        this.user = user;
        this.socket = socket;
        //접속한 Client로부터 데이터를 읽어들이기 위한 DataInputStream 생성
        in = new DataInputStream(socket.getInputStream());
        //최초 사용자로부터 닉네임을 읽어들임
        this.name = in.readUTF();
        //사용자 추가해줍니다.
        user.AddClient(name, socket);
    }

    public void run()
    {
        try
        {

            while(true)
            {
                String msg = in.readUTF();
                user.sendMsg(msg , name);
            }
        }catch(Exception e) {

            user.RemoveClient(this.name);
        }
    }
}