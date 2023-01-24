package com.company;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class KopiereBytesV2 {
    public static void test() throws IOException {
        CharArrayReader in = null;
        CharArrayWriter out = null;

        try{
            char[] chararray = new char[] { 'a', 'b', 'c' };
            in = new CharArrayReader(chararray);
            out = new CharArrayWriter(100);

            for (int i = 0; i < chararray.length; i++) {
                out.write(in.read());
            }
            System.out.println(out.toString());
        } finally{
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
