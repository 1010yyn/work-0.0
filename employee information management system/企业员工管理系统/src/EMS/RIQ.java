//奖惩查询
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

public class RIQ extends JFrame implements ActionListener
{

	JLabel lbl1 = new JLabel("奖 惩 信 息 查 询");
	JLabel lbl2 = new JLabel("奖惩编号：");
	JLabel lbl3 = new JLabel("员工编号：");
	JTextField rid = new JTextField(10);// 培训编号
	JTextField eid = new JTextField(10);// 员工编号
	JButton bt = new JButton("查询");
	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "顺序号", "奖惩编号", "员工编号","奖惩时间", "奖惩地点", "奖惩原因","备注" };

	public RIQ()
	{
		setTitle("奖 惩 信 息 查 询");
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
		this.add(rid);
		rid.setBounds(180, 380, 80, 23);
		rid.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(340, 380, 80, 25);
		lbl3.setFont(f);
		this.add(eid);
		eid.setBounds(400, 380, 80, 23);
		eid.setFont(f);
		this.add(bt);
		bt.setBounds(600, 380, 80, 25);
		bt.setFont(f);
		bt.addActionListener(this);

		setSize(830, 520);
		this.setVisible(true);
		this.setLocation(200, 100);

		if (Database.joinDB())
		{
			String sql = "select ID,R_Number,E_Number,R_Date,R_Address,R_Causation,R_Remark from rap";
			try
			{
				if (Database.query(sql))
				{
					while (Database.rs.next())
					{
						String id = ("" + Database.rs.getInt("ID"));
						String rNumber = ("" + Database.rs.getInt("R_Number"));
						String eNumber = ("" + Database.rs.getInt("E_Number"));
						String rDate = Database.rs.getString("R_Date");
						String rAddress = Database.rs.getString("R_Address");
						String rCausation = Database.rs.getString("R_Causation");
						String rRemark = Database.rs.getString("R_Remark");

						Vector v = new Vector();
						v.add(id);
						v.add(rNumber);
						v.add(eNumber);
						v.add(rDate);
						v.add(rAddress);
						v.add(rCausation);
						v.addElement(rRemark);
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
		new RIQ();
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
			if (rid.getText().equals("") && eid.getText().equals(""))
			{
				esql = "select ID,R_Number,E_Number,R_Date,R_Address,R_Causation,R_Remark from rap";
			} else if (rid.getText().equals(""))
			{
				esql = "select ID,R_Number,E_Number,R_Date,R_Address,R_Causation,R_Remark from rap where E_Number = '"
						+ eid.getText() + "'";
			} else if (eid.getText().equals(""))
			{
				esql = "select ID,R_Number,E_Number,R_Date,R_Address,R_Causation,R_Remark from rap where R_Number = '"
						+ rid.getText() + "'";
			}

			if (Database.joinDB())
			{
				try
				{
					if (Database.query(esql))
						while (Database.rs.next())
						{
							String id = ("" + Database.rs.getInt("ID"));
							String rNumber = ("" + Database.rs.getInt("R_Number"));
							String eNumber = ("" + Database.rs.getInt("E_Number"));
							String rDate = Database.rs.getString("R_Date");
							String rAddress = Database.rs.getString("R_Address");
							String rCausation = Database.rs.getString("R_Causation");
							String rRemark = Database.rs.getString("R_Remark");

							Vector v = new Vector();
							v.add(id);
							v.add(rNumber);
							v.add(eNumber);
							v.add(rDate);
							v.add(rAddress);
							v.add(rCausation);
							v.addElement(rRemark);
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
