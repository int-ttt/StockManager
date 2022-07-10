package net.intt.stock.server.util;

import net.intt.util.Printer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jline.reader.LineReader;

import java.io.PrintStream;
import java.util.Locale;

public class JlineStream implements Printer {

    private LineReader reader;

    public JlineStream(LineReader reader) {
        this.reader = reader;
    }

    @Override
    public void print(Object x) {
        if (reader.isReading()) {
            reader.callWidget(LineReader.CLEAR);
            reader.getTerminal().writer().print(x);
            reader.callWidget(LineReader.REDRAW_LINE);
            reader.callWidget(LineReader.REDISPLAY);
        } else reader.getTerminal().writer().print(x);
        reader.getTerminal().writer().flush();
    }

    @Override
    public void println(Object x) {
        if (reader.isReading()) {
            reader.callWidget(LineReader.CLEAR);
            reader.getTerminal().writer().println(x);
            reader.callWidget(LineReader.REDRAW_LINE);
            reader.callWidget(LineReader.REDISPLAY);
        } else reader.getTerminal().writer().println(x);
        reader.getTerminal().flush();
    }

    public void println() {
        if (reader.isReading()) {
            reader.callWidget(LineReader.CLEAR);
            reader.getTerminal().writer().println();
            reader.callWidget(LineReader.REDRAW_LINE);
            reader.callWidget(LineReader.REDISPLAY);
        } else reader.getTerminal().writer().println();
    }
}
