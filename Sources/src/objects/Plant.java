package objects;

import java.io.Serializable;
import java.util.HashMap;

import common.Main;

/**
 * @author Potager Team
 * @version 1
 * date 14/11/2014
 * Classe représentant un plant de l'application
 */

public class Plant implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int taille;
	private int superficie;
	private int position;
	private int ensoleillement;
	private int rotation;
	private String nom;
	private String description;
	private String image;
	private String famille=Main.FAMILLE;
	private String type=Main.TYPE;
	private HashMap<Plant,Boolean> adj;
	private HashMap<Plant,Boolean> suite;
	/**
	 * Création d'un plant
	 * @param nom
	 * 			nom du plant
	 * @param image
	 * 			fichier image
	 * @param description
	 * 			description du plant
	 * @param famille
	 * 			famille du plant
	 * @param type
	 * 			type du plant
	 * @param taille
	 * 			taille du plant (en cm)
	 * @param superficie
	 * 			superfcie au sol du plant (/!\ cas non traité)
	 * @param position
	 * 			positions possibles du plant
	 * @param ensoleillement
	 * 			Ensoleillement requis du plant
	 * @param rotation
	 * 			Temps (en année) de rotation minimum du plant
	 */
	public Plant(String nom, String image, String description, String famille, String type,int taille, int superficie, int position, int ensoleillement, int rotation)
	{
		this.taille=taille;
		this.superficie=superficie;
		this.position=position;
		this.ensoleillement=ensoleillement;
		this.rotation=rotation;
		this.nom=nom;
		this.description=description;
		this.image=image;
		this.famille=famille;
		this.type=type;
		adj=new HashMap<Plant, Boolean>();
		suite=new HashMap<Plant, Boolean>();
	}
	
	public String toString()
	{
		return nom;
	}
	
	/**
	 * Regarde si 2 plants sont identiques
	 * @param p
	 * 			plant comparé
	 * @return
	 * 			true si les deux plants sont les mêmes
	 */
	public boolean equals(Plant p)
	{
		return nom.equals(p.nom)&& famille.equals(p.famille) && type.equals(p.type); 
	}
	/**
	 * Regarde s'il est possible de mettre un plant après celui ci
	 * @param p
	 * 			nouveau plant
	 * @return
	 * 			true si le nouveau plant n'a pas de conflit avec ce plant
	 */
	public boolean empilementPossible(Plant p)
	{
		boolean possible=true;
		if(p!=null)
		{
			if(p.famille.equals(famille)||p.type.equals(type))
				possible=false;
		}
		return possible;
	}
	
	/**
	 * Ajoute une règle de cohabitation
	 * @param p
	 * 			plant à ajouter
	 * @param b
	 * 			possible ou non
	 */
	public void addAdjRegle(Plant p, boolean b)
	{
		adj.put(p, b);
	}
	/**
	 * Ajoute une règle de rotation
	 * @param p
	 * 			plant à ajouter
	 * @param b
	 * 			possible ou non
	 */
	public void addSuiteRegle(Plant p, boolean b)
	{
		suite.put(p, b);
	}
	/**
	 * Vérifie les règles de cohabiation
	 * @param p
	 * 			Avec le plant p
	 * @return
	 * 			true si possible, false si déconseillé, null s'il n'y a pas de règle spécifique
	 */
	public Boolean verifAdj(Plant p)
	{
		if(adj.containsKey(p))
			return adj.get(p);
		return null;
	}

	/**
	 * Vérifie les règles de rotation
	 * @param p
	 * 			Avec le plant p
	 * @return
	 * 			true si possible, false si déconseillé, null s'il n'y a pas de règle spécifique
	 */
	public Boolean verifSuite(Plant p)
	{
		if(suite.containsKey(p))
			return suite.get(p);
		return null;
	}
	/**
	 * Envoie la famille du plant
	 * @return
	 * 			famille du plant
	 */
	public String getFamille()
	{
		return famille;
	}
	/**
	 * Envoie le type du plant
	 * @return
	 * 			type du plant
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * Envoie le nom du plant
	 * @return
	 * 			nom du plant
	 */
	public String getNom()
	{
		return nom;
	}
	/**
	 * Envoie le texte lié à l'ensoleillement
	 * @return
	 * 			texte d'ensoleillement
	 */
	public String getExposition()
	{
		return ensoleillement==1?Main.SOLEIL_1:Main.SOLEIL_0;
	}
	/**
	 * Envoie la taille en cm du plant
	 * @return
	 * 			taille du plant
	 */
	public int getTaille() 
	{
		return taille;
	}
	/**
	 * Envoie les positions possibles du plant
	 * @return
	 * 			positions possibles
	 */
	public int getPosition() 
	{
		return position;
	}
	/**
	 * Envoie le temps de rotation en années
	 * @return
	 * 			temps de rotation minimum
	 */
	public int getRotation() 
	{
		return rotation;
	}
	/**
	 * Envoie la description du plant
	 * @return
	 * 			description du plant
	 */
	public String getDescription() 
	{
		return description;
	}
	/**
	 * Envoie l'image du plant
	 * @return
	 * 			image du plant
	 */
	public String getImage() 
	{
		return image;
	}
	/**
	 * Envoie l'ensoleillement requis du plant
	 * @return
	 * 			ensoleillement requis du plant
	 */
	public int getEnsoleillement() 
	{
		return ensoleillement;
	}
	/**
	 * Envoie la superficie du plant
	 * @return
	 * 			superficie du plant
	 */
	public int getSuperficie()
	{
		return superficie;
	}
}
