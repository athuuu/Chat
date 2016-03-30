package com.bng.chat.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.bng.chat.server.BD.Utils;

public class Service extends Thread implements Closeable{
	private Socket client;
	private String pseudo;
	private String mdp;
	private BufferedReader in;
	private PrintStream out; 
	
	public Service(Socket c) throws IOException {
		this.client=c;
		in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		out = new PrintStream(this.client.getOutputStream());
		start();
	}
		
	public void run(){
		String operation = "";
		while(!operation.equals("fin") && operation != null){
			operation=extractMessage();
		}
		try {
			close();
		} catch (IOException e) {}
	}
	
	private String extractMessage(){
		String operation=" ";
		try {
			String msg = reception();
			System.out.println("le serveur vient de recevoir = "+msg);
			String[] element=msg.split(":");
			if(element.length>1){
				operation=element[0];
				switch(operation){
				case "inscription": inscription(element);break;
				case "verification":verification(element);break;
				case "tchat":tchat(element);break;
				case "room":room(element);break;
				case "contact":contact(element);break;
				case "icone":icone(element); break;
				
				}
				
			}else operation=msg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return operation;
	}
	
	
	public void inscription(String[] element){
		try{
			pseudo = element[1];
			mdp = element[2];
			if(!Utils.verification(pseudo, mdp)){
				Utils.insererClient(pseudo, mdp);
				send("ok");
			}else send("error:ce client existe deja");
			}catch(ArrayIndexOutOfBoundsException e){
				send("error:le mot de passe ne doit pas etre vide");
			}
	}
	public void verification(String[] element){
		try{
		pseudo = element[1];
		mdp = element[2];
		if(Utils.verification(pseudo, mdp)){
			send("ok");
		}else{
			send("error:Login ou mot de passe incorrecte");
		}
		}catch(ArrayIndexOutOfBoundsException e){
			send("error:le mot de passe ne doit pas etre vide");
		}
	}
	
	public void room(String[] element){
		try{
			String type = element[1];
			switch(type){
			case "creer": {
				Utils.creerRoom(element[2], pseudo);
				send("ok");
			}break;
			case "ajout": {
				Utils.ajouterClientRoom(element[2], element[3]);
				send("ok");
			}break;
			case "liste": {
				String ob="";
				for(Object a:Utils.listeRoom(pseudo))
					ob+=(String)a+" ";
				if(ob.length()!=0)
					ob+=ob.substring(0, ob.length()-1);
				send(ob);
			}break;
			case "quitter":{
				Utils.quitterRoom(element[2], pseudo) ;
				send("ok");
			}break;
			}
			
		}catch(ArrayIndexOutOfBoundsException e){
			send("error:Erreur survenue cote serveur");
		}
	}
	public void contact(String[] element){
		try{
			String type = element[1];
			switch(type){
			case "ajout": {
				Utils.AjouterContact(pseudo, element[2]);
				Utils.AjouterContact(element[2], pseudo);
				send("ok");
			}break;
			case "supprimer": {
				Utils.supprimerContact(pseudo, element[2]);
				Utils.supprimerContact(element[2],pseudo );
				send("ok");
			}break;
			case "ajouterRoom":{
				Utils.ajouterClientRoom(element[2], element[3]);
				send("ok");
			}break;
			case "liste" : send(Utils.listeContact(pseudo));break;
		}
			
		}catch(ArrayIndexOutOfBoundsException e){
			send("error:Erreur survenue cote serveur");
		}
	}
	public void icone(String[] element){
		
	}
	public void sendListe(String[] liste){
		String l="";
		for(String s:liste){
			System.out.println(s);
			l+=s+" ";
		}
		send(l.substring(0, l.length()-1));
			
	}
	public void send(String msg){
		out.println(msg);
		out.flush();
	}
	public String reception() throws IOException{
		return in.readLine();
	}
	public void tchat(String[] element){
		
	}
	@Override
	public void close() throws IOException {
		out.flush();
		out.close();
		in.close();
		client.close();
		
	}
	
	
	
	

}
