/**
 * @version 0.0.5
 * @auther int_t
 *
 * server file
 */

package net.intt.stock.server;

import net.intt.stock.server.threads.ChatThread;
import net.intt.stock.server.db.SQLite;
import net.intt.stock.server.threads.LoginThread;
import net.intt.stock.server.util.JlineStream;
import net.intt.util.LogManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ServerLauncher  {

    public static final List<PrintWriter> broadcast = new ArrayList<>();
    public static final List<String> playerList = new ArrayList<>();
    public static final LineReader reader = LineReaderBuilder.builder().build();
    public static LogManager log = new LogManager("StockServer", new JlineStream(reader));
    private static final int port = 56077;
    private static boolean quit = true;

    public static void main(String[] args) {
        try {
            SQLite.getInstance().DBInit();
            ServerSocket serverSocket = new ServerSocket(56077);
            log.info("socket : " + port + " open to server");

            new Thread(new ChatThread()).start();

            while (quit) {
                Socket socket = serverSocket.accept();

                broadcast.add(new PrintWriter(
                        socket.getOutputStream(),
                        true));

                Thread thread = new Thread(new LoginThread(socket));
                thread.start();
//                Thread thread = new Thread(new ServerThread(socket));
//                thread.start();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setQuit(boolean quit) {
        ServerLauncher.quit = quit;
    }
}