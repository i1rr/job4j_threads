package io;

import java.io.*;

public final class ParseFileSave {
//- Методы getContent написаны в стиле копипаста.
// Нужно применить шаблон стратегия. content(Predicate<Character> filter)
//
//3. Загрузите код в репозиторий. Оставьте ссылку на коммит.
//
//4. Переведите ответственного на Петра Арсентьева.

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
