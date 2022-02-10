import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread{

    public boolean quit = false;

    private BufferedReader br;
    private PrintWriter pw;

    public UserThread() {
    }

    public UserThread(BufferedReader br, Socket socket)  {
        try {
            this.br = br;
            this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("l");
            try {
                String arg = br.readLine();
                if (arg.indexOf("/") == 0) {
                    String[] args = arg.substring(1).split("\\s");
                } else {
                    String[] args = arg.split("\\s");

                    for (String s : args) {
                        pw.println(s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
