package tools;
/**
 * @author Mathieu Lochet
 * @version 1
 * @date 14/11/2014
 * Classe représentant l'historique d'une planche. Un historique est une association de Paires de Dates à un Plant.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import objects.Plant;

public class Historique implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Paire<Date,Date>,Plant> historique;
	/**
	 * Creation d'un historique
	 */
	public Historique()
	{
		historique=new TreeMap<Paire<Date,Date>,Plant>();
	}
	/**
	 * Renvoie le nombre d'élément de l'historique
	 * @return
	 * 			nombre d'éléments dans l'historique
	 */
	public int size()
	{
		if(getDernierPlant()==null||getDernierPlant().getSecond().existe())
			return historique.size();
		else
			return historique.size()-1;
	}
	//On ajoute un plant dans l'historique
	/**
	 * Permet d'obtenir la liste des plants, par ordre déchronologique
	 * @return
	 * 			liste déchronologique des plantations
	 */
	public ArrayList<Paire<Plant, Date>> getAffichageHistorique()
	{
		ArrayList<Paire<Plant,Date>> historiqueAff=new ArrayList<Paire<Plant,Date>>();
		for(Paire<Date,Date> p : historique.keySet())
		{
			if(p.getSecond().existe())
				historiqueAff.add(new Paire<Plant,Date>(historique.get(p),p.getSecond()));
		}
		return historiqueAff;
	}
	/**
	 * Vérifie s'il est possible de planter le plant
	 * @param p
	 * 			Plant que l'utilisateur sélectionne
	 * @return
	 * 			Vrai s'il est compatible, faux sinon
	 */
	public boolean verifPlantDerniereFois(Plant p)
	{
		ArrayList<Paire<Plant,Date>> historiqueAff=getAffichageHistorique();
		boolean possible=true;
		for(Paire<Plant,Date> paire : historiqueAff)
		{
			if(p.equals(paire.getPremier()) && !paire.getSecond().anneeEcoulee(p.getRotation()))
				possible=false;
		}
		return possible;
	}
	
	/**
	 * Ajouter un plant à l'historique à la date courante
	 * @param p
	 * 			Plant à ajouter
	 */
	public void ajouter(Plant p)
	{
		Paire<Date,Date> dernier=getDernierPlant();
		if(dernier==null||dernier.getSecond().existe())
		{
			Date debut = Date.getCurrentDate();
			Date fin=new Date(-1);
			historique.put(new Paire<Date,Date>(debut,fin),p);
		}
	}
	//On récupère la date du dernier plant planté
	/**
	 * Permet de récupérer les dates de plantation du dernier plant
	 * @return 
	 * 			Paire de dates de pose du dernier plant
	 */
	public Paire<Date,Date> getDernierPlant()
	{
		if(historique.size()>0)
			return historique.firstEntry().getKey();
		else
			return null;
	}
	
	/**
	 * Permet de récupérer le dernier plant planté
	 * @return
	 * 			dernier plant planté
	 */
	public Plant getSommet()
	{
		if(historique.size()>0)
			return historique.firstEntry().getValue();
		else
			return null;
	}
	
	/**
	 * Vérifie qu'il est possible de planter un plant
	 * @return
	 * 			0 ou 1
	 */
	public int empilementPossible()
	{
		Paire<Date,Date> paire=getDernierPlant();
		return (paire==null||paire.getSecond().existe()?0:1);
	}
	
	/**
	 * Retire le plant du carré en l'ajoutant à l'historique
	 */
	public void validerPlant()
	{
		Paire<Date,Date> paire=getDernierPlant();
		if(paire!=null && !paire.getSecond().existe())
		{
			Plant p = historique.get(paire);
			historique.remove(paire);
			paire.setValeurs(paire.getPremier(), Date.getCurrentDate());
			historique.put(paire, p);
		}
	}
	
	/**
	 * Retire le plant actuel de l'historique
	 */
	public void depiler()
	{
		if(empilementPossible()==1)
			historique.remove(getDernierPlant());
	}
}
