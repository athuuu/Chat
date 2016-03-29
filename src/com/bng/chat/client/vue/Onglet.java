package com.bng.chat.client.vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;



public class Onglet extends javax.swing.JTabbedPane{
    Onglet moi; //pour avoir acces au l'element this dans les class internes
	public Onglet() {
		super();
        moi =this;
	}
 
	public void addTab(String title, Component component,int endroit) {
            super.addTab(title, component); //on ajoute une Tab à JTabbedPane
            super.setTabComponentAt(endroit, new CloseTabPanel(title)); //on applique le closeTabPanel a l'element "endroit"
	}
 
		//fonction qui permet d'affiché le bouton close
        public void afficheIconAt(int endroit){
            ((CloseTabPanel)moi.getTabComponentAt(endroit)).afficheIcon(true);
        }
		//fonction qui permet d'enlever le bouton close
        public void cacheIconAt(int endroit){
            ((CloseTabPanel)moi.getTabComponentAt(endroit)).afficheIcon(false);
        }
 
 
 
class CloseTabPanel extends JPanel{
        JButton button; 
 
	//constructeur sans boolean  qui de base met un bouton close
    public CloseTabPanel(String titre) {
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            setOpaque(false);
            JLabel label = new JLabel(titre);
            add(label);
            button = new TabButton();
            add(button);
            setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
	}
	//constructeur avec boolean  qui permet de choisir si oui ou non on veux un bouton close
		public CloseTabPanel(String titre, boolean b){
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            setOpaque(false);
            JLabel label = new JLabel(titre);
            add(label);
            button = new TabButton();
            if(b){
            add(button);
            }
            //add more space to the top of the component
            setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        }
        //permet d'afficher ou cacher le bouton close
        public void afficheIcon(boolean b){
            if(b){
                if(this.getComponentCount()==1)
                    this.add(button);
            }else{
                if(this.getComponentCount()>1)
                    this.remove(button);
            }
        }
}
class TabButton extends JButton implements ActionListener {
    public TabButton() {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("Fermer cet onglet");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Rends le bouton transparent
            setContentAreaFilled(false);
            //pas besoin d'avoir le focus
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            addActionListener(this);            
        }
		/*
		* fonction qui ferme l'onglet du bouton close sur lequel on a cliqué
		*/
    public void actionPerformed(ActionEvent e) {
            int X = new Double(((JButton)e.getSource()).getMousePosition().getX()).intValue();
            int Y = new Double(((JButton)e.getSource()).getMousePosition().getY()).intValue();
 
            int i = moi.getUI().tabForCoordinate((JTabbedPane)moi, X,Y);
            if (i != -1) {
                moi.remove(i);
            }
        }
 
        //we don't want to update UI for this button
        public void updateUI() {
        }
 
        //dessine la croix dans le bouton
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);int delta = 4;
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            g2.drawImage(new ImageIcon(VueChat.class.getResource("close_dark.png")).getImage(), delta, delta, null);
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            } 
            //g2.setStroke(new BasicStroke(2));
            
            if (getModel().isRollover()) {
            	g2.drawImage(new ImageIcon(VueChat.class.getResource("close_white.png")).getImage(), delta-1, delta-1, null);
            } 
            
        }
    }
}