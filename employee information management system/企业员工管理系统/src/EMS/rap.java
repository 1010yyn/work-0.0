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

public class rap extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("�� �� �� Ϣ �� ѯ");
	JLabel lbl2 = new JLabel("˳��ţ�");
	JLabel lbl3 = new JLabel("���ͱ�ţ�");
	JLabel lbl4 = new JLabel("Ա����ţ�");
	JLabel lbl5 = new JLabel("����ʱ�䣺");
	JLabel lbl6 = new JLabel("���͵ص㣺");
	JLabel lbl7 = new JLabel("����ԭ��");
	JLabel lbl8 = new JLabel("��ע��");

	JTextField id = new JTextField(10);// н�ʱ��
	JTextField wid = new JTextField(10);// Ա�����
	JTextField eid = new JTextField(10);// н�ʱ��
	JTextField bwage = new JTextField(10);// Ա�����
	JTextField boon = new JTextField(10);// н�ʱ��
	JTextField bonus = new JTextField(10);// Ա�����
	JTextField sum = new JTextField(10);// н�ʱ��

	JButton add = new JButton("���");
	JButton delete = new JButton("ɾ��");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "˳���", "���ͱ��", " Ա�����", "����ʱ��", "���͵ص�", "����ԭ��", "��ע" };

	public rap()
	{
		setTitle("н �� �� Ϣ �� ѯ");
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
				System.out.println("��ʼ������ʧ�ܣ�");
			}
		}
	}

	public static void main(String args[])
	{
		new rap();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String esql = null;
		int rc = dtm.getRowCount();
		if (e.getSource() == add)
		{
			esql = "insert into rap values(" + id.getText() + "," + wid.getText() + "," + eid.getText() + ",'"
					+ bwage.getText() + "','" + boon.getText() + "','" + bonus.getText() + "','" + sum.getText() + "')";
			System.out.println(esql);
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "н����Ϣ��ӳɹ�!");
					}
					else
						new JOptionPane().showMessageDialog(null, "н����Ϣ���ʧ��!");
				} catch (Exception eBIQ)
				{
					System.out.println("��ʼ������ʧ�ܣ�");
				}
			}
		} else if (e.getSource() == delete)
		{
			esql = "delete from rap where ID =" + id.getText() + " and R_Number =" + wid.getText() + " and E_Number ="
					+ eid.getText() + " and R_Date ='" + bwage.getText() + "' and R_Address ='" + boon.getText()
					+ "' and R_Causation ='" + bonus.getText() + "' and R_Remark ='" + sum.getText() + "'";
			System.out.println(esql);
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "������Ϣɾ���ɹ�!");
					}
					else
						new JOptionPane().showMessageDialog(null, "������Ϣɾ��ʧ��!");
				} catch (Exception eBIQ)
				{
					System.out.println("��ʼ������ʧ�ܣ�");
				}
			}
		}
		for (int i = 0; i < rc; i++)// ������м�¼
		{
			dtm.removeRow(0);
		}
		esql = "select ID,R_Number,E_Number,R_Date,R_Address,R_Causation,R_Remark from rap";
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
				new JOptionPane().showMessageDialog(null, "�������ݿ�ʧ��!");
			}
		}
	}
}
