package gui.mainPanels.observers;

import common.ProgramDatas;

public interface Observer 
{
	/**
	 * Envoie les données de l'application
	 * @return
	 * 			Données de l'application
	 */
	public ProgramDatas getDatas();
	/**
	 * Met à jour la vue en fonction des observables
	 */
	public void update();
}
