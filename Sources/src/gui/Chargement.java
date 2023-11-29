package gui;



import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.dom4j.DocumentException;

import common.Main;
import common.ProgramDatas;

public class Chargement extends JFrame
{
	private static final long serialVersionUID = 1L;
	private ProgramDatas datas;
	private Fenetre mainFrame;
	/**
	 * Dessin des images sur le splash screen
	 * @param g
	 * 			Element graphique
	 * @param id
	 * 			Numero de l'élément à afficher
	 * @param d
	 * 			Dimension de l'image
	 * @return
	 * 			faux lorsque l'application est complètement chargée
	 * @throws DocumentException
	 * 			Chargement XML
	 * @throws FileNotFoundException
	 * 			Chargement XML
	 * @throws ClassNotFoundException
	 * 			Chargement XML
	 * @throws IOException
	 * 			Chargement XML
	 */
	private boolean renderSplashFrame(Graphics2D g, int id, Dimension d) throws DocumentException, FileNotFoundException, ClassNotFoundException, IOException 
	{
        g.setComposite(AlphaComposite.Clear);
        g.setPaintMode();
		ImageIcon icon = new ImageIcon("./image/liste/"+(id+1)+".png");
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);

        switch(id)
        {
        	case 0:
        		datas.chargerPlantsInformations();
        	break;
        	case 1:
        		datas.chargerPlants();
        	break;
        	case 2:
        		datas.chargerPotagers();
        	break;
        	case 3:
        		Main.setConstantes();
        		mainFrame.setDatas(datas);
        		mainFrame.setMenuIcones();
            break;
        	case 4:
        		mainFrame.initPanels();
            break;
        	case 5:
        		mainFrame.initEvents();
        	break;
        	case 6:
        	break;
        	default:
        		return false;
        }
        return true;
    }
	/**
	 * Création d'une instance de chargement de l'application
	 * @param f
	 * 			Instance de fenetre de l'application
	 * @throws FileNotFoundException
	 * 			Chargement XML
	 * @throws ClassNotFoundException
	 * 			Chargement XML
	 * @throws DocumentException
	 * 			Chargement XML
	 * @throws IOException
	 * 			Chargement XML
	 */
    public Chargement(Fenetre f) throws FileNotFoundException, ClassNotFoundException, DocumentException, IOException
    {
        super("");
        setSize(300, 200);
        setLayout(new BorderLayout());
        addWindowListener(closeWindow);
        
		datas=new ProgramDatas();
        datas.chargerInformations();
        datas.chargerLangues();
        mainFrame=f;
    }
    /**
     * Début du chargement
     * @throws FileNotFoundException
	 * 			Chargement XML
     * @throws IllegalStateException
	 * 			Chargement XML
     * @throws DocumentException
	 * 			Chargement XML
     * @throws ClassNotFoundException
	 * 			Chargement XML
     * @throws IOException
	 * 			Chargement XML
     * @throws InterruptedException
	 * 			Chargement XML
     */
    public void start() throws FileNotFoundException, IllegalStateException, DocumentException, ClassNotFoundException, IOException, InterruptedException
    {
        //AFFICHAGE GRAPHIQUE DU SPLASHSCREEN
        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null)
            return;
        Graphics2D g = splash.createGraphics();
        if (g == null) 
            return;
        
        for(int i=0;renderSplashFrame(g,i,splash.getSize());i++)
        {
            try
            {
            	splash.update();
            	if(i%2==0)
            		Thread.sleep(0);
            	else
            		Thread.sleep(0);
            }
            catch(InterruptedException e){}
        }
        Thread.sleep(500);
    }
    
    private static WindowListener closeWindow = new WindowAdapter()
    {
        public void windowClosing(WindowEvent e)
        {
            e.getWindow().dispose();
        }
    };
    /**
     * Récupération des données de l'application
     * @return
     * 			Données chargées
     */
    public ProgramDatas getDatas()
    {
    	return datas;
    }
}
