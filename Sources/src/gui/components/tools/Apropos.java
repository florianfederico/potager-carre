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
	 * Identifiant de s�rialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Correspond � l'image mit en fond d'�cran.
	 */
	private Image img;
	/**
	 * Permet de r�cup�rer toute les donn�es de l'application.
	 */
	private Fenetre mainFrame;
	
	/**
	 * Constructeur Apropos.
	 * <p>
	 * A la construction d'un objet Apropos, la fen�tre est cr��. 
	 * On y insert ensuite une image en fond d'�cran et le texte 
	 * de pr�sentation du projet.
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
	    						+ "Ce projet a �t� r�alis� dans le cadre du projet de deuxi�me ann�e du<br>DUT-Informatique de Lannion.<br><br>"
	    						+ "Ce logiciel a �t� con�u et d�velopp� par une �quipe de 7 personnes<br><br>"
	    						+ "<strong>FEDERICO Florian</strong> : Chef de Projet<br>"
	    						+ "<strong>GUILLAUME Corentin</strong> : R�dacteur <br>"
	    						+ "<strong>THOMAS Anthony</strong> : Designer<br>"
	    						+ "<strong>LOCHET Mathieu</strong> : Architecte et int�grateur logiciel <br>"
	    						+ "<strong>PIN K�vin</strong> : D�veloppeur logiciel<br>"
	    						+ "<strong>ODIC Fabien</strong> : R�alisateur des fiches l�gumes <br> "
	    						+ "<strong>THO J�r�my</strong> : Infographiste<br><br>"
	    						+ "Projet r�alis� sous la tutelle de <strong>M. Vialat</strong>"
	    						+ "</body>"
	    						+ "</html>");
	    
	    
	    jp2.add(jl3);
	    this.add(jp2);
	  }
}

