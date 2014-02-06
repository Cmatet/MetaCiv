package civilisation.individu.cognitons;

import java.awt.Color;

import civilisation.individu.Esprit;

public abstract class Meme extends Cogniton{

	public Meme()
	{
		
	}
	
	public Meme(Esprit e)
	{
		super(e);
	}

	public String getType()
	{
		return "Meme";
	}
	
	/**
	 * Les memes peuvent �tre transmis � d'autres
	 * Plus le t�t de transfert est �lev�, plus c'est ais�
	 */
	public int getTauxTransfert()
	{
		return 0;
	}
	
	public Color getColor()
	{
		return Color.PINK;
	}
	
}
