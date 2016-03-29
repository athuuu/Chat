package com.bng.chat.client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.bng.chat.client.Model.ModelClient;
import com.bng.chat.client.vue.VueClient;
import com.bng.chat.client.vue.VueConnexion;

public class ControllerClient implements ActionListener {
	private ModelClient model;
	private VueClient vue;
	
	public ControllerClient(VueClient vue){
		this.vue = vue;
		this.model = this.vue.getModel();
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
			int i=JOptionPane.showConfirmDialog(vue, "Voulez vous vraiment quittez ?");
			if(i==1) System.exit(0);
		}
		else if(e.getSource()==this.vue.getCreerRoom()){
			
		}
		else if(e.getSource()==this.vue.getAddContact()){
			
		}
		
	}

}
