package emailsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//INCLUDE LIBRARIES NEEDED FOR FILE STREAM
import java.io.*;

class ViewTextAttachment extends JFrame
{
	//DECLARE FILEIN STREAM FOR GETTING STRING
	private static BufferedReader fileIn;
	//DECLARE COMPONENETS TO BE PLACED ON GUI
	private static JTextArea attachmentWindow;
	private static byte[] textAttachment;
	private static Container pane;
	private static FileOutputStream fileOut;
	private static String name;

	
	//CONSTRUCTOR FOR SERVER GUI
	public ViewTextAttachment(byte[] textAttachment, String name)
	{
		this.textAttachment = textAttachment;
		this.name = name;
		//SET WINDOW TITLE
		setTitle("View Text Attachment");
		//INITIALISE COMPONENTS
		attachmentWindow = new JTextArea(30,50);
		//SET UP JTEXTAREAS PROPERTIES
		attachmentWindow.setWrapStyleWord(true);
		attachmentWindow.setLineWrap(true);
		attachmentWindow.setEditable(false);
		//ADD COMPONENTS TO CONTENT PANEL
		pane = getContentPane();
		pane.add(attachmentWindow, BorderLayout.CENTER);
		pane.add(new JScrollPane(attachmentWindow));
		//CATCH FILE READ ERROR
		try
		{
			getTextAttachmentContents();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error Reading From File");
		}
	}

	//CONVERTS TEXT ATTACHMENT INTO STRING
	public void getTextAttachmentContents() throws IOException
	{
		//SET UP NEW FILE OUTPUT STREAM
		fileOut = new FileOutputStream(name);
		//WRITE FILE FROM BYTE ARRAY
		fileOut.write(textAttachment);
		//CREATE NEW FILE OBJECT
		File temp = new File(name);
		//CREATE NEW FILE INPUT STREAM
		fileIn = new BufferedReader(new FileReader(temp));
		//DECLARE VARIABLES FOR FILE INPUT
		String input;
		String text = "";
		//GET FIRST STRING FROM FILE
		input = fileIn.readLine();
		text = (text + input + "\n");
		//GET REST OF STRINGS FROM FILE
		while (input != null)
		{
			text = (text + input + "\n");
			input = fileIn.readLine();
		}
		//CLOSE STREAM
		fileIn.close();
		//UPDATE GUI WITH STRING
		attachmentWindow.setText(text);
	}
}