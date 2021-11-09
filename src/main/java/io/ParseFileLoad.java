package io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFileLoad {
    private final File file;

    public ParseFileLoad(File file) {
        this.file = file;
    }

    private synchronized String getContent(Predicate<Integer> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContent() {
        return getContent(i -> i > 0);
    }

    public synchronized String getContentWithoutUnicode() {
        return getContent(i -> i >= 0x80);
    }
}