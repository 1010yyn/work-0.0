//������̨
package EMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class management extends JFrame implements Runnable
{

	Thread t = new Thread(this);// �ڴ����ﴴ���̲߳�ʵ����
	JDesktopPane deskpane = new JDesktopPane();// �ڴ����ｨ���������沢ʵ����
	JPanel p = new JPanel();// ����һ����岢ʵ����
	Label lp1 = new Label("��  ӭ  ʹ  ��  ��  ҵ  Ա  ��  ��  ��  ϵ  ͳ ��");

	// �˵��ϵ�ͼ�괴����ʵ����-----------------------------------
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
	// ��----------------------------------------------------

public management(){//���캯��
	setTitle("��ҵԱ������ϵͳ");//���ô������
	Container con = getContentPane();
	con.setLayout(new BorderLayout());//����һ������
	con.add(deskpane,BorderLayout.CENTER);//ʵ����������Ĳ���

	Font f =new Font("������",Font.PLAIN,12);//����һ�����壬�Ժ���������ȫ�������������壬����Ū��ô�������ڵ�
	
	JMenuBar mb = new JMenuBar();//ʵ�����˵���
	//ʵ�����˵���ʼ
	JMenu systemM = new JMenu("ϵͳ����");
	systemM.setFont(f);
	JMenu manageM = new JMenu("��Ϣ����");
	manageM.setFont(f);
	JMenu employeeMM = new JMenu("Ա����Ϣ����");//�������Ϣ����Ķ����˵�
	employeeMM.setFont(f);
	JMenu selectM = new JMenu("��Ϣ��ѯ");
	selectM.setFont(f);
	JMenu employeeSM =new JMenu("Ա����Ϣ��ѯ");//�������Ϣ��ѯ�Ķ����˵�
	employeeSM.setFont(f);
	JMenu aboutM=new JMenu("����");
	aboutM.setFont(f);

	JMenuItem password = new JMenuItem("�����޸�");
	password.setFont(f);
	JMenuItem Login = new JMenuItem("���µ�½");
	Login.setFont(f);
	JMenuItem addDelete = new JMenuItem("���/ɾ���û�");
	addDelete.setFont(f);
	JMenuItem exit = new JMenuItem("�˳�ϵͳ");
	exit.setFont(f);
	systemM.add(password);
	systemM.add(Login);
	systemM.add(addDelete);
	systemM.add(exit);
	//ʵ����ϵͳ����˵��Ĳ˵������
	
    // �˳������¼�		
		this.addWindowListener(new WindowAdapter(){
	     public void windowClosing(WindowEvent e){
	        System.exit(0);
         }});

    //Ϊϵͳ����˵����¼�----------------------------------------------------
    password.addActionListener(new ActionListener(){//�����޸ļ���
    	public void actionPerformed(ActionEvent e){
    		System.out.println("AmendPassword");
    		new Amendpassword();
    	}
    });
    Login.addActionListener(new ActionListener(){//���µ�½����
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Login");
    		setVisible(false);
    		new login();
    	}
    });
    addDelete.addActionListener(new ActionListener(){//���/ɾ���û�����
    	public void actionPerformed(ActionEvent e){
    		new adddelete();
    	}
    });
    exit.addActionListener(new ActionListener(){//�˳�ϵͳ����
    	public void actionPerformed(ActionEvent e){
    		setVisible(false);
            System.exit(0);
    	}
    });
    //--------------------------------------------------------------------------------------------
	
	//ʵ������Ϣ����Ĳ˵���
	JMenuItem departmentM = new JMenuItem("������Ϣ����");
	departmentM.setFont(f);
	JMenuItem employeeM = new JMenuItem("������Ϣ����");
	employeeM.setFont(f);
	JMenuItem trainM = new JMenuItem("��ѵ��Ϣ����");
	trainM.setFont(f);
	JMenuItem RewardspunishmentM = new JMenuItem("������Ϣ����");
	RewardspunishmentM.setFont(f);
	JMenuItem wageM =new JMenuItem("н����Ϣ����");
	wageM.setFont(f);
	employeeMM.add(trainM);
	employeeMM.add(employeeM);
	employeeMM.add(RewardspunishmentM);
	employeeMM.add(wageM);
	manageM.add(employeeMM);
	manageM.add(departmentM);
	//ʵ������Ϣ����Ĳ˵������
    //Ϊ����˵����¼�------------------------------------------------------------------
    departmentM.addActionListener(new ActionListener(){//������Ϣ�������
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Departmentmanage");
    		new department();
    	}
    });
    employeeM.addActionListener(new ActionListener(){//������Ϣ�������
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Employeemanage");
    		new employee();
    	}
    });
    trainM.addActionListener(new ActionListener(){//��ѵ��Ϣ�������
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Trainmanage");
    		new training();
   		}
   	});
    RewardspunishmentM.addActionListener(new ActionListener(){//������Ϣ�������
    	public void actionPerformed(ActionEvent e){
    		System.out.println("Rewardspunishment");
    		new rap();
   		}
   	});
    wageM.addActionListener(new ActionListener(){//н����Ϣ�������
    	public void actionPerformed(ActionEvent e){
    		System.out.println("WageManage");
    		new wage();
   		}
   	});

	//ʵ������Ϣ��ѯ�Ĳ˵���
	JMenuItem departmentS = new JMenuItem("������Ϣ��ѯ");
	departmentS.setFont(f);
	JMenuItem employeeS = new JMenuItem("������Ϣ��ѯ");
	employeeS.setFont(f);
	JMenuItem trainS = new JMenuItem("��ѵ��Ϣ��ѯ");
	trainS.setFont(f);
	JMenuItem RewardspunishmentS = new JMenuItem("������Ϣ��ѯ");
	RewardspunishmentS.setFont(f);
	JMenuItem wageS =new JMenuItem("н����Ϣ��ѯ");
	wageS.setFont(f);
	employeeSM.add(trainS);
	employeeSM.add(employeeS);
	employeeSM.add(RewardspunishmentS);
	employeeSM.add(wageS);
	selectM.add(employeeSM);
	selectM.add(departmentS);		
	//ʵ������Ϣ��ѯ�Ĳ˵������

    //Ϊ��ѯ�˵����¼�---------------------------------------------------------
	departmentS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
		public void actionPerformed(ActionEvent e){
			System.out.println("DIQ");
			new DIQ();
		}
	});
	employeeS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
		public void actionPerformed(ActionEvent e){
			System.out.println("BIQ");
			new BIQ();
		}
	});
	trainS.addActionListener(new ActionListener(){//��ѵ��Ϣ��ѯ����
		public void actionPerformed(ActionEvent e){
			System.out.println("TIQ");
			new TIQ();
		}
	});
	RewardspunishmentS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
		public void actionPerformed(ActionEvent e){
			System.out.println("RIQ");
			new RIQ();
		}
	});
	wageS.addActionListener(new ActionListener(){//н����Ϣ��ѯ����
		public void actionPerformed(ActionEvent e){
			System.out.println("SIQ");
			new SIQ();
		}
	});

	JMenuItem about =new JMenuItem("����");
	about.setFont(f);
	aboutM.add(about);
    //Ϊ���ڲ˵����¼�--------------------------------------------------------
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

    //����ȫ���������ͼ��----------------------------------------------------------------------------------
    //����ͼ��
    Image img=Toolkit.getDefaultToolkit().getImage("image\\main.gif");
    setIconImage(img);
    //��Ӳ˵�ͼ��
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
    //�������------------------------------------------------------------

	    JToolBar jToolBar1 = new JToolBar();//����һ��������
