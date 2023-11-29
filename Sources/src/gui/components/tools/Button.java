package gui.components.tools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Button est la classe permettant la redéfinition de la classe JButton
 * 
 * La classe Button hérite de la classe JButton.
 * 
 * @author PotagerTeam
 * @see JButton
 */
public class Button extends JButton
{
	/**
	 * Identifiant de sérialisation du JPanel.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur Button.
	 * <p>
	 * A la construction d'un objet Button, la super-classe JButton est appeler avec un titre
	 *  puis la méthode initButton afin de réaliser les modification sur le boutton.
	 *  </p>
	 * @param c
	 * 				Le titre du boutton.
	 * 
	 * @see JButton
	 */
	public Button(String c)
	{
		super(c);
		initButton();
	}
	/**
	 * Constructeur Button.
	 * <p>
	 * A la construction d'un objet Button, la super-classe JButton est appeler sans titre
	 *  puis la méthode initButton afin de réaliser les modification sur le boutton.
	 *  </p>
	 *  
	 */
	public Button()
	{
		super();
		initButton();
	}
	public void updateUI()
	{
		super.updateUI();
	}
	/**
	 * Procède au ajout des évenement sur le boutton 
	 */
	public void initEvent()
	{
		this.addMouseListener
		(
			new MouseListener()
			{
				//Lorsque l'utilisateur clique
				public void mouseClicked(MouseEvent e)
				{
				}
				//Lorsque la souris entre dans le panel
				public void mouseEntered(MouseEvent e)
				{
					if(getForeground()!=Color.GRAY)
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
				}
				//Lorsque la souris sort du panel
				public void mouseExited(MouseEvent e) 
				{
					setCursor(Cursor.getDefaultCursor());	
				}
				
				public void mousePressed(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) 
				{
				}
			}
		);
	}
	/**
	 * Modifie le boutton
	 */
	public void initButton()
	{
		setFont(new Font("Arial", Font.BOLD, 12));
		setVerticalTextPosition(SwingConstants.CENTER); 
		setHorizontalTextPosition(SwingConstants.CENTER);
		initEvent();
	}
}
