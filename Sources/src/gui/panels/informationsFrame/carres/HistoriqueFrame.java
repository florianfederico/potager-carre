package gui.panels.informationsFrame.carres;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import gui.Fenetre;
import gui.panels.vues.planche.informations.affichageCarre.HistoriquePanel;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import objects.Carre;

public class HistoriqueFrame extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carre carre;
	private HistoriquePanel panel;
	private Fenetre mainFrame;
	private Image img;
	/**
	 * Crée une instance graphique de l'historique d'un carré
	 * @param c
	 * 			Carré possédant l'historique
	 * @param f
	 * 			Frame principale
	 */
	public HistoriqueFrame(Carre c, Fenetre f)
	{
		super(f, f.getDatas().getLangueElement("noms.historique"), true);
		mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.icones")).getImage().getScaledInstance
		(
				320, 
				190, 
				Image.SCALE_DEFAULT
		);
		carre=c;
		initComponents();
		initFrame();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		panel = new HistoriquePanel(carre, -1, new Dimension(300,150), mainFrame)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) 
			{
				super.paintComponent(g);
				g.drawImage(img,0,0,null);
				repaint();
		    }
		};
		panel.setCarre(carre);
	}
	/**
	 * Initialise la fenetre
	 */
	public void initFrame()
	{
		setSize(320,190);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(panel);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
