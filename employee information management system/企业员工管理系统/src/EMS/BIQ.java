//Ա��������Ϣ��ѯ

//JTextField id = new JTextField(10);
//	JTextField name = new JTextField(10);
//	JTextField sex = new JTextField(10);
//	JTextField bdate = new JTextField(10);
//	JTextField marriage = new JTextField(10);
//	JTextField politicsvisage = new JTextField(10);
//	JTextField schoolage = new JTextField(10);
//	JTextField edate = new JTextField(10);
//	JTextField induedate = new JTextField(10);
//	JTextField department = new JTextField(10);
//	JTextField headship = new JTextField(10);
//	JTextField state = new JTextField(10);
//	JTextField remark = new JTextField(10);

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

public class BIQ extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("�� �� �� Ϣ �� ѯ");
	JLabel lbl2 = new JLabel("Ա����ţ�");
	JLabel lbl3 = new JLabel("Ա��������");
	JTextField btxtid = new JTextField(10);// Ա�����
	JTextField btxtname = new JTextField(10);// Ա������
	JButton btn1 = new JButton("��ѯ");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	JTable table;
	DefaultTableModel dtm;

	String columns[] =
	{ "Ա�����", "Ա������", " �Ա� ", "��������", "����״��", "������ò", " ѧ�� ", "���빫˾ʱ��", "ת��ʱ��", " ���� ", " ְ�� ", "Ա��״̬", " ��ע " };

	public BIQ()
	{
		setTitle("�� �� �� Ϣ �� ѯ");
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
		this.add(btxtid);
		btxtid.setBounds(180, 380, 80, 23);
		btxtid.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(340, 380, 80, 25);
		lbl3.setFont(f);
		this.add(btxtname);
		btxtname.setBounds(400, 380, 80, 23);
		btxtname.setFont(f);
		this.add(btn1);
		btn1.setBounds(600, 380, 80, 25);
		btn1.setFont(f);
		btn1.addActionListener(this);

		btxtid.setBorder(BorderFactory.createLineBorder(Color.black));
		btxtname.setBorder(BorderFactory.createLineBorder(Color.black));
		btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		sl.setBorder(BorderFactory.createLineBorder(Color.black));

		if (Database.joinDB())
		{
			String sql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee";
			try
			{
				if (Database.query(sql))
				{
					while (Database.rs.next())
					{
						String eNumber = ("" + Database.rs.getInt("E_Number"));
						String eName = Database.rs.getString("E_Name");
						String eSex = Database.rs.getString("E_Sex");
						String eBornDate = sdf.format(Database.rs.getDate("E_BornDate"));
						String eMarriage = Database.rs.getString("E_Marriage");
						String ePoliticsVisage = Database.rs.getString("E_PoliticsVisage");
						String eSchoolAge = Database.rs.getString("E_SchoolAge");
						String eEnterDate = sdf.format(Database.rs.getDate("E_EnterDate"));
						String eInDueFormDate = sdf.format(Database.rs.getDate("E_InDueFormDate"));
						String eHeadship = Database.rs.getString("E_Headship");
						String eEstate = Database.rs.getString("E_Estate");
						String eRemark = Database.rs.getString("E_Remark");

						Vector v = new Vector();
						v.add(eNumber);
						v.add(eName);
						v.add(eSex);
						v.add(eBornDate);
						v.add(eMarriage);
						v.add(ePoliticsVisage);
						v.add(eSchoolAge);
						v.add(eEnterDate);
						v.add(eInDueFormDate);
						v.add(eHeadship);
						v.add(eEstate);
						v.add(eRemark);
						dtm.addRow(v);
					}
					Database.close();
				}
			} catch (Exception eBIQ)
			{
				System.out.println("��ʼ������ʧ�ܣ�");
			}
		}

		setSize(830, 520);
		this.setVisible(true);
		this.setLocation(200, 100);
	}

	public void actionPerformed(ActionEvent e)
	{
		String esql;
		int rc = dtm.getRowCount();
		if (e.getSource() == btn1)
		{
			for (int i = 0; i < rc; i++)// ������м�¼
			{
				dtm.removeRow(0);
			}
			if (btxtid.getText().equals("") && btxtname.getText().equals(""))
			{
				esql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee";
			} else if (btxtname.getText().equals(""))
			{
				esql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee where E_Number = "
						+ btxtid.getText() + "";
			} else if (btxtid.getText().equals(""))
			{
				esql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee where E_Name = '"
						+ btxtname.getText() + "'";
			} else
			{
				esql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee where E_Number = "
						+ btxtid.getText() + " and E_Name like '%" + btxtname.getText() + "%'";
			}

			if (Database.joinDB())
			{
				try
				{
					if (Database.query(esql))
						while (Database.rs.next())
						{
							String eNumber = ("" + Database.rs.getInt("E_Number"));
							String eName = Database.rs.getString("E_Name");
							String eSex = Database.rs.getString("E_Sex");
							String eBornDate = sdf.format(Database.rs.getDate("E_BornDate"));
							String eMarriage = Database.rs.getString("E_Marriage");
							String ePoliticsVisage = Database.rs.getString("E_PoliticsVisage");
							String eSchoolAge = Database.rs.getString("E_SchoolAge");
							String eEnterDate = sdf.format(Database.rs.getDate("E_EnterDate"));
							String eInDueFormDate = sdf.format(Database.rs.getDate("E_InDueFormDate"));
							String eHeadship = Database.rs.getString("E_Headship");
							String eEstate = Database.rs.getString("E_Estate");
							String eRemark = Database.rs.getString("E_Remark");

							Vector v = new Vector();
							v.add(eNumber);
							v.add(eName);
							v.add(eSex);
							v.add(eBornDate);
							v.add(eMarriage);
							v.add(ePoliticsVisage);
							v.add(eSchoolAge);
							v.add(eEnterDate);
							v.add(eInDueFormDate);
							v.add(eHeadship);
							v.add(eEstate);
							v.add(eRemark);
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

	public static void main(String args[])
	{
		new BIQ();
	}
}
