package client;

import java.io.IOException; 
import java.io.PrintStream; 
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {
    //IP do servidor
    private String ipServidor;
    //porta do servidor
    private int porta;
    private String msg ="";
    public String enviaMSG(String msgR) {
        //lê o ip e a porta do servidor
        //ipServidor = JOptionPane.showInputDialog(null, "Insira o IP do servidor","192.168.25.34");
        ipServidor = "192.168.2.232";
        //porta      = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a porta do servidor","7000"));
        porta = 7000;
        //Declaro o Socket cliente
        Socket s = null;
        //Declaro a Stream de saida de dados
        PrintStream ps = null;
        try {
            //Cria o socket com o recurso desejado na porta especificada!
            s = new Socket(ipServidor, porta);
            //Cria a Stream de saida de dados
            ps = new PrintStream(s.getOutputStream());
            //String msg = JOptionPane.showInputDialog(null,"Cliente","Insira sua mensagem aqui");
            msg = msgR;
            //Imprime uma linha para a stream de saída de dados
            ps.println(msg);
        } catch (IOException e) {
            System.out.println("Algum problema ocorreu ao criar ou enviar dados pelo Socket.");
            e.printStackTrace();
        } finally {
            try {
                //Encerra o socket cliente
                s.close();
            } catch (Exception e) {
                System.out.println("Algum problema ocorreu ao finalizar o socket");
            }
        }
        return msg;
    }
    
    public void tratamento(){
        
    }
}
