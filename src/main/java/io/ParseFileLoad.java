package io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFileLoad {
    private final File file;

    public ParseFileLoad(File file) {
        this.file = file;
    }

    private synchronized String getContent(Predicate<Integer> filter) throws IOException {
        BufferedInputStream i = new BufferedInputStream(new FileInputStream(file));
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = i.read()) > 0) {
            if (filter.test(data)) {
                output.append((char) data);
            }
        }
        return output.toString();
    }

    public synchronized String getContent() throws IOException {
        return getContent(i -> i > 0);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(i -> i > 0 && i < 128);
    }
}