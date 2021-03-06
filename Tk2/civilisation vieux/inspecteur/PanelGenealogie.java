package civilisation.inspecteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;


import civilisation.individu.Humain;
import civilisation.world.World;
import civilisation.Communaute;
import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Un sous panel pour repr�senter la g�n�alogie d'un agent
 * @author DTEAM
 * @version 1.0 - 3/2013
*/

public class PanelGenealogie extends JPanel{

	private static int border = 5;
	Humain t;


	public PanelGenealogie()
	{
		this.setLayout(new BorderLayout());
		//this.setMinimumSize(new Dimension(180,90));
		//this.setMaximumSize(new Dimension(180,90));

		t = null;

		
		this.setVisible(true);
	}


	
	public void actualiser(Humain t)
	{
		this.t = t;
		this.updateUI();
		
	}
	
    public void paintComponent(Graphics g) {
    	
        Graphics2D g2d = (Graphics2D) g;
        if (t != null)
        {
        	
        	
            Color c;
    	try {
			Image iconeFemme = ImageIO.read(this.getClass().getResource("icones/user-green-female.png"));
			Image iconeHomme = ImageIO.read(this.getClass().getResource("icones/user.png"));
			Image iconeMort = ImageIO.read(this.getClass().getResource("icones/headstone-rip.png"));
			Image icone;
    		
			if (t.getMere() != null)
			{
	            drawMembreFamille(g2d, border + 40, border, Color.RED, iconeFemme);
			}
			else
			{
	            drawMembreFamille(g2d, border + 40, border, Color.RED, iconeMort);
			}
			
			if (t.getPere() != null)
			{
				drawMembreFamille(g2d, border + 0, border, Color.BLUE, iconeHomme);
			}
			else
			{
				drawMembreFamille(g2d, border + 0, border, Color.BLUE, iconeMort);
			}

            if (t.isFemme())
            {
            	c = Color.red;
            	icone = iconeFemme;
            }
            else
            {
            	c = Color.blue;
            	icone = iconeHomme;
            }
            
            drawMembreFamille(g2d, border + 20, border + 30, c, icone);

            g2d.setPaint(Color.BLACK);
            g2d.drawLine(border + 10, border + 20, border + 10, border + 25);
            g2d.drawLine(border + 50, border + 20, border + 50, border + 25);
            g2d.drawLine(border + 10, border + 25, border + 50, border + 25);
            g2d.drawLine(border + 30, border + 25, border + 30, border + 30);
            
            if (!t.getEnfants().isEmpty())
            {
                g2d.drawLine(border + 30, border + 50, border + 30, border + 55);
                g2d.drawLine(border + 30, border + 55, border + 0, border + 55);
                g2d.drawLine(border + 0, border + 55, border + 0, border + 70);
                g2d.drawLine(border + 0, border + 70, border + 10, border + 70);
                for (int i = 0; i < t.getEnfants().size(); i++)
                {
                	if (t.getEnfants().get(i).isFemme())
                	{
        	            drawMembreFamille(g2d, border + 10 + 10*i, border + 60, Color.RED, iconeFemme);
                	}
                	else
                	{
        	            drawMembreFamille(g2d, border + 10 + 10*i, border + 60, Color.BLUE, iconeHomme);
                	}
                }
            }

            if (t.getConjoint() != null)
            {
                g2d.drawLine(border + 40, border + 40, border + 50, border + 40);

                if (t.getConjoint().isFemme())
                {
        	           drawMembreFamille(g2d, border + 50, border + 30, Color.RED, iconeFemme);
               	}
               	else
               	{
        	           drawMembreFamille(g2d, border + 50, border + 30, Color.BLUE, iconeHomme);
               	}
                
            }
            
        
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    }
    
    public void drawMembreFamille(Graphics2D g2d, int x, int y, Color c, Image ico)
    {
        g2d.setPaint(c);
        g2d.fill(new Rectangle2D.Double(x, y, 20, 20));
        g2d.setPaint(new Color(187,210,225));
        g2d.fill(new Rectangle2D.Double(x+2, y+2, 16, 16));

		g2d.drawImage(ico, (int)x+2, (int)y+2, null);

    }
    
}
	


