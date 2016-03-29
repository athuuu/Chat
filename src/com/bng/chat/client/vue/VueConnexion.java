package com.bng.chat.client.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.bng.chat.client.controller.ControllerConnexion;

public class VueConnexion extends JFrame{
 
	private static final long serialVersionUID = 1L;
	private JButton connexion, comptes;
	private JTextField username, serveur;
	private JPasswordField mdp;
	
	public VueConnexion(){
		super("BNGchat");
		init();
		ihm();
	}
	
	protected void init(){
		setSize(350, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControllerConnexion control = new ControllerConnexion(this);
		connexion = new JButton("Connexion");connexion.addActionListener(control);
		comptes = new JButton("Comptes");comptes.addActionListener(control);
		username = new JTextField(15);username.setFont(new Font("ubuntu", Font.BOLD, 13));
		serveur = new JTextField(15);serveur.setFont(new Font("ubuntu", Font.BOLD, 13));
		mdp = new JPasswordField(15);mdp.setFont(new Font("ubuntu", Font.BOLD, 13));
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	protected void ihm(){
		setLayout(new GridLayout(2, 1));
		add(new JLabel("Bienvenue"));
		JPanel pano = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 20));
		JPanel pano1 = new JPanel();
		JPanel pano2 = new JPanel(new BorderLayout());
		pano.add(new JLabel("pseudo          :"));
		pano.add(username);
		pano.add(new JLabel ("Mot de passe:"));
		pano.add(mdp);
		pano.add(new JLabel("IP Serveur      :") );
		pano.add(serveur);
		pano1.add(comptes);
		pano1.add(connexion);
		pano2.add(pano);
		pano2.add(pano1, BorderLayout.SOUTH);
		add(pano2);
	}
	
	public void setListener(ActionListener action){
		connexion.addActionListener(action);
		comptes.addActionListener(action);
	}
	
	
	public JButton getConnexion() {
		return connexion;
	}

	public void setConnexion(JButton connexion) {
		this.connexion = connexion;
	}

	public JButton getComptes() {
		return comptes;
	}

	public void setComptes(JButton comptes) {
		this.comptes = comptes;
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JTextField getServeur() {
		return serveur;
	}

	public void setServeur(JTextField serveur) {
		this.serveur = serveur;
	}

	public JPasswordField getMdp() {
		return mdp;
	}

	public void setMdp(JPasswordField mdp) {
		this.mdp = mdp;
	}

	public static void main(String[] a){
		new VueConnexion();
	}
}
