import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import java.net.*;

public class MyClien extends JFrame{

	private PrintWriter writer;
	Socket socket;
	private JTextArea ta=new JTextArea();
	private JTextField tf=new JTextField();
	Container cc;
	
	public MyClien(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cc=this.getContentPane();
		final JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		scrollPane.setViewportView(ta);
		cc.add(tf, "South");
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//The socket receives the text from the writer
				writer.println(tf.getText());
				
				ta.append(tf.getText()+'\n');
				ta.setSelectionEnd(ta.getText().length());
				tf.setText("");
			}

		});
	}
	
	public void connect() {
		ta.append("attempting to connect"+'\n');
		try {
			socket=new Socket("142.157.249.217",8998);
			
			//Set the writer towards the socket's OutputStream
			writer=new PrintWriter(socket.getOutputStream(),true);
			
			ta.append("connection accomplished"+'\n');
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MyClien clien=new MyClien("transfer data to server");
		clien.setSize(500,500);
		clien.setVisible(true);
		clien.connect();
	}

}
