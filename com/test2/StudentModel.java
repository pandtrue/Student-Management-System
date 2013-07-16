/**
 * Model of student table, can be treat as a table
 * @author Tianyi
 *
 */
package com.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class StudentModel extends AbstractTableModel{
	Vector rowData, colName;
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url = "jdbc:sqlserver://localhost:1433; DatabaseName=test";
	String user = "sa";
	String passwd = "sa";
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			
	
	public void init(String sql) {
		
		if(sql.equals("")) {
			sql = "Select * from stu";
		}
		colName = new Vector();
		colName.add("ID");
		colName.add("Name");
		colName.add("Sex");
		colName.add("Age");
		colName.add("Brith Palce");
		colName.add("Major");
		
		rowData = new Vector();
		
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector col = new Vector();
				col.add(rs.getString(1));
				col.add(rs.getString(2));
				col.add(rs.getString(3));
				col.add(rs.getInt(4));
				col.add(rs.getString(5));
				col.add(rs.getString(6));
				
				rowData.add(col);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null){
					rs.close();
				}
				if(ps!= null){
					ps.close();
				}
				if(ct!= null){
					ct.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateStudent(String sql, String[] paras) {
		boolean res = true;
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
			ps = ct.prepareStatement(sql);
			for (int i=0;i<paras.length;i++) {
				ps.setString(i+1, paras[i]);
			}
			ps.executeUpdate();
			
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null){
					rs.close();
				}
				if(ps!= null){
					ps.close();
				}
				if(ct!= null){
					ct.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return res;
	}
	
	// Get data model based on the sql command passed in
	public StudentModel(String sql) {
		this.init(sql);
	}
	
	// Define a constructor to initialize the data model(student table)
	public StudentModel() {
		this.init("");
	}
	
	@Override
	public int getColumnCount() {
		return this.colName.size();
	}

	@Override
	public int getRowCount() {
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return ((Vector)this.rowData.get(row)).get(col);
	}

	@Override
	public String getColumnName(int column) {
		return (String)this.colName.get(column);
	}
}