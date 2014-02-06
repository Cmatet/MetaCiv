package civilisation.individu.plan;

import civilisation.individu.Esprit;
import civilisation.individu.Humain;

/** 
 * Classe abstraite repr�sentant un "r�flexe", un plan se d�clenchant automatiquement si le seuil est atteint
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class Reflexe extends Plan{

	public Reflexe(Esprit e, Humain h) {
		super(e, h);
	}

	/**
	 * Le seuil � partir duquel le r�flexe se d�clenche (10 par d�faut)
	 * @return le seuil du r�flexe
	 */
	public int getSeuil() {
		return 10;
	}
	
	/**
	 * @return la priorit� de ce r�flexe par rapport aux autres (dans le cas ou plusieurs sont d�clench�s simultanement
	 */
	public int getPriorite() {
		return 0;
	}
	
	/**
	 * Le seuil � partir duquel le r�flexe se d�clenche (10 par d�faut)
	 * @return le seuil du r�flexe
	 */
	public boolean isTriggered() {
		return (getSeuil() <= getPoids());
	}
	
	public int getType(){
		return 1; //1 = reflexe
	}
	
	/**
	 * Modifie le poids du projet d'un poids p,
	* Ne modifie pas le poids total
	 */
	public void changerPoids(int p)
	{

		poids += p;
	}
}
