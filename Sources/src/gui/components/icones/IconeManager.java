package gui.components.icones;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * IconeManager est la classe g�rant tous les ic�nes de l'application.
 * 
 *
 */
public class IconeManager 
{
	private HashMap<Integer, Icone> iconList;
	/**
	 * Cr�ation d'un gestionnaire d'icones
	 */
	public IconeManager()
	{
		 iconList=new HashMap<Integer, Icone>();
	}
	/**
	 * Ajout d'une icone a la liste des icones utilisables
	 * @param icon
	 * 			Icone a ajouter a la liste
	 */
	public void ajout(Icone icon)
	{
		int i=icon.getValue();
		if((i>0&&((i&(i-1))==0))&&!iconList.containsKey(i))
			iconList.put(i, icon);
	}
	/**
	 * Permet de r�cup�rer une icone � partir de son identifiant binaire
	 * @param i
	 * 			Identifiant binaire
	 * @return
	 * 			Icone correspondante
	 */
	public Icone getIcone(Integer i)
	{
		return iconList.get(i);
	}
	/**
	 * Renvoie la liste des icones form�e par l'entier 
	 * @param list
	 * 			Entier o� chaqu bit correspond � un identifiant d'icone
	 * @return
	 * 			Liste des icones correspondantes aux bits de la liste
	 */
	public ArrayList<Icone> getList(Integer list)
	{
		ArrayList<Icone> liste= new ArrayList<Icone>();
		Icone icon;
		for(int i=0;list>Math.pow(2, i);i++)
		{
			if(((list>>i)&1)==1)
			{
				icon=iconList.get((int)Math.pow(2, i));
				liste.add(icon.clone());
			}
		}
		return liste;
	}
}
