package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Skill extends Cogniton{

	
	public Skill()
	{
		
	}
	
	public Skill(Esprit e )
	{
		super(e);
		//modifierProjets(this.getTabPlan(), Boolean add, Esprit e)

	}

	public Color getColor()
	{
		return new Color(116,208,241);
	}


	public String getType()
	{
		return "Skill";  // Par d�faut
	}

	
	/**
	 * Les skills peuvent �tre enseign�es � d'autres
	 * Plus le t�t de transfert est �lev�, plus c'est ais�
	 */
	public int getTauxTransfert()
	{
		return 0;
	}
	
	public String[] getChaine()
	{
		return null;  // Par d�faut
	}
}
