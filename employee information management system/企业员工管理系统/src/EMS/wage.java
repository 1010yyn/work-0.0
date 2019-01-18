//薪资信息管理
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

public class wage extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("薪 资 信 息 查 询");
	JLabel lbl2 = new JLabel("顺序号：");
	JLabel lbl3 = new JLabel("薪资编号：");
	JLabel lbl4 = new JLabel("员工编号：");
	JLabel lbl5 = new JLabel("基本工资：");
	JLabel lbl6 = new JLabel("福利：");
	JLabel lbl7 = new JLabel("奖金：");
	JLabel lbl8 = new JLabel("总薪资：");

	JTextField id = new JTextField(10);// 薪资编号
	JTextField wid = new JTextField(10);// 员工编号
	JTextField eid = new JTextField(10);// 薪资编号
	JTextField bwage = new JTextField(10);// 员工编号
	JTextField boon = new JTextField(10);// 薪资编号
	JTextField bonus = new JTextField(10);// 员工编号
	JTextField sum = new JTextField(10);// 薪资编号

	JButton add = new JButton("添加");
	JButton delete = new JButton("删除");

	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "顺序号", "薪资编号", " 员工编号", "基本工资", "福利", "奖金", "总薪资/元" };

	public wage()
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
		lbl2.setBounds(760, 90, 80, 25);
		lbl2.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(760, 120, 80, 25);
		lbl3.setFont(f);
		this.add(lbl4);
		lbl4.setBounds(760, 150, 80, 25);
		lbl4.setFont(f);
		this.add(lbl5);
		lbl5.setBounds(760, 180, 80, 25);
		lbl5.setFont(f);
		this.add(lbl6);
		lbl6.setBounds(760, 210, 80, 25);
		lbl6.setFont(f);
		this.add(lbl7);
		lbl7.setBounds(760, 240, 80, 25);
		lbl7.setFont(f);
		this.add(lbl8);
		lbl8.setBounds(760, 270, 80, 25);
		lbl8.setFont(f);

		this.add(id);
		id.setBounds(820, 90, 80, 23);
		id.setFont(f);
		this.add(wid);
		wid.setBounds(820, 120, 80, 23);
		wid.setFont(f);
		this.add(eid);
		eid.setBounds(820, 150, 80, 23);
		eid.setFont(f);
		this.add(bwage);
		bwage.setBounds(820, 180, 80, 23);
		bwage.setFont(f);
		this.add(boon);
		boon.setBounds(820, 210, 80, 23);
		boon.setFont(f);
		this.add(bonus);
		bonus.setBounds(820, 240, 80, 23);
		bonus.setFont(f);
		this.add(sum);
		sum.setBounds(820, 270, 80, 23);
		sum.setFont(f);

		this.add(add);
		add.setBounds(760, 310, 60, 25);
		add.setFont(f);
		add.addActionListener(this);
		this.add(delete);
		delete.setBounds(840, 310, 60, 25);
		delete.setFont(f);
		delete.addActionListener(this);

		setSize(955, 520);
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
		new wage();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String esql = null;
		int rc = dtm.getRowCount();
		if (e.getSource() == add)
		{
			esql = "insert into wage values(" + id.getText() + "," + wid.getText() + "," + eid.getText() + ","
					+ bwage.getText() + "," + boon.getText() + "," + bonus.getText() + "," + sum.getText() + ")";
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "薪资信息添加成功!");
					}
					else
						new JOptionPane().showMessageDialog(null, "薪资信息添加失败!");
				} catch (Exception eBIQ)
				{
					System.out.println("初始化数据失败！");
				}
			}
		} else if (e.getSource() == delete)
		{
			esql = "delete from wage where ID =" + id.getText() + " and W_Number =" + wid.getText() + " and E_Number ="
					+ eid.getText() + " and W_BasicWage =" + bwage.getText() + " and W_Boon =" + boon.getText()
					+ " and W_Boons =" + bonus.getText() + " and W_FactWage =" + sum.getText() + "";
			System.out.println(esql);
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "部门信息删除成功!");
					}
					else
						new JOptionPane().showMessageDialog(null, "部门信息删除失败!");
				} catch (Exception eBIQ)
				{
					System.out.println("初始化数据失败！");
				}
			}
		}
		for (int i = 0; i < rc; i++)// 清楚已有记录
		{
			dtm.removeRow(0);
		}
		esql = "select ID,W_Number,E_Number,W_BasicWage,W_Boon,W_Boons,W_FactWage from wage";
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
				new JOptionPane().showMessageDialog(null, "连接数据库失败!");
			}
		}
	}
}
