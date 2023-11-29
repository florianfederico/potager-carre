package gui.components.tools;

import gui.Fenetre;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PotagerMenu extends MenuBar
{
	private static final long serialVersionUID = 1L;
	private Menu file;
	private Menu about;
	private MenuItem save;
	private MenuItem saveAgain;
	private MenuItem exit;
	private MenuItem aPropos;
	private Fenetre mainFrame;
	/**
	 * Barre de menu de l'application
	 * @param f
	 * 			Frame principale
	 */
	public PotagerMenu(Fenetre f)
	{
		mainFrame=f;
		initMenu();
		initMenuItem();
		initEvents();
		initCombinaisons();
		initMenuBar();
	}
	/**
	 * Initialisation de la barre de menu
	 */
	public void initMenu()
	{
		file=new Menu(mainFrame.getDatas().getLangueElement("menu.fichier"));
		about=new Menu(mainFrame.getDatas().getLangueElement("menu.aide"));
	}
	/**
	 * Active ou desactive des items
	 * @param bool
	 * 			Active ou desactive
	 */
	public void activeItems(boolean bool)
	{
		save.setEnabled(bool);
		saveAgain.setEnabled(bool);
	}
	/**
	 * Initialise les composants de la barre de menu
	 */
	public void initMenuItem()
	{
		aPropos = new MenuItem(mainFrame.getDatas().getLangueElement("menu.propos"));
		save = new MenuItem(mainFrame.getDatas().getLangueElement("menu.enregistrer"));	
		saveAgain = new MenuItem(mainFrame.getDatas().getLangueElement("menu.enregistrerSous"));
		exit = new MenuItem(mainFrame.getDatas().getLangueElement("menu.quitter"));	
		save.setEnabled(false);
		saveAgain.setEnabled(false);
	}
	/**
	 * Initialise les composants inférieurs
	 */
	public void initCombinaisons()
	{
		file.add(save);
		file.add(saveAgain);
		file.add(exit);
		about.add(aPropos);
	}
	/**
	 * Crée la barre de menu
	 */
	public void initMenuBar()
	{
	    add(file);
	    add(about);
	}
	
	/**
	 * Initialise les events
	 */
	public void initEvents()
	{
		exit.addActionListener
	    (
	    	new ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent e) 
	    		{
					mainFrame.onClose();
	    		}
	    	}
	    );
		save.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					mainFrame.enregistrerJardin();
				}
			}
		);
		saveAgain.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					mainFrame.enregistrerSous();
				}
			}
		);
		aPropos.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					new Apropos(mainFrame);
				}
			}
		);
	}
}
