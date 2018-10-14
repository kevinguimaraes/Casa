package sistemachatp2p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import geral.Relogio;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaChat extends javax.swing.JFrame {

	final int  C_MSG = 0;
	final int  C_PORTA = 1;
	final int  C_NICKNAME = 2;
	final int  C_IP = 3;
	private JFrame frame;
	private JTextField tPorta;
	private JTextField tNickname;
	private JTextArea textAreaReceive;
	private JTextField tIP;
	private JTextField tPortaCliente;
	private JTextField tSend;
	static TelaChat window;
	ArrayList<Contato> contatos;
	private JTable tblContato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TelaChat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaChat() {
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		contatos = new ArrayList<Contato>();
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPortaServidor = new JLabel("Porta Servidor: ");
		lblPortaServidor.setBounds(10, 11, 92, 14);
		frame.getContentPane().add(lblPortaServidor);
		
		tPorta = new JTextField();
		tPorta.setBounds(112, 8, 103, 20);
		frame.getContentPane().add(tPorta);
		tPorta.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(222, 11, 81, 14);
		frame.getContentPane().add(lblNickname);
		
		tNickname = new JTextField();
		tNickname.setBounds(307, 8, 131, 20);
		frame.getContentPane().add(tNickname);
		tNickname.setColumns(10);
		
		JButton btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servidor servidor= new Servidor();
				servidor.setTela(window);
		        servidor.setPorta(tPorta.getText());
		        servidor.start();
			}
		});
		btnIniciarServidor.setBounds(448, 7, 144, 23);
		frame.getContentPane().add(btnIniciarServidor);
		
		textAreaReceive = new JTextArea();
		textAreaReceive.setEditable(false);
		textAreaReceive.setBounds(138, 50, 455, 233);
		frame.getContentPane().add(textAreaReceive);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(20, 305, 53, 20);
		frame.getContentPane().add(lblIp);
		
		tIP = new JTextField();
		tIP.setBounds(70, 305, 86, 20);
		frame.getContentPane().add(tIP);
		tIP.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(166, 308, 49, 14);
		frame.getContentPane().add(lblPorta);
		
		tPortaCliente = new JTextField();
		tPortaCliente.setBounds(217, 305, 86, 20);
		frame.getContentPane().add(tPortaCliente);
		tPortaCliente.setColumns(10);
		
		tSend = new JTextField();
		tSend.setBounds(10, 336, 582, 20);
		frame.getContentPane().add(tSend);
		tSend.setColumns(10);
		
		JButton bSend = new JButton("Enviar");
		bSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente c= new Cliente();
	            Relogio r = new Relogio();
	            String ip=tIP.getText();
	            String porta=tPortaCliente.getText();
	            String mensagem=tSend.getText();
	            textAreaReceive.append("\n\t\t\t"+r.getDate()+ " - " + mensagem);
	            mensagem += ";" + tPorta.getText() + ";" + tNickname.getText();
	        try {
	            c.iniciaCliente(ip, porta, mensagem);
	        } catch (IOException ex) {
	            Logger.getLogger(TelaChat.class.getName()).log(Level.SEVERE, null, ex);
	        }
			}
		});
		bSend.setBounds(10, 362, 582, 23);
		frame.getContentPane().add(bSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 50, 108, 233);
		
		frame.getContentPane().add(scrollPane);
		
		tblContato = new JTable();
		tblContato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = tblContato.getSelectedRow();
				tIP.setText(contatos.get(linha).getIP());
				tPortaCliente.setText(contatos.get(linha).getPorta());
				
			}
		});
		tblContato.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Contatos"
			}
		));
		scrollPane.setViewportView(tblContato);
	}
	
	 public void insereMensagemServidor(String mensagem){
	        this.textAreaReceive.append("\n"+mensagem);
	    }
	 
	 public void trataMensagemServidor(String mensagem) {
		 String msgs[] = mensagem.split(";");
		 String ip = msgs[C_IP].split(":")[0].split("/")[1];
		 Contato c = new Contato(msgs[C_NICKNAME],ip,msgs[C_PORTA]);
		 this.atualizarContatos(c);
		 
		 this.insereMensagemServidor(msgs[C_MSG]);
	 }
	 
	 protected void atualizarContatos(Contato c) {
		 	
		 if (!contatos.contains(c))
		 {
			 System.out.println("\n" + c.getNickname());
			 System.out.println(c.getIP());
			 System.out.println(c.getPorta());
			 contatos.add(c);
			System.out.println("NAO EXISTE AINDA"); 
		 }
		tblContato.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Contato"
				}
			));
		DefaultTableModel modelo = (DefaultTableModel) tblContato.getModel();
		
		for(Contato contato: contatos)
		{
			Object[] novaLinha = new Object[] {
					contato.getNickname(),
			};
			modelo.addRow(novaLinha);
		}
	}
}
