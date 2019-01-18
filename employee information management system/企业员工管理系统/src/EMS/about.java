package EMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class about extends JFrame implements ActionListener
{
	JLabel label = new JLabel("运行环境：Windows XP/2003等");// 创建标签并实例化
	JLabel labe2 = new JLabel("开发语言：JAVA");// 创建标签并实例化
	JLabel labe3 = new JLabel("数据库类型：MySQL Workbench");// 创建标签并实例化
	JLabel labe4 = new JLabel("开发人员：江南大学计科1502");// 创建标签并实例化

	public about()
	{// 构造方法
		setTitle("关于");// 设置标题
		this.setLayout(new GridLayout(4, 1));// 设置网格布局
		this.add(label);// 添加标签
		this.add(labe2);// 添加标签
		this.add(labe3);// 添加标签
		this.add(labe4);// 添加标签
		this.setBackground(Color.cyan);// 添加背景颜色

		setResizable(false);// 不可更改大小
		setSize(380, 220);// 设置大小
		setVisible(true);// 可见
		this.setLocation(500, 300);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
