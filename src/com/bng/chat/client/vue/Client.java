package com.bng.chat.client.vue;

import java.io.IOException;
import com.bng.chat.client.SocketClient;

public class Client {
	
	private SocketClient socketClient;
	private String pseudo;
	private String mdp;

	public Client(){
		this("chat", "passer");
	}
	public Client(String nom, String mdp){
		this.pseudo = nom;
		this.mdp = mdp;
		try {
			this.socketClient = new SocketClient(pseudo, mdp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Client(String add, String name, String mdp){
		this.pseudo=name;
		this.mdp = mdp;
		try {
			this.socketClient = new SocketClient(add, pseudo, mdp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SocketClient getSocketClient() {
		return socketClient;
	}
	public void setSocketClient(SocketClient socketClient) {
		this.socketClient = socketClient;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
