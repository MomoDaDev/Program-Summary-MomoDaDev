package com.company;

import java.io.*;
import java.nio.*;

public class Kopiere {
    public static void test() throws IOException {
        InputStream IS1 = new FileInputStream("././files/SequenceInputStreamin1.txt");
        InputStream IS2 = new FileInputStream("././files/SequenceInputStreamin2.txt");
        SequenceInputStream sequenceInputStream = new SequenceInputStream(IS1, IS2);
        InputStreamReader inputStreamReader = new InputStreamReader(sequenceInputStream);
        BufferedReader bR = new BufferedReader(inputStreamReader);

        int line;
        while ((line = sequenceInputStream.read()) != -1) {
            byte[] data = ByteBuffer.allocate(4).putInt(line).array();
            CharBuffer cB = ByteBuffer.wrap(data).asCharBuffer();
            System.out.println(cB);
        }
        bR.close();
        inputStreamReader.close();
        sequenceInputStream.close();

        System.out.println("--------------------------------");

        InputStream IS11 = new FileInputStream("././files/SequenceInputStreamin1.txt");
        InputStream IS22 = new FileInputStream("././files/SequenceInputStreamin2.txt");
        SequenceInputStream sequenceInputStream1 = new SequenceInputStream(IS11, IS22);
        InputStreamReader inputStreamReader1 = new InputStreamReader(sequenceInputStream1);
        BufferedReader bR1 = new BufferedReader(inputStreamReader1);
        while (bR1.ready()) {
            System.out.println(bR1.readLine());
        }

        bR1.close();
        inputStreamReader1.close();
        sequenceInputStream1.close();
    }
}
