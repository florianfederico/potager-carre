package gui.mainPanels.observers;

import objects.Potager;

public interface PotagerObserver extends Observer
{
	/**
	 * Envoie le potager observ�
	 * @return
	 * 			Potager en cour d'�dition
	 */
	public Potager getPotager();
}
