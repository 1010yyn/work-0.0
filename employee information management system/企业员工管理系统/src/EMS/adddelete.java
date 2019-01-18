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
		setTitle("���/ɾ���û�");
		getContentPane().setLayout(null);// ���ò���

		jLabel1 = new JLabel("�û�����");
		jLabel2 = new JLabel("���룺");
		jLabel3 = new JLabel("ȷ�����룺");
		jLabel4 = new JLabel("�û���:");
		jLabel5 = new JLabel("����:");
		jLabel6 = new JLabel("���");

		name = new JTextField("");
		ID = new JTextField("");

		pas1 = new JPasswordField();
		pas2 = new JPasswordField();
		pas3 = new JPasswordField();

		add = new JButton("���");
		delete = new JButton("ɾ��");
		clear = new JButton("���");

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
						Admins.addItem(name);// ��ȡ���ݲ����
					}
					Database.stmt.close();// �ر����ݿ�
					Database.connect.close();// �ر�����
				}
			} else
				System.out.println("���ݿ�����ʧ�ܣ�");
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
				new JOptionPane().showMessageDialog(null, "�û�������Ϊ��!");
			else if (pas1.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "���벻��Ϊ��!");
			else if (ID.getText().equals(""))
				new JOptionPane().showMessageDialog(null, "����Ա��Ų���Ϊ��!");
			else if (!pas1.getText().equals(pas2.getText()))
				new JOptionPane().showMessageDialog(null, "�����������벻ͬ��������ȷ��!");
			else
			{
				String sql1 = "selecte * from Admin where User_ID	='" + ID.getText() + "'";
				if (Database.joinDB())
				{
					try
					{
						if (Database.query(sql1))
						{
							new JOptionPane().showMessageDialog(null, "�ù���Ա����Ѵ��ڣ�������ȷ��!");
						} else
						{
							sql1 = "insert into Admin values('" + ID.getText() + "','" + name.getText() + "','"
									+ pas1.getText() + "')";
							if (Database.update(sql1))
							{
								new JOptionPane().showMessageDialog(null, "����Ա��Ϣ����ӳɹ�!");
								Database.close();
							} else
								new JOptionPane().showMessageDialog(null, "����Ա��Ϣ���ʧ��!");
						}
					} catch (Exception e1)
					{
						new JOptionPane().showMessageDialog(null, "���ݿ�����ʧ��!");
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
							new JOptionPane().showMessageDialog(null, "�ù���Ա��Ϣ�ѳɹ�ɾ��!");
							Database.close();
						} else
							new JOptionPane().showMessageDialog(null, "�ù���Ա��Ϣɾ��ʧ��!");
					} else
					{
						new JOptionPane().showMessageDialog(null, "�ù���Ա��Ϣɾ��ʧ��!");
					}
				} catch (Exception e1)
				{
					new JOptionPane().showMessageDialog(null, "���ݿ�����ʧ��!");
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