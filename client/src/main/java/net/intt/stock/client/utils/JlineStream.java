package net.intt.stock.client.utils;

import net.intt.util.Printer;
import org.jetbrains.annotations.NotNull;
import org.jline.reader.LineReader;

import javax.annotation.Nullable;
import java.io.PrintStream;

public class JlineStream extends PrintStream implements Printer {

    private LineReader reader;

    public JlineStream(LineReader reader) {
        super(System.out);
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
        reader.getTerminal().flush();
    }

    @Override
    public void print(int i) {
        print((Object) i);
    }

    @Override
    public void print(char c) {
        print((Object) c);
    }

    @Override
    public void print(long l) {
        print((Object) l);
    }

    @Override
    public void print(float f) {
        print((Object) f);
    }

    @Override
    public void print(@NotNull char[] s) {
        print((Object) s);
    }

    @Override
    public void print(double d) {
        print((Object) d);
    }

    @Override
    public void print(@Nullable String s) {
        print((Object) s);
    }

    @Override
    public void print(boolean b) {
        print((Object) b);
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

    @Override
    public void println(int x) {
        println((Object) x);
    }

    @Override
    public void println(char x) {
        println((Object) x);
    }

    @Override
    public void println(long x) {
        println((Object) x);
    }

    @Override
    public void println(float x) {
        println((Object) x);
    }

    @Override
    public void println(@NotNull char[] x) {
        println((Object) x);
    }

    @Override
    public void println(double x) {
        println((Object) x);
    }

    @Override
    public void println(@Nullable String x) {
        println((Object) x);
    }

    @Override
    public void println(boolean x) {
        println((Object) x);
    }

    @Override
    public void println() {
        if (reader.isReading()) {
            reader.callWidget(LineReader.CLEAR);
            reader.getTerminal().writer().println();
            reader.callWidget(LineReader.REDRAW_LINE);
            reader.callWidget(LineReader.REDISPLAY);
        } else reader.getTerminal().writer().println();
    }
}
