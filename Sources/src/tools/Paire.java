package tools;

import java.io.Serializable;

/**
 * Classe permettant de manipuler des paires d'objets.
 * @author Potager Team
 * @version 1
 * date 14/11/2014
 */

public class Paire <L,R> implements Serializable,Comparable<Paire<L,R>>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private L premier;
	private R second;
	/**
	 * Permet de créer un object associant deux variables de type prédéfinit
	 * @param p
	 * 			Premier élément
	 * @param s
	 * 			Second élément
	 */
	public Paire(L p, R s)
	{
		premier=p;
		second=s;
	}
	//Permet de modifier les valeurs d'une paire
	/**
	 * Permet d'assigner des valeurs à l'objet
	 * @param x
	 * 			valeur premier membre
	 * @param y
	 * 			valeur second membre
	 */
	public void setValeurs(L x, R y)
	{
		premier=x;
		second=y;
	}
	
	/* GETTERS */
	/**
	 * Permet de récupérer le premier élément de la paire
	 * @return premier
	 * 			Premier élément de la paire
	 */
	public L getPremier()
	{
		return premier;
	}
	/**
	 * Permet de récupérer le second élément de la paire
	 * @return second
	 * 			Second élément de la paire
	 */
	public R getSecond()
	{
		return second;
	}
	
	public String toString()
	{
		return "{"+premier+" | "+second+"}";
	}
	/**
	 * Permet de comparer 2 paires de dates, à homogénéiser à tout type d'objet
	 * @param arg0
	 * 			Paire de dates
	 * @return 
	 * 			0 si egal, 1 si supérieur à arg0, -1 sinon
	 */
	public int compareTo(Paire<L,R> arg0) 
	{
		if(this==arg0)
			return 0;
		return ((Date)premier).compareTo((Date)arg0.second);
	}
	/**
	 * Permet de comparer 2 paires de dates, à homogénéiser à tout type d'objet
	 * @param arg0
	 * 			Paire de dates
	 * @return 
	 * 			0 si egal, 1 si supérieur à arg0, -1 sinon
	 */
	public int equals(Paire<L,R> arg0)
	{
		if(this==arg0)
			return 0;
		return ((Date)premier).compareTo((Date)arg0.second);
	}
}
