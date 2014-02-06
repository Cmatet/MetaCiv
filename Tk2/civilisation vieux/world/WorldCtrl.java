package civilisation.world;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import civilisation.Configuration;
import civilisation.individu.cognitons.Cogniton;
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
	
	
	public void setup()
	{
		super.setup();
		World.getInstance().setup();
		
		
		/* N'a plus lieu d'�tre avec MetaCiv
		// Initialisation des SondesACognitons 
		File[] files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/beliefs").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        sondes.add(new SondeACognitons("civilisation.individu.cognitons.beliefs."+file.getName().split("\\.class")[0]));
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/memes").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        sondes.add(new SondeACognitons("civilisation.individu.cognitons.memes."+file.getName().split("\\.class")[0]));
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/percepts").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        sondes.add(new SondeACognitons("civilisation.individu.cognitons.percepts."+file.getName().split("\\.class")[0]));
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/skills").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        sondes.add(new SondeACognitons("civilisation.individu.cognitons.skills."+file.getName().split("\\.class")[0]));
		    }
		}
		files = new File(System.getProperty("user.dir")+"/bin/civilisation/individu/cognitons/traits").listFiles();
		for (File file : files) {
		    if (file.isFile()) {
		        sondes.add(new SondeACognitons("civilisation.individu.cognitons.traits."+file.getName().split("\\.class")[0]));
		    }
		}
		System.out.println("Fin de la mise en place des sondes � cognitons.");
		System.out.println("Nombre de sondes : " + sondes.size());
*/
	}
	
	
	
	public void watch()
	{

		tick++;
		
		/* Gestion des sondes � cognitons */
		for (int i = 0; i < sondes.size(); i++)
		{
			//System.out.println("Sonde debut : " + i);
			sondes.get(i).activer();
			//System.out.println("Sonde fin : " + i);

		}
		
		/* Gestion des ressources*/
		if (tick %75 == 0)
		{
			for (int x = 0; x < world.getWidth(); x++)
			{
				for (int y = 0; y < world.getHeight(); y++)
				{
					if (world.grid[x][y].getColor() == world.ColorPlaines)
					{
						world.grid[x][y].incrementPatchVariable("gibier", 0.1);
						world.grid[x][y].incrementPatchVariable("baies", 0.1);
					}
					if (world.grid[x][y].getColor() == world.ColorForets)
					{
						world.grid[x][y].incrementPatchVariable("gibier", 0.2);
						world.grid[x][y].incrementPatchVariable("baies", 0.2);
					}
					if (world.grid[x][y].getColor() == world.ColorLittoral)
					{
						world.grid[x][y].incrementPatchVariable("gibier", 0.1);
						world.grid[x][y].incrementPatchVariable("baies", 0.05);
					}
					if (world.grid[x][y].getColor() == world.ColorCollines)
					{
						world.grid[x][y].incrementPatchVariable("gibier", 0.08);
						world.grid[x][y].incrementPatchVariable("baies", 0.05);
					}
					if (world.grid[x][y].getColor() == world.ColorMontagnes)
					{
						world.grid[x][y].incrementPatchVariable("gibier", 0.08);
					}
					if (world.grid[x][y].getColor() == world.ColorDeserts)
					{
					world.grid[x][y].incrementPatchVariable("gibier", 0.02);
					}
					
					if(world.grid[x][y].smell("passage") > Configuration.EffacementRoute || countCouleurVoisine(world.grid[x][y] , world.getColorForets()) >= 1 )
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
					
					if(world.grid[x][y].getColor() == world.getColorPlaines() && world.grid[x][y].smell("passage") < Configuration.seuilEmergenceForet )
					{
						world.grid[x][y].setColor(world.getColorForets());
						
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



