/** Cette classe represente le processus qui permet au serveur de recuperer les messages des utilisateurs et les 
 * afficher a la zone de texte de l'utilisateur
 */
package com.bng.chat.client;

import java.io.IOException;

/**
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 2.0
 */

public class ProcessReceiver extends Thread{
	private Gui client;
	
	/**
	 * Creer le processus en le reliant à une interface d'utilisateur
	 * @param client
	 */
	public ProcessReceiver(Gui client){
		this.client=client;
	}
	/**
	 * cette methode permet de recuperer les messages recus sur le flux d'entree du client et affiche le message
	 */
	public void receive(){
		System.out.println("thread demarré");
		try {
			String msg=client.getUser().receiveMessage();
			System.out.println(msg);
			if(msg!=null)
				client.ajouterMessage(msg);
		} catch (IOException e) {
			client.ajouterMessage("Erreur de reception");
		}
	}
	/**
	 * cette methode tourne jusqu'a ce que l'utilisateur soit deconnecté
	 */
	public void run(){
		while(this.client.getUser()!=null){
			receive();
		}
	}

}
