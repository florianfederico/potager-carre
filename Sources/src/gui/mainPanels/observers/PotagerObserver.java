package gui.mainPanels.observers;

import objects.Potager;

public interface PotagerObserver extends Observer
{
	/**
	 * Envoie le potager observé
	 * @return
	 * 			Potager en cour d'édition
	 */
	public Potager getPotager();
}
