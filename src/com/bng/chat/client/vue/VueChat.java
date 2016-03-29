package com.bng.chat.client.vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import com.bng.chat.client.Model.ModelRoom;




public class VueChat extends JFrame{
	private static final long serialVersionUID = 2771823025938512831L;
	private JToolBar outils;
	private int nbRoom=0;
	public JButton 
		send, //bouton d'envoi 
		quitter, //bouton d'arret
		addContact, //bouton connexion
		//deconnexion, //bouton pour se deconnecter 
		icone, //bouton pour charger une image out icone
		fichier; //bouton pour charger un fichier
	private JTextArea zoneMsg;
	private Onglet tab;
	public JTextField zoneTexte;
	private JList<String> contacts;
	private Room discussion;
	
	public VueChat(){
		discussion = new Room("Room 1");
		init();
		ihm();	
		
	}
	
	protected void init(){
		this.setTitle("Chat Room");
		this.setSize(605, 540);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		tab = new Onglet();
		outils = new JToolBar();
		zoneTexte = new JTextField(30); 
		zoneMsg = discussion;//new JTextArea(25, 20);
		send = new JButton("Envoyer");
		quitter = new JButton();
		addContact = new JButton();
		icone = new JButton();
		fichier = new JButton();
		contacts = new JList<String>();
		
	}
	
	protected void ihm(){
		ImageIcon img = new ImageIcon(VueChat.class.getResource("add_contact.png"));
		addContact.setToolTipText("ajouter un utilisateur au room");
		addContact.setIcon(img);
		outils.add(addContact);
		quitter.setToolTipText("Quitter le room");
		ImageIcon img2 = new ImageIcon(VueChat.class.getResource("end_button.png"));
		quitter.setIcon(img2);
		outils.add(quitter);
		//deconnexion.setToolTipText("se deconnecter au serveur");
		//outils.add(deconnexion);
		zoneTexte.setFont(new Font("ubuntu", Font.BOLD, 15));
		contacts.setListData(new String[]{"bng", "sane", "sine"});
		zoneMsg.setEditable(false);
		JPanel pan = new JPanel();
		ajoutComponentIhm();
		add(outils, BorderLayout.PAGE_START);
		pan.add(zoneTexte);
		ImageIcon img3 = new ImageIcon(VueChat.class.getResource("blush.png"));
		icone.setIcon(img3);
		icone.setToolTipText("Selectionner et envoyer un emoticon");
		pan.add(icone);
		ImageIcon img4 = new ImageIcon(VueChat.class.getResource("folder_up.png"));
		fichier.setIcon(img4);
		fichier.setToolTipText("Selectionner et envoyer un fichier");
		pan.add(fichier);
		pan.add(send);
		add(pan, BorderLayout.SOUTH);
		this.setVisible(true);

	}
	
	protected void ajoutComponentIhm(){
		JPanel pano = new JPanel(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill=GridBagConstraints.BOTH;
		con.gridx=0; con.gridy=0;
		con.gridwidth=10; con.gridheight=10;
		con.weightx=50;con.weighty=10;
		pano.add(tab, con);
		con.gridx=10; con.gridy=1;
		con.gridwidth=10; con.gridheight=10;
		con.weightx=25;con.weighty=10;
		pano.add(new JScrollPane(contacts), con);
		tab.addTab(this.discussion.getNom(),this.zoneMsg, nbRoom);
		add(pano, BorderLayout.CENTER);
	}
	
	 
	
	public static void main(String [] a){
		new VueChat();
	}


	
	
	
}
