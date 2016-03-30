package com.bng.chat.server.BD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Utils {
	public static Connection connect;	
	public static int getIdClient(String pseudo){
		int id =-1;
		try{
			connect = ConnexionBD.getConnection();
			String req = "select count(id) from client where pseudo='"+pseudo+"'";
			String req2 = "select id from client where pseudo='"+pseudo+"'";
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			
			resultats.next();
			if(resultats.getInt(1)!=0){
				Statement s2=connect.createStatement();
				ResultSet resultats2=s2.executeQuery(req2);
				resultats2.next();
				id = resultats2.getInt(1);
			}
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return id;
	}
	public static void insererClient(String pseudo, String mdp){
		try{
			connect = ConnexionBD.getConnection();
			String req = "insert into client(pseudo, mdp) values (?,?)" ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setString(1, pseudo);
			sta.setString(2, mdp);
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void afficherInfoClient(String pseudo){
		try{
			connect = ConnexionBD.getConnection();
			String req = "select * from client where pseudo='"+pseudo+"'";
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			while(resultats.next()){
				for (int i = 1; i <= 3; i++)
					System.out.print(resultats.getString(i) + " ");
				System.out.println();
			}
			//id = resultats.getInt(1);	
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void supprimerContact(String pseudo, String pseudo2){
		int idContact = getIdClient(pseudo);
		try{
			connect = ConnexionBD.getConnection();
			String req = "delete from contact where idClient = ? and pseudoContact = ? " ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setInt(1, idContact);
			sta.setString(2, pseudo2);
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public static void AjouterContact(String pseudo, String pseudo2){
		int idContact = getIdClient(pseudo);
		try{
			connect = ConnexionBD.getConnection();
			String req = "insert into contact (idClient, pseudoContact) values (?,?)" ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setInt(1, idContact);
			sta.setString(2, pseudo2);
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static String listeContact(String pseudo){
		int idContact = getIdClient(pseudo);
		String contact = "";
		try{
			connect = ConnexionBD.getConnection();
			String req = "select pseudoContact from contact where idClient="+idContact;
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			
			while(resultats.next())
				contact+=resultats.getString(1)+" ";	
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return contact;
	}
	public static void creerRoom(String nomRoom, String pseudo){
		int idMembre = getIdClient(pseudo);
		try{
			connect = ConnexionBD.getConnection();
			String req = "insert into room (nom, membre) values (?,?)" ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setString(1, nomRoom);
			sta.setInt(2, idMembre);
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void quitterRoom(String nomRoom, String pseudo){
		int idContact = getIdClient(pseudo);
		try{
			connect = ConnexionBD.getConnection();
			String req = "delete from room where nom = ? and membre = ?" ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setInt(2, idContact);
			sta.setString(1, nomRoom);
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void ajouterClientRoom(String pseudo, String nomRoom){
		creerRoom(nomRoom, pseudo);
	}
	public static Object[] listeRoom(String pseudo){
		int idContact = getIdClient(pseudo);
		ArrayList<String> list = new ArrayList<String>();
		try{
			connect = ConnexionBD.getConnection();
			String req = "select nom from room where membre="+idContact;
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			
			while(resultats.next())
				list.add(resultats.getString(1));	
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return list.toArray();
	}
	public static Object[] listeMembreRoom(String nomRoom){
		ArrayList<String> list = new ArrayList<String>();
		try{
			connect = ConnexionBD.getConnection();
			String req = "select pseudo from  client, room where room.nom='"+nomRoom+"' and client.Id=room.membre";
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			
			while(resultats.next()){
				list.add(resultats.getString(1));
				//resultats2.close();
			}
					
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return list.toArray();
	}	
	public static void insererMessage(String pseudo, String nomRoom, String msg){
		int idClient = getIdClient(pseudo);
		try{
			connect = ConnexionBD.getConnection();
			String req = "insert into message(idClient, nomRoom, message, date) values (?,?,?,?)" ;
			PreparedStatement sta = connect.prepareStatement(req);
			sta.setInt(1, idClient);
			sta.setString(2, nomRoom);
			sta.setString(3, msg);
			sta.setDate(4, new Date(System.currentTimeMillis()));
			sta.execute();
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static Object[] listeMessageClient(String nomRoom){
		ArrayList<String> list = new ArrayList<String>();
		try{
			connect = ConnexionBD.getConnection();
			String req = "select message from message where nomRoom='"+nomRoom+"'";
			Statement s=connect.createStatement();
			ResultSet resultats=s.executeQuery(req);
			
			while(resultats.next())
				list.add(resultats.getString(1));	
			connect.close();
			
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return list.toArray();
	}
	public static boolean verification(String pseudo, String mdp){
		int id=getIdClient(pseudo);
		if(id==-1)return false;
		else{
			String mdpstock = "";
			try{
				connect = ConnexionBD.getConnection();
				String req = "select mdp from client where Id="+id;
				Statement s=connect.createStatement();
				ResultSet resultats=s.executeQuery(req);
				resultats.next();
				mdpstock=resultats.getString(1);
				//System.out.println(mdpstock);
				connect.close();
			
			}catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
			return mdpstock.equals(mdp);
		}
	}
	
}
