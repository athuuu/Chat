package com.bng.chat.client.Model;

import java.io.Closeable;
import java.io.IOException;


import com.bng.chat.client.vue.Client;

public class ModelClient implements Closeable{

	private Client client;
	
	public ModelClient(Client c){
		this.client = c;
	}
	
	public String[] listeContact() throws IOException{
		this.client.getSocketClient().sendMessage("contact:", "liste");		
		return this.client.getSocketClient().receiveMessage().split(" ");
		
		 
	}
	
	public String[] listeRoom() throws IOException{
		this.client.getSocketClient().sendMessage("room:","liste");		
		return this.client.getSocketClient().receiveMessage().split(" ");
		
		
	}
	
	public boolean ajouterContact(String nomContact) throws IOException{
		this.client.getSocketClient().sendMessage("contact:ajout:", nomContact);
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
	}
	
	public boolean creerRoom(String nomRoom) throws IOException{
		this.client.getSocketClient().sendMessage("room:ajout:", nomRoom);
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
		
	}
	
	public boolean quitterRoom(String nomRoom) throws IOException{
		this.client.getSocketClient().sendMessage("room:quitter:", nomRoom);
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
		
	}
	
	public boolean supprimerContact(String nomContact) throws IOException{
		this.client.getSocketClient().sendMessage("contact:supprimer:", nomContact);
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
		
	}
	
	public Client getClient() {
		return client;
	}

	public void close() throws IOException {
		this.client.getSocketClient().deconnexion();
		
	}
	

}
