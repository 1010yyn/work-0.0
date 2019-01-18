package EMS;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class adddelete extends JFrame implements ActionListener
{
	private JButton add, delete, clear;
	private JComboBox Admins;
	private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
	private JPasswordField pas1, pas2, pas3;
	private JTextField name, ID;

	public adddelete()
	{
		JFrame jf = this;
		setTitle("添加/删除用户");
		getContentPane().setLayout(null);// 设置布局

		jLabel1 = new JLabel("用户名：");
		jLabel2 = new JLabel("密码：");
		jLabel3 = new JLabel("确认密码：");
		jLabel4 = new JLabel("用户名:");
		jLabel5 = new JLabel("密码:");
		jLabel6 = new JLabel("编号");

		name = new JTextField("");
		ID = new JTextField("");

		pas1 = new JPasswordField();
		pas2 = new JPasswordField();
		pas3 = new JPasswordField();

		add = new JButton("添加");
		delete = new JButton("删除");
		clear = new JButton("清除");

		Admins = new JComboBox();

		this.add(jLabel1);
		jLabel1.setBounds(30, 30, 70, 20);
		this.add(jLabel2);
		jLabel2.setBounds(30, 90, 70, 18);
		this.add(jLabel3);
		jLabel3.setBounds(25, 120, 80, 18);
		this.add(jLabel4);
		jLabel4.setBounds(250, 30, 80, 18);
		this.add(jLabel5);
		jLabel5.setBounds(250, 90, 60, 18);
		this.add(jLabel6);
		jLabel6.setBounds(30, 60, 60, 20);

		this.add(name);
		name.setBounds(100, 30, 130, 24);
		this.add(ID);
		ID.setBounds(100, 60, 130, 20);

		this.add(Admins);
		Admins.setBounds(300, 30, 130, 24);

		this.add(pas1);
		pas1.setBounds(100, 90, 130, 24);
		this.add(pas2);
		pas2.setBounds(100, 120, 130, 24);
		this.add(pas3);
		pas3.setBounds(300, 90, 130, 24);

		this.add(add);
		add.addActionListener(this);
		add.setBounds(80, 150, 70, 27);
		this.add(delete);
		delete.addActionListener(this);
		delete.setBounds(300, 150, 130, 27);
		this.add(clear);
		clear.addActionListener(this);
		clear.setBounds(160, 150, 70, 27);

		String sql = "select User_Name from admin";

		try
		{
			if (Database.joinDB())
			{
				if (Database.query(sql))
				{
					Admins.addItem("");
					while (Database.rs.next())
					{
						String name = Database.rs.getString("User_Name");
						Admins.addItem(name);// 读取数据并添加
					}
					Database.stmt.close();// 关闭数据库
					Database.connect.close();// 关闭连接
				}
			} else
				System.out.println("数据库连接失败！");
		} catch (Exception e)
		{
			System.out.println("ERROR!");
		}

		this.setVisible(true);
		this.setSize(500, 250);
		this.setLocation(400, 280);
	}

	public static void main(String args[])
	{
		new adddelete();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == add)
		{
			if (name.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "用户名不能为空!");
			else if (pas1.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "密码不能为空!");
			else if (ID.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "管理员编号不能为空!");
			else if (!pas1.getText().equals(pas2.getText()))
				new JOptionPane().showMessageDialog(null, "两次输入密码不同，请重新确认!");
			else
			{
				String sql1 = "selecte * from Admin where User_ID	='" + ID.getText() + "'";
				if (Database.joinDB())
				{
					try
					{
						if (Database.query(sql1))
						{
							new JOptionPane().showMessageDialog(null, "该管理员编号已存在，请重新确认!");
						} else
						{
							sql1 = "insert into Admin values('" + ID.getText() + "','" + name.getText() + "','"
									+ pas1.getText() + "')";
							if (Database.update(sql1))
							{
								new JOptionPane().showMessageDialog(null, "管理员信息已添加成功!");
								Database.close();
							} else
								new JOptionPane().showMessageDialog(null, "管理员信息添加失败!");
						}
					} catch (Exception e1)
					{
						new JOptionPane().showMessageDialog(null, "数据库连接失败!");
					}
				}
			}
		} else if (e.getSource() == delete)
		{
			String name = "" + Admins.getSelectedItem();
			String sql2 = "delete from Admin where User_Name='" + name + "'";
			String sql3 = "select * from Admin where User_Name='" + name + "' and Password='" + pas3.getText() + "'";
			if (Database.joinDB())
			{
				try
				{
					if (Database.query(sql3))
					{
						if (Database.update(sql2))
						{
							new JOptionPane().showMessageDialog(null, "该管理员信息已成功删除!");
							Database.close();
						} else
							new JOptionPane().showMessageDialog(null, "该管理员信息删除失败!");
					} else
					{
						new JOptionPane().showMessageDialog(null, "该管理员信息删除失败!");
					}
				} catch (Exception e1)
				{
					new JOptionPane().showMessageDialog(null, "数据库连接失败!");
					Database.close();
				}
			}
		} else if (e.getSource() == clear)
		{
			name.setText("");
			pas1.setText("");
			pas2.setText("");
		}
	}
}