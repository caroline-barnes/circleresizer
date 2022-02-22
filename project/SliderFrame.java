package project;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Color;
import javax.swing.JPanel;

public class SliderFrame<event> extends JFrame 
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JSlider diameterSlider; // creating sliders
	private final JSlider redSlider;
	private final JSlider greenSlider;
	private final JSlider blueSlider;
    private final JLabel lang;
    private final OvalPanel myPanel; // creating resizable circle
    private final JPanel sliderPanel;
    private final JPanel langPanel;
	private static final String[] languages = {"en", "fr"};
	private Locale locale;
	private ResourceBundle project;
	private JLabel circLabel; //labels that will change language
	private JLabel diamLabel;
	private JLabel areaLabel;
	private JLabel circValue; //labels that will change according to size of circle
	private JLabel diamValue;
	private JLabel areaValue;
	private JPanel circPanel; //combining the two labels onto one panel
	private JPanel diamPanel;
	private JPanel areaPanel;
	private JList<String> list; //language list

   public SliderFrame ()
   {
      super("Java Project - Colour Slider");

      myPanel = new OvalPanel(); // create panel to draw circle
      myPanel.setBackground(Color.WHITE); //set background colour
      myPanel.setOpaque(true);

      locale = Locale.getDefault(); //selecting default locale
      project = ResourceBundle.getBundle("project", locale); //import language bundle
      langPanel = new JPanel(); //create panel to choose language
	  list = new JList<>(languages); 
  	  list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  	  langPanel.add(list); //to select language from list
	  
	  list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				handleList();//call method to change language
			}	
		});	
	  
      double diam = 10.0; //starting size of circle
      double area = 78.54; //based on starting size of circle
      double circ = 31.42; //based on starting size of circle
      diamPanel = new JPanel(); 
	  circPanel = new JPanel();
	  areaPanel = new JPanel();
	  
      diamValue = new JLabel("=" + diam);
      circValue = new JLabel("=" + circ);
      areaValue = new JLabel("=" + area);
      
      diamLabel = new JLabel("Diameter");//in English to start with
      circLabel = new JLabel("Circumference");
      areaLabel = new JLabel("Area");
      
	  sliderPanel = new JPanel(); //creating sliderPanel
      setLayout(new FlowLayout(FlowLayout.LEADING)); //FlowLayout 
      diameterSlider = 
         new JSlider(SwingConstants.VERTICAL, 0, 200, 10); //vertical diameterSlider
      diameterSlider.setMajorTickSpacing(10); // create tick every 10
      diameterSlider.setPaintTicks(true); // paint ticks on slider
      
      redSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0); //colour sliders also vertical
      redSlider.setMajorTickSpacing(50);
      redSlider.setPaintTicks(true);
      greenSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0);
      greenSlider.setMajorTickSpacing(50);
      greenSlider.setPaintTicks(true);
      blueSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0);
      blueSlider.setMajorTickSpacing(50);
      blueSlider.setPaintTicks(true);

      int redAmount = 0, greenAmount = 0, blueAmount = 0;
      //initialisation of colour variables from slider
      
      // register JSlider event listener
      diameterSlider.addChangeListener(
         new ChangeListener() 
         {  
            @Override
            public void stateChanged(ChangeEvent q)
            {
            ChangeListener e = null;   
            myPanel.setDiameter(diameterSlider.getValue());
            diameterSlider.addChangeListener((ChangeListener) e);
            diamValue.setText("=" + diameterSlider.getValue());
            
            DecimalFormat numberFormat = new DecimalFormat("#.00");           
            double radius = 0.5 * (diameterSlider.getValue());
            double area = radius * radius * 3.14159;
            areaValue.setText("=" + numberFormat.format(area));
            double circ = 3.14159 * (diameterSlider.getValue());
            circValue.setText("=" + numberFormat.format(circ));
            } 
         } );
 
      redSlider.addChangeListener(
    		  new ChangeListener() 
    		  {  
    			  @Override
    	          public void stateChanged(ChangeEvent r)
    	          {
    	             ChangeListener e = null;
    	             redSlider.addChangeListener((ChangeListener) e);
    	             int redAmount = redSlider.getValue();
    	             changeColor(redAmount, greenAmount, blueAmount);
    	          } 
    	       } );
      
      greenSlider.addChangeListener(
 	         new ChangeListener() 
 	         {  
 	            @Override
 	            public void stateChanged(ChangeEvent g)
 	            {
 	                ChangeListener e = null;
 	                greenSlider.addChangeListener((ChangeListener) e);
 	                //greenLabel.setText("Green=" + greenSlider.getValue());
 	                int greenAmount = greenSlider.getValue();
   	                changeColor(redAmount, greenAmount, blueAmount);
 	            } 
 	         } );
      
      blueSlider.addChangeListener(
 	         new ChangeListener () 
 	         {  
 	            // handle change in slider value
 	            @Override
 	            public void stateChanged(ChangeEvent b)
 	            {
 	                ChangeListener e = null;
 	                blueSlider.addChangeListener((ChangeListener) e); 
 	                //blueLabel.setText("Blue=" + blueSlider.getValue());
 	                int blueAmount = blueSlider.getValue();
   	                changeColor(redAmount, greenAmount, blueAmount);
 	            } 
 	         } );
      
      add(sliderPanel, BorderLayout.WEST);
      sliderPanel.add(diameterSlider); 
      sliderPanel.add(redSlider);
      sliderPanel.add(greenSlider);
      sliderPanel.add(blueSlider);
      add(myPanel, BorderLayout.EAST); 
      add(diamPanel, BorderLayout.SOUTH);
      diamPanel.add(diamLabel, BorderLayout.WEST);
      diamPanel.add(diamValue, BorderLayout.EAST);
      
      add(circPanel, BorderLayout.SOUTH);
      circPanel.add(circLabel, BorderLayout.WEST);
      circPanel.add(circValue, BorderLayout.EAST);
      
      add(areaPanel, BorderLayout.SOUTH);
      areaPanel.add(areaLabel, BorderLayout.WEST);
      areaPanel.add(areaValue, BorderLayout.EAST);

      add(langPanel, BorderLayout.SOUTH);

      langPanel.add(lang = new JLabel(project.getString("lang")), BorderLayout.WEST);
   }
   
   public void handleList(){
		int index = list.getSelectedIndex();
		locale = new Locale(languages[index]);
		project = ResourceBundle.getBundle("project", locale);
		
		diamLabel.setText(project.getString("diam"));//import 
		circLabel.setText(project.getString("circ"));
		areaLabel.setText(project.getString("area"));
		lang.setText(project.getString("lang"));	
	}
   
   void changeColor(int redAmount, int greenAmount, int blueAmount)
   {
	   redAmount = redSlider.getValue(); 
	   greenAmount = greenSlider.getValue();
	   blueAmount = blueSlider.getValue();
	   myPanel.setForegroundColor(redAmount, greenAmount, blueAmount);  
   }
}

