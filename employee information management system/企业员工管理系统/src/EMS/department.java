//部门信息管理
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

public class department extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("部 门 信 息 管 理");
	JLabel lbl2 = new JLabel("部门编号：");
	JLabel lbl3 = new JLabel("部门名称：");
	JLabel lbl4 = new JLabel("部门人数：");
	JTextField did = new JTextField(10);// 部门编号
	JTextField dname = new JTextField(10);// 部门名称
	JTextField dsum = new JTextField(10);// 部门人数
	JButton add = new JButton("添加");
	JButton delete = new JButton("删除");
	JTable table;

	public static String name, id, sum;

	DefaultTableModel dtm;

	String columns[] =
	{ "编号", "名称", " 人数" };

	public department()
	{
		setTitle("部 门  信 息 管 理");
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
		lbl2.setBounds(100, 380, 80, 25);
		lbl2.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(340, 380, 80, 25);
		lbl3.setFont(f);
		this.add(lbl4);
		lbl4.setBounds(580, 380, 80, 25);
		lbl4.setFont(f);

		this.add(did);
		did.setBounds(160, 380, 80, 23);
		did.setFont(f);
		this.add(dname);
		dname.setBounds(400, 380, 80, 23);
		dname.setFont(f);
		this.add(dsum);
		dsum.setBounds(640, 380, 80, 23);
		dsum.setFont(f);

		this.add(add);
		add.setBounds(160, 410, 80, 25);
		add.setFont(f);
		add.addActionListener(this);
		this.add(delete);
		delete.setBounds(640, 410, 80, 25);
		delete.setFont(f);
		delete.addActionListener(this);

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
		new department();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		id = did.getText();
		name = dname.getText();
		sum = dsum.getText();
		String esql = null;
		int rc = dtm.getRowCount();
		if (e.getSource() == add)
		{
			esql = "insert into department values('" + id + "','" + name + "','" + sum + "')";
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "部门信息添加成功!");
					} else
						new JOptionPane().showMessageDialog(null, "部门信息添加失败!");
				} catch (Exception e2)
				{
					new JOptionPane().showMessageDialog(null, "数部门信息添加失败!");
				}
			} else
				new JOptionPane().showMessageDialog(null, "数据库连接失败!");
		} else if (e.getSource() == delete)
		{
			esql = "delete from department where D_Number='" + id + "' and D_Name='" + name + "' and D_Count=" + sum
					+ "";
			System.out.println(esql);
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
						new JOptionPane().showMessageDialog(null, "部门信息删除成功!");
					else
						new JOptionPane().showMessageDialog(null, "部门信息删除失败!");
				} catch (Exception e1)
				{
					new JOptionPane().showMessageDialog(null, "数部门信息删除失败!");
				}
			} else
				new JOptionPane().showMessageDialog(null, "数据库删除失败!");
		}
		for (int i = 0; i < rc; i++)// 清楚已有记录
		{
			dtm.removeRow(0);
		}
		esql = "select D_Number,D_Name,D_Count from department";
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
				new JOptionPane().showMessageDialog(null, "连接数据库失败!");
			}
		}
	}
}
