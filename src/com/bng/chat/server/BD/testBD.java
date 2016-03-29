package com.bng.chat.server.BD;

public class testBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pseudo = "chat";
		//String mdp = "passer";
		//*/
		System.out.println(Utils.getIdClient("chat"));
		if(Utils.verification("bass", "passer"))
		Utils.insererClient("bass", "passer");
		/*Utils.AjouterContact("sane", "sine");
		Utils.AjouterContact("sane", "chat");
		Utils.AjouterContact("sane", "bng");
		Utils.afficherInfoClient("sine");
		Utils.listeContact("sane");
		Utils.afficherInfoClient("sine");
		Utils.afficherInfoClient("bng");
		Utils.afficherInfoClient("chat");
		Utils.creerRoom("room1", "sine");
		Utils.ajouterClientRoom("bng", "room1");
		Utils.ajouterClientRoom("sane", "room1");
		Utils.ajouterClientRoom("chat", "room1");
		
		Utils.ajouterClientRoom("sine", "room2");
		Utils.ajouterClientRoom("sane", "room2");
		Utils.ajouterClientRoom("chat", "room2");*/
		for(Object a:Utils.listeRoom(pseudo))
			System.out.println((String)a);
		for(Object a:Utils.listeMembreRoom("room1"))
			System.out.println((String)a);
		/*Utils.quitterRoom("room1", "sane");
		Utils.quitterRoom("room2", "sane");*/
		//Utils.insererMessage(pseudo, "room1", "message de test");
		//for(Object a:Utils.listeMessageClient("room1"))
			//System.out.println((String)a);
		//System.out.println(Utils.verification(pseudo, "passer"));
		//System.out.println("bassiroun".substring(0, 8));
		System.out.println( Utils.listeContact("sane"));
		/*Utils.creerRoom("room2", "bng");
		Utils.creerRoom("room3", "bng");
		Utils.creerRoom("room4", "bng");
		Utils.creerRoom("room5", "bng");*/
		String c="";
		for(Object o:Utils.listeRoom("sine"))
			c+=(String)o+" ";
		//for(String a : b)
		if(c.length()!=0)
			System.out.println(c.substring(0, c.length()-1));
		else
			System.out.println("vide");
		
		
		
	}

}
