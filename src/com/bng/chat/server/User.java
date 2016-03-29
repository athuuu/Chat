/**
 * Class user represente un tableau des utlisateurs connectés sur le reseau
 */
package com.bng.chat.server;

/**
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 2.0
 */
import java.net.*;

public class User {
	private Socket[] clients;
	 static int nbclient=0;
	
	/**
	 * contructeur avec le nombre total dutilisateur
	 * @param n
	 */
	public User(int n){
		this.clients=new Socket[n];
	}
	
	/**
	 * cette methode permet d'ajouter un utilisateur dans le tableau
	 * @param user
	 */
	public void add(Socket user){
		if(nbclient<clients.length){
			clients[nbclient++]=user;
		}
	}
	/**
	 * cette methode permet de supprimer un utilisateur dans le tableau
	 * A chaque suppression, le tableau est retrie
	 * @param user
	 */
	
	public void delete(Socket user){
		int indice=0;
		for(int i=0; i<nbclient; i++){
			if (clients[i].equals(user)){
				clients[i]=null;
				indice=i;
				break;
			}
		}
		Socket tmp=null;
		for(int i=indice; i<nbclient; i++){
			tmp=clients[i];
			clients[i]=clients[i+1];
			clients[i+1]=tmp;
		}
		nbclient--;
	}
	
	/**
	 * cette methode permet d'avoir un tableau des utilisateur sauf celui donné en parametre
	 * @param client
	 * @return tabuser
	 */
	public synchronized Socket[] getClientsEnLigne(Socket client){
		Socket tab[]=new Socket[nbclient-1];
		for(int i=0, j=0; i<nbclient; i++){
			if(clients[i]!=null && !clients[i].equals(client)){
				tab[j]=clients[i];
				j++;
			}
		}
		return tab;
	}
	
	
}
