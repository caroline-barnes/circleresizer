package project;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class OvalPanel extends JPanel 
{
   
	private static final long serialVersionUID = 1L;
	private int diameter = 10; // setting initial diameter
 
	public void paintComponent(Graphics g)//create component
   {
      super.paintComponent(g);
      g.fillOval(10, 10, diameter, diameter);//filling diameter using value from JSlider
   }

   // validate and set diameter, then repaint 
   public void setDiameter(int newDiameter)
   {
      // if diameter invalid, default to 10
      diameter = (newDiameter >= 0 ? newDiameter : 10);
      repaint(); // repaint panel
   } 

   // used by layout manager to determine preferred size
   public Dimension getPreferredSize()
   {
      return new Dimension(220, 220);
   }

   public Dimension getMinimumSize()// used by layout manager to determine minimum size
   {
      return getPreferredSize();
   }

   public void setForegroundColor(int redAmount, int greenAmount, int blueAmount) 
   {
     Color color = new Color(redAmount, greenAmount, blueAmount);
     setForeground(color);//using values from colour JSliders to colour panel
   } 
}// end class OvalPanel
