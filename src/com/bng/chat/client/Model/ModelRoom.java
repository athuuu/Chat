package com.bng.chat.client.Model;

import java.util.Observable;

import com.bng.chat.client.vue.Room;

public class ModelRoom extends Observable{
	
	//private List<Client> list;
	
	private Room room;
	public ModelRoom(Room r){
		this.room = r;
	}
	
	public void addElement(String com){
		this.room.append(com);
		setChanged();
		notifyObservers();
	}

}
