//薪资查询

//JTextField id = new JTextField(10);// 薪资编号
//	JTextField wid = new JTextField(10);// 员工编号
//	JTextField eid = new JTextField(10);// 薪资编号
//	JTextField bwage = new JTextField(10);// 员工编号
//	JTextField boon = new JTextField(10);// 薪资编号
//	JTextField bonus = new JTextField(10);// 员工编号
//	JTextField sum = new JTextField(10);// 薪资编号

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

public class SIQ extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("薪 资 信 息 查 询");
	JLabel lbl2 = new JLabel("薪资编号：");
	JLabel lbl3 = new JLabel("员工编号：");
	JTextField wid = new JTextField(10);// 薪资编号
	JTextField eid = new JTextField(10);// 员工编号
	JButton bt = new JButton("查询");
	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "顺序号", "薪资编号", " 员工编号", "基本工资", "福利", "奖金", "总薪资/元" };

	public SIQ()
	{
		setTitle("薪 资 信 息 查 询");
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
		this.add(wid);
		wid.setBounds(180, 380, 80, 23);
		wid.setFont(f);
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
			String sql = "select ID,W_Number,E_Number,W_BasicWage,W_Boon,W_Boons,W_FactWage from wage";
			try
			{
				if (Database.query(sql))
				{
					while (Database.rs.next())
					{
						String id = ("" + Database.rs.getInt("ID"));
						String wNumber = ("" + Database.rs.getInt("W_Number"));
						String eNumber = ("" + Database.rs.getString("E_Number"));
						String wBasicWage = ("" + Database.rs.getString("W_BasicWage"));
						String wBoon = ("" + Database.rs.getString("W_Boon"));
						String wBonus = ("" + Database.rs.getString("W_Boons"));
						String wFactWage = ("" + Database.rs.getString("W_FactWage"));

						Vector v = new Vector();
						v.add(id);
						v.add(wNumber);
						v.add(eNumber);
						v.add(wBasicWage);
						v.add(wBoon);
						v.add(wBonus);
						v.add(wFactWage);
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
		new SIQ();
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
			if (wid.getText().equals("") && eid.getText().equals(""))
			{
				esql = "select ID,W_Number,E_Number,W_BasicWage,W_Boon,W_Boons,W_FactWage from wage";
			} else if (wid.getText().equals(""))
			{
				esql = "select ID,W_Number,E_Number,W_BasicWage,W_Boon,W_Boons,W_FactWage from wage where E_Number = '"
						+ eid.getText() + "'";
			} else if (eid.getText().equals(""))
			{
				esql = "select ID,W_Number,E_Number,W_BasicWage,W_Boon,W_Boons,W_FactWage from wage where W_Number = '"
						+ wid.getText() + "'";
			}
			if (Database.joinDB())
			{
				try
				{
					if (Database.query(esql))
						while (Database.rs.next())
						{
							String id = ("" + Database.rs.getInt("ID"));
							String wNumber = ("" + Database.rs.getInt("W_Number"));
							String eNumber = ("" + Database.rs.getString("E_Number"));
							String wBasicWage = ("" + Database.rs.getString("W_BasicWage"));
							String wBoon = ("" + Database.rs.getString("W_Boon"));
							String wBonus = ("" + Database.rs.getString("W_Boons"));
							String wFactWage = ("" + Database.rs.getString("W_FactWage"));

							Vector v = new Vector();
							v.add(id);
							v.add(wNumber);
							v.add(eNumber);
							v.add(wBasicWage);
							v.add(wBoon);
							v.add(wBonus);
							v.add(wFactWage);
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
