package civilisation.inspecteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Type;

import javax.swing.JButton;

/** 
 * G�re l'interaction avec la fen�tre de r�glage des param�tres de la simulation
 * @author DTEAM
 * @version 1.0 - 2/2013
*/

public class ActionParametresCogniListener implements ActionListener{

	PanelParametres p;
	int index;
	int indiceCogni;
	
	public ActionParametresCogniListener(PanelParametres p, int i, int indiceCogni)
	{
		this.p = p;
		index = i;
		this.indiceCogni = indiceCogni;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			try {
				p.modifierParametreCogni(index,indiceCogni);
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}



}
