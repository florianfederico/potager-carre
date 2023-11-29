package objects;
/**
 * @author PotagerTeam
 * @version 1
 * @date 14/11/2014
 * Classe représentant un potager
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import common.Main;

import tools.Paire;

public class Potager implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Planche>planches;
	private String nomFichier;
	private String nom;
	private String adresse;
	private int ensoleillement;
	private boolean movable;
	private Paire<Integer,Integer> positionBousole;
	/**
	 * Crée une instance de potager
	 */
	public Potager()
	{
		planches=new Vector<Planche>();
		positionBousole=new Paire<Integer,Integer>(0,0);
		movable=true;
		nom="";
		adresse="";
		nomFichier="";
	}

	/* SETTERS */
	/**
	 * Permet de modifier les informations du potager
	 * @param n
	 * 			nom du potager
	 * @param e
	 * 			ensoleillement du potager
	 * @param a
	 * 			adresse du potager
	 */
	public void setValues(String n, String e, String a)
	{
		nom=n;
		setEnsoleillement(e.equals("Ensoleillé")?0:1);
		adresse=a;
	}
	/**
	 * Supprime une planche
	 * @param p
	 * 			planche à supprimer
	 */
	public void removePlanche(Planche p)
	{
		planches.remove(p);
	}
	/**
	 * Ajoute une planche
	 * @param p
	 * 			planche à ajouter
	 */
	public void ajoutPlanche(Planche p)
	{
		planches.add(p);
	}
	/**
	 * Place la boussole
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void setBoussole(int x, int y)
	{
		positionBousole.setValeurs(x,y);
		movable=false;
	}
	
	/* GETTERS */
	/**
	 * Récupère l'adresse du potager
	 * @return
	 * 			adresse du potager
	 */
	public String getAdresse()
	{
		return !adresse.equals("")?adresse:Main.ADRESSE;
	}
	/**
	 * Récupère le nom du fichier du potager
	 * @return
	 * 			nom du fichier du potager
	 */
	public String getNomFichier()
	{
		return nomFichier;
	}
	/**
	 * Récupère la position x de la boussole
	 * @return
	 * 			position x de la boussole
	 */
	public int getBoussolePosX()
	{
		return positionBousole.getPremier();
	}
	/**
	 * Récupère la position y de la boussole
	 * @return
	 * 			position y de la boussole
	 */
	public int getBoussolePosY()
	{
		return positionBousole.getSecond();
	}
	/**
	 * Récupère le nom du potager
	 * @return
	 * 			nom du potager
	 */
	public String getNom()
	{
		return nom.equals("")?Main.getNomPotager():nom;
	}
	/**
	 * Récupère le nom exact du potager
	 * @return
	 * 			nom exact du potager
	 */
	public String getVraiNom()
	{
		return nom;
	}
	/**
	 * Change le nom du fichier de sauvegarde
	 * @param nomFi
	 * 			nom voulu
	 * @return
	 * 			0 si nom possible, 1 si le nom ne peut pas être valide
	 */
	public int setNomFichier(String nomFi)
	{
		if(nomFi.equals("con") || 
		   nomFi.contains("\\")||
		   nomFi.contains("/") ||
		   nomFi.contains("?") ||
		   nomFi.contains(":") ||
		   nomFi.contains("*") ||
		   nomFi.contains("<") ||
		   nomFi.contains(">") ||
		   nomFi.contains("|"))
			return 1;
		else
		{
			File dossierJardins = new File (Main.PATH_POTAGER+nomFi+".p2a");
			if(dossierJardins.exists() && !nomFichier.equals(nomFi+".p2a"))
				return 1;
			nomFichier=nomFi+".p2a";
		}
		return 0;
	}
	/**
	 * Permet de récupérer la liste des planches
	 * @return
	 * 			liste des planches
	 */
	public Vector<Planche> getPlanches()
	{
		return planches;		
	}
	/**
	 * Sauvegarde le fichier du potager
	 * @throws FileNotFoundException
	 * 			Serialisation
	 * @throws IOException
	 * 			Serialisation
	 */
	public void save() throws FileNotFoundException, IOException
	{
		ObjectOutputStream save=new ObjectOutputStream(new FileOutputStream(Main.PATH_POTAGER+nomFichier));
		save.writeObject(this);
		save.close();
	}

	/**
	 * Permet de créer la paire de positions du sud
	 * @return
	 * 			paire (x,y) de la position utilisable du sud
	 */
	public Paire<Integer, Integer> positionBoussole()
	{
		Integer posX=0;
		Integer posY=0;
		int x=positionBousole.getPremier();
		int y=positionBousole.getSecond();
		if(x<=50)
			posX=-1;
		else if(x<=480)
			posX=0;
		else
			posX=1;

		if(y<=50)
			posY=-1;
		else if(y<=282)
			posY=0;
		else
			posY=1;
		Paire<Integer,Integer> p=new Paire<Integer,Integer>(posX,posY);
		return p;
	}
	/**
	 * Modifie l'ensoleillement du potager
	 * @param ensoleillement
	 * 			nouvel ensoleillement
	 */
	public void setEnsoleillement(int ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	/**
	 * Envoie l'indicateur de possibilité de modification du potager
	 * @return
	 * 			true s'il est possible de déplacer la boussole
	 */
	public boolean isMovable()
	{
		return movable;
	}
	/**
	 * Envoie la valeur de l'ensoleillement
	 * @return
	 * 			Ensoleillement du potager
	 */
	public int getEnsoleillement() {
		return ensoleillement;
	}
}
