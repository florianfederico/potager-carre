package objects;
/**
 * @author PotagerTeam
 * @version 1
 * @date 14/11/2014
 * Classe représentant un carré d'une planche
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import tools.Date;
import tools.Historique;
import tools.Paire;

public class Carre extends Observable implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Historique historique;
	private int ensoleillement;
	private int numero;
	private int possibilites;
	/**
	 * Création d'un carré
	 * @param e
	 * 			ensoleillement
	 * @param n
	 * 			numero du carré
	 */
	public Carre(int e, int n)
	{
		historique=new Historique();
		possibilites=-1;
		ensoleillement=e;
		numero=n;
	}
	/**
	 * Renvoie l'historique en associant un plant avec la date où il a été retiré
	 * @return
	 * 			historique du carré
	 */
	public ArrayList<Paire<Plant, Date>> getAffichageHistorique()
	{
		return historique.getAffichageHistorique();
	}
	/**
	 * Ajoute un plant dans le carré
	 * @param p
	 * 			plant à ajouter
	 */
	public void ajouterHistorique(Plant p)
	{
		historique.ajouter(p);
		setChanged();
		notifyObservers();
	}
	/**
	 * Annule le plant, le retire de l'historique
	 */
	public void annulerContenu()
	{
		historique.depiler();
		setChanged();
		notifyObservers();
	}
	/**
	 * Ajoute le plant à l'historique, vide le carré
	 */
	public void vider()
	{
		historique.validerPlant();
		setChanged();
		notifyObservers();
	}
	/**
	 * Modifie la valeur de l'ensoleillement
	 * @param e
	 * 			nouvellement valeur, 0 ou 1
	 */
	public void setEnsoleillement(int e)
	{
		ensoleillement=e;
		swapEnsoleillement();
	}
	/**
	 * Inverse l'ensoleillement
	 */
	public void swapEnsoleillement() {
		ensoleillement=-(ensoleillement-1);
		setChanged();
		notifyObservers();
	}
	/**
	 * Récupère le numéro du carré
	 * @return
	 * 			numero du carré
	 */
	public int getNumero()
	{
		return numero;
	}
	/**
	 * Récupère l'ensoleillement du carré
	 * @return
	 * 			ensoleillement du carré
	 */
	public int getEnsoleillement()
	{
		return ensoleillement;
	}
	/**
	 * Récupère l'historique du carré
	 * @return
	 * 			historique du carré
	 */
	public Historique getHistorique()
	{
		return historique;
	}
	
	
	// 0 0 0 
	// milieu angle bord
	/**
	 * Vérifie la règle de position
	 * @param p 
	 * 			plant choisi
	 * @return 
	 * 			64 si elle est invalide
	 */
	public int positionnement(Plant p){ // PLACEMENT
		int res=0;
		int position = p.getPosition();
		
		if(numero==4){
			if(((position>>2)&1)==0){
				res|=64;
			}
		}
		
		else if(numero%2==0 && numero != 4){
			if(((position>>1)&1)==0){
				res|=64;
			}
		}
		
		else if(numero%2==1){
			if(((position>>0)&1)==0){
				res|=64;
			}	
		}
		return res;
	}
	/**
	 * Vérifie la règle de placement
	 * @param p
	 * 			plant planté
	 * @return
	 * 			0 si possible, 16 | 32| 128 | 2048 en fonction des conflits
	 */
	public int dernierPlantMemeFamilleOuType(Plant p){ //PLACEMENET
		int res=0;
		if(!(getHistorique().verifPlantDerniereFois(p)))
			res|=128;
		else
		{
			Plant pf=getHistorique().getSommet();
			if(pf!=null)
			{
				Boolean b=p.verifSuite(pf);
				if(b != null )
				{
					if(!b.booleanValue())
						res |=2048;
				}
				else
				{
					if(pf.getFamille().equals(p.getFamille()))
						res|=16;
					if(pf.getType().equals(p.getType()))
						res|=32;
				}
			}
		}
		return res;
		
	}
	/**
	 * Met à jour les conflits d'erreurs possibles de plantation
	 * @param p
	 * 			conflits possibles
	 */
	public void setPossibilites(int p)
	{
		possibilites=p;
	}
	/**
	 * Permet de récupérer les conflits de plantation
	 * @return
	 * 			conflits possibles
	 */
	public int getPossibilites()
	{
		return possibilites;
	}
	/**
	 * Cherche s'il est possible de planter quelque chose
	 * @param p
	 * 			plant proposé
	 * @param pl
	 * 			planche du carré
	 * @param pos
	 * 			position du sud
	 * @return
	 * 			0 si possible, 1 si impossible et un entier supérieur à 1 s'il y a des conflits
	 */
	public int posePossible(Plant p, Planche pl, Paire<Integer,Integer> pos) 
	{
		//REMARQUES
		// tester d'abord si plant déjà pésent.
		
		int possible=0;
		possible|=historique.empilementPossible(); //legume déjà présent
		//possibilites|=(p.empilementPossible(historique.getSommet())?0:1); //1 Test si le legume peut être mis sur la pile
		if(possible==0)
		{
			if(p!=null)
			{
				possible |= pl.memeFamOuTypeCarreAdjacent(this, p); // 10 regles de côte a côte
				possible |= pl.plantDejaPresentSurPlanche(this, p);
				possible |= dernierPlantMemeFamilleOuType(p);
				possible |= positionnement(p);
				possible |= pl.verifierEnsoleillement(this, p, pos);
				possible |= pl.verifierPoseOmbre(this, p, pos);
				possible |= pl.verifierEnsoleillementSud(this, p, pos);
			}
		}
		return possible;
	}
	/**
	 * Cherche s'il est possible de planter quelque chose
	 * @param p
	 * 			plant proposé
	 * @param pl
	 * 			planche du carré
	 * @param pos
	 * 			position du sud
	 * @see Carre#posePossible(Plant, Planche, Paire)
	 */
	public void updatePosePossible(Plant p, Planche pl, Paire<Integer,Integer> pos)
	{
		possibilites=-1;
		if(p!=null)
			possibilites=posePossible(p,pl, pos);
		setChanged();
		notifyObservers();
	}
}


