package gui.panels.informationsFrame.carres;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import gui.Fenetre;
import gui.panels.ScrollablePanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

public class ReglesFrame extends JDialog
{
	private static final long serialVersionUID = 1L;
	private int possibilites;
	private JPanel panel;
	private ScrollablePanel regles;
	private Fenetre mainFrame;
	private Image img;
	/**
	 * Crée une fenetre affichant la liste des conflits de règles
	 * @param i
	 * 			entier contenant les messages d'erreurs (bitmask)
	 * @param f
	 * 			Frame principale
	 */
	public ReglesFrame(int i, Fenetre f)
	{
		super(f, f.getDatas().getLangueElement("noms.regleErreur"), true);
		possibilites=i;
		mainFrame=f;
		img=new ImageIcon(mainFrame.getDatas().getInformation("path.imagesDivers")+mainFrame.getDatas().getInformation("fonds.icones")).getImage().getScaledInstance
		(
				470, 
				270, 
				Image.SCALE_DEFAULT
		);
		initComponents();
		initFrame();
	}
	/**
	 * Initialise les composants
	 */
	public void initComponents()
	{
		panel=new JPanel()
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
		JScrollPane scroll=new JScrollPane();
		regles=new ScrollablePanel();
		scroll.setViewportView(regles);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		regles.setLayout(new GridLayout(0,1));
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		scroll.setPreferredSize(new Dimension(450,200));
		
		panel.add(new JLabel("<html><span><strong>"+mainFrame.getDatas().getLangueElement("erreurs.reglesConflit1")+"<br>"+mainFrame.getDatas().getLangueElement("erreurs.reglesConflit2")+"</strong></span></html>"));
		panel.add(scroll);

		for(int i=0, n=1; possibilites >= Math.pow(2,i); i++)
		{
			if(((possibilites>>i)&1)==1)
				regles.add(new JLabel("<html><span> <strong>"+(n++)+"</strong> - <em>"+mainFrame.getRegle(i)+"</em></span></html>"));
		}
	}
	/**
	 * Initialise la fenetre
	 */
	public void initFrame()
	{
		setSize(470,270);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(panel);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}
}
