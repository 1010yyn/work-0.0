//登录界面
package EMS;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener
{
	JFrame jf;

	JTextField textName = new JTextField("Admin1");
	JPasswordField textPassword = new JPasswordField("123456");

	JLabel label = new JLabel("登 陆");
	JLabel labelName = new JLabel("用户名：");
	JLabel labelPassword = new JLabel("密　码：");

	JButton buttonEnter = new JButton("登录");
	JButton buttoncancel = new JButton("重置");

	public static String nm;

	public login()
	{
		jf = this;
		setTitle("企业员工管理系统");
		Font f = new Font("新宋体", Font.PLAIN, 12);
		Container con = getContentPane();
		con.setLayout(null);

		label.setBounds(130, 10, 140, 20);
		label.setFont(new Font("新宋体", Font.BOLD, 16));
		
		con.add(label);
		labelName.setBounds(55, 45, 55, 20);
		labelName.setFont(f);
		con.add(labelName);
		textName.setBounds(105, 45, 120, 20);
		con.add(textName);

		labelPassword.setBounds(55, 75, 55, 20);
		con.add(labelPassword);
		labelPassword.setFont(f);
		textPassword.setBounds(105, 75, 120, 20);
		con.add(textPassword);

		buttonEnter.setBounds(95, 115, 60, 20);
		buttonEnter.setFont(f);
		buttonEnter.addActionListener(this);
		con.add(buttonEnter);
		buttonEnter.setFocusable(true);

		buttoncancel.setBounds(170, 115, 60, 20);
		buttoncancel.setFont(f);
		buttoncancel.addActionListener(this);
		con.add(buttoncancel);

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

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == buttonEnter)// 确认登陆
		{
			if (textName.getText().equals(""))
			{
				new JOptionPane().showMessageDialog(null, "用户名不能为空!");
			} else if (textPassword.getText().equals(""))
			{
				new JOptionPane().showMessageDialog(null, "密码不能为空!");
			} else
			{
				nm = textName.getText();
				String sql = "select * from admin where User_Name = '" + textName.getText() + "' and Password = '"
						+ textPassword.getText() + "'";
				Judge(sql);
			}
		} else if (e.getSource() == buttoncancel)// 重置信息
		{
			textName.setText("");
			textPassword.setText("");
		}
	}

	private void Judge(String sqlString)
	{
		if (Database.joinDB())
		{
			if (Database.query(sqlString))
				try
				{
					if (Database.rs.next())
					{
						System.out.println("密码正确");
						Database.close();
						jf.setVisible(false);
						management.main(null);
					} else
					{
						System.out.println("密码错误");
						textName.setText("");
						textPassword.setText("");
						new JOptionPane().showMessageDialog(null, "用户名或密码错误!", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex)
				{
					System.out.println(ex.getMessage());
				}
		} else
		{
			System.out.println("连接数据库失败!!!");
		}
	}

	public static void main(String args[])
	{
		new login();
	}
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