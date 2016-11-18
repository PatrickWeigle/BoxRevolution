package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class CBox {

    public int id; 
    public Socket socket; 
    public DataInputStream in;
    public DataOutputStream out; 

    public CBox(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
        }
    }
    
    
    
}
