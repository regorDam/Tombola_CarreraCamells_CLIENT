
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * █║▌│ █│║▌ ║││█║▌ │║║█║ 
 * Author : Regør [★] 
 * Who in Black Byte 
 * Program Tombola Carrera Camells V.1
 */
public class CClient {

    private Socket client = null;
    private ObjectOutputStream sortida = null;
    private ObjectInputStream entrada = null;
    private String HOST = null;
    private String misatge = null;
    private int PORT = 0;

    public CClient() {
        conexio();
        obtenirFlux();
        protocol();
    }

    private void conexio() {
        try {
            HOST = "172.16.253.48";
            PORT = Integer.parseInt("4444");
            System.out.println("Intentant conectar amb " + HOST + ":" + PORT);
            client = new Socket(HOST, PORT);
            System.out.println("Conectat a " + client.getInetAddress().getHostName());
        } catch (IOException ex) {
            System.out.println("Error Host desconegut");
        }
    }

    private void obtenirFlux() {
        try {
            sortida = new ObjectOutputStream(client.getOutputStream());
            sortida.flush();
            entrada = new ObjectInputStream(client.getInputStream());
            System.out.println("S'han rebut els flux de ·E/S");
        } catch (IOException ex) {
            System.out.println("Error al obtenir el flux de E/S");
        }
    }

    private void enviarDades(String msg) {
        try {
            sortida.writeObject(msg);
            sortida.flush();
        } catch (IOException e) {
            System.err.println("error d'escritura d'objecte");
        }
    }

    private void protocol() {
        try {
            String msg = (String) entrada.readObject();
            System.out.println("<--" + msg);
            while (true) {
                //!((String) entrada.readObject()).substring(0, 5).equals("ByeBye")
                System.out.println("Esperant torn...");
                obtenirFlux();
                msg = (String) entrada.readObject();
                System.out.println("<--" + msg);
                if(msg.substring(0, 5).equals("ByeBye")){
                    break;
                }
                enviarDades(cLlegir.dato());
                /*
                obtenirFlux();
                msg = (String) entrada.readObject();
                System.out.println(msg);
                        */
                
            }
            client.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("THE F*******K HOLLY SHIIT "+ex.toString());
        }
    }

    public static void main(String[] args) {
        new CClient();
    }

}
