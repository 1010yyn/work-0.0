//主控制台
package EMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class management extends JFrame implements Runnable
{

	Thread t = new Thread(this);// 在窗体里创建线程并实例化
	JDesktopPane deskpane = new JDesktopPane();// 在窗体里建立虚拟桌面并实例化
	JPanel p = new JPanel();// 创建一个面板并实例化
	Label lp1 = new Label("欢  迎  使  用  企  业  员  工  管  理  系  统 ！");

	// 菜单上的图标创建并实例化-----------------------------------
	ImageIcon icon1 = new ImageIcon("image//tjsc.gif");
	ImageIcon icon2 = new ImageIcon("image//cxdl.gif");
	ImageIcon icon3 = new ImageIcon("image//xgmm.gif");
	ImageIcon icon4 = new ImageIcon("image//tcxt.gif");
	ImageIcon icon5 = new ImageIcon("image//jj.gif");
	ImageIcon icon7 = new ImageIcon("image//cx.gif");
	ImageIcon icon8 = new ImageIcon("image//gl.gif");
	ImageIcon icon9 = new ImageIcon("image//xt.gif");
	ImageIcon icon10 = new ImageIcon("image//xxgl.gif");
	ImageIcon icon11 = new ImageIcon("image//xxcx.gif");
	ImageIcon icon12 = new ImageIcon("image//bz.gif");
	ImageIcon icon13 = new ImageIcon("image//gy.gif");
	ImageIcon icon14 = new ImageIcon("image//glxx.gif");
	ImageIcon icon15 = new ImageIcon("image//cxxx.gif");
	// 完----------------------------------------------------

public management(){//构造函数
	setTitle("企业员工管理系统");//设置窗体标题
	Container con = getContentPane();
	con.setLayout(new BorderLayout());//创建一个布局
	con.add(deskpane,BorderLayout.CENTER);//实例虚拟桌面的布局

	Font f =new Font("新宋体",Font.PLAIN,12);//设置一个字体，以后设置字体全部调用这种字体，懒得弄那么花花哨哨的
	
	JMenuBar mb = new JMenuBar();//实例化菜单栏
	//实例化菜单开始
	JMenu systemM = new JMenu("系统管理");
	systemM.setFont(f);
	JMenu manageM = new JMenu("信息管理");
	manageM.setFont(f);
	JMenu employeeMM = new JMenu("员工信息管理");//这个是信息管理的二级菜单
	employeeMM.setFont(f);
	JMenu selectM = new JMenu("信息查询");
	selectM.setFont(f);
	JMenu employeeSM =new JMenu("员工信息查询");//这个是信息查询的二级菜单
	employeeSM.setFont(f);
	JMenu aboutM=new JMenu("关于");
	aboutM.setFont(f);

	JMenuItem password = new JMenuItem("密码修改");
	password.setFont(f);
	JMenuItem Login = new JMenuItem("重新登陆");
	Login.setFont(f);
	JMenuItem addDelete = new JMenuItem("添加/删除用户");
	addDelete.setFont(f);
	JMenuItem exit = new JMenuItem("退出系统");
	exit.setFont(f);
	systemM.add(password);
	systemM.add(Login);
	systemM.add(addDelete);
	systemM.add(exit);
	//实例化系统管理菜单的菜单项结束
	
    // 退出窗体事件		
		this.addWindowListener(new WindowAdapter(){
	     public void windowClosing(WindowEvent e){
	        System.exit(0);
         }});

    //为系统管理菜单加事件----------------------------------------------------
    password.addActionListener(new ActionListener(){//密码修改监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("AmendPassword");
    		new Amendpassword();
    	}
    });
    Login.addActionListener(new ActionListener(){//重新登陆监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Login");
    		setVisible(false);
    		new login();
    	}
    });
    addDelete.addActionListener(new ActionListener(){//添加/删除用户监听
    	public void actionPerformed(ActionEvent e){
    		new adddelete();
    	}
    });
    exit.addActionListener(new ActionListener(){//退出系统监听
    	public void actionPerformed(ActionEvent e){
    		setVisible(false);
            System.exit(0);
    	}
    });
    //--------------------------------------------------------------------------------------------
	
	//实例化信息管理的菜单项
	JMenuItem departmentM = new JMenuItem("部门信息管理");
	departmentM.setFont(f);
	JMenuItem employeeM = new JMenuItem("基本信息管理");
	employeeM.setFont(f);
	JMenuItem trainM = new JMenuItem("培训信息管理");
	trainM.setFont(f);
	JMenuItem RewardspunishmentM = new JMenuItem("奖惩信息管理");
	RewardspunishmentM.setFont(f);
	JMenuItem wageM =new JMenuItem("薪资信息管理");
	wageM.setFont(f);
	employeeMM.add(trainM);
	employeeMM.add(employeeM);
	employeeMM.add(RewardspunishmentM);
	employeeMM.add(wageM);
	manageM.add(employeeMM);
	manageM.add(departmentM);
	//实例化信息管理的菜单项结束
    //为管理菜单加事件------------------------------------------------------------------
    departmentM.addActionListener(new ActionListener(){//部门信息管理监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Departmentmanage");
    		new department();
    	}
    });
    employeeM.addActionListener(new ActionListener(){//基本信息管理监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Employeemanage");
    		new employee();
    	}
    });
    trainM.addActionListener(new ActionListener(){//培训信息管理监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Trainmanage");
    		new training();
   		}
   	});
    RewardspunishmentM.addActionListener(new ActionListener(){//奖惩信息管理监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Rewardspunishment");
    		new rap();
   		}
   	});
    wageM.addActionListener(new ActionListener(){//薪资信息管理监听
    	public void actionPerformed(ActionEvent e){
    		System.out.println("WageManage");
    		new wage();
   		}
   	});

	//实例化信息查询的菜单项
	JMenuItem departmentS = new JMenuItem("部门信息查询");
	departmentS.setFont(f);
	JMenuItem employeeS = new JMenuItem("基本信息查询");
	employeeS.setFont(f);
	JMenuItem trainS = new JMenuItem("培训信息查询");
	trainS.setFont(f);
	JMenuItem RewardspunishmentS = new JMenuItem("奖惩信息查询");
	RewardspunishmentS.setFont(f);
	JMenuItem wageS =new JMenuItem("薪资信息查询");
	wageS.setFont(f);
	employeeSM.add(trainS);
	employeeSM.add(employeeS);
	employeeSM.add(RewardspunishmentS);
	employeeSM.add(wageS);
	selectM.add(employeeSM);
	selectM.add(departmentS);		
	//实例化信息查询的菜单项结束

    //为查询菜单加事件---------------------------------------------------------
	departmentS.addActionListener(new ActionListener(){//部门信息查询监听
		public void actionPerformed(ActionEvent e){
			System.out.println("DIQ");
			new DIQ();
		}
	});
	employeeS.addActionListener(new ActionListener(){//基本信息查询监听
		public void actionPerformed(ActionEvent e){
			System.out.println("BIQ");
			new BIQ();
		}
	});
	trainS.addActionListener(new ActionListener(){//培训信息查询监听
		public void actionPerformed(ActionEvent e){
			System.out.println("TIQ");
			new TIQ();
		}
	});
	RewardspunishmentS.addActionListener(new ActionListener(){//奖惩信息查询监听
		public void actionPerformed(ActionEvent e){
			System.out.println("RIQ");
			new RIQ();
		}
	});
	wageS.addActionListener(new ActionListener(){//薪资信息查询监听
		public void actionPerformed(ActionEvent e){
			System.out.println("SIQ");
			new SIQ();
		}
	});

	JMenuItem about =new JMenuItem("关于");
	about.setFont(f);
	aboutM.add(about);
    //为关于菜单加事件--------------------------------------------------------
	about.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new about();
		}
	});
    //---------------------------------------------------------------------

	mb.add(systemM);
	mb.add(manageM);
	mb.add(selectM);
	mb.add(aboutM);
    setJMenuBar(mb);

    //以下全都是在添加图标----------------------------------------------------------------------------------
    //窗口图标
    Image img=Toolkit.getDefaultToolkit().getImage("image\\main.gif");
    setIconImage(img);
    //添加菜单图标
	systemM.setIcon(icon9);
	manageM.setIcon(icon8);
	selectM.setIcon(icon7);
	
	addDelete.setIcon(icon1);
	Login.setIcon(icon2);
	password.setIcon(icon3);
	exit.setIcon(icon4);
	employeeMM.setIcon(icon5);
	employeeSM.setIcon(icon5);
	departmentM.setIcon(icon10);
	departmentS.setIcon(icon11);
	aboutM.setIcon(icon13);
	about.setIcon(icon13);

	employeeM.setIcon(icon14);
	trainM.setIcon(icon14);
	RewardspunishmentM.setIcon(icon14);
	wageM.setIcon(icon14);

	employeeS.setIcon(icon15);
	trainS.setIcon(icon15);
	RewardspunishmentS.setIcon(icon15);
	wageS.setIcon(icon15);		
    //添加完了------------------------------------------------------------

	    JToolBar jToolBar1 = new JToolBar();//创建一个工具栏
