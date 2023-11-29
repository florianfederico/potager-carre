package objects;
/**
 * @author PotagerTeam
 * @version 1
 * @date 14/11/2014
 * Classe repr�sentant une planche d'un potager
 */

import java.io.Serializable;
import java.util.Vector;

import common.Main;
import tools.Date;
import tools.Paire;

public class Planche implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carre[] carres;
	private Paire<Integer,Integer> position;
	private String nom;
	private int ensoleillement;
	/**
	 * Cr�� une planche dans le potager
	 * @param nomPlanche
	 * 			nom de la planche
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 * @param ensoleillement
	 * 			ensoleillement
	 */
	public Planche(String nomPlanche, int x, int y, int ensoleillement)
	{
		nom=nomPlanche;
		position=new Paire<Integer,Integer>(x,y);
		this.ensoleillement=ensoleillement;
		carres=new Carre[9];
		for(int i=0;i<carres.length;i++)
			carres[i]=new Carre(ensoleillement, i);
	}
	

	/**
	 * Modifie les positions de la planche
	 * @param x
	 * 			position x
	 * @param y
	 * 			position y
	 */
	public void setPositions(int x, int y)
	{
		position.setValeurs(x,y);
	}
	
	/**
	 * R�cup�re les carr�s formant la planche
	 * @return
	 * 			carr�s de la planche
	 */
	public Carre[] getCarres()
	{
		return carres;
	}
	/**
	 * R�cup�re l'ensoleillement de la planche
	 * @return
	 * 			ensoleillement de la planche
	 */
	public int getEnsoleillement()
	{
		return ensoleillement;
	}
	/**
	 * R�cup�re le nom de la planche
	 * @return
	 * 			nom de la planche. Si non existant, nom par defaut
	 */
	public String getNom()
	{
		return nom.equals("")?Main.getNomPlanche():nom;
	}
	/**
	 * R�cup�re la position x de la planche
	 * @return
	 * 			position x
	 */
	public int getX()
	{
		return position.getPremier();
	}
	/**
	 * R�cup�re la position y de la planche
	 * @return
	 * 			position y
	 */
	public int getY()
	{
		return position.getSecond();
	}
	/**
	 * R�cup�re le carr� � un certain indice
	 * @param i
	 * 			indice du carr�
	 * @return
	 * 			carr� � l'indice i
	 */
	public Carre getCarre(int i)
	{
		return carres[i];
	}
	/**
	 * Change les valeurs de la planche
	 * @param n
	 * 			nom de la planche
	 * @param o
	 * 			ensoleillement de la planche
	 */
	public void setValues(String n, String o)
	{
		nom=n;
		ensoleillement=(o.equals(Main.ENSOLEILLE)?0:1);//"Ensoleill�"
		for(Carre c : carres)
			if(c.getEnsoleillement()==-1)
				c.setEnsoleillement(ensoleillement);
	}
	/**
	 * Annule le plant sur chaque carr�, le retire de l'historique
	 */
	public void reinitialiserPlanche() 
	{
		for(Carre c : carres)
			c.annulerContenu();
	}

	/**
	 * Ajoute le plant � l'historique, vide chaque carr�
	 */
	public void viderPlanche() 
	{
		for(Carre c : carres)
			c.vider();
	}
	
	
	 //__________ REGLES ______
	
	/**
	 * Calcule la liste des carr�s adjacents
	 * @param c
	 * 			numero du carr� test�
	 * @return
	 * 			liste des carr�s adjacents
	 */
	public Vector<Carre> carreAdjacent(int c){
		
		 Vector <Carre> tabCarreAdjacentPossible = new Vector<Carre>() ;
		if(c+3 <= 8)
			tabCarreAdjacentPossible.add(carres[c+3]);
		if(c-3 >= 0)
			tabCarreAdjacentPossible.add(carres[c-3 ]);
		if(c+1 <= 8 && (c+1)%3 !=0)
			tabCarreAdjacentPossible.add(carres[c+1]);
		if(c-1 >= 0 && (c-1)%3 !=2)
				tabCarreAdjacentPossible.add(carres[c-1]);
		return tabCarreAdjacentPossible;
		
	}
	/**
	 * v�rifie les r�gles de cohabitation
	 * @param c
	 * 			carr� test�
	 * @param p
	 * 			plant choisi
	 * @return
	 * 			0 si possible, 2 | 4 | 4096 en fonction des conflits
	 */
	public int memeFamOuTypeCarreAdjacent(Carre c, Plant p)// PLACEMENT
	{ 
		Vector<Carre> carreAdjacent = this.carreAdjacent(c.getNumero());
		int res = 0;
		String famille="";
		String type="";
		famille=p.getFamille();
		type=p.getType();
		for(int i=0; i<carreAdjacent.size(); i++)
		{
			Plant plant=carreAdjacent.get(i).getHistorique().getSommet();
			if(plant!=null)
			{
				Boolean b=plant.verifAdj(p);
				if(b!=null)
				{
					if(!b.booleanValue())
						res |=4096;
				}
				else
				{
					if((((res>>1)&1)==0) && 
						!carreAdjacent.get(i).getHistorique().getDernierPlant().getSecond().existe() && 
						(plant.getFamille().equals(famille)))
					{
						res |= 2;
					}
					if(
						(((res>>2)&1)==0) && 
						!carreAdjacent.get(i).getHistorique().getDernierPlant().getSecond().existe() && 
						(plant.getType().equals(type)))
					{				
						res |= 4;
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * v�rifie les r�gles d'unicit�
	 * @param c
	 * 			carr� test�
	 * @param p
	 * 			plant choisi
	 * @return
	 * 			0 si possible, 8 si d�j� pr�sent
	 */
	public int plantDejaPresentSurPlanche(Carre c, Plant p)//PLACEMENT
	{ 
		int res=0;
		Paire<Date,Date> paire=null;
		for(int i=0; i<this.carres.length; i++)
		{
			paire=this.carres[i].getHistorique().getDernierPlant();		
			if(paire!=null&&!paire.getSecond().existe()&&p.equals(carres[i].getHistorique().getSommet()))
				res|=8; 
		}
		return res;
	}
	
	/**
	 * Cherche les carr�s pouvant faire de l'ombre ou pouvant �tre victime d'ombre
	 * @param c
	 * 			carr� test�
	 * @param pos
	 * 			position du sud
	 * @param x
	 * 			poid du vecteur x
	 * @param y
	 * 			poid du vecteur y
	 * @return
	 * 			le numero du carr� � tester (-1) si inexistant
	 */
	public int numeroOmbre(int c, Paire<Integer,Integer> pos, int x, int y)
	{
		if(c+pos.getPremier()*x > c && (c+pos.getPremier())%3==0)
			return -1;
		else if(c+pos.getPremier()*x < c && (c+pos.getPremier())%3==2)
			return -1;
		else if(c+c+pos.getPremier()*x+pos.getSecond()*y==8)
			return -1;
		else
			return c+pos.getPremier()*x+pos.getSecond()*y;
	}
	/**
	 * V�rifie que le carr� est ensoleill� si le plant a besoin de soleil
	 * @param c
	 * 			carr� test�
	 * @param p
	 * 			plant choisi
	 * @param pos
	 * 			position du sud
	 * @return
	 * 			0 si possible, 256 si conflit
	 */
	public int verifierEnsoleillement(Carre c, Plant p, Paire<Integer,Integer> pos)
	{
		int res=0;
		if(p.getEnsoleillement()==1)
		{
			int numero = numeroOmbre(c.getNumero(), pos, 1, 3);
			Plant plant=null;
			if(numero>=0 && numero<=8)
			{
				plant=carres[numero].getHistorique().getSommet();
				if(plant!=null)
				{
					if(plant.getTaille()>=60)
						res |= 256;
				}
			}
			if(c.getEnsoleillement()==0)
				res|=256;
		}
		return res;
	}
	/**
	 * V�rifie que le plant n'utilise pas un carr� ensoleill� inutilement
	 * @param c
	 * 			carr� test�
	 * @param p
	 * 			plant choisi
	 * @param pos
	 * 			position du sud
	 * @return
	 * 			0 si possible, 1024 si conflit
	 */
	public int verifierEnsoleillementSud(Carre c, Plant p, Paire<Integer,Integer> pos)
	{
		int res=0;
		if(p.getEnsoleillement()==0)
		{
			int x=pos.getPremier()+1;
			int y=pos.getSecond()+1;
			int posC=x+y*3;
			Vector<Carre>sud=carreAdjacent(posC);
			sud.add(carres[posC]);
			if(c.getNumero()!=4 && sud.contains(c))
				res |= 1024;
		}
		return res;
	}
	
	/**
	 * V�rifie que le carr� ne risque pas de faire de l'ombre
	 * @param c
	 * 			carr� test�
	 * @param p
	 * 			plant choisi
	 * @param pos
	 * 			position du sud
	 * @return
	 * 			0 si possible, 512 si conflit
	 */
	public int verifierPoseOmbre(Carre c, Plant p, Paire<Integer,Integer> pos)
	{
		int res=0;
		if(p.getTaille()>=60)
		{
			int numero = numeroOmbre(c.getNumero(), pos, -1, -3);
			Plant plant=null;
			if(numero>=0 && numero<=8)
			{
				plant=carres[numero].getHistorique().getSommet();
				if(plant!=null)
				{
					if(plant.getEnsoleillement()==1)
						res |= 512;
				}
			}
		}
		return res;
	}
	
}
	

