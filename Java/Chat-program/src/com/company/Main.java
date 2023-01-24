package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static String username = "Moritz";
    public static String zwischenablage = "";
    public static void main(String[] args) {
        Main t = new Main();
        t.starteServer();
    }

    public void starteServer(){
        try {
            //Starte den Server
            ServerSocket server = new ServerSocket(3649);

            //Gibt den Hoast und die IP auf der console aus.
            InetAddress adresse = InetAddress.getLocalHost();
            System.out.println("Server gestartet \n" +
                    "Server auf IP:  " +  adresse.getHostAddress() + adresse.getHostName());

            serverRun(server);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    //Die Methode des Servers
    public void serverRun(ServerSocket server) throws IOException{
        //Der Server läuft und bekommt zum Start den eben erstellten Socket übergeben.
        while(true){
            //Die accept Methode wird aufgerufen.
            //Der  Server wartet auf eingehende Verbindungen.
            //Wenn eine verbindung reinkommt wird dann ein neuer Socket erzeugt.
            Socket client = server.accept();

            String clientAdresse;
            clientAdresse = client.getInetAddress().getHostAddress() + client.getInetAddress().getHostName();
            System.out.println(clientAdresse);

            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

                PrintWriter output = new PrintWriter(client.getOutputStream());
                //Diese Endlosschleife ist dazu da jede anfrage der Clients anzunemen und zurück zu senden.
                //Eine Empfangene Nachricht wird in den Zwischenspeicher gelegt.
                //Anschliessend wird dieser String mit dem Wort "Antwort versehen" und zurück gesendet.
                Thread check = new Thread(new Reader());
                check.start();

                Scanner scanner = new Scanner(System.in);
                while (true){
                    System.out.print("You: ");
                    String text = scanner.next();
                    zwischenablage += username + ": " + text + "\n";
                }

            } catch (Exception e) {

            }
        }

    }
}