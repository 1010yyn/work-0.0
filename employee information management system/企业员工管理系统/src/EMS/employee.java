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

public class employee extends JFrame implements ActionListener
{
	JLabel lbl1 = new JLabel("Ա �� �� �� �� Ϣ �� ��");
	JLabel lbl2 = new JLabel("��ţ�");
	JLabel lbl3 = new JLabel("�Ա�");
	JLabel lbl4 = new JLabel("�������ڣ�");
	JLabel lbl5 = new JLabel("����״����");
	JLabel lbl6 = new JLabel("������ò��");
	JLabel lbl7 = new JLabel("ѧ����");
	JLabel lbl8 = new JLabel("���빫˾ʱ�䣺");
	JLabel lbl9 = new JLabel("ת��ʱ�䣺");
	JLabel lbl10 = new JLabel("���ű�� ��");
	JLabel lbl11 = new JLabel("ְ��");
	JLabel lbl12 = new JLabel("Ա��״̬��");
	JLabel lbl13 = new JLabel("��ע��");
	JLabel lbl14 = new JLabel("������");
	JTextField id = new JTextField(10);
	JTextField name = new JTextField(10);
	JTextField sex = new JTextField(10);
	JTextField bdate = new JTextField(10);
	JTextField marriage = new JTextField(10);
	JTextField politicsvisage = new JTextField(10);
	JTextField schoolage = new JTextField(10);
	JTextField edate = new JTextField(10);
	JTextField induedate = new JTextField(10);
	JTextField department = new JTextField(10);
	JTextField headship = new JTextField(10);
	JTextField state = new JTextField(10);
	JTextField remark = new JTextField(10);

	JButton add = new JButton("���");
	JButton delete = new JButton("ɾ��");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	JTable table;
	DefaultTableModel dtm;

	String columns[] =
	{ "Ա�����", "Ա������", " �Ա� ", "��������", "����״��", "������ò", " ѧ�� ", "���빫˾ʱ��", "ת��ʱ��", " ���ű�� ", " ְ�� ", "Ա��״̬", " ��ע " };

	public employee()
	{
		setTitle("�� ��  �� Ϣ �� ��");
		getContentPane().setLayout(null);
		dtm = new DefaultTableModel();
		table = new JTable(dtm);

		JScrollPane sl = new JScrollPane();
		sl.getViewport().add(table);
		dtm.setColumnCount(5);
		dtm.setColumnIdentifiers(columns);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		sl.setBounds(60, 60, 800, 300);
		this.add(sl);

		Font f = new Font("����", Font.PLAIN, 12);
		this.add(lbl1);
		lbl1.setBounds(430, 10, 300, 30);
		lbl1.setFont(new Font("����", Font.BOLD, 24));
		this.add(lbl2);
		lbl2.setBounds(900, 60, 80, 25);
		lbl2.setFont(f);
		this.add(lbl3);
		lbl3.setBounds(900, 100, 80, 25);
		lbl3.setFont(f);
		this.add(lbl4);
		lbl4.setBounds(900, 120, 80, 25);
		lbl4.setFont(f);
		this.add(lbl5);
		lbl5.setBounds(900, 140, 80, 25);
		lbl5.setFont(f);
		this.add(lbl6);
		lbl6.setBounds(900, 160, 80, 25);
		lbl6.setFont(f);
		this.add(lbl7);
		lbl7.setBounds(900, 180, 80, 25);
		lbl7.setFont(f);
		this.add(lbl8);
		lbl8.setBounds(900, 200, 100, 25);
		lbl8.setFont(f);
		this.add(lbl9);
		lbl9.setBounds(900, 220, 80, 25);
		lbl9.setFont(f);
		this.add(lbl10);
		lbl10.setBounds(900, 240, 80, 25);
		lbl10.setFont(f);
		this.add(lbl11);
		lbl11.setBounds(900, 260, 80, 25);
		lbl11.setFont(f);
		this.add(lbl12);
		lbl12.setBounds(900, 280, 80, 25);
		lbl12.setFont(f);
		this.add(lbl13);
		lbl13.setBounds(900, 300, 80, 25);
		lbl13.setFont(f);
		this.add(lbl14);
		lbl14.setBounds(900, 80, 80, 25);
		lbl14.setFont(f);

		this.add(id);
		id.setBounds(980, 60, 80, 23);
		id.setFont(f);
		this.add(name);
		name.setBounds(980, 80, 80, 23);
		name.setFont(f);
		this.add(sex);
		sex.setBounds(980, 100, 80, 23);
		sex.setFont(f);
		this.add(bdate);
		bdate.setBounds(980, 120, 80, 23);
		bdate.setFont(f);
		this.add(marriage);
		marriage.setBounds(980, 140, 80, 23);
		marriage.setFont(f);
		this.add(politicsvisage);
		politicsvisage.setBounds(980, 160, 80, 23);
		politicsvisage.setFont(f);
		this.add(schoolage);
		schoolage.setBounds(980, 180, 80, 23);
		schoolage.setFont(f);
		this.add(edate);
		edate.setBounds(980, 200, 80, 23);
		edate.setFont(f);
		this.add(induedate);
		induedate.setBounds(980, 220, 80, 23);
		induedate.setFont(f);
		this.add(department);
		department.setBounds(980, 240, 80, 23);
		department.setFont(f);
		this.add(headship);
		headship.setBounds(980, 260, 80, 23);
		headship.setFont(f);
		this.add(state);
		state.setBounds(980, 280, 80, 23);
		state.setFont(f);
		this.add(remark);
		remark.setBounds(980, 300, 80, 23);
		remark.setFont(f);

		this.add(add);
		add.setBounds(900, 330, 80, 25);
		add.setFont(f);
		add.addActionListener(this);
		this.add(delete);
		delete.setBounds(980, 330, 80, 25);
		delete.setFont(f);
		delete.addActionListener(this);

		setSize(1130, 450);
		this.setVisible(true);
		this.setLocation(200, 100);

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
	}

