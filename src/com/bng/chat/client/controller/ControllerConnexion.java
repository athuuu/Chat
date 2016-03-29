package com.bng.chat.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.bng.chat.client.Model.ModelClient;
import com.bng.chat.client.Model.ModelConnexion;
import com.bng.chat.client.vue.VueClient;
import com.bng.chat.client.vue.VueCompte;
import com.bng.chat.client.vue.VueConnexion;

public class ControllerConnexion implements ActionListener{
	private VueConnexion vue ;
	private ModelConnexion model;
	
	public ControllerConnexion(VueConnexion vue) {
		this.vue = vue;
		try {
			this.model = new ModelConnexion();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(vue, "Erreur survenue lors de l'appel de modelconnexion");
		}
	}
	
	public ControllerConnexion(VueConnexion vue, ModelConnexion model){
		this.vue = vue;
		this.model = model;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.vue.getConnexion()){
			try {
				this.model = new ModelConnexion(this.vue.getUsername().getText(), new String(this.vue.getMdp().getPassword()), this.vue.getServeur().getText());
				if(this.model.verification() ){
					System.out.println("athentification effectué avec succes");
					this.vue.setVisible(false);
					new VueClient(new ModelClient(this.model.getClient()));
				}
				else{
					JOptionPane.showMessageDialog(vue, "Erreur sur l'authentification\nveuillez ressayer");
					this.vue.getUsername().setText("");
					this.vue.getMdp().setText("");
					this.model.close();
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(vue, "Erreur survenue lors de la construction du modelConnexion");
			}			
		}else if( e.getSource() == this.vue.getComptes()){
			new VueCompte(vue);			
			
		}
	}
	
}
