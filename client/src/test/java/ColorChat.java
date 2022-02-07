class ColorChat {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

    public static final String Red(Object value) {
        return ANSI_RED + value + ANSI_RESET;
    }

    public static final String BLUE(Object value) {
        return ANSI_BLUE + value + ANSI_RESET;
    }

    public static final String BLACK(Object value) {
        return ANSI_BLACK + value + ANSI_RESET;
    }

    public static final String CYAN(Object value) {
        return ANSI_CYAN + value + ANSI_RESET;
    }

    public static final String WHITE(Object value) {
        return ANSI_WHITE + value + ANSI_RESET;
    }

    public static final String GREEN(Object value) {
        return ANSI_GREEN + value + ANSI_RESET;
    }

    public static final String YELLOW(Object value) {
        return ANSI_YELLOW + value + ANSI_RESET;
    }

    public static final String PURPLE(Object value) {
        return ANSI_PURPLE + value + ANSI_RESET;
    }
}
