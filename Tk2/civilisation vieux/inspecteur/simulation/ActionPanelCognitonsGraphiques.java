package civilisation.inspecteur.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import civilisation.Configuration;

/** 
 * G�re l'interaction avec la fen�tre de des cognitons graphiques
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionPanelCognitonsGraphiques implements ActionListener{

	PanelModificationSimulation p;
	int index;
	
	public ActionPanelCognitonsGraphiques(PanelModificationSimulation p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) /*Remplacer par la version actuellement visible dans l'�diteur*/
		{

			
			System.out.println("---Enregistrement des param�tres de la simulation---");
			File cible = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources");
			
			/*
			 * On met de c�t� les diff�rents environnments
			 * A ameliorer!
			 */
			File environnements = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements");
			if (environnements.isDirectory())
				System.out.println("--� Sauvegarde des environnements de simulation");
				try {
					copierDossier(environnements , new File(System.getProperty("user.dir")+"/bin/civilisation/environnements"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	
			System.out.println("--� Suppression de l'ancienne version");
			supprimerDossier(cible);
			
			cible.mkdir();
			
			PrintWriter out;
			try {
				out = new PrintWriter(cible.getPath()+"/"+"parametres"+Configuration.getExtension());
				if (Configuration.environnementACharger == null){
					out.println("Carte : " + "AUCUNE");
				}
				else{
					out.println("Carte : " + Configuration.environnementACharger + Configuration.getExtension());
				}
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

					
			System.out.println("--� Enregistrement des cognitons");
			File cognitons = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/cognitons");
			cognitons.mkdir();
			for (int i = 0; i < Configuration.cognitons.size();i++){
				Configuration.cognitons.get(i).enregistrer(cognitons);
			}

			System.out.println("--� Enregistrement des objets");
			File objets = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/objets");
			objets.mkdir();
			for (int i = 0; i < Configuration.objets.size();i++){
				Configuration.objets.get(i).enregistrer(objets);
			}

			System.out.println("--� Enregistrement des items pheromones");
			File phero = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/itemPheromones");
			phero.mkdir();
			for (int i = 0; i < Configuration.itemsPheromones.size();i++){
				Configuration.itemsPheromones.get(i).enregistrer(phero);
			}
			
			System.out.println("--� Enregistrement des plans");
			File plans = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/plans");
			plans.mkdir();
			for (int i = 0; i < Configuration.plans.size();i++){
				Configuration.plans.get(i).enregistrer(plans);
			}			
			
			System.out.println("--� Enregistrement des terrains");
			File terrains = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/terrains");
			terrains.mkdir();
			for (int i = 0; i < Configuration.terrains.size();i++){
				Configuration.terrains.get(i).enregistrer(terrains);
			}

			/*On remet les environnements en place*/
			environnements = new File(System.getProperty("user.dir")+"/bin/civilisation/environnements");
			System.out.println(environnements.getAbsolutePath() + " "+environnements.isDirectory());
			if (environnements.isDirectory()){
				System.out.println("env");
				try {
					copierDossier(environnements , new File(System.getProperty("user.dir")+"/bin/civilisation/ressources/environnements"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				supprimerDossier(environnements);
			}


			
		}
		else if (index == 1) /*Archiver l'ancienne version des ressources*/
		{
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			
	    	File versionActuelle = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources");
	    	File archive = new File(System.getProperty("user.dir")+"/bin/civilisation/ressources"+dateFormat.format(date));
	    	System.out.println(System.getProperty("user.dir")+"/bin/civilisation/ressources"+" "+dateFormat.format(date));

	    	try {
	    		copierDossier(versionActuelle, archive);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("Echec lors de la copie des fichiers");
			}
	    	
		}
		else if (index == 2) 
		{
			p.afficherStructureCognitive();
	    	
		}
		else if (index == 3) 
		{
			p.afficherEnvironnement();
	    	
		}
		else if (index == 4) 
		{
			p.afficherObjets();
	    	
		}
	}
	
	
    public static void copierDossier(File src, File dest) throws IOException{
     
    	if(src.isDirectory()){
    	     
    		if(!dest.exists()){
    		   dest.mkdir();
    		}
 
    		String files[] = src.list();
 
    		for (String file : files) {
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   copierDossier(srcFile,destFile);
    		}
 
    	}
    	else{
    		InputStream in = new FileInputStream(src);
    	    OutputStream out = new FileOutputStream(dest); 
 
	        byte[] buffer = new byte[1024];
	        
	        int length;
	        //copy the file content in bytes 
	        while ((length = in.read(buffer)) > 0){
	    	   out.write(buffer, 0, length);
	        }

	        in.close();
	        out.close();
    	}
    }


    public void supprimerDossier(File cible){
    	if (cible.isDirectory()){
            File [] fileList = cible.listFiles();
            for(int i = 0;i<fileList.length;i++){
            	  supprimerDossier(fileList[i]);
            }
    	}
    	cible.delete();
    }
}
