package civilisation.inventaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import civilisation.individu.Humain;
import civilisation.urbanisme.Batiment;

/** 
 * Inventaire que peuvent poss�der les agents, pour manipuler diff�rents objets
 * @author DTEAM
 * @version 1.0 - 2/2013
 * 
 * IMPORTANT: ne JAMAIS utiliser directement objets.get() mais getListObjet(typeObjet) � la palce !
*/



public class Inventaire {

	private static final int capaciteMax = 100;
	
	HashMap<String, ArrayList<ObjetInventaire>> objets;
	Humain h;
	
	
	public Inventaire()
	{
		objets = new HashMap<String, ArrayList<ObjetInventaire>>();
	}
	
	public Inventaire(Humain h)
	{
		objets = new HashMap<String, ArrayList<ObjetInventaire>>();
		this.h = h;
	}
	
	/**
	 * Retire un objet � l'inventaire
	 * @param l'objet � retirer
	 * @param l'humain associ� � cet inventaire (null si ce n'est pas un inventaire d'humain)
	 */
	public void remove(String typeObjet, int i)
	{
		ArrayList<ObjetInventaire> list = getListObjet(typeObjet);
		if(list != null)
		{
			ObjetInventaire o = list.get(i);
			list.remove(i);
			if( o.cognitonsLies() != null && h != null)
			{
				for (int j = 0; j < o.cognitonsLies().length; j++)
				{
					h.getEsprit().removeCognitonByFullname(o.cognitonsLies()[j]);
				}
			}
		}
	}
	
	/**
	 * Ajoute un objet � l'inventaire
	 * @param l'objet � ajouter
	 * @param l'humain associ� � cet inventaire (null si ce n'est pas un inventaire d'humain)
	 */
	public void add(ObjetInventaire o)
	{	
		/*Gestion du poids et/ou du nombre d'objets ici*/
		if (getSize() < capaciteMax)
		{
			getListObjet(o.toString()).add(o);
			
			if( h != null && o.cognitonsLies() != null )
			{
				for (int i = 0; i < o.cognitonsLies().length; i++)
				{
					h.getEsprit().addCognitonByName(o.cognitonsLies()[i]);
				}
			}
		}
	}
	
	public void donner(String typeObjet, int i, Humain h_receveur)
	{
		h_receveur.getInventaire().add(getListObjet(typeObjet).get(i));
		remove(typeObjet, i);
	}
	
	public void deposer(String typeObjet, int i, Batiment b)
	{
		b.getInventaire().add(getListObjet(typeObjet).get(i));
		objets.remove(i);
	}
	
	/**
	 * D�pose tous les objets d'un type donn� dans un b�timent
	 * @param B�timent r�cepteur
	 * @param Un objet du type � d�poser
	 */
	public void deposerType(Batiment b, String typeObjet)
	{
		for (int i = 0; i < getListObjet(typeObjet).size();i++)
		{
			deposer(typeObjet, i, b);
		}
	}
	
	/**
	 * D�pose tous les objets consid�r�s comme des objets � r�colter (viande, bois, etc�)
	 * @param b B�timent r�cepteur
	 */
	public void deposerRecolte(Batiment b)
	{
		//System.out.println("taille occup�e :" + b.getInventaire().getSize());
		for(Entry<String, ArrayList<ObjetInventaire>> e : objets.entrySet())
		{
			ArrayList<ObjetInventaire> list = getListObjet(e.getKey());
			if(list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).is("recolte"))
					{
						deposer(list.get(i).toString(), i, b);
					}
				}
			}
		}
	}
	
	public int getSize()
	{
		int size = 0;
		for(Entry<String, ArrayList<ObjetInventaire>> e : objets.entrySet())
		{
			size += e.getValue().size();
		}
		return size;
	}
	
	public int getSize(String typeObjet)
	{
		return getListObjet(typeObjet).size();
	}
	
	public ArrayList<ObjetInventaire> getListObjet(String typeObjet)
	{
		if(!objets.containsKey(typeObjet)) objets.put(typeObjet, new ArrayList<ObjetInventaire>());
		return objets.get(typeObjet);
	}
	
	public ObjetInventaire get(String typeObjet, int i)
	{
		return getListObjet(typeObjet).get(i);
	}
	
	public ArrayList<ObjetInventaire> getAll()
	{
		ArrayList<ObjetInventaire> allObjets = new ArrayList<ObjetInventaire>();
		
		for(Entry<String, ArrayList<ObjetInventaire>> e : objets.entrySet())
		{
			ArrayList<ObjetInventaire> list = getListObjet(e.getKey());
			if(list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					allObjets.add(list.get(i));
				}
			}
		}
		
		return allObjets;
	}
	

	/**
	 * @return l'index du premier �l�ment ayant une valeur nutritive dans l'inventaire, -1 sinon
	 */
	public ObjetInventaire getFirstAliment()
	{
		for(Entry<String, ArrayList<ObjetInventaire>> e : objets.entrySet())
		{
			ArrayList<ObjetInventaire> list = getListObjet(e.getKey());
			if(list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i).getValeurNutritive() != -1)
					{
						return list.get(i);
					}
				}
			}
		}

		return null;
	}


	
	
}
