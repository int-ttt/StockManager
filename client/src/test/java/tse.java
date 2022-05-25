import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class tse {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5556);
        Thread t = new Thread(new tses(socket));
        Thread t2 = new Thread(new tseT(socket));
        t.start();
        t2.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    static class tses implements Runnable {

        private final BufferedReader br;
        private final PrintWriter pw;

        public tses(Socket socket) throws IOException {
            this.br = new BufferedReader(new InputStreamReader(System.in));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.print("d:");
                    String str = br.readLine();
                    pw.println(str);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static class tseT implements Runnable {

        private final BufferedReader br;
        private final PrintWriter pw;

        public tseT(Socket socket) throws IOException {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                String str;
                while (true) {
                    str = br.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class se {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(5556);
        System.out.println(socket);

        while (true) {
            Socket s = socket.accept();
            System.out.println(s);
            Thread t = new Thread(new tseT(s));
            t.start();


            if (false) break;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    static class tseT implements Runnable {

        private final BufferedReader br;
        private final PrintWriter pw;

        public tseT(Socket socket) throws IOException {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                    pw.println(str);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}