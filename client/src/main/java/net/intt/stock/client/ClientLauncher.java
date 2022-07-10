package net.intt.stock.client;

import net.intt.stock.client.utils.Application;
import net.intt.stock.client.utils.JlineStream;
import net.intt.util.LogManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.IOException;
import java.net.Socket;

public class ClientLauncher {

    public static final LineReader reader = LineReaderBuilder.builder().build();

    public static final LogManager log = new LogManager("Finance", new JlineStream(reader));

    public static void main(String[] args) {
        Socket socket;
        try {
            all: while (true) {
                try {
                    socket = Application.ServerJoin(56077, /*new Scanner(System.in).nextLine()*/"localhost");
                } catch (IOException e) {
                    log.error("서버가 열려있지 않습니다");
                    throw new Exception();
//                    continue;
                }
                Application.MainClient client = new Application.MainClient(socket);
                while (true) {
                    int re;
                    try {
                        if ((re = Integer.parseInt(client.login())) == 0) {
                            break;
                        }
                        else if (re == -2) {
                            break all;
                        }
                    } catch (Exception e) {
                        continue all;
                    }
                }
                if (client.client() == 0) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
