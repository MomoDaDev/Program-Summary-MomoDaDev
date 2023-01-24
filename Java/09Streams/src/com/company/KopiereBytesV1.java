package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KopiereBytesV1 {
    public static void test() throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        int count=0;
        try {
            in = new FileInputStream("././files/Streamtestin.txt");
            out = new FileOutputStream("././files/Streamtestout.txt");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
                count++;
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("Zahl der Bytes " + count);
    }
}
