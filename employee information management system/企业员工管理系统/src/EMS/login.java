//��¼����
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

	JLabel label = new JLabel("�� ½");
	JLabel labelName = new JLabel("�û�����");
	JLabel labelPassword = new JLabel("�ܡ��룺");

	JButton buttonEnter = new JButton("��¼");
	JButton buttoncancel = new JButton("����");

	public static String nm;

	public login()
	{
		jf = this;
		setTitle("��ҵԱ������ϵͳ");
		Font f = new Font("������", Font.PLAIN, 12);
		Container con = getContentPane();
		con.setLayout(null);

		label.setBounds(130, 10, 140, 20);
		label.setFont(new Font("������", Font.BOLD, 16));
		
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

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == buttonEnter)// ȷ�ϵ�½
		{
			if (textName.getText().equals(""))
			{
				new JOptionPane().showMessageDialog(null, "�û�������Ϊ��!");
			} else if (textPassword.getText().equals(""))
			{
				new JOptionPane().showMessageDialog(null, "���벻��Ϊ��!");
			} else
			{
				nm = textName.getText();
				String sql = "select * from admin where User_Name = '" + textName.getText() + "' and Password = '"
						+ textPassword.getText() + "'";
				Judge(sql);
			}
		} else if (e.getSource() == buttoncancel)// ������Ϣ
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
						System.out.println("������ȷ");
						Database.close();
						jf.setVisible(false);
						management.main(null);
					} else
					{
						System.out.println("�������");
						textName.setText("");
						textPassword.setText("");
						new JOptionPane().showMessageDialog(null, "�û������������!", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex)
				{
					System.out.println(ex.getMessage());
				}
		} else
		{
			System.out.println("�������ݿ�ʧ��!!!");
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