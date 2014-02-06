package civilisation.world;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import civilisation.AddOn;
import civilisation.Configuration;
import civilisation.individu.cognitons.SondeACognitons;

import edu.turtlekit2.kernel.agents.Observer;
import edu.turtlekit2.kernel.environment.Patch;

/** 
 * Contr�leur du monde
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class WorldCtrl extends Observer
{
	private int tick = 0;
	private World world = World.getInstance();
	public static ArrayList<SondeACognitons> sondes = new ArrayList<SondeACognitons>();
	
	
	@Override
	public void setup()
	{
		super.setup();
		World.getInstance().setup();
	}
	
	
	
	@Override
	public void watch()
	{
		
		tick++;
		
		/* Gestion des ressources*/
		if (tick %100 == 0)
		{
			for (int x = 0; x < world.getWidth(); x++)
			{
				for (int y = 0; y < world.getHeight(); y++)
				{
					Terrain t = Configuration.couleurs_terrains.get(world.grid[x][y].getColor());
					for (int i = 0 ; i < t.getPheroCroissance().size() ; i++) {
						world.grid[x][y].incrementPatchVariable(t.getPheromones().get(i).getNom(), t.getPheroCroissance().get(i));
					}
					
					if(world.grid[x][y].smell("passage") > Configuration.EffacementRoute || countCouleurVoisine(world.grid[x][y] , World.getColorForets()) >= 1 )
						{
					world.grid[x][y].setPatchVariable("passage",world.grid[x][y].smell("passage")-Configuration.EffacementRoute);
						}
		else
				{
		world.grid[x][y].setPatchVariable("passage",0);
				}	
					if(world.grid[x][y].isMarkPresent("Route") && world.grid[x][y].smell("passage")< Configuration.EffacementRoute)
					{
						//effacer route
					}
					
					if(world.grid[x][y].getColor() == World.getColorPlaines() && world.grid[x][y].smell("passage") < Configuration.seuilEmergenceForet )
					{
						world.grid[x][y].setColor(World.getColorForets());
						
					}
				}
			}
		}
	}

	/**Verifie si un patch voisin a la couleur donnee.
	 * Retourne vrai si la couleur est presente, faux sinon.
	 */
	private int countCouleurVoisine(Patch p, Color c)
	{
		int resultat = 0;
		Patch voisins[] = p.getNeighbors();

		for (int l = 0; l < voisins.length; l++)
		{
			if (voisins[l].getColor() == c)
			{
				resultat++;
			}
		}
		
		return resultat;
	}
	
}



