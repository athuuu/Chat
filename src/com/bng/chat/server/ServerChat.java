package com.bng.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerChat {
	//le journalisateur
	final static Logger logger=Logger.getLogger("com.bng.chat.server");
	private ServerSocket serveur;
	private Socket client ;
	static final int PORT=9999;
	
	
	public ServerChat() throws IOException{
		this(PORT);
	}
	
	public ServerChat(int val) throws IOException{
		serveur = new ServerSocket(val);
		init();
	}
		
	public void init(){
		logger.info("Serveur démarré");
		while(true){
			try{
				client = serveur.accept();
				logger.fine("un nouveau client vient de se connecter au serveur "+client.getLocalSocketAddress());
				new Service(client);
			}catch(IOException e){}
		}
	}
	
	public static void main(String[] args) {
		try {
			new ServerChat();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
