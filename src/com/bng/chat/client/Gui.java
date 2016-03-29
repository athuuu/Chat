/**
 * La class ClientGUI represente l'interface graphique d'un utilisateur
 * cette classe contient un objet de client qui permet d'envoyer et de recevoir avec ce client
 * 
 */
package com.bng.chat.client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * 
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 2.0
 */

@SuppressWarnings("serial")
public class Gui extends JFrame {
    private String title = "Logiciel de discussion en ligne";
    private String pseudo = null;
    private SocketClient user;

    //private JFrame window = new JFrame(this.title);
    private JTextArea txtOutput = new JTextArea();
    private JTextField txtMessage = new JTextField();
    private JButton btnSend = new JButton("Envoyer");
    /**
     * Constructeur par defaut. Ce constructeur permet de creer l'interface et le dialog pour le pseudo
     * @author Mr Fall
     */
    public Gui() {
    	//this.setUser(user);
    	this.setTitle(title);
        this.createIHM();
        this.requestPseudo();
    }

    /**
     * Methode IHM, cette methode permet de creer l'interface graphique 
     * et d'ajouter les ecouteurs sur la fenetre, sur le champ de text et sur le bouton envoyer
     * @author Mr Fall
     */
    public void createIHM() {
    	this.setAlwaysOnTop(true);
        // Assemblage des composants
        JPanel panel = (JPanel)this.getContentPane();
        JScrollPane sclPane = new JScrollPane(txtOutput);
        panel.add(sclPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(this.txtMessage, BorderLayout.CENTER);
        southPanel.add(this.btnSend, BorderLayout.EAST);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Gestion des évènements
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                window_windowClosing(e);
            }
        });
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSend_actionPerformed(e);
            }
        });
	txtMessage.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent event) {
		if (event.getKeyChar() == '\n')
		    btnSend_actionPerformed(null);
	    }
	});

        // Initialisation des attributs
        this.txtOutput.setBackground(new Color(220,220,220));
        this.txtOutput.setEditable(false);
        this.setSize(500,400);
        this.setVisible(true);
        this.txtMessage.requestFocus();
    }
    /**
     * La methode requestPseud. Cette methode permet de saisir l'adresse ip du serveur et le pseudo
     * après avoir saisi ces informations, le titre de la fenetre est changée avec le pseudo
     * le client est initialisé si l'adresse est bonne sinon le une boite de dialogue est ouverte , affiche un message et arrete le programme.
     * @author Mr Fall, (modifier)
     */

    public void requestPseudo() {
         String ip_server = JOptionPane.showInputDialog(this, "L'adresse IP du serveur : ",this.title,  JOptionPane.OK_OPTION);
         this.pseudo = JOptionPane.showInputDialog(this, "Entrez votre pseudo : ",this.title,  JOptionPane.OK_OPTION);
         if (this.pseudo != null ){
			System.out.println("le pseudo est :"+pseudo);
			this.setTitle(pseudo);
			try {
				this.user=new SocketClient(ip_server, this.pseudo);
				new ProcessReceiver(this).start();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Erreur de creation de la socket client");
				System.exit(0);
			}
         }
		else  
        	System.exit(0);
    }

    /**
     * Cette Methode permet de mettre fin à notre fenetre pour cela, elle envoie le message fin au serveur leur indiquant la fin
     * de la transmission et elle deconnecte aussi la socket client
     * @param e
     */
    public void window_windowClosing(WindowEvent e) {
    	
    	try {
    		this.user.sendMessage("", "fin");
			this.user.deconnexion();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "Erreur de la fermeture de la socket");
		}
    	this.dispose();
    	System.exit(-1);
    }
    
    /**
     * Cette methode permet d'ajouter le texte saisi par l'utilisateur sur sa zone de texte 
     * elle permet aussi d'envoyer le message sur le reseau (au serveur)
     * @param e
     */
    
    public void btnSend_actionPerformed(ActionEvent e) {
    	String msg=this.txtMessage.getText();
    	if(msg!=null){
    		this.user.sendMessage(this.pseudo," \u2192 "+msg);
    		this.ajouterMessage(msg);
    		this.txtMessage.setText("");
    		this.txtMessage.requestFocus();
    	}
    	else
    		System.exit(0);
    }
    
    /**
     * Cette methode permet simplement ajouter un text dans la zone de texte de l'utilisateur
     * elle servira à la reception de nouveaux messages
     * @param text : message à afficher
     */
    public void ajouterMessage(String text){
    	this.txtOutput.append(text+"\n");
    }
   
    

	public SocketClient getUser() {
		return user;
	}

	
	public static void main(String[] args) {
         new Gui();
       
    }
}