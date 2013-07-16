package com.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class StuUpdateDialog extends JDialog implements ActionListener{

	//Define swing components we need
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;   
	JButton jb1,jb2; 
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;    
	JPanel jp1,jp2,jp3; 
	//owne its super frame
	//title frame name
	//model ture means you have to finish/cancel move on the current frame to 
	//control the super frame, false doesn't have this restriction. 
	public StuUpdateDialog(Frame owner, String title, boolean model, StudentModel sm, int rowNum){ 
		super(owner,title,model); 
		jl1=new JLabel("Student ID");   
		jl2=new JLabel("Name"); 
		jl3=new JLabel("Sex");   
		jl4=new JLabel("Age"); 
		jl5=new JLabel("Birth Place");   
		jl6=new JLabel("Major"); 
		
		jtf1=new JTextField();
		jtf1.setText((String) sm.getValueAt(rowNum, 0));
		// jtf1 cannot be changed
		jtf1.setEditable(false);
		jtf2=new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jtf3=new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		jtf4=new JTextField();
		jtf4.setText(String.valueOf(sm.getValueAt(rowNum, 3)));
		jtf5=new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNum, 4));
		jtf6=new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNum, 5));
		
		jb1=new JButton("Update");
		//Regist listener
		jb1.addActionListener(this);   
		jb2=new JButton("Cancel"); 
		jp1=new JPanel();  
		jp2=new JPanel();   
		jp3=new JPanel();   
		//Set layout
		jp1.setLayout(new GridLayout(6,1));    
		jp2.setLayout(new GridLayout(6,1));   
		//Add Components
		jp1.add(jl1);  
		jp1.add(jl2);    
		jp1.add(jl3);   
		jp1.add(jl4);    
		jp1.add(jl5);    
		jp1.add(jl6);
		
		jp2.add(jtf1);   
		jp2.add(jtf2);   
		jp2.add(jtf3);   
		jp2.add(jtf4);   
		jp2.add(jtf5);   
		jp2.add(jtf6);
		
		jp3.add(jb1);   
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);   
		this.add(jp2,BorderLayout.CENTER);   
		this.add(jp3,BorderLayout.SOUTH);   
		//’πœ÷   
		this.setSize(300,250);   
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
		this.setVisible(true); 
	}  
	
	public void actionPerformed(ActionEvent e){   
		if(e.getSource()==jb1) {   
			
			StudentModel temp=new StudentModel();   
			String sql="update stu set stuName=?, stuSex=?, stuAge=?, stuPlace=?, stuDept=? where stuId=?";   
			String[] paras={jtf3.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf1.getText() };
		
			if(!temp.updateStudent(sql,paras)) {   
				JOptionPane.showMessageDialog(this,"ÃÌº” ß∞‹");
			}   
			//Close dialog
			this.dispose();
		}
	}
}