//	    jToolBar1.setLayout(new GridLayout(9,1));//设置成网格布局
    // add 20080818 ///////////////////////////////////////
	    jToolBar1.setLayout(new GridLayout(1,20));//设置成网格布局
    // add 20080818 ///////////////////////////////////////
    JButton jButton1 = new JButton();//创建并实例化按钮
    jButton1.setToolTipText("员工基本信息管理");//设置按钮悬停信息
    JButton jButton2 = new JButton();
    jButton2.setToolTipText("员工基本信息查询");
    JButton jButton3 = new JButton();
    jButton3.setToolTipText("修改密码");
    JButton jButton6 = new JButton();
    jButton6.setToolTipText("退出系统");        

    
    jToolBar1.setMaximumSize(new java.awt.Dimension(600, 50));//设置工具栏最大值
    jToolBar1.setMinimumSize(new java.awt.Dimension(600, 50));//设置工具栏最小值
    
    //添加工具栏中按钮的方法        
    jButton1.setIcon(new ImageIcon("image//1.png"));
    jButton1.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Employeemanage");
    		new employee();
   		}
   	});
    jToolBar1.add(jButton1);

    jButton2.setIcon(new ImageIcon("image//2.png"));
    jButton2.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		new BIQ();
   		}
   	});
    jToolBar1.add(jButton2);

    jButton3.setIcon(new ImageIcon("image//3.png"));
    jButton3.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		new Amendpassword();
   		}
   	});
    jToolBar1.add(jButton3);

    jButton6.setIcon(new javax.swing.ImageIcon("image//5.png"));
    jButton6.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
            System.exit(0);
   		}
   	});        
	jToolBar1.add(jButton6);		

	//添加工具栏中按钮的方法结束
	
//    jToolBar1.setBounds(0, 0, 30, 600);//工具栏位置
    // add 20080818 ///////////////////////////////////////
    jToolBar1.setBounds(0, 0, 600,50);//工具栏位置
    // add 20080818 ///////////////////////////////////////
//    jToolBar1.setEnabled(false);//禁止更改大小
    jToolBar1.setEnabled(true);//禁止更改大小
    // add 20080818 ///////////////////////////////////////
    con.add(jToolBar1,BorderLayout.NORTH);//布局
    // add 20080818 ///////////////////////////////////////
//    con.add(jToolBar1,BorderLayout.WEST);//布局

    p.setLayout(new BorderLayout());
    p.add(lp1,BorderLayout.EAST);
    t.start();

    con.add(p,BorderLayout.SOUTH);

    Toolkit t = Toolkit.getDefaultToolkit();
    int width = t.getScreenSize().width - 120 ;//100; //200
    int height = t.getScreenSize().height - 100; //80; //100
    setSize(600,400);
    setLocation(400,200);
	setVisible(true);
	setResizable(false);
}

	// 线程的方法
	public void run()
	{
		System.out.println("线程启动了!");// 友好提示
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = t.getScreenSize().width;
		System.out.println("x=" + x);
		lp1.setForeground(Color.red);
		while (true)
		{
			if (x < -600)
			{
				x = t.getScreenSize().width;
			}
			lp1.setBounds(x, 0, 700, 20);
			x -= 10;
			try
			{
				Thread.sleep(100);
			} catch (Exception e)
			{
				
			}
		}
	}

	// 退出窗体事件
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	public static void main(String args[])
	{// 主函数
		new management();
	}
}