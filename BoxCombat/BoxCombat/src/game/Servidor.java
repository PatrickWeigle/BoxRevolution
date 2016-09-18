package game;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket;
import javax.swing.JOptionPane;

public class Servidor extends Thread{

    private static Servidor instance = null;
    
    
    
    //porta em que vamos estabelecer a comunicação
    static int porta;
    public void run() {
    //    lê a porta
        porta = 7000;
      //  declaro o ServerSocket
        ServerSocket serv = null;
       // declaro o Socket de comunicação
        Socket s = null;
       // declaro o leitor para a entrada de dados
        BufferedReader entrada = null;
        try {
  //          cria o ServerSocket na porta 7000 se estiver disponível
            serv = new ServerSocket(porta);
//            Aguarda uma conexão na porta especificada e retorna o socket que irá comunicar com o
            System.out.println("Servidor escutando na porta "+porta+"...");
            s = serv.accept();
  //          cria um BufferedReader para o canal da stream de entrada de dados do socket s
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            aguarda por algum dado e imprime a linha recebida
            String dados = entrada.readLine();
            while (dados.indexOf("sair") == -1){
                if(dados != null){			
                  //  exibe a mensagem recebida pelo client
                    //JOptionPane.showMessageDialog(null, dados, "Servidor - Mensagem recebida", JOptionPane.WARNING_MESSAGE);
                    //ChatMSN msn = ChatMSN.getInstance();
                   
                    //msn.setConv(dados);
                    //msn.atualizarChat();
                   
                }
                //finaliza a conexão do Socket s
                s.close();
                //Estabelece novamente o ServerSocket
                s = serv.accept();
                //BufferedReader para o canal da stream de entrada de dados do socket s
                entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //Aguarda por algum dado e imprime a linha recebida
                dados = entrada.readLine();
            }
        } catch (IOException e) {
            //Imprime uma notificação na saída padrão caso haja algo errado.
            e.printStackTrace();
        } finally {
            try {
                //Encerra o socket de comunicação
                s.close();
                //Encerra o ServerSocket
                serv.close();
            } catch (IOException e) {
                //Imprime uma notificação na saída padrão caso haja algo errado.
                e.printStackTrace();
            }
        } }
}
