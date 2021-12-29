package Multichat;
import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;
class A extends JFrame implements Runnable , ActionListener
{
	Thread t;
	GUI gui;
	Socket s;
	String mess;
	A(GUI g,Socket x)
	{
		s=x;
		gui = g;
		gui.button.addActionListener(this);
		t=new Thread(this);
		t.start();		
	}
	public void run()
	{
		try
		{
			while(true)
			{
				InputStream is=s.getInputStream();
				byte data[]=new byte[50];
				is.read(data);
				String mfc=new String(data);
				mfc=mfc.trim();			 
				gui.t2.append("My Friend:  "+mfc+"\n");				
				set(s);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
    public void set(Socket p)
    {
    	s = p;
    }
    public Socket get()
    {
    	return s;
    }
	public void actionPerformed(ActionEvent e1)
	{   
		try
		{
			s = get();
			mess=gui.t1.getText();
			OutputStream os=s.getOutputStream();
			os.write(mess.getBytes());
			String text = gui.t1.getText();
			gui.t2.append("Me : "+text + "\n");
			gui.t1.selectAll();
			gui.t1.setText("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 

	}
	public void setDefaultCloseOperation(int i)
	{
		try{s.close();}
		catch(Exception e){}
		//t.destroy();
	}
}
class ChatMultiServer
{
	static int c=0;
	public static void main(String args[])throws Exception
	{
		System.out.println("server socket is creating");
		ServerSocket ss=new ServerSocket(1010);
		System.out.println("server socket is created");
		System.out.println("waiting for client");
		GUI g = new GUI("Server");
		while(true)
		{
			Socket s=ss.accept();
			new A(g,s);			
		}
	}
}

class GUI extends JFrame
{
	JButton button;
	JTextArea t1;
	JTextArea t2;
	String mess="";
	JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	GUI(String str)
	{
		setTitle(str);
		button=new JButton("SEND");
		t1=new JTextArea(10,20);
		t2=new JTextArea(10,20);
		DefaultCaret caret = (DefaultCaret)t1.getCaret();
		DefaultCaret caret2 = (DefaultCaret)t2.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane1 = new JScrollPane(t2);
		scrollPane2=new JScrollPane(t1);
		scrollPane1.setBounds(20,20,400,200);
		scrollPane2.setBounds(20,250,400,50);
		button.setBounds(20,300,100,50);
		setLayout(null);
		add(scrollPane1);add(scrollPane2);add(button);
		t2.setEditable(false);
		setSize(500,400);
		setVisible(true);
		System.out.println("Hi you can start chatting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
