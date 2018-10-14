/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemachatp2p;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rafael
 */
public class Servidor extends Thread{
   private TelaChat tela;
   private String porta;

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
   
   

    public TelaChat getTela() {
        return tela;
    }

    public void setTela(TelaChat tela) {
        this.tela = tela;
    }
    
    @Override
    public void run(){
        try {
            this.iniciaServidor();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void iniciaServidor() throws IOException {
        System.out.println("Servidor iniciado");
        int portaInt= Integer.parseInt(this.porta);
        ServerSocket server = new ServerSocket(portaInt);
        while(true){
            Socket cliente = server.accept();
            InputStream is= cliente.getInputStream();
            Scanner entrada = new Scanner(is);
            String mensagem="";
            System.out.println("Processando mensagem cliente...");
            while(entrada.hasNextLine()){
                mensagem+=entrada.nextLine();
            }
            
            tela.trataMensagemServidor(mensagem + ";" + cliente.getRemoteSocketAddress());
            cliente.close();
            System.out.println("Entrada cliente");
            
            
        }
        
        

    }
    
    
}