//	    jToolBar1.setLayout(new GridLayout(9,1));//���ó����񲼾�
    // add 20080818 ///////////////////////////////////////
	    jToolBar1.setLayout(new GridLayout(1,20));//���ó����񲼾�
    // add 20080818 ///////////////////////////////////////
    JButton jButton1 = new JButton();//������ʵ������ť
    jButton1.setToolTipText("Ա��������Ϣ����");//���ð�ť��ͣ��Ϣ
    JButton jButton2 = new JButton();
    jButton2.setToolTipText("Ա��������Ϣ��ѯ");
    JButton jButton3 = new JButton();
    jButton3.setToolTipText("�޸�����");
    JButton jButton6 = new JButton();
    jButton6.setToolTipText("�˳�ϵͳ");        

    
    jToolBar1.setMaximumSize(new java.awt.Dimension(600, 50));//���ù��������ֵ
    jToolBar1.setMinimumSize(new java.awt.Dimension(600, 50));//���ù�������Сֵ
    
    //��ӹ������а�ť�ķ���        
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

	//��ӹ������а�ť�ķ�������
	
//    jToolBar1.setBounds(0, 0, 30, 600);//������λ��
    // add 20080818 ///////////////////////////////////////
    jToolBar1.setBounds(0, 0, 600,50);//������λ��
    // add 20080818 ///////////////////////////////////////
//    jToolBar1.setEnabled(false);//��ֹ���Ĵ�С
    jToolBar1.setEnabled(true);//��ֹ���Ĵ�С
    // add 20080818 ///////////////////////////////////////
    con.add(jToolBar1,BorderLayout.NORTH);//����
    // add 20080818 ///////////////////////////////////////
//    con.add(jToolBar1,BorderLayout.WEST);//����

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

	// �̵߳ķ���
	public void run()
	{
		System.out.println("�߳�������!");// �Ѻ���ʾ
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

	// �˳������¼�
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	public static void main(String args[])
	{// ������
		new management();
	}
}