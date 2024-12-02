package com.interview.notes.code.year.y2023.Oct23.test7;

import java.io.*;

class Superclass {
    int superField;

    Superclass(int superField) {
        this.superField = superField;
    }
}

class Subclass extends Superclass implements Serializable {
    int subField;

    Subclass(int superField, int subField) {
        super(superField);
        this.subField = subField;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        try {
            // Serialization
            Subclass obj = new Subclass(1, 2);

            // Serialize the object to a file
            FileOutputStream fileOut = new FileOutputStream("object.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();

            // Deserialization
            // Deserialize the object from the file
            FileInputStream fileIn = new FileInputStream("object.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Subclass deserializedObj = (Subclass) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("Superclass Field: " + deserializedObj.superField);
            System.out.println("Subclass Field: " + deserializedObj.subField);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
