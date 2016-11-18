
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static int counter = 0; 
    private ArrayList<CBox> netPlayers = new ArrayList<CBox>(); 
    
    public Server() {
        ServerSocket server;
        DataInputStream in; 

        System.out.println("ServerSocket inicializado na porta 5000.");
        
        try {
            server = new ServerSocket(6000); 
            while (true) { 
                if(counter < 2){
                    Socket socket = server.accept(); 
                   
                    CBox aux = new CBox(counter, socket); 
                    System.out.println(" *** Player " + counter + " se conectou.");
                    counter++; 
                    netPlayers.add(aux); 
                    new Thread(new Server.EscutaCliente(aux)).start(); 
                    
                    if(counter == 2){ 
                        System.out.println("Servidor cheio. Nenhum cliente a mais poderá se conectar;\n----------------------");
                        encaminharParaTodos("start"); 
                    }
                }
            }
        } catch (Exception ex) {
        }
        
    }                                    
    

    private void encaminharParaTodos(String texto){
        try {
            for(CBox obj : netPlayers){
                obj.out.writeUTF(texto);
            }
        } catch (Exception e) {
        }
    }
    

    private class EscutaCliente implements Runnable {

        CBox np;

        public EscutaCliente(CBox np) {
            this.np = np;
        }

        @Override
        public void run() {
            String s = "";
            try {
                while (true) {
                    s = np.in.readUTF(); 
                    if(np.id == 0){ 
                        netPlayers.get(1).out.writeUTF(s); 
                    } else { 
                        netPlayers.get(0).out.writeUTF(s); 
                    }
                   System.out.println("id " + np.id + " :" + s);
                }
            } catch (Exception ex) {
            }
        }
    }
    
    
    public static void main(String args[]) {
        new Server(); 
    }
           
}
