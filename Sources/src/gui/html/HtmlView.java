package gui.html;

import gui.Fenetre;

import java.io.*;

import objects.Plant;

/**
 * HtmlView est la classe permettant de gérer le documents html
 * 
 * 
 * Il récupère les informations du documents html dans une chaîne de caractère
 * et y remplace les balises par les différentes caractéristiques du plant.
 * 
 * 
 * Une balise est entouré de '@'.
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
	 * Correspond à un légumes.
	 * Il contient les différentes caractéristiques de légumes.
	 */
	private Plant plant;
	/**
	 * La chaîne de caractère permettant de récupérer le contenu du document HTML générique.
	 */
	private String conteneur;
	/**
	 * Permet de récupérer toute les données de l'application.
	 */
	private Fenetre mainFrame;
	
	/**
	 * Constructeur HtmlView.
	 * <p>
	 * Le conteneur est créé vide.
	 * </p>
	 * @param p
	 * 				Le plant sélectionné.
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
	 * @return Le contenu de document html, sous la forme d'une chaîne de caractère.
	 * 
	 */
	public String loadFich()
	{
		try
		{
			BufferedReader fich = new BufferedReader(new FileReader(mainFrame.getDatas().getInformation("path.vues")+mainFrame.getDatas().getInformation("default.htmlFile")));
			String ligne;
			
			//Récupère le contenu du fichier html générique
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
			
			//remplace les balises par les caractéristique du légumes
			conteneur = conteneur.replaceAll("@NOM@", this.plant.getNom());
			conteneur = conteneur.replaceAll("@TAILLE@", ""+this.plant.getTaille()+" cm");
			conteneur = conteneur.replaceAll("@SUPERFICIE@", ""+this.plant.getSuperficie()+" carré(s)");
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
