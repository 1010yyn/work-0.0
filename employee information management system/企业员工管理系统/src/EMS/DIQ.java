//部门信息查询
package EMS;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JScrollPane.*;
import javax.swing.table.*;
import javax.swing.JScrollPane.*;
import java.sql.*;

public class DIQ extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("部 门信 息 查 询");
	JLabel lbl2 = new JLabel("部门编号：");
	JLabel lbl3 = new JLabel("部门名称：");
	JTextField did = new JTextField(10);// 薪资编号
	JTextField dname = new JTextField(10);// 员工编号
	JButton bt = new JButton("查询");
	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "编号", "名称", " 人数" };

	public DIQ()
	{
		setTitle("部 门 信 息 查 询");
		getContentPane().setLayout(null);
		dtm = new DefaultTableModel();
		table = new JTable(dtm);

		JScrollPane sl = new JScrollPane();
		sl.getViewport().add(table);
		dtm.setColumnCount(5);
		dtm.setColumnIdentifiers(columns);

		lbl1.setBounds(300, 10, 300, 30);
		lbl1.setFont(new Font("宋体", Font.BOLD, 24));
		this.add(lbl1);

		sl.setBounds(60, 60, 680, 300);
		this.add(sl);

		Font f = new Font("宋体", Font.PLAIN, 12);
		this.add(lbl2);
		lbl2.setBounds(120, 380, 80, 25);
		lbl2.setFont(f);
		this.add(did);
		did.setBounds(180, 380, 80, 23);
		did.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(340, 380, 80, 25);
		lbl3.setFont(f);
		this.add(dname);
		dname.setBounds(400, 380, 80, 23);
		dname.setFont(f);
		this.add(bt);
		bt.setBounds(600, 380, 80, 25);
		bt.setFont(f);
		bt.addActionListener(this);

		setSize(830, 520);
		this.setVisible(true);
		this.setLocation(200, 100);

		if (Database.joinDB())
		{
			String sql = "select D_Number,D_Name,D_Count from department";
			try
			{
				if (Database.query(sql))
				{
					while (Database.rs.next())
					{
						String dNumber = ("" + Database.rs.getInt("D_Number"));
						String dName = ("" + Database.rs.getString("D_Name"));
						String dCount = ("" + Database.rs.getString("D_Count"));

						Vector v = new Vector();
						v.add(dNumber);
						v.add(dName);
						v.add(dCount);
						dtm.addRow(v);
					}
					Database.close();
				}
			} catch (Exception eBIQ)
			{
				System.out.println("初始化数据失败！");
			}
		}
	}

	public static void main(String args[])
	{
		new DIQ();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String esql = null;
		int rc = dtm.getRowCount();
		if (e.getSource() == bt)
		{
			for (int i = 0; i < rc; i++)// 清楚已有记录
			{
				dtm.removeRow(0);
			}
			if (did.getText().equals("") && dname.getText().equals(""))
			{
				esql = "select D_Number,D_Name,D_Count from department";
			} else if (did.getText().equals(""))
			{
				esql = "select D_Number,D_Name,D_Count from department where D_Name = '"
						+ dname.getText() + "'";
			} else if (dname.getText().equals(""))
			{
				esql = "select D_Number,D_Name,D_Count from department where D_Number = '"
						+ did.getText() + "'";
			}
			if (Database.joinDB())
			{
				try
				{
					if (Database.query(esql))
						while (Database.rs.next())
						{
							String dNumber = ("" + Database.rs.getInt("D_Number"));
							String dName = ("" + Database.rs.getString("D_Name"));
							String dCount = ("" + Database.rs.getString("D_Count"));

							Vector v = new Vector();
							v.add(dNumber);
							v.add(dName);
							v.add(dCount);
							dtm.addRow(v);
						}
					Database.close();
				} catch (Exception e1)
				{
					new JOptionPane().showMessageDialog(null, "读取数据失败!");
				}
			}
		}

	}
}

