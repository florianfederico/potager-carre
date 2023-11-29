package gui.mainPanels.observers;

import common.ProgramDatas;

public interface Observer 
{
	/**
	 * Envoie les donn�es de l'application
	 * @return
	 * 			Donn�es de l'application
	 */
	public ProgramDatas getDatas();
	/**
	 * Met � jour la vue en fonction des observables
	 */
	public void update();
}
