package Shuju;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.*;
import java.io.LineNumberReader;
import java.lang.reflect.Array;
import java.util.*;

import Look.Lookone;
//���ж�ȡ�ļ����ݣ����ļ����ݰ��ո�ָ����γ������ɰ����������в��ң����������ؼ��֣�
class Linklist //��������
{
	private Node<String> first;
	int size;
	private static class Node<String>//�ڵ���Ϊ�ڲ���
	{
		String data;
		Node<String> next;
		Node(String data,Node<String> next)//�ڵ�Ĺ��캯������ʼ���ڵ���������ָ����
		{
			this.data=data;
			this.next=next;
		}
		public void display()//�ڵ����еķ�������ӡ�ڵ�ֵ��
		{
			System.out.println(data+" ");
		}
	}
	
	public Linklist()//����Ĺ��캯������ʼ��ͷ�ڵ�
	{
		first=null;//��ʼ��ͷ�ڵ�
	}
	public void addNode(String data)//��ӽڵ�,�������
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
	public Node<String> findByData(String data)//��ֵ�����ȡ�ڵ�
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
	public void displayAllNode()//��ʾ���нڵ�
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

	public static void Transform(Linklist linklist) //���ı���ȡ�����ո�ֿ�ת��������
	{
		//String[] str=new String[8];//���ڴ�Ŷ�ȡ��ÿһ���ַ���
		ArrayList<String> str=new ArrayList<String>();
		try
		{
		//FileReader fr=new FileReader("2.txt");��������
		//BufferefReader������
		LineNumberReader lnr=new LineNumberReader(new InputStreamReader(new FileInputStream("D://XM//2.txt"),"UTF-8"));
		String line=new String();
		
		while((line=lnr.readLine())!=null)
		{
			if(line.startsWith("#"))
			{
				continue;
			}
			if(line.contains("bu"))//��λ
			{
				str.add(line);
			}
		}
		lnr.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException("���ж�ȡʧ��");
		}
		finally
		{
			for(int j=0;j<str.size();j++)//��ÿ�е�ÿ���ַ������д���
			{
                split(linklist,str.get(j));//str�����е�ÿ��Ԫ����һ�е��ַ���
			}
		}
		
	}
	public static void split(Linklist l,String str)//�ַ��������������䰴�տո��и�
	{
		String reg=" +";
		String[] arr=str.split(reg);//arr�����д�ŵľ���ÿ���и���γɵ�һ����С�ַ���
		for(String s:arr)
		{
			//���ú�����ʵ�ְ�ÿ���и�õ��ַ�������ȡ��������ӵ�������ȥ
			l.addNode(s);
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		Linklist  linklist=new Linklist();//����������
		Transform(linklist);
		linklist.displayAllNode();
		
	}
}
