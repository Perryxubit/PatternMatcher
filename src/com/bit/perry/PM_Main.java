package com.bit.perry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class PM_Main extends JFrame {

  // little simple program to test your Regular expression
	PM_Main() {
		super("Pattern Matcher v1.1 by Perry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set size and location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int size_x = 600;
		int size_y = 600;
		int loc_x = dimension.width/2-size_x/2;
		int loc_y = dimension.height/2-size_y/2;

		JPanel mp_top = new JPanel();
		mp_top.setLayout(new GridLayout(2,1));
		
		//---jp1
		JPanel jpinput = new JPanel();
		jpinput.setLayout(new BorderLayout());
		
		JLabel jl = new JLabel("Please input your free text for testing: ");
		final JTextArea data = new JTextArea("");
		data.setEditable(true);
		JScrollPane jpdata = new JScrollPane(data);
		
		jpinput.add(jl, BorderLayout.NORTH);
		jpinput.add(jpdata, BorderLayout.CENTER);
		//------
		
		//---jp2
		JPanel jpoutput = new JPanel();
		jpoutput.setLayout(new BorderLayout());
		//upper
		JPanel jp_pattern = new JPanel();
		jp_pattern.setLayout(new FlowLayout());
		JButton pm_button = new JButton("Match");
		pm_button.setPreferredSize(new Dimension(80,20));
		JLabel j3label = new JLabel("Regular Expression: ");
		final JTextField inputField = new JTextField("",32);
		jp_pattern.add(j3label);
		jp_pattern.add(inputField);
		jp_pattern.add(pm_button); 
			
		//bottom
		final JTextArea output = new JTextArea("");
		output.setEditable(false);
		JScrollPane jsp_output = new JScrollPane(output);
		jpoutput.add(jp_pattern, BorderLayout.NORTH);
		jpoutput.add(jsp_output, BorderLayout.CENTER);
		//------
		
		
		//button function:
		//<a.*?href="(.*?)">(.*?)</a>
		pm_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
		            public void run(){
		            	if(inputField.getText().length()<=0) {
							JOptionPane.showMessageDialog(null, "input your pattern, please.");
							return;
						}
						final int N = 144;
						output.setText("");
						for(int i=0; i<N; i++) output.setText(output.getText() + "-");
						output.setText(output.getText() + "\n");
						
						String odata = data.getText();
						String pt = inputField.getText();
						Pattern p = Pattern.compile(pt, Pattern.DOTALL);
				        Matcher m = p.matcher(odata);
				        while(m.find()){
				        	String w = m.group();    	
				        	output.setText(output.getText() + w + "\n");	
				        	for(int i=0; i<N; i++) output.setText(output.getText() + "-");
							output.setText(output.getText() + "\n");
				        }
				        output.setText(output.getText() + " Fin." + "\n");
		            }
		        }).start();	
				
			}
		});
		
		mp_top.add(jpinput);
		mp_top.add(jpoutput);
		
		add(mp_top);
		setSize(size_x, size_y);
        setLocation(loc_x, loc_y);
        setVisible(true);
	}
	
	public static void main(String []args) throws Exception {
        new PM_Main();
    }
}
