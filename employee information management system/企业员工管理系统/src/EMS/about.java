package EMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class about extends JFrame implements ActionListener
{
	JLabel label = new JLabel("���л�����Windows XP/2003��");// ������ǩ��ʵ����
	JLabel labe2 = new JLabel("�������ԣ�JAVA");// ������ǩ��ʵ����
	JLabel labe3 = new JLabel("���ݿ����ͣ�MySQL Workbench");// ������ǩ��ʵ����
	JLabel labe4 = new JLabel("������Ա�����ϴ�ѧ�ƿ�1502");// ������ǩ��ʵ����

	public about()
	{// ���췽��
		setTitle("����");// ���ñ���
		this.setLayout(new GridLayout(4, 1));// �������񲼾�
		this.add(label);// ��ӱ�ǩ
		this.add(labe2);// ��ӱ�ǩ
		this.add(labe3);// ��ӱ�ǩ
		this.add(labe4);// ��ӱ�ǩ
		this.setBackground(Color.cyan);// ��ӱ�����ɫ

		setResizable(false);// ���ɸ��Ĵ�С
		setSize(380, 220);// ���ô�С
		setVisible(true);// �ɼ�
		this.setLocation(500, 300);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
