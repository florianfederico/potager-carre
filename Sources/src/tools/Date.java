package tools;

import java.io.Serializable;

/**
 * Classe de gestion des dates. Une date est gérée par un entier écrit sous la forme YYYYMMDD.
 * @author Potager Team
 * @version 1
 * date 14/11/2014
 */

public class Date implements Serializable,Comparable<Date>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long identifiant;
	private int date;
	/**
	 * Création d'une date sous la forme YYYYMMJJ
	 * @param d
	 * 			entier désignant le jour
	 */
	public Date(int d)
	{
		date=d;
		identifiant=System.currentTimeMillis();
	}
	/**
	 * Permet de vérifier le nombre d'années écoulées
	 * @param a
	 * 			Un nombre d'années
	 * @return
	 * 			true s'il s'est écoulé a années entre la date et la date courante
	 */
	public boolean anneeEcoulee(int a)
	{
		Date d=getCurrentDate();
		if(d.date-a*10000>=date)
			return true;
		return false;
	}
	
	/**
	 * Permet de créer une date à partir d'un String
	 * @param d
	 * 			String désignant une date
	 */
	private Date(String d)
	{
		try
		{
			String decomposition=d.split("/")[2]+d.split("/")[1]+d.split("/")[0];
			date=Integer.parseInt(decomposition);
			identifiant=System.currentTimeMillis();
		}
		catch(Exception e)
		{
		}
	}
	
	public String toString()
	{
		int annee=date/10000;
		int mois=date/100-annee*100;
		int jour=date-mois*100-annee*10000;
		return jour+"/"+mois+"/"+annee;
	}
	
	//Permet de renvoyer false si la date n'a pas encore ete specifiee
	/**
	 * Permet de vérifier si une date est passée ou non
	 * @return 
	 * 			true si la date est passée, false sinon
	 */
	public boolean existe()
	{
		return date!=-1;
	}
	//On récupère la date du jour pour créer la date de plantation
	/**
	 * Permet d'obtenir la date du jour
	 * @return
	 * 			Date du jour
	 */
	public static Date getCurrentDate()
	{
		String format = "dd/MM/yyyy";
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format); 
		java.util.Date date = new java.util.Date(); 
		return new Date(formater.format(date));
	}
	
	public int compareTo(Date d)
	{
		return (int)(d.identifiant-identifiant);
	}
}
