package objects;
/**
 * @author PotagerTeam
 * @version 1
 * @date 14/11/2014
 * Classe repr�sentant un carr� d'une planche
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
	 * Cr�ation d'un carr�
	 * @param e
	 * 			ensoleillement
	 * @param n
	 * 			numero du carr�
	 */
	public Carre(int e, int n)
	{
		historique=new Historique();
		possibilites=-1;
		ensoleillement=e;
		numero=n;
	}
	/**
	 * Renvoie l'historique en associant un plant avec la date o� il a �t� retir�
	 * @return
	 * 			historique du carr�
	 */
	public ArrayList<Paire<Plant, Date>> getAffichageHistorique()
	{
		return historique.getAffichageHistorique();
	}
	/**
	 * Ajoute un plant dans le carr�
	 * @param p
	 * 			plant � ajouter
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
	 * Ajoute le plant � l'historique, vide le carr�
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
	 * R�cup�re le num�ro du carr�
	 * @return
	 * 			numero du carr�
	 */
	public int getNumero()
	{
		return numero;
	}
	/**
	 * R�cup�re l'ensoleillement du carr�
	 * @return
	 * 			ensoleillement du carr�
	 */
	public int getEnsoleillement()
	{
		return ensoleillement;
	}
	/**
	 * R�cup�re l'historique du carr�
	 * @return
	 * 			historique du carr�
	 */
	public Historique getHistorique()
	{
		return historique;
	}
	
	
	// 0 0 0 
	// milieu angle bord
	/**
	 * V�rifie la r�gle de position
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
	 * V�rifie la r�gle de placement
	 * @param p
	 * 			plant plant�
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
	 * Met � jour les conflits d'erreurs possibles de plantation
	 * @param p
	 * 			conflits possibles
	 */
	public void setPossibilites(int p)
	{
		possibilites=p;
	}
	/**
	 * Permet de r�cup�rer les conflits de plantation
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
	 * 			plant propos�
	 * @param pl
	 * 			planche du carr�
	 * @param pos
	 * 			position du sud
	 * @return
	 * 			0 si possible, 1 si impossible et un entier sup�rieur � 1 s'il y a des conflits
	 */
	public int posePossible(Plant p, Planche pl, Paire<Integer,Integer> pos) 
	{
		//REMARQUES
		// tester d'abord si plant d�j� p�sent.
		
		int possible=0;
		possible|=historique.empilementPossible(); //legume d�j� pr�sent
		//possibilites|=(p.empilementPossible(historique.getSommet())?0:1); //1 Test si le legume peut �tre mis sur la pile
		if(possible==0)
		{
			if(p!=null)
			{
				possible |= pl.memeFamOuTypeCarreAdjacent(this, p); // 10 regles de c�te a c�te
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
	 * 			plant propos�
	 * @param pl
	 * 			planche du carr�
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


