package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader path = new FileReader("././files/201015Messdaten4EHIF.csv");

        BufferedReader br = new BufferedReader(path);

        String currentline;
        ArrayList<Data> data = new ArrayList<Data>();
        ArrayList<ArrayList<Data>> segments = new ArrayList<ArrayList<Data>>();

        br.readLine(); //skips first line

        while ((currentline = br.readLine()) != null){
            String[] cra = currentline.split(";");
            data.add(new Data(Integer.parseInt(cra[0]), Integer.parseInt(cra[1]), Integer.parseInt(cra[2]), Integer.parseInt(cra[3]), Double.parseDouble(cra[4].replace(",", "."))));
        }
        br.close();

        data = (sortData(data));
        double min = getMin(data);
        double wert = (getMax(data) - min) / 10.0;
        int seg = 1;
        ArrayList<Data> currentsegment = new ArrayList<Data>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).result > (min + wert * seg)){
                segments.add(currentsegment);
                currentsegment = new ArrayList<Data>();
                currentsegment.add(data.get(i));
                seg++;
            } else{
                currentsegment.add(data.get(i));
            }
        }

        System.out.println("1. AverageOfAll: " + getAverage(data));

        System.out.println("2. LargestVector: " + getMax(data));

        FileWriter fwpath = new FileWriter("././files/Results.csv");


        fwpath.append("Segment");
        fwpath.append(";");
        fwpath.append("AverageOfBrightness");
        fwpath.append("\n");
        for (int i = 0; i < segments.size(); i++) {
            double average = getAverage(segments.get(i));
            fwpath.append(i + 1 + ";" + average);
            fwpath.append("\n");
            System.out.println("3. Segment " + (i + 1) + ": " + average);
        }
        fwpath.flush();
        fwpath.close();

        System.out.println("4. measuringpointtwice: " + checkiftwice(data));

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).toString());
        }

        System.out.println(segments.size());
    }

    public static boolean checkiftwice(ArrayList<Data> data){
        boolean b = false;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if(i != j){
                    if(data.get(i).x == data.get(j).x && data.get(i).y == data.get(j).y && data.get(i).z == data.get(j).z){
                        b = true;
                    }
                }
            }
        }
        return b;
    }

    public static double getAverage(ArrayList<Data> data){
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i).brightness;
        }
        return sum / data.size();
    }

    public static ArrayList<Data> sortData(ArrayList<Data> data){
        double d;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size() - 1; j++) {
                if(data.get(i).result < data.get(j).result){
                    d = data.get(i).result;
                    data.get(i).result = data.get(j).result;
                    data.get(j).result = d;
                }
            }
        }
        return data;
    }

    public static double getMax(ArrayList<Data> data){
        double max = Double.MIN_VALUE;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).result > max){
                max = data.get(i).result;
            }
        }
        return max;
    }
    public static double getMin(ArrayList<Data> data){
        double min = Double.MAX_VALUE;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).result < min){
                min = data.get(i).result;
            }
        }
        return min;
    }
}
