package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/** 
 * G�re l'interaction avec la fen�tre de l'inspecteur communaut�
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionInspecteurCommunauteListener implements ActionListener{

	PanelInspecteurCommunaute p;
	int index;

	public ActionInspecteurCommunauteListener(PanelInspecteurCommunaute p, int i)
	{
		this.p = p;
		index = i;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (index == 0) 
		{
			p.incrementerAgentID();
		}
		else if (index == 1) 
		{
			p.decrementerAgentID();
		}
		
	}



}
