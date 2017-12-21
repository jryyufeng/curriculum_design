package Shuju;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.*;
import java.io.LineNumberReader;
import java.lang.reflect.Array;
import java.util.*;

import Look.Lookone;
//按行读取文件内容，把文件内容按空格分隔，形成链表（可按照行数进行查找，或者其他关键字）
class Linklist //单链表类
{
	private Node<String> first;
	int size;
	private static class Node<String>//节点作为内部类
	{
		String data;
		Node<String> next;
		Node(String data,Node<String> next)//节点的构造函数，初始化节点的数据域和指针域
		{
			this.data=data;
			this.next=next;
		}
		public void display()//节点类中的方法：打印节点值域
		{
			System.out.println(data+" ");
		}
	}
	
	public Linklist()//链表的构造函数：初始化头节点
	{
		first=null;//初始化头节点
	}
	public void addNode(String data)//添加节点,正序添加
	{
		if(first==null)
		{
			first=new Node<String>(data,null);
		}
		else
		{
			Node<String> current=first;
			while(current.next!=null)
			{
				current=current.next;
			}
			current.next=new Node<String>(data,null);
		}
		size++;
	}
	public Node<String> findByData(String data)//按值域货获取节点
	{
		Node<String> current=first;
		while(current.data!=data)
		{
			if(current.next==null)
				return null;
			current=current.next;
		}
		return current;
	}
	public void displayAllNode()//显示所有节点
	{
		Node<String> current=first;
		while(current!=null)
		{
			current.display();
			current=current.next;
		}
		System.out.println();
	}
}


public class Link extends Lookone implements ActionListener {
	
	Link() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sop(Object obj)
	{
		System.out.println(obj);
	}

	public static void Transform(Linklist linklist) //将文本读取并按空格分开转换成链表
	{
		//String[] str=new String[8];//用于存放读取的每一行字符串
		ArrayList<String> str=new ArrayList<String>();
		try
		{
		//FileReader fr=new FileReader("2.txt");会有乱码
		//BufferefReader的子类
		LineNumberReader lnr=new LineNumberReader(new InputStreamReader(new FileInputStream("D://XM//2.txt"),"UTF-8"));
		String line=new String();
		
		while((line=lnr.readLine())!=null)
		{
			if(line.startsWith("#"))
			{
				continue;
			}
			if(line.contains("bu"))//定位
			{
				str.add(line);
			}
		}
		lnr.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException("按行读取失败");
		}
		finally
		{
			for(int j=0;j<str.size();j++)//对每行的每个字符串进行处理
			{
                split(linklist,str.get(j));//str数组中的每个元素是一行的字符串
			}
		}
		
	}
	public static void split(Linklist l,String str)//字符串处理函数：将其按照空格切割
	{
		String reg=" +";
		String[] arr=str.split(reg);//arr数组中存放的就是每行切割后形成的一个个小字符串
		for(String s:arr)
		{
			//调用函数：实现把每行切割好的字符串数组取出来，添加到链表上去
			l.addNode(s);
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		Linklist  linklist=new Linklist();//创建单链表
		Transform(linklist);
		linklist.displayAllNode();
		
	}
}
