/**
 * La class client represente la socket client. C'est dans cette classe que la connexion entre le client et
 * le serveur est établie et maintenue. 
 */
package com.bng.chat.client;
import java.io.*;
import java.net.*;
/**
 * 
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 2.0
 * @since 16/02/2016
 */

public class SocketClient {
	public static int PORT_SERVER=9999;
	private Socket socket;
	private BufferedReader msgrecu;
	private PrintWriter msgenvoi;
	
	/**
	 * un client est construit avec son pseudo
	 * @param pseudo
	 * @throws IOException
	 */
	public SocketClient(String pseudo, String mdp) throws IOException{
		this("127.0.0.1", pseudo, mdp);
	}
	/**
	 * Un client est construit avec son pseudo est son adresse ip
	 * Dans cet  constructeur on cree le flux entrée et de sortie du client et on envoie le pseudo au serveur
	 * @param add
	 * @param pseudo
	 * @throws IOException
	 */
	
	public SocketClient(String add, String pseudo, String mdp) throws IOException{
		this.socket=new Socket(add, PORT_SERVER);
		msgrecu=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		msgenvoi=new PrintWriter(this.socket.getOutputStream());
		//this.sendMessage("pseudo:"+pseudo, " mdp:"+mdp);
	}
	
	/**
	 * Cette methode permet de d'envoyer un message en parametre au serveur
	 * le pseudo permet d'indiquer la provenace du message aux autres clients
	 * @param s : le pseudo 
	 * @param msg : le message
	 */
	
	public void sendMessage(String s, String msg){
		msgenvoi.print(s+msg+"\n");
		msgenvoi.flush();
	}
	
	/**
	 * Cette methode permet de lire sur le flux d'entrée pour recuper les message recus
	 * @return msg
	 * @throws IOException
	 */
	public String receiveMessage() throws IOException{
		return msgrecu.readLine();
	}
	
	/**
	 * cette methode permet au client de se deconnecter au serveur
	 * @throws IOException
	 */
	public void deconnexion() throws IOException{
		msgenvoi.flush();
		msgenvoi.close();
		msgrecu.close();
		socket.close();
	}
	

}