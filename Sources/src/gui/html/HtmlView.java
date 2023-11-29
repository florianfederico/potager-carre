package gui.html;

import gui.Fenetre;

import java.io.*;

import objects.Plant;

/**
 * HtmlView est la classe permettant de g�rer le documents html
 * 
 * 
 * Il r�cup�re les informations du documents html dans une cha�ne de caract�re
 * et y remplace les balises par les diff�rentes caract�ristiques du plant.
 * 
 * 
 * Une balise est entour� de '@'.
 * 
 *  
 * @see Plant
 * @see Fenetre
 *  
 * @author PotagerTeam
 *
 */
public class HtmlView 
{
	/**
	 * Correspond � un l�gumes.
	 * Il contient les diff�rentes caract�ristiques de l�gumes.
	 */
	private Plant plant;
	/**
	 * La cha�ne de caract�re permettant de r�cup�rer le contenu du document HTML g�n�rique.
	 */
	private String conteneur;
	/**
	 * Permet de r�cup�rer toute les donn�es de l'application.
	 */
	private Fenetre mainFrame;
	
	/**
	 * Constructeur HtmlView.
	 * <p>
	 * Le conteneur est cr�� vide.
	 * </p>
	 * @param p
	 * 				Le plant s�lectionn�.
	 * @param f
	 * 				L'ensemble de l'application.
	 */
	public HtmlView(Plant p, Fenetre f)
	{
		plant = p;
		mainFrame=f;
		conteneur="";
	}
	
	/**
	 * Retourne le contenu de document html.
	 * @return Le contenu de document html, sous la forme d'une cha�ne de caract�re.
	 * 
	 */
	public String loadFich()
	{
		try
		{
			BufferedReader fich = new BufferedReader(new FileReader(mainFrame.getDatas().getInformation("path.vues")+mainFrame.getDatas().getInformation("default.htmlFile")));
			String ligne;
			
			//R�cup�re le contenu du fichier html g�n�rique
			while((ligne= fich.readLine())!=null)
				conteneur+=ligne;
			fich.close();
			int i=plant.getPosition();
			String str="";
			if((i>>2&1)==1)
				str+=mainFrame.getDatas().getLangueElement("plant.pos2");
			if((i>>1&1)==1)
				str+=mainFrame.getDatas().getLangueElement("plant.pos1");
			if((i&1)==1)
				str+=mainFrame.getDatas().getLangueElement("plant.pos0");
			
			//remplace les balises par les caract�ristique du l�gumes
			conteneur = conteneur.replaceAll("@NOM@", this.plant.getNom());
			conteneur = conteneur.replaceAll("@TAILLE@", ""+this.plant.getTaille()+" cm");
			conteneur = conteneur.replaceAll("@SUPERFICIE@", ""+this.plant.getSuperficie()+" carr�(s)");
			conteneur =conteneur.replaceAll("@POSITION@", str);
			conteneur =conteneur.replaceAll("@DESCRIPTION@", this.plant.getDescription());
			conteneur =conteneur.replaceAll("@ROTATION@", ""+this.plant.getRotation()+" ans entre 2 plantations");
			conteneur =conteneur.replaceAll("@ENSOLEILLEMENT@", ""+this.plant.getExposition());
			conteneur =conteneur.replaceAll("@TYPE@", this.plant.getType());
			conteneur =conteneur.replaceAll("@FAMILLE@", this.plant.getFamille());
		}
		catch (IOException ioe)
		{
			System.out.println("Erreur -- " + ioe.toString());
		}
		
		return conteneur;
	}
	
	
	
	
}
