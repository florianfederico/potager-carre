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
 * FicheLegume est la classe permettant l'affichage d'un fiche L�gume � paritr d'HTML contenu dans un string.
 * 
 * Une fiche L�gume est compos� d'un plant.
 * FicheLegume r�cup�re une cha�ne de carat�re 
 * contenant de l'HTML et l'interpr�te dans une 
 * JTextPane afin de visualiser la fiche l�gume
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
	 * Identifiant de s�rialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Un plan du potager. Il contient les diff�rentes caract�ristiques du l�gumes.
	 */
	private Plant plant;
 
	
	/**
	 * Constructeur FicheLegume
	 * <p>
	 * A la construction d'un objet FicheLegume, il proc�de � la cr�ation de la fen�tre
	 * afin d'interpr�ter l'html r�cup�rer dans une cha�ne de caract�re gr�ce � la classe HtmlView.
	 * </p>
	 * 
	 * @param p
	 * 				Le plant � afficher dans la fiche l�gume.
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
		
		
		//Cr�ation de la fen�tre
		JFrame fenetre = new JFrame(f.getDatas().getLangueElement("nom.legumotheque"));
		
		fenetre.setBounds(800,600,800,600);
		
		//Interpr�tation de l'html
		
		plant = p;
		
		//R�cup�ration du text html
		html = new HtmlView(plant, f);
		es = html.loadFich();
		
		//r�cup�ration du chemin de l'image du plant 0
		image = f.getDatas().getInformation("path.imagesPlants")+this.plant.getImage();
		chemin = new File(image).toURI().toString();

		//On remplace le chemin de l'image dans la cha�ne de caract�re
		es =es.replaceAll("@IMAGE@", chemin);

		//d�finition du type du JTextPane en html
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
		
		//Modifie la couleur du fond d'�cran du JTextPane
		textPane.setBackground(Color.decode("#B7CA79"));
		
		//ajout du JtextPane dans un JScrollPane
		scrollPane = new JScrollPane(textPane);
		
		fenetre.setContentPane(scrollPane);
		
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		
		
		
		
		

		
		
	}
}



