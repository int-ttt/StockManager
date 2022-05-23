import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.print ("hello goodbye");
        Thread.sleep(5000);
        System.out.print("\rBlyat");
//        long total = 235;
//        long startTime = System.currentTimeMillis();
//
//        for (int i = 1; i <= total; i = i + 3) {
//            try {
//                Thread.sleep(50);
//                printProgress(startTime, total, i);
//            } catch (InterruptedException e) {
//            }
//        }
    }


    private static void printProgress(long startTime, long total, long current) {

        String etaHms = "N/A" ;

        int percent = (int) (current * 100 / total);
        String string = '\r' +
                String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")) +
                String.format(" %d%% [", percent) +
                String.join("", Collections.nCopies(percent, "=")) +
                '>' +
                String.join("", Collections.nCopies(100 - percent, " ")) +
                ']' +
                String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")) +
                String.format(" %d/%d, ETA: %s", current, total, etaHms);

        System.out.print(string);
    }
}