	public static void main(String args[])
	{
		new employee();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String esql = null;
		int rc = dtm.getRowCount();
		if (e.getSource() == add)
		{
			esql = "insert into employee values(" + id.getText() + ",'" + name.getText() + "','"+sex.getText()+"','"+bdate.getText()+"','"+marriage.getText()+"','"+politicsvisage.getText()+"','"+schoolage.getText()+"','"+edate.getText()+"','"+induedate.getText()+"',"+department.getText()+",'"+headship.getText()+"','"+state.getText()+"','"+remark.getText()+"')";
			System.out.println(esql);
			if (Database.joinDB())
			{
				try
				{
					if (Database.update(esql))
					{
						new JOptionPane().showMessageDialog(null, "Ա��������Ϣ��ӳɹ�!");
					} else
						new JOptionPane().showMessageDialog(null, "Ա��������Ϣ���ʧ��!");
				} catch (Exception e2)
				{
					new JOptionPane().showMessageDialog(null, "Ա��������Ϣ���ʧ��!");
				}
			} else
				new JOptionPane().showMessageDialog(null, "���ݿ�����ʧ��!");
		} else if (e.getSource() == delete)
		{
			esql = "delete from employee where E_Number=" + id.getText() + " and E_Name='" + name.getText() + "'and E_BornDate='"+bdate.getText()+"' and E_Marriage='"+marriage.getText()+"' and E_PoliticsVisage='"+politicsvisage.getText()+"' and E_SchoolAge='"+schoolage.getText()+"' and E_EnterDate='"+edate.getText()+"' and E_InDueFormDate='"+induedate.getText()+"' and E_Headship='"+headship.getText()+"' and E_Estate='"+state.getText()
			+"' and E_Remark='"+remark.getText()+"'";
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
		esql = "select E_Number,E_Name,E_Sex,E_BornDate,E_Marriage,E_PoliticsVisage,E_SchoolAge,E_EnterDate,E_InDueFormDate,E_Headship,E_Estate,E_Remark from employee";
		System.out.println(esql);
		if (Database.joinDB())
		{
			try
			{
				if (Database.query(esql))
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
		
	}
}
