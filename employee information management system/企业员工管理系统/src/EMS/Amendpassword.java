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
	JLabel lbe1 = new JLabel("�޸�����");
	JPanel p = new JPanel();
	JLabel oldpwd, newpwd, repeat;
	JPasswordField opwd, npwd, rpt;
	JButton enter, cancel;
	JFrame jf;
	Font f = new Font("������", Font.PLAIN, 12);
	int height = 20;
	int width = 50;

	public Amendpassword()
	{
		jf = this;
		this.setLayout(null);
		this.setFont(f);
		setTitle("�޸�����");

		oldpwd = new JLabel("�����룺");
		newpwd = new JLabel("�����룺");
		repeat = new JLabel("�����룺");

		opwd = new JPasswordField("");
		npwd = new JPasswordField("");
		rpt = new JPasswordField("");

		enter = new JButton("ȷ��");
		cancel = new JButton("ȡ��");

		this.add(lbe1);
		lbe1.setFont(new Font("������", Font.BOLD, 16));
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

		setResizable(false);// ���ڴ�С���ɵ�
		Image img = Toolkit.getDefaultToolkit().getImage("image\\main.gif");// ����ͼ��
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
				new JOptionPane().showMessageDialog(null, "�����벻��Ϊ��!");
			else if (npwd.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "�����벻��Ϊ��!");
			else if (!npwd.getText().equals(rpt.getText()))
				new JOptionPane().showMessageDialog(null, "�����������벻ͬ��������ȷ��!");
			else if (npwd.getText().equals(rpt.getText()))
			{
				String sql1 = "select * from admin where User_Name = '" + login.nm + "' and Password = '"
						+ opwd.getText() + "'";// ��ѯ�����Ƿ���ȷ
				String sql2 = "update Admin set Password='" + rpt.getText() + "' where User_Name = '" + login.nm + "'";// �޸�����
				if (Database.joinDB())
				{
					if (Database.query(sql1))
						try
						{
							if (Database.rs.next())// ��������ȷ
							{
								Database.update(sql2);
								Database.close();
								this.dispose();
								new JOptionPane().showMessageDialog(null, "�����޸ĳɹ�!");
							} else
							{
								new JOptionPane().showMessageDialog(null, "�����������!");
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
					System.out.println("�������ݿⲻ�ɹ�!!!");
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