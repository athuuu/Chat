package com.bng.chat.client.Model;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.bng.chat.client.SocketClient;
import com.bng.chat.client.vue.Client;

public class ModelConnexion implements Closeable{
	private String adresseServer;
	private Client client;	
	
	public ModelConnexion()  throws IOException{
		//this("chat", "passer", "127.0.0.1");
		this.client = null;
		this.adresseServer = null;
	}
	public ModelConnexion(String p, String m, String add) throws IOException{
		this.adresseServer = add;
		this.client  = new Client(this.adresseServer, p, m);
		System.out.println("connexion établie avec le serveur");
	}
	
	public boolean verification() throws IOException{
		this.client.getSocketClient().sendMessage("verification:",this.client.getPseudo()+":"+this.client.getMdp());
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
	}
	
	
	public boolean inscription(String p, String m, String add) throws IOException{
		this.client = new Client(add, p, m);
		this.client.getSocketClient().sendMessage("inscription", ":"+p+":"+m);
		return this.client.getSocketClient().receiveMessage().toLowerCase().equals("ok");
	}
	
	public void close() throws IOException {
		this.client.getSocketClient().deconnexion();
	}
	public Client getClient() {
		return client;
	}
		
	

}
