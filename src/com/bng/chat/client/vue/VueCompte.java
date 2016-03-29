package com.bng.chat.client.vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bng.chat.client.Model.ModelConnexion;


public class VueCompte extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;
	private String mdp;
	private String add;
	public VueCompte(final JFrame frame){
		super(frame, "Saisie des informations", true);
		final JTextField n=new JTextField(11);
		final JTextField p=new JTextField(11);
		final JTextField a=new JTextField(11);
		this.setSize(270, 250);
		Container contenu = this.getContentPane ();
		JPanel pan=new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));//new GridLayout(2, 2));
		contenu.setLayout(new BorderLayout());
		pan.add(new JLabel("pseudo   :"));
		pan.add(n);
		pan.add(new JLabel("password :"));
		pan.add(p);
		pan.add(new JLabel("serveur  :"));
		pan.add(a);
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("cancel");
		ok.addActionListener(new ActionListener(){
			private ModelConnexion model;
			public void actionPerformed(ActionEvent e) {
				pseudo = n.getText();
				mdp = p.getText();
				add = a.getText();
				try {
					model = new ModelConnexion();
					if(model.inscription(pseudo, mdp, add))
					JOptionPane.showMessageDialog(frame, "Inscription effectuée avec succès");
					else JOptionPane.showMessageDialog(frame, "Probléme survenu lors de l'inscription");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Erreur survenue lors de la connexion avec le serveur");
				}
				VueCompte.this.setVisible(false);
			}	
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				VueCompte.this.setVisible(false);
			}
		});
		JPanel bou = new JPanel();
		contenu.add(pan);
		bou.add(ok);
		bou.add(cancel);
		contenu.add(bou, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	public String getPseudo(){	return pseudo;	}
	public String getMDP(){return mdp;}
	public String getAdd(){return add;}
	
	
}


