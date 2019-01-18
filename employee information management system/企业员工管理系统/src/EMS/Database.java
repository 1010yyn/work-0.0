//���ݿ����
package EMS;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database
{
	private final static String ID = "root";
	private final static String PWD = "root";
	private final static String URL = "jdbc:mysql://localhost:3306/ems";
	public static ResultSet rs;
	public static Statement stmt;
	public static Connection connect;
	public static String dbms = "ems";

	public static void main(String args[])
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // ����MYSQL JDBC��������
			System.out.println("Success loading Mysql Driver!\n");
		} catch (Exception e)
		{
			System.out.print("Error loading Mysql Driver!\n");
			e.printStackTrace();// �׳��쳣
		}
		try
		{
			connect = DriverManager.getConnection(URL, ID, PWD);// �����������ݿ�
			System.out.println("Success connect Mysql server!");
			stmt = connect.createStatement();
		} catch (Exception e)
		{
			try
			{// ����������ݿ�ʧ�ܣ��½����ݿ�
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", ID, PWD);// �����ӵ�һ�����е����ݿ�
				// ����URLΪ jdbc:mysql//��������ַ/���ݿ���
				System.out.println("Success connect Mysql server!");
				stmt = connect.createStatement();
				stmt.executeUpdate("create database EMS");// �������ݿ�EMS
				stmt.close();// �ر����ݿ�
				connect.close();// �ر�����
				connect = DriverManager.getConnection(URL, ID, PWD);// �������ӵ��½������ݿ�
				stmt = connect.createStatement();
				stmt.executeUpdate("create table Admin"// ����Ա��Ϣ
						+ "(User_ID int not null primary key,"// ����Ա���
						+ "User_Name varchar(20) not null,"// ����Ա����
						+ "Password varchar(20) not null);");// ����Ա����
				stmt.executeUpdate("insert into Admin values(1030415201,'Admin1','123456')");// Ĭ�Ϲ���Ա��Ϣ1
				stmt.executeUpdate("insert into Admin values(1030415202,'Admin2','123456')");// Ĭ�Ϲ���Ա��Ϣ2
				stmt.executeUpdate("insert into Admin values(1030415203,'Admin3','123456')");// Ĭ�Ϲ���Ա��Ϣ3
				stmt.executeUpdate("create table RAP"// ������Ϣ
						+ "(ID varchar(20) not null primary key,"// ˳���
						+ "R_Number int(20) not null,"// ���ͱ��
						+ "E_Number int(20) not null,"// Ա�����
						+ "R_Date datetime not null,"// ����ʱ��
						+ "R_Address varchar(50) not null,"// ���͵ص�
						+ "R_Causation varchar(200) not null,"// ����ԭ��
						+ "R_Remark varchar(500));");// ��ע
				stmt.executeUpdate("create table Training"// ��ѵ��Ϣ
						+ "(ID int not null primary key,"// ˳���
						+ "T_Number varchar(20) not null,"// ��ѵ���
						+ "T_Content varchar(100) not null,"// ��ѵ����
						+ "E_Number int not null,"// Ա�����
						+ "T_Date int,"// ��ѵ����
						+ "T_Money int);");// ��ѵ����
				stmt.executeUpdate("create table Wage"// н����Ϣ
						+ "(ID int not null primary key,"// ˳���
						+ "W_Number int not null,"// н�ʱ��
						+ "E_Number int not null,"// Ա�����
						+ "W_BasicWage decimal(18,2) not null,"// ��������
						+ "W_Boon decimal(18,2) not null,"// ����
						+ "W_Bonus decimal(18,2) not null,"// ����
						+ "W_FactWage decimal(18,2) not null);");// ��н��
				stmt.executeUpdate("create table Employee" + "(E_Number int not null primary key,"// Ա�����
						+ "E_Name varchar(30) not null,"// ����
						+ "E_Sex varchar(2) not null,"// �Ա�
						+ "E_BornDate datetime not null,"// ��������
						+ "E_Marriage varchar(4) not null,"// ����
						+ "E_PoliticsVisage varchar(20) not null,"// ����״��
						+ "E_SchoolAge varchar(20),"// ѧ��
						+ "E_EnterDate datetime,"// ��������
						+ "E_InDueFormDate datetime not null,"// ת������
						+ "D_Name int not null,"// ���ű��
						+ "E_Headship varchar(20) not null,"// ְ��
						+ "E_Estate varchar(10) not null,"// ��ְ��
						+ "E_Remark varchar(500));");// ��ע
				stmt.executeUpdate("create table Department"// ������Ϣ
						+ "(D_Number int not null primary key,"// ���
						+ "D_Name varchar(20) not null,"// ����
						+ "D_Count int not null);");// ����
				stmt.close();// �ر����ݿ�
				connect.close();// �ر�����
			} catch (Exception e1)
			{
				System.out.print("get data error!");
				e1.printStackTrace();
			}
		}
	}

	public static boolean joinDB()
	{
		boolean flag = true;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e)
		{
			System.out.print("Error loading Mysql Driver!");
			return false;
		}
		try
		{
			connect = DriverManager.getConnection(URL, ID, PWD);// �������ݿ�
			stmt = connect.createStatement();
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean query(String sqlString)
	{
		try
		{
			rs = stmt.executeQuery(sqlString);
		} catch (SQLException e)
		{
			return false;
		}
		return true;
	}

	public static boolean update(String sqlString)
	{
		try
		{
			stmt.executeUpdate(sqlString);
		} catch (SQLException e)
		{
			return false;
		}
		return true;
	}
	public static void close()
	{
		try
		{
			stmt.close();// �ر����ݿ�
			connect.close();// �ر�����
		} catch (SQLException e)
		{
		}
	}
}