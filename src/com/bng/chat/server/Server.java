/** Class serveur represente un serveur TCP multithread. Le serveur demarre un thread à chaque nouvelle connexion
 *  Par defaut le nombre total d'utilisateur est de 100
 */
package com.bng.chat.server;

/**
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 2.0
 * @since 16/02/2016
 */
import java.net.*;
import java.io.*;

public class Server {
	static final int PORT=9999;
	static final int NBUSER=100;
	private User clients;
	private ServerSocket serveur;
	private Socket client ;
	//private FileWriter BdUser;
	
	/**
	 * Creer un serveur ecoutant dans le port par defaut 6789 
	 * @throws IOException
	 */
	public Server() throws IOException{
		this(PORT);
	}
	
	/**
	 * Creer un serveur avec un port spécifique . Cette methode creer aussi le tableau d'utilisateurs
	 * @param port
	 * @throws IOException
	 */
	public Server(int port) throws IOException{
		serveur=new ServerSocket(port, NBUSER);
		this.clients=new User(NBUSER);
		this.init();
	}
	/**
	 * methode initialisation du service
	 * cette methode tourne à l'infinie et attend une connexion d'un utilisateur . A chaque utilisateur, elle enrégistre le client dans 
	 * le tableau clients et dans le fichier et initialise le thread pour le service du client .
	 * 
	 */
	public void init(){
		System.out.println("Le server est lancé");
		while(true){
			try {
				client=serveur.accept();
				//saveclient(client);
				this.clients.add(client);
				System.out.println("le client "+client.getInetAddress()+" vient de se connecter au serveur");
				new ProcessClient(client).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * cette methode permet d'enrégistrer un client dans un fichier BdUser.txt
	 * @param client
	 * @throws IOException
	 */
	/*public void saveclient(Socket client) throws IOException{
		BdUser=new FileWriter(new File("BdUser.txt"), true);
		String pseudo=new BufferedReader(new InputStreamReader(client.getInputStream())).readLine()+" "+client.getInetAddress().getHostAddress()+" "+client.getPort()+"\n";
		BdUser.write(pseudo);
		BdUser.flush();
		BdUser.close();
	}*/
	
	/**
	 * cette methode permet de supprimer un client qui se trouve dans le fichier
	 * @param socket
	 * @throws IOException
	 */
	/*
	public void supprimerclient(Socket socket) throws IOException{
		BufferedReader src=new BufferedReader(new FileReader(new File("BdUser.txt")));
		FileWriter dst=new FileWriter(new File("dst.txt"), true);
		String text=src.readLine();
		String[] line;
		while(text!=null){
			System.out.println(text);
			line=text.split(" ");
			if(!line[1].equals(socket.getInetAddress().getHostAddress()) && !line[2].equals(socket.getPort())){
				dst.write(text);
				dst.flush();
			}
			text=src.readLine();
		}
		src.close();
		dst.close();
		new File("BdUser.txt").delete();
		new File("dst.txt").renameTo(new File("BdUser.txt"));
		
	}*/
	/**
	 * Class proccessus client
	 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
	 *
	 */
	
	class ProcessClient extends Thread{
		private Socket client;
		private BufferedReader in; 
		private Socket[] clientsconnectes;
		
		/**
		 * creation d'un client avec son socket et son flux d'entree
		 * @param client
		 */
		public ProcessClient(Socket client){
			this.client=client;
			
			
			 try {
				in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
				
			} catch (IOException e) {
				System.out.println("Erreur de création de flux d'entrée et de sortie");
			}
			
		}
		/**
		 * la methode run du thread
		 */
		public void run(){
			//this.clientsconnectes=clients.getClientsEnLigne(this.client);
			String msg="ifall";
			this.sendMessageToClients("un client vient de se connecter");
			while(!msg.toUpperCase().equals("FIN")){
				try {
					in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
					msg=in.readLine();
					this.sendMessageToClients(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			this.sendMessageToClients("un client vient de se déconnecter");
			clients.delete(client);
			
			try {
				//supprimerclient(client);
				client.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		/**
		 * envoyer un message à tous les clients connecté au serveur
		 * @param msg
		 */
		public void sendMessageToClients(String msg){
			clientsconnectes=clients.getClientsEnLigne(this.client);
			for(int i=0; i<clientsconnectes.length; i++){
				try {
					PrintStream send = new PrintStream(clientsconnectes[i].getOutputStream());
					send.print(msg+"\n");
					send.flush();
					//send.close();
				} catch (IOException e){
					System.out.println("probleme avec l'io");
					//e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			System.out.println("Erreur de demarrage du serveur");
		}
	}

}
