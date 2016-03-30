package com.bng.chat.client.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bng.chat.client.Model.ModelClient;

public class VueAddClient extends JDialog implements ActionListener{
	private VueClient vue;
	private JList<String> room;
	private JTextField contact;
	private JButton ajout;
	private JButton cancel;
	private ModelClient model;
	
	public VueAddClient(VueClient vue, ModelClient model){
		super(vue, "Ajouter d'un contact dans une room");
		this.vue=vue;
		this.model= model;
		room = this.vue.getDiscussion();
		contact = new JTextField(5);
		ajout = new JButton("OK");ajout.addActionListener(this);
		cancel = new JButton("annuler");cancel.addActionListener(this);
		addRoom();
		
	}
	public void addRoom(){
		if(this.vue.getNbRoom() != 0){
			JPanel pano = new JPanel();
			pano.add(new JLabel("Nom du contact"));
			pano.add(contact);
			pano.add(room);
			pano.add(cancel);
			pano.add(ajout);
			this.setContentPane(pano);
		}
		else{
			JOptionPane.showMessageDialog(vue, "Vous devez avoir votre room ou participez à un room pour ajouter des contacts");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ajout ){
			if(contact.getText()==null){
				JOptionPane.showMessageDialog(vue, "entrez un nom de contact valide");
			}else{
				String nomRoom = room.getSelectedValue();
				try {
					this.model.AjouterContactRoom(nomRoom, contact.getText());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(vue, "erreur lors de la connexion avec la base de données");
				}
			}
		}else if(e.getSource() == cancel){
			this.setVisible(false);
		}
			
		
	}
}
