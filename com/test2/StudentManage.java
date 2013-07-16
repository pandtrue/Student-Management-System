package com.test2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


/** 
 * Mini version STS
 */
public class StudentManage extends JFrame implements ActionListener{

	// Define components
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	JTable jt1;
	JScrollPane jsp;
	JTextField jtf;
	StudentModel sm;
	
	JTable jt = null;
	
	public static void main(String[] args) {
		StudentManage t = new StudentManage();
		

	}
	
	public StudentManage() {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("Search");
		jb1.addActionListener(this);
		jl1 = new JLabel("Please enter the name");
		
		// Add all components into JPanel
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2 = new JPanel();
		jb2 = new JButton("Add");
		jb2.addActionListener(this);
		jb3 = new JButton("Edit");
		jb3.addActionListener(this);
		jb4 = new JButton("Delete");
		jb4.addActionListener(this);
		
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		

		// Create a data model object
		sm = new StudentModel();
		
		
		// Initialize JTable
		jt = new JTable(sm);
		
		// Initialize jsp
		jsp = new JScrollPane(jt);
		
		// Add jsp to JFrame
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Check which button is clicked
		if(arg0.getSource() == jb1) {
			
			String name = this.jtf.getText().trim();
			String sql = "Select * from stu where stuName='" + name + "'";
			//Create a new data model and update
			sm = new StudentModel(sql);
			jt.setModel(sm);
		}
		
		//When user click add
		else if(arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "Add New Student", true);
			sm = new StudentModel();
			jt.setModel(sm);
		}
		
		// Update Student info
		else if(arg0.getSource() == jb3) {
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please the row you want to update");
				return;
			} 
			StuUpdateDialog su = new StuUpdateDialog(this, "Update Student Information", true, sm, rowNum);
			
			sm = new StudentModel();
			jt.setModel(sm);
		}
			
		// Delete Student
		else if(arg0.getSource() == jb4) {
			//1. Get Student ID 
			// Return the row user chose
			// If no row has been chose, return -1
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please the row you want to delete");
				return;
			} 
			String stuID = (String) sm.getValueAt(rowNum, 0);
			
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuID};
			StudentModel temp = new StudentModel();
			temp.updateStudent(sql, paras);
			
			sm = new StudentModel();
			jt.setModel(sm);
		}
	}
}