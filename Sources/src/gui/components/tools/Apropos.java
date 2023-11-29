package gui.components.tools;

import gui.Fenetre;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <b>Apropos est la classe permattant d'afficher la section A Propos</b>
 * 
 * 
 * @author PotagerTeam
 *
 */
public class Apropos extends JFrame
{
	/**
	 * Identifiant de sérialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Correspond à l'image mit en fond d'écran.
	 */
	private Image img;
	/**
	 * Permet de récupérer toute les données de l'application.
	 */
	private Fenetre mainFrame;
	
	/**
	 * Constructeur Apropos.
	 * <p>
	 * A la construction d'un objet Apropos, la fenêtre est créé. 
	 * On y insert ensuite une image en fond d'écran et le texte 
	 * de présentation du projet.
	 * </p>
	 * 
	 * @param f
	 * 				L'ensemble de l'application.
	 * 
	 * @see Fenetre
	 */
	public Apropos(Fenetre f)
	{
	    this.setTitle(f.getDatas().getLangueElement("menu.propos"));
	    mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.icones")).getImage().getScaledInstance
		(
				350, 
				240, 
				Image.SCALE_DEFAULT
		);
	    setSize(350,240);
		setResizable(false);
	    this.setLocationRelativeTo(null);         
	    this.setVisible(true);
	    
	   	    
	    JPanel jp2 = new JPanel()
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
	    jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    
	   
	   //A ne pas stoquer en XML 
	    JLabel jl3 = new JLabel("<html>"
	    						+ "<head><style>body{}</style></head>"
	    						+ "<body>"
	    						+ "Ce projet a été réalisé dans le cadre du projet de deuxième année du<br>DUT-Informatique de Lannion.<br><br>"
	    						+ "Ce logiciel a été conçu et développé par une équipe de 7 personnes<br><br>"
	    						+ "<strong>FEDERICO Florian</strong> : Chef de Projet<br>"
	    						+ "<strong>GUILLAUME Corentin</strong> : Rédacteur <br>"
	    						+ "<strong>THOMAS Anthony</strong> : Designer<br>"
	    						+ "<strong>LOCHET Mathieu</strong> : Architecte et intégrateur logiciel <br>"
	    						+ "<strong>PIN Kévin</strong> : Développeur logiciel<br>"
	    						+ "<strong>ODIC Fabien</strong> : Réalisateur des fiches légumes <br> "
	    						+ "<strong>THO Jérémy</strong> : Infographiste<br><br>"
	    						+ "Projet réalisé sous la tutelle de <strong>M. Vialat</strong>"
	    						+ "</body>"
	    						+ "</html>");
	    
	    
	    jp2.add(jl3);
	    this.add(jp2);
	  }
}

