//н�ʲ�ѯ

//JTextField id = new JTextField(10);// н�ʱ��
//	JTextField wid = new JTextField(10);// Ա�����
//	JTextField eid = new JTextField(10);// н�ʱ��
//	JTextField bwage = new JTextField(10);// Ա�����
//	JTextField boon = new JTextField(10);// н�ʱ��
//	JTextField bonus = new JTextField(10);// Ա�����
//	JTextField sum = new JTextField(10);// н�ʱ��

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
	JLabel lbl1 = new JLabel("н �� �� Ϣ �� ѯ");
	JLabel lbl2 = new JLabel("н�ʱ�ţ�");
	JLabel lbl3 = new JLabel("Ա����ţ�");
	JTextField wid = new JTextField(10);// н�ʱ��
	JTextField eid = new JTextField(10);// Ա�����
	JButton bt = new JButton("��ѯ");
	JTable table;

	DefaultTableModel dtm;

	String columns[] =
	{ "˳���", "н�ʱ��", " Ա�����", "��������", "����", "����", "��н��/Ԫ" };

	public SIQ()
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
				System.out.println("��ʼ������ʧ�ܣ�");
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
			for (int i = 0; i < rc; i++)// ������м�¼
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
					new JOptionPane().showMessageDialog(null, "��ȡ����ʧ��!");
				}
			}
		}

	}
}
