package com.bng.chat.client.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import com.bng.chat.client.Model.ModelClient;
import com.bng.chat.client.controller.ControllerClient;

public class VueClient extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> contacts;
	private JList<String> discussion;
	private JTabbedPane tab;
	private JComboBox<String> status;
	private ModelClient model;
	private JMenuItem deconnexion;
	private JMenuItem quitter;
	private JMenuItem addContact;
	private JMenuItem creerRoom;
	
	
	public VueClient(ModelClient c){
		this.model = c;
		init();
		ihm();
		makeMenu();
		addListenerAuMenu();
		
	}
	public void init(){
		setTitle("Application Chat");
		setSize(300, 550);
		
		try {
			String[] liste1 = this.model.listeContact();
			String[] liste2 = this.model.listeRoom();
			if(liste1.length !=0 && liste2.length!=0 ){
				contacts = new JList<String>(liste1);
				discussion = new JList<String>(liste2);
			}else{
				contacts = new JList<String>();
				discussion = new JList<String>();
			}
		} catch (IOException e) {
			
		}
		
		tab = new JTabbedPane(JTabbedPane.BOTTOM);
		status = new JComboBox<String>(new String[]{
				"connecté",
				"absent",
				"invisible",
				"déconnecté"				
		});	
	}
	
	public void ihm(){
		JPanel pano = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon(new File("").getAbsolutePath()+"/images/48x48/business_user.png"));
		img.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.BLUE));
		pano.add(img);
		pano.add(new JLabel("BNG"));
		pano.add(status);
		tab.addTab("Contacts", new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/user.png"), new JScrollPane(contacts));
		tab.addTab("Salon de discussion", new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/comments.png"), new JScrollPane(discussion));
		add(pano, BorderLayout.BEFORE_FIRST_LINE);
		add(tab);
		setVisible(true);
	}
	public void makeMenu(){
		JMenuBar bar = new JMenuBar();
		JMenu Fichier = new JMenu("Fichier");Fichier.setMnemonic('F');
		JMenu Contact = new JMenu("Contact");Fichier.setMnemonic('C');
		JMenu Discussion = new JMenu("Discussion");Fichier.setMnemonic('D');
		
		//Contruction du menu Fichier
		deconnexion = new JMenuItem("Deconnexion", 'd');
		deconnexion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.ALT_MASK));
		deconnexion.setIcon(new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/security.png"));
		quitter = new JMenuItem("Quitter", 'q');
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
		quitter.setIcon(new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/delete.png"));
		Fichier.add(deconnexion);
		Fichier.add(quitter);
		
		//construction du menu Contact
		addContact = new JMenuItem("add Contact", 'a');
		addContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.ALT_MASK));
		addContact.setIcon(new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/users.png"));
		Contact.add(addContact);
		
		//Construction du menu discussion
		creerRoom = new JMenuItem("Creer Salon", 'c');
		creerRoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		creerRoom.setIcon(new ImageIcon(new File("").getAbsolutePath()+"/images/16x16/comment.png"));
		Discussion.add(creerRoom);
		
		//ajout des composants dans la barre
		bar.add(Fichier);
		bar.add(Contact);
		bar.add(Discussion);
		
		//ajout de la barre dans le frame
		this.setJMenuBar(bar);
	}
	public void addListenerAuMenu(){
		ControllerClient c = new ControllerClient(this);
		deconnexion.addActionListener(c);
		quitter.addActionListener(c);
		creerRoom.addActionListener(c);
		addContact.addActionListener(c);
	}
	
	public JComboBox<String> getStatus() {
		return status;
	}
	public void setStatus(JComboBox<String> status) {
		this.status = status;
	}
	public JList<String> getContacts() {
		return contacts;
	}
	public void setContacts(JList<String> contacts) {
		this.contacts = contacts;
	}
	public JList<String> getDiscussion() {
		return discussion;
	}
	public void setDiscussion(JList<String> discussion) {
		this.discussion = discussion;
	}
	public ModelClient getModel() {
		return model;
	}
	public JMenuItem getDeconnexion() {
		return deconnexion;
	}
	public JMenuItem getQuitter() {
		return quitter;
	}
	public JMenuItem getAddContact() {
		return addContact;
	}
	public JMenuItem getCreerRoom() {
		return creerRoom;
	}
	
	
}
