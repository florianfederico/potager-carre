package gui;

import gui.components.icones.*;
import gui.components.tools.PotagerMenu;
import gui.mainPanels.*;
import gui.panels.legumotheque.Legumotheque;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import objects.Carre;
import objects.Planche;
import objects.Potager;
import common.ProgramDatas;

public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	private ProgramDatas datas;
	private AccueilPanel accueilPanel;
	private PotagerPanel potagerPanel;
	private PlanchePanel planchePanel;
	private Legumotheque legumotheque;
	private JPanel pane;
	private PotagerMenu menu;
	private IconeManager iconManager;
	/**
	 * Associe les donn�es � la fenetre
	 * @param d
	 * 			Donn�es de l'application
	 */
	public void setDatas(ProgramDatas d)
	{
		datas=d;
	}

	/**
	 * Change le carr� selectionner
	 * @param c
	 * 			carr� s�lectionn�
	 */
	public void setCarre(Carre c)
	{
		planchePanel.setCarre(c);
	}
	
	/**
	 * Enregistre le potager
	 */
	public void enregistrerJardin()
	{
		
		Potager potager=potagerPanel.getPotager();
		if(potager.getNomFichier().equals(""))
		{
			String nomFichier;
			nomFichier=JOptionPane.showInputDialog (datas.getLangueElement("demandes.nomFichier"));
			if(nomFichier!=null&&!nomFichier.equals(""))
			{
				if(potager.setNomFichier(nomFichier)==1)
				{
					JOptionPane.showMessageDialog(this, datas.getLangueElement("erreurs.nomFichier"), datas.getLangueElement("noms.erreur"), JOptionPane.ERROR_MESSAGE);
					enregistrerJardin();
					return;
				}
				datas.getListeJardins().add(potager);
				accueilPanel.updateListe();
			}
			else
				return;
		}
		try 
		{
			potager.save();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	/**
	 * Passe en mode vue planche avec la planche selectionn�e
	 */
	public void ouvrirPlanche()
	{
		Planche p = potagerPanel.getPotagerVue().getPotagerContent().getFullPlanche().getPlanche();
		planchePanel.setCurrentPlanche(p, potagerPanel.getPotager());
		updatePanel(planchePanel);
	}
	/**
	 * Permet d'enregistrer en renommant un fichier
	 * @see Fenetre#enregistrerJardin()
	 */
	public void enregistrerSous()
	{
		Potager potager=potagerPanel.getPotager();
		if(!potager.getNomFichier().equals(""))
		{
			String nomFichier;	
			nomFichier=JOptionPane.showInputDialog (datas.getLangueElement("demandes.nomFichier"));
			if(nomFichier!=null)
			{
				if(potager.setNomFichier(nomFichier)==1 || nomFichier.equals(""))
				{
					JOptionPane.showMessageDialog(this, datas.getLangueElement("erreurs.nomFichier"), datas.getLangueElement("noms.erreur"), JOptionPane.ERROR_MESSAGE);
					enregistrerSous();
					return;
				}
				datas.getListeJardins().add(potager);
				accueilPanel.updateListe();
			}
			else
				return;
		}
		enregistrerJardin();
	}
	/**
	 * Initialise la barre de menus en haut de l'application
	 */
	public void setMenuIcones()
	{
		menu=new PotagerMenu(this);
		setMenuBar(menu);
		initIcones();		
	}
	/**
	 * Initialise la totalit� des icones de l'application
	 */
	private void initIcones()
	{
		iconManager=new IconeManager();
		iconManager.ajout(new AfficherGrille(this, datas.getIcones().get("AfficherGrille")));
		iconManager.ajout(new DeplacerPlanche(this, datas.getIcones().get("DeplacerPlanche")));
		iconManager.ajout(new EditerPlanche(this, datas.getIcones().get("EditerPlanche")));
		iconManager.ajout(new EditerPotager(this, datas.getIcones().get("EditerPotager")));
		iconManager.ajout(new Enregistrer(this, datas.getIcones().get("Enregistrer")));
		iconManager.ajout(new FermerPotager(this, datas.getIcones().get("FermerPotager")));
		iconManager.ajout(new NouveauPotager(this, datas.getIcones().get("NouveauPotager")));
		iconManager.ajout(new NouvellePlanche(this, datas.getIcones().get("NouvellePlanche")));
		iconManager.ajout(new OuvrirPlanche(this, datas.getIcones().get("OuvrirPlanche")));
		iconManager.ajout(new OuvrirPotager(this, datas.getIcones().get("OuvrirPotager")));
		iconManager.ajout(new ReinitialiserPlanche(this, datas.getIcones().get("ReinitialiserPlanche")));
		iconManager.ajout(new SupprimerPlanche(this, datas.getIcones().get("SupprimerPlanche")));
		iconManager.ajout(new SupprimerPotager(this, datas.getIcones().get("SupprimerPotager")));
		iconManager.ajout(new ViderPlanche(this, datas.getIcones().get("ViderPlanche")));
		iconManager.ajout(new RetourPotager(this, datas.getIcones().get("RetourPotager")));
		iconManager.ajout(new ModifierEnsoleillement(this, datas.getIcones().get("ModifierEnsoleillement")));
		iconManager.ajout(new AnnulerLegume(this, datas.getIcones().get("AnnulerLegume")));
		iconManager.ajout(new ViderCarre(this, datas.getIcones().get("ViderCarre")));
	}

	/**
	 * Supprimer un potager de l'application
	 * @param p
	 * 			potager � supprimer
	 */
	public void removePotager(Potager p)
	{
		File jardinFile = new File(datas.getInformation("path.potager")+p.getNomFichier());
		jardinFile.delete();
		datas.getListeJardins().remove(p);
		accueilPanel.updateListe();
	}
	
	/**
	 * R�cup�re le texte d'un conflit de r�gle
	 * @param i
	 * 			cl� de la r�gle
	 * @return
	 * 			texte associ� � la cl�
	 * @see ProgramDatas#getRegle(int)
	 */
	public String getRegle(int i)
	{
		return datas.getRegle(i);
	}

	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager et annule les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setAction(int i)
	{
		potagerPanel.setAction(i);
	}
	/**
	 * Change l'action en cour de l'utilisateur sur la vue potager sans annuler les autres
	 * @param i
	 * 			ID de l'action
	 */
	public void setActionUnique(int i)
	{
		potagerPanel.setActionUnique(i);		
	}
	/**
	 * Met les informations de la planche � jour
	 */
	public void updatePlancheInfos()
	{
		potagerPanel.updatePlancheInfos();	
	}
	/**
	 * Met les informations du potager � jour
	 */
	public void updatePotagerInfos()
	{
		potagerPanel.updatePotagerInfos();	
	}
	/**
	 * R�cup�re la legumoth�que
	 * @return
	 * 			legumoth�que
	 */
	public Legumotheque getLegumotheque()
	{
		return legumotheque;
	}
	/**
	 * Initialise les panels de l'application
	 */
	public void initPanels()
	{
		legumotheque=new Legumotheque(this);
		accueilPanel=new AccueilPanel(this);
		potagerPanel=new PotagerPanel(this);
		planchePanel=new PlanchePanel(this);
		;
		pane=new JPanel()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private Image img=new ImageIcon(datas.getInformation("path.imagesDivers")+datas.getInformation("fonds.accueil")).getImage().getScaledInstance
			(
					Integer.parseInt(datas.getInformation("dimensions.mainPanel").split(",")[0]), 
					Integer.parseInt(datas.getInformation("dimensions.mainPanel").split(",")[1]), 
					Image.SCALE_DEFAULT
			);
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(img,0,0,null);
				repaint();
		    }
		};
		pane.setPreferredSize(new Dimension(Integer.parseInt(datas.getInformation("dimensions.mainPanel").split(",")[0]),Integer.parseInt(datas.getInformation("dimensions.mainPanel").split(",")[1])));
		legumotheque.addObserver(potagerPanel);
		legumotheque.addObserver(planchePanel);

		pane.add(legumotheque);
	}
	/**
	 * Supprime la planche selectionn�e
	 */
	public void supprimerPlanche()
	{
		potagerPanel.supprimerPlanche();
	}
	/**
	 * Ajoute une nouvelle planche au potager
	 */
	public void ajoutPlanche()
	{
		potagerPanel.ajoutPlanche();
	}
	/**
	 * Supprime tous les plants de chaque carr� de la planche selectionn�e sans les ajouter � leur historique
	 */
	public void reinitialiserPlanche()
	{
		potagerPanel.reinitialiserPlanche();
	}
	/**
	 * Ajoute tous les plants de chaque carr� � l'historique
	 */
	public void viderPlanche() 
	{
		potagerPanel.viderPlanche();
	}
	/**
	 * Renvoie le panel de la vue potager
	 * @return
	 * 			panel vue potager
	 */
	public PotagerPanel getPotagerPanel()
	{
		return potagerPanel;
	}
	/**
	 * Renvoie le panel de l'accueil
	 * @return
	 * 			panel accueil
	 */
	public AccueilPanel getAccueilPanel()
	{
		return accueilPanel;
	}
	/**
	 * Initialise la fenetre principale de l'application
	 */
	public void initFenetre()
	{
		setTitle(datas.getLangueElement("noms.FENETRE_PRINCIPALE"));
		setSize(Integer.parseInt(datas.getInformation("dimensions.fenetre").split(",")[0]),Integer.parseInt(datas.getInformation("dimensions.fenetre").split(",")[1]));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage(datas.getInformation("path.imagesIcones")+datas.getInformation("default.iconeImage"));
		
		setIconImage(img);
		setVisible(true);
		updatePanel(accueilPanel);
	}
	/**
	 * Permet de regarder si le nom du potager existe
	 * @param nom
	 * 			nom du potager
	 * @return
	 * 			true s'il existe d�j�
	 */
	public boolean nomPotagerExiste(String nom)
	{
		Vector<Potager> jardins=datas.getListeJardins();
		boolean existe=false;
		Potager p=null;
		for(Iterator<Potager> it=jardins.iterator();it.hasNext()&&!existe;)
		{
			p=it.next();
			existe=p.getNom().equals(nom)&&!p.getNomFichier().equals(getPotager().getNomFichier());
		}
		return existe;
	}

	/**
	 * Permet de regarder si le nom de la planche existe
	 * @param nom
	 * 			nom de la planche
	 * @return
	 * 			true s'il existe
	 */
	public boolean nomPlancheExiste(String nom)
	{
		Potager p = potagerPanel.getPotager();
		Vector<Planche>planches= p.getPlanches();
		
		boolean existe=false;
		Planche pIterator=null;
		for(Iterator<Planche> it=planches.iterator();it.hasNext()&&!existe;)
		{
			pIterator=it.next();
			existe=pIterator.getNom().equals(nom)&&pIterator!=potagerPanel.getPotagerVue().getPotagerContent().getFullPlanche().getPlanche();
		}
		return existe;
	}
	/**
	 * Permet de r�cup�rer le potager en cour de modification
	 * @return
	 * 			Potager en cour d'�dition
	 */
	public Potager getPotager()
	{
		return potagerPanel.getPotager();
	}
	/**
	 * Action lors de la fermeture de l'application
	 */
	public void onClose()
	{
		String choix[]=
		{
			datas.getLangueElement("demandes.QUITTER_OPTION1"),
			datas.getLangueElement("demandes.QUITTER_OPTION2")
		};
		int PromptResult = JOptionPane.showOptionDialog(null, datas.getLangueElement("demandes.VALIDATION_QUITTER"), datas.getLangueElement("noms.FENETRE_VALIDATION_QUITTER"), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, choix, choix);
		if(PromptResult==0) 
			System.exit(0);
		else
			validate();
	}
	/**
	 * Events possibles sur la fenetre
	 */
	public void initEvents()
	{
		addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent we)
				{ 
					onClose();
				}
			}
		);
	}
	/**
	 * Mise � jour du panel � afficher
	 * @param pan
	 * 			panel � afficher
	 */
	public void updatePanel(JPanel pan)
	{
		pane.removeAll();
		if(pan==accueilPanel)
		{
			menu.activeItems(false);
			accueilPanel.deSelect();
		}
		else
		{
			menu.activeItems(true);
			pane.add(legumotheque);
		}
		pane.add(pan);
		pane.updateUI();
		setContentPane(pane);
	}
	/**
	 * Permet de r�cup�rer les donn�es de l'application
	 * @return
	 * 			Donn�es de l'application contenues dans les fichiers XML
	 */
	public ProgramDatas getDatas()
	{
		return datas;
	}
	/**
	 * Initialisation de la vue potager
	 */
	public void initJardin()
	{
		potagerPanel=new PotagerPanel(this);
		potagerPanel.jardinPopUp();
	}
	/**
	 * Charge les donn�es du potager dans la vue potager
	 * @param j
	 * 			Potager a afficher
	 */
	public void loadPotager(Potager j)
	{
		potagerPanel=new PotagerPanel(this,j);
		updatePanel(potagerPanel);
	}
	/**
	 * Affiche le message de placement de la boussole
	 */
	public void validerPotager()
	{
		potagerPanel.boussolePopUp();
		updatePanel(potagerPanel);
	}
	/**
	 * Permet de supprimer le plant se trouvant dans le carr� selectionn� et de l'ajouter � l'historique
	 */
	public void viderCarre() {
		planchePanel.viderCarre();
	}
	/**
	 * Permet d'inverser l'ensoleillement du carr�
	 */
	public void swapEnsoleillement() {
		planchePanel.swapEnsoleillement();
	}
	/**
	 * Permet de supprimer le plant se trouvant dans le carr� selectionn� sans l'ajouter � l'historique
	 */
	public void annulerLegume() {
		planchePanel.annulerLegumes();
	}

	/**
	 * Envoie l'instance de l'objet de gestionnaire d'icones
	 * @return
	 * 			gestionnaire d'icones
	 */
	public IconeManager getIconeManager() 
	{
		return iconManager;
	}
}
