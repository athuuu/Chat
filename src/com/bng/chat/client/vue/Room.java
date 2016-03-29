package com.bng.chat.client.vue;

import java.util.Vector;

import javax.swing.JTextArea;

public class Room extends JTextArea {
	
	private String nom;	
	public Room(){
		super(25, 20);
		this.nom ="room";
	}
	public Room(String nom){
		super(25, 20);
		this.nom= nom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	

}
