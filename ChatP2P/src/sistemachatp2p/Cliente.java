/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemachatp2p;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import geral.Relogio;
/**
 *
 * @author kevin
 */
public class Cliente extends Thread {
    
   
    
    public void iniciaCliente(String ip, String porta, String mensagem) throws IOException{
        System.out.println("Cliente iniciado");
        
        Socket cliente = new Socket(ip,Integer.parseInt(porta));
        OutputStream out=cliente.getOutputStream();
        
        PrintStream saida = new PrintStream(out); 
        Relogio r = new Relogio();
        
        String horarioEnvio = r.getDate();
        
        saida.println(horarioEnvio +": " + mensagem);
        saida.close();
        
    }
}
