/*
 * Copyright (c) 2017, 2018.
 */
package scheme;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Frame;

/**
 * <p> This class represents and implements the front end of the project dealing with user requests and the outputs.</p>
 * @author amjadm@miamioh.edu
 */
public class MainMenu extends JFrame {

	/**
	 * <p>Create the frame.</p>
	 */
	public MainMenu() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame mainPage = new Frame( "DNA ANALYZER" );
		mainPage.setSize( 800, 800 );
		mainPage.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		//mainPage.pack();
		mainPage.setVisible( true );
		
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
	}
/**
 * 
package scheme;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;


public class MainMenu extends JFrame {

	
	public MainMenu() {
		
		
		
		
		JLabel chooseFileLabel = new JLabel("Choose your File:");
		chooseFileLabel.setForeground(Color.BLUE);
		

		JTextPane selectedFileOutput = new JTextPane();
		selectedFileOutput.setEditable(false);
		
		JTextArea textArea = new JTextArea();
		Image scaledImage=new ImageIcon(MainMenu.class.getResource("/icons/openFile.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		getContentPane().setLayout(new MigLayout("", "[148px,grow][75px][356.00px]", "[55px][][][309.00,grow]"));
		
		JTextArea showFileContext = new JTextArea();
		
		
		JButton chooseFileButton = new JButton();
		chooseFileButton.setHorizontalAlignment(SwingConstants.LEADING);
		chooseFileButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		chooseFileButton.setText("...");
		chooseFileButton.setToolTipText("Click here to choose your file");
		chooseFileButton.setBorderPainted(false);
		chooseFileButton.setContentAreaFilled(false);
		chooseFileButton.setIcon(new ImageIcon(scaledImage));
		chooseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text & jpg Images", "txt", "jpg");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       selectedFileOutput.setText(chooser.getSelectedFile().getPath());
			    }
				
			}
		});
		
		getContentPane().add(chooseFileLabel, "flowx,cell 0 0,alignx left,growy");
		getContentPane().add(chooseFileButton, "cell 0 0,alignx left,aligny top");
		getContentPane().add(selectedFileOutput, "cell 2 0,growx,aligny center");
		getContentPane().add(showFileContext, "cell 0 3 3 1,grow");
		
		
		
		
	}
}

 */
}
