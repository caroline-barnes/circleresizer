package project;

import javax.swing.*;

public class Main extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args)
   { 
      SliderFrame<Object> sliderFrame1 = new SliderFrame<Object>(); //creating sliderFrame
      sliderFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      //close when user clicks x in corner
      sliderFrame1.setSize(430, 380); //setting size of frame
      sliderFrame1.setVisible(true); //showing frame 
      sliderFrame1.setResizable(false);
	  
   } 
} // end class SliderDemo

