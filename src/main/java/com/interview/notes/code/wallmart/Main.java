package com.interview.notes.code.wallmart;

import java.io.IOException;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) {
        Main a = new Main();
        try {
            a.method1();
            a.method2();
            System.out.println("a");
        } // insert code here so that program will compile and produce the output: b
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            System.out.println("c");
        }
    }

    void method2() throws SQLException {
        throw new SQLException();
    }

    void method1() throws IOException {
        throw new IOException();
    }
}
