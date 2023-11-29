package gui.html;

import objects.Plant;
import gui.Fenetre;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 * FicheLegume est la classe permettant l'affichage d'un fiche Légume à paritr d'HTML contenu dans un string.
 * 
 * Une fiche Légume est composé d'un plant.
 * FicheLegume récupère une chaîne de caratère 
 * contenant de l'HTML et l'interprète dans une 
 * JTextPane afin de visualiser la fiche légume
 * 
 * 
 * 
 * @see Plant
 * @see HtmlView
 * @author PotagerTeam
 *
 */



public class FicheLegume
{
	/**
	 * Identifiant de sérialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Un plan du potager. Il contient les différentes caractéristiques du légumes.
	 */
	private Plant plant;
 
	
	/**
	 * Constructeur FicheLegume
	 * <p>
	 * A la construction d'un objet FicheLegume, il procède à la création de la fenêtre
	 * afin d'interpréter l'html récupérer dans une chaîne de caractère grâce à la classe HtmlView.
	 * </p>
	 * 
	 * @param p
	 * 				Le plant à afficher dans la fiche légume.
	 * @param f
	 * 				L'ensemble de l'application.
	 * 
	 * @see HtmlView
	 */
	
	public FicheLegume(final Plant p, Fenetre f)
	{
		HtmlView html;
		String image;
		String chemin;
		String es;
		
		JTextPane textPane = new JTextPane();
		HTMLDocument doc;
		HTMLEditorKit editorKit;
		JScrollPane scrollPane;
		
		
		//Création de la fenêtre
		JFrame fenetre = new JFrame(f.getDatas().getLangueElement("nom.legumotheque"));
		
		fenetre.setBounds(800,600,800,600);
		
		//Interprétation de l'html
		
		plant = p;
		
		//Récupération du text html
		html = new HtmlView(plant, f);
		es = html.loadFich();
		
		//récupération du chemin de l'image du plant 0
		image = f.getDatas().getInformation("path.imagesPlants")+this.plant.getImage();
		chemin = new File(image).toURI().toString();

		//On remplace le chemin de l'image dans la chaîne de caractère
		es =es.replaceAll("@IMAGE@", chemin);

		//définition du type du JTextPane en html
		textPane.setContentType( "text/html" );
		textPane.setEditable(false); 
		doc = (HTMLDocument)textPane.getDocument(); 
		editorKit = (HTMLEditorKit)textPane.getEditorKit(); 
		
		try {
			editorKit.insertHTML(doc, doc.getLength(), es, 0, 0, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Modifie la couleur du fond d'écran du JTextPane
		textPane.setBackground(Color.decode("#B7CA79"));
		
		//ajout du JtextPane dans un JScrollPane
		scrollPane = new JScrollPane(textPane);
		
		fenetre.setContentPane(scrollPane);
		
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		
		
		
		
		

		
		
	}
}



