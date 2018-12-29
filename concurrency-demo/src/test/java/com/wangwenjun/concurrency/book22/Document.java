package com.wangwenjun.concurrency.book22;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <B>概要说明：</B><BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018年12月29日 上午 11:54
 */
public class Document {

    private boolean changed = false;

    private List<String> content = new ArrayList<>();

    private final FileWriter writer;

    private static AutoSaveThread autoSaveThread;

    private Document(String documentPath, String documentName) throws IOException {

        this.writer = new FileWriter(new File(documentPath, documentName));
    }


    public static Document create(String documentPath, String documentName) throws IOException {

        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }


    public void edit(String context) {

        synchronized (this) {
            this.content.add(context);
            this.changed = true;
        }
    }

    public void close() throws IOException {

        autoSaveThread.interrupt();
        writer.close();
    }

    public void save() throws IOException {

        synchronized (this) {
            if (!changed) {
                return;
            }

            Optional.of(Thread.currentThread() + " execute the save action").ifPresent(System.out::println);
            for (String cacheLine : content) {
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }

            this.writer.flush();
            this.changed = false;
            this.content.clear();
        }

    }


}
