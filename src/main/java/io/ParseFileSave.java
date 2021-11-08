package io;

import java.io.*;

public final class ParseFileSave {
    private final File file;

    public ParseFileSave(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file));
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
