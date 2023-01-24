package com.company;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCase1ForTemperature {
    public static Temperature obj = null;

    public static StringBuffer output = null;

    @BeforeAll
    public static void setup(){
        System.out.println("Doing setup");
        output = new StringBuffer();
    }
    @BeforeEach
    public void setupBeforeEach(){
        obj = new Temperature();
    }

    @Test
    @Order(1)
    public void RunTest1(){
        // the first entry is the wanted result, the last two entries are bad entries
        int[] int_array = new int[] {5,9,-5,8,-2,8,-5,-34,67,34,89,-43,12,47,-274,5527};
        for (int temp : int_array){
            obj.addTemperature(temp);
        }
        System.out.println("Result RunTest1: " + obj.getResult());
        if (-2 == obj.getResult()) {
            output.append("RunTest1 run successfully and correct;\n");
        } else {
            output.append("RunTest1 didn't run correct;\n");
        }
    }
    @Test
    @Order(2)
    public void RunTest2(){
        // the first entry is the wanted result, the last two entries are bad entries
        int[] int_array = new int[] {5,9,-5,8,-2,8,-5,-34,2,67,34,89,-43,12,47,-274,5527};
        for (int temp : int_array){
            obj.addTemperature(temp);
        }
        System.out.println("Result RunTest2: " + obj.getResult());
        if (2 == obj.getResult()) {
            output.append("RunTest2 run successfully and correct;\n");
        } else {
            output.append("RunTest2 didn't run correct;\n");
        }
    }
    @Test
    @Order(3)
    public void RunTest3(){
        // the first entry is the wanted result, the last two entries are bad entries
        int[] int_array = new int[] {-274,5527,23844,-238829};
        for (int temp : int_array){
            obj.addTemperature(temp);
        }
        System.out.println("Result RunTest3: " + obj.getResult());
        if (0 == obj.getResult()) {
            output.append("RunTest3 run successfully and correct;\n");
        } else {
            output.append("RunTest3 didn't run correct;\n");
        }
    }
    @Test
    @Order(4)
    public void RunTest4(){
        System.out.println("Result RunTest4: " + obj.getResult());
        if (0 == obj.getResult()) {
            output.append("RunTest4 run successfully and correct;\n");
        } else {
            output.append("RunTest4 didn't run correct;\n");
        }
    }

    @Test
    @Order(5)
    public void RemoveAllEntries() {
        obj.removeAllEntries();
        if (0 == obj.sizeOftemperatures()) {
            output.append("RemoveAllEntries run successfully and correct;\n");
        } else {
            output.append("RemoveAllEntries didn't run correct;\n");
        }
    }
    @AfterAll
    public static void Result(){
        System.out.println("There should be no Exceptions");
        System.out.println(output);
    }
}
