//������Ϣ����
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
	JLabel lbl1 = new JLabel("�� �� �� Ϣ �� ��");
	JLabel lbl2 = new JLabel("���ű�ţ�");
	JLabel lbl3 = new JLabel("�������ƣ�");
	JLabel lbl4 = new JLabel("����������");
	JTextField did = new JTextField(10);// ���ű��
	JTextField dname = new JTextField(10);// ��������
	JTextField dsum = new JTextField(10);// ��������
	JButton add = new JButton("���");
	JButton delete = new JButton("ɾ��");
	JTable table;

	public static String name, id, sum;

	DefaultTableModel dtm;

	String columns[] =
	{ "���", "����", " ����" };

	public department()
	{
		setTitle("�� ��  �� Ϣ �� ��");
		getContentPane().setLayout(null);
		dtm = new DefaultTableModel();
		table = new JTable(dtm);

		JScrollPane sl = new JScrollPane();
		sl.getViewport().add(table);
		dtm.setColumnCount(5);
		dtm.setColumnIdentifiers(columns);

		lbl1.setBounds(300, 10, 300, 30);
		lbl1.setFont(new Font("����", Font.BOLD, 24));
		this.add(lbl1);

		sl.setBounds(60, 60, 680, 300);
		this.add(sl);

		Font f = new Font("����", Font.PLAIN, 12);
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
				System.out.println("��ʼ������ʧ�ܣ�");
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
						new JOptionPane().showMessageDialog(null, "������Ϣ��ӳɹ�!");
					} else
						new JOptionPane().showMessageDialog(null, "������Ϣ���ʧ��!");
				} catch (Exception e2)
				{
					new JOptionPane().showMessageDialog(null, "��������Ϣ���ʧ��!");
				}
			} else
				new JOptionPane().showMessageDialog(null, "���ݿ�����ʧ��!");
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
						new JOptionPane().showMessageDialog(null, "������Ϣɾ���ɹ�!");
					else
						new JOptionPane().showMessageDialog(null, "������Ϣɾ��ʧ��!");
				} catch (Exception e1)
				{
					new JOptionPane().showMessageDialog(null, "��������Ϣɾ��ʧ��!");
				}
			} else
				new JOptionPane().showMessageDialog(null, "���ݿ�ɾ��ʧ��!");
		}
		for (int i = 0; i < rc; i++)// ������м�¼
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
				new JOptionPane().showMessageDialog(null, "�������ݿ�ʧ��!");
			}
		}
	}
}
