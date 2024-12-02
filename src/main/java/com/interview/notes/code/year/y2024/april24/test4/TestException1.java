package com.interview.notes.code.year.y2024.april24.test4;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException1 {
    public static void main(String[] args) {
        try {
            go();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void go() throws IOException, JAXBException, FileNotFoundException {
        // Implementation that might throw IOException, JAXBException, or FileNotFoundException
    }
}
