package com.luxcampus.Lection_11.CahtRoom;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class Client {
    private String name;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Client(String name, BufferedReader reader, BufferedWriter writer) {
        this.name = name;
        this.reader = reader;
        this.writer = writer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", reader=" + reader +
                ", writer=" + writer +
                '}';
    }
}
