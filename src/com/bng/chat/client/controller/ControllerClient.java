package com.bng.chat.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;

import com.bng.chat.client.Model.ModelClient;
import com.bng.chat.client.vue.VueAddClient;
import com.bng.chat.client.vue.VueClient;
import com.bng.chat.client.vue.VueConnexion;

public class ControllerClient implements ActionListener {
	private ModelClient model;
	private VueClient vue;
	
	public ControllerClient(VueClient vue){
		this.vue = vue;
		this.model = this.vue.getModel();
		this.vue.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosed(WindowEvent e) {
				int i=JOptionPane.showConfirmDialog(ControllerClient.this.vue, "Voulez vous vraiment quittez ?", null, JOptionPane.YES_NO_OPTION);
				
				if(i==0) {
					try {
						ControllerClient.this.model.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(ControllerClient.this.vue, "erreur sur la fermeture");
					}
					System.exit(0);
				}
			}
			
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.vue.getDeconnexion()){
			try {
				this.model.close();
				this.vue.setVisible(false);
				new VueConnexion();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(vue, "erreur sur la fermeture");
			}
		}
		else if(e.getSource()==this.vue.getQuitter()){
			int i=JOptionPane.showConfirmDialog(vue, "Voulez vous vraiment quittez ?", null, JOptionPane.YES_NO_OPTION);
			
			if(i==0) {
				try {
					this.model.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(vue, "erreur sur la fermeture");
				}
				System.exit(0);
			}
		}
		else if(e.getSource()==this.vue.getCreerRoom()){
			String nomRoom = JOptionPane.showInputDialog(this.vue, "Entrez le nom du Salon");
			try {
				if(this.model.creerRoom(nomRoom)){
					JOptionPane.showMessageDialog(vue, "Salon creer avec succes");
					this.vue.setDiscussion(new JList<String>(this.model.listeRoom()));
					this.vue.ihm();
				}else{
					JOptionPane.showMessageDialog(vue, "erreur sur la creation du salon");
				}
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(vue, "erreur survenue lors de la creation du room");
			}
		}
		else if(e.getSource()==this.vue.getAddContact()){
			String pseudo = JOptionPane.showInputDialog(this.vue, "Entrez le pseudo de la personne");
			try {
				if(this.model.ajouterContact(pseudo)){
					JOptionPane.showMessageDialog(vue, "Vous être maintenant ami avec"+pseudo);
					this.vue.setContacts(new JList<String>(this.model.listeContact()));
					this.vue.ihm();
				}else{
					JOptionPane.showMessageDialog(vue, "erreur sur l'ajout au contact");
				}
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(vue, "erreur survenue lors de l'ajout du contact");
			}
		}
		else if(e.getSource()==this.vue.getAddContactRoom()){
			new VueAddClient(this.vue, this.model).setVisible(true);
		}
		else if(e.getSource()==this.vue.getSuppContact()){
			String pseudo = JOptionPane.showInputDialog(this.vue, "Entrez le pseudo de la personne");
			try {
				if(this.model.supprimerContact(pseudo)){
					JOptionPane.showMessageDialog(vue, "Suppression effectuée avec succes ");
					this.vue.setContacts(new JList<String>(this.model.listeContact()));
					this.vue.ihm();
				}else{
					JOptionPane.showMessageDialog(vue, "erreur sur l'ajout au contact");
				}
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(vue, "erreur survenue lors de l'ajout du contact");
			}
		}
		
	}
	

}
