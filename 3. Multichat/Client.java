package Multichat;
import java.net.*;
import java.io.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
class Client extends JFrame implements ActionListener
{   

	JButton button, button1;
	JTextArea t1;
	JTextArea t2;
	String mess="";
	Socket s;
	JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	public static int Title=0;
	public Client()
	{      
		if (this.getClass() == Client.class) 
		{ 
			Title++; 
		}    
		try
		{
			setTitle("Client"+Title++); 	
			button=new JButton("SEND");
			button1=new JButton("CLEAR");
			t1=new JTextArea(10,20);
			t2=new JTextArea(10,20);
			DefaultCaret caret = (DefaultCaret)t1.getCaret();
			DefaultCaret caret2 = (DefaultCaret)t2.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			scrollPane1 = new JScrollPane(t2);
			scrollPane2= new JScrollPane(t1);
			scrollPane2.setBounds(20,250,400,50);
			button.setBounds(20,300,100,50);
			button1.setBounds(140,300,100,50);
			scrollPane1.setBounds(20,20,400,200);
			setLayout(null);
			add(scrollPane2);
			add(button);
			add(button1);
			add(scrollPane1);
			t2.setEditable(false);
			button.addActionListener(this);
			button1.addActionListener(this);
			setSize(500,400);
			setVisible(true);
			setLayout(null);
			System.out.println("connecting to server");
			System.out.println("client connected to server"); 	
			System.out.println("Hi you can start chatting");
			s= new Socket("localhost",1010);

			while(true)
			{	  
				InputStream is=s.getInputStream();
				byte data[]=new byte[50];
				is.read(data);
				String mfc=new String(data);
				mfc=mfc.trim();
				t2.append("My Pal :"+mfc+"\n");
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public static int getTitleCount() 
	{
		return Title;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "SEND")
		{
			try
			{
				String n=t1.getText();
				OutputStream os=s.getOutputStream();
				os.write(n.getBytes());
				t2.append("Me :"+n+"\n");
				t1.setText("");
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		else
			t2.setText("");

	}
	/*public void setDefaultCloseOperation(int i)
	{
		try{s.close();}
		catch(Exception e){}
		//t.destroy();
	}*/
	public static void main(String args[]) throws Exception
	{
		Client c = new Client();	
	}
}