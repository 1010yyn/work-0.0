package EMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.*;

public class Amendpassword extends JFrame implements ActionListener
{
	public static Statement stmt;
	JLabel lbe1 = new JLabel("修改密码");
	JPanel p = new JPanel();
	JLabel oldpwd, newpwd, repeat;
	JPasswordField opwd, npwd, rpt;
	JButton enter, cancel;
	JFrame jf;
	Font f = new Font("新宋体", Font.PLAIN, 12);
	int height = 20;
	int width = 50;

	public Amendpassword()
	{
		jf = this;
		this.setLayout(null);
		this.setFont(f);
		setTitle("修改密码");

		oldpwd = new JLabel("旧密码：");
		newpwd = new JLabel("新密码：");
		repeat = new JLabel("新密码：");

		opwd = new JPasswordField("");
		npwd = new JPasswordField("");
		rpt = new JPasswordField("");

		enter = new JButton("确定");
		cancel = new JButton("取消");

		this.add(lbe1);
		lbe1.setFont(new Font("新宋体", Font.BOLD, 16));
		lbe1.setBounds(120, 10, width * 2, height);
		this.add(oldpwd);
		oldpwd.setFont(f);
		oldpwd.setBounds(75, 40, width, height);
		this.add(newpwd);
		newpwd.setFont(f);
		newpwd.setBounds(75, 60, width, height);
		this.add(repeat);
		repeat.setFont(f);
		repeat.setBounds(75, 80, width, height);

		this.add(opwd);
		opwd.setBounds(125, 40, width * 2, height);
		this.add(npwd);
		npwd.setBounds(125, 60, width * 2, height);
		this.add(rpt);
		rpt.setBounds(125, 80, width * 2, height);

		this.add(enter);
		enter.addActionListener(this);
		enter.setBounds(80, 115, 60, height);
		this.add(cancel);
		cancel.addActionListener(this);
		cancel.setBounds(160, 115, 60, height);

		setResizable(false);// 窗口大小不可调
		Image img = Toolkit.getDefaultToolkit().getImage("image\\main.gif");// 窗口图标
		setIconImage(img);
		Toolkit t = Toolkit.getDefaultToolkit();

		int w = t.getScreenSize().width;
		int h = t.getScreenSize().height;
		setBounds(w / 2 - 150, h / 2 - 90, 310, 180);
		this.addWindowListener(new Wadapt());
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Amendpassword();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == enter)
		{
			if (opwd.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "旧密码不能为空!");
			else if (npwd.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "新密码不能为空!");
			else if (!npwd.getText().equals(rpt.getText()))
				new JOptionPane().showMessageDialog(null, "两次输入密码不同，请重新确认!");
			else if (npwd.getText().equals(rpt.getText()))
			{
				String sql1 = "select * from admin where User_Name = '" + login.nm + "' and Password = '"
						+ opwd.getText() + "'";// 查询密码是否正确
				String sql2 = "update Admin set Password='" + rpt.getText() + "' where User_Name = '" + login.nm + "'";// 修改密码
				if (Database.joinDB())
				{
					if (Database.query(sql1))
						try
						{
							if (Database.rs.next())// 旧密码正确
							{
								Database.update(sql2);
								Database.close();
								this.dispose();
								new JOptionPane().showMessageDialog(null, "密码修改成功!");
							} else
							{
								new JOptionPane().showMessageDialog(null, "密码输入错误!");
								opwd.setText("");
								npwd.setText("");
								rpt.setText("");
							}
						} catch (Exception ex)
						{
							System.out.println(ex.getMessage());
						}
				} else
				{
					System.out.println("连接数据库不成功!!!");
				}
			} else
			{
				this.dispose();
			}
		}
	}

	public static boolean q(String sql)
	{
		return true;
	}

	class Wadapt extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			Frame frm = (Frame) evt.getSource();
			frm.setVisible(false);
			frm.dispose();
			System.exit(0);
		}
	}
}