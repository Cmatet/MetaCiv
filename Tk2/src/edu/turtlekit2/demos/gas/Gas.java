/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package edu.turtlekit2.demos.gas;

import java.awt.Color;

import edu.turtlekit2.kernel.agents.Turtle;


/** 
 * Adapted from the gas simulation of TurtleKit (v1). 
 * @author G.Beurier
 * @version 1.1 - 4/2010 
 * 
 * The turtle "gaz" is only a turtle who needs space !!!
  @author Fabien MICHEL
  @version 1.1 6/12/1999 */

@SuppressWarnings("serial")
public class Gas extends Turtle 
{
	public int wall;

	public Gas()
	{
		super("go");
	}
	public void setup()
	{
		setColor(Color.cyan);
		wall = getAttributes().getInt("wall");
		int u = (int) (Math.random()*wall);
		int v = (int) (Math.random()*getWorldHeight());
		moveTo(u,v);
		playRole("gas");
	}



	/**The gas looks for free space (without an other turtle or a wall)
	but can't go through the wall (white color patches)
	and rebounds against the sides of the world*/ 
	public String go()
	{
		if ( (xcor()+dx()) < 0 || (xcor()+dx()) >= getWorldWidth() || (ycor()+dy()) < 0 || (ycor()+dy()) >= getWorldHeight())
			randomHeading();
		for(int i = 0; i<4;i++)
		{
			if (getPatchColorAt(dx(),dy()) != Color.white && countTurtlesAt(dx(),dy()) == 0)
			{
				fd(1);
				break;
			}
			else
				randomHeading();
		}
		return ("go");
	}





}


