package Look;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import DESone.Queuetry;
import Look.Lookone.winClose;
import Shuju.Queue;
public class Encrypt3 extends Lookone implements ActionListener{
	public Queue<String> q1=new Queue<String>(10); //��������
	public Queue<String> q2=new Queue<String>(10);
	static Encrypt3 en3=new Encrypt3();
	public static Map<String,String> map=new HashMap<String,String>();
	public static Map<String,String> map1=new HashMap<String,String>();
	int flag1;
	ArrayList as=new ArrayList();
	JFrame frame=new JFrame("�ļ�����");
	Container c=frame.getContentPane();
	JPanel p=new JPanel();
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	JPanel p6=new JPanel();
	JPanel p11=new JPanel();
	JPanel p22=new JPanel();
	JPanel p33=new JPanel();
	JPanel p44=new JPanel();
	JPanel p551=new JPanel();
	JScrollPane p55 = new JScrollPane(p551);
	Box p41=Box.createVerticalBox();//
	JButton b1=new JButton("ѡ��");
	JButton b2=new JButton("����");
	JButton b3=new JButton("����");
	JButton b4=new JButton("������������");
	JButton b5=new JButton("ɾ��");
	Label l1=new Label("�ļ��б�");//��ǩ
	Label l2=new Label("�ļ�����");//��ǩ
	//Label l3=new Label("��");//��ǩ
	String a[]={"�ĵ�","ͼƬ","��Ƶ","��Ƶ"};
	JComboBox combox1=new JComboBox(a);
	//combox1.setSelectedIndex(0);//��0��Ԫ����Ϊ��ǰ�ɼ�
	//�ļ�ѡ��
	JList list1;
	int flag=1;
	public ArrayList choosefile(){
		 ArrayList as=new ArrayList();
			JFileChooser filechoose=new JFileChooser();
			filechoose.setMultiSelectionEnabled(true);
			int result=filechoose.showDialog(filechoose, "���");
			if(result==JFileChooser.APPROVE_OPTION){
			File[] selectfiles=filechoose.getSelectedFiles();//��ȡѡ����ļ�
			for(int i=0;i<selectfiles.length;i++){
				String a=selectfiles[i].getAbsolutePath();
				as.add(a);
			}
			}
			//System.out.println(as);
			return as;
		
	}
	public void myframe(){
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p55.setOpaque(false);
		p55.getViewport().setOpaque(false);
		p551.setLayout(new BorderLayout());
		b1.setForeground(Color.BLUE);
		b1.setBackground(Color.CYAN);
		b1.setPreferredSize(new Dimension(120,50));
		final DefaultListModel listmodel = new DefaultListModel();
		
		b1.setBorderPainted(false);//ȥ�߿�
		b2.setForeground(Color.BLUE);
		b2.setBackground(Color.CYAN);
		b2.setPreferredSize(new Dimension(120,50));
		b2.setBorderPainted(false);//ȥ�߿�
		b3.setForeground(Color.BLUE);
		b3.setBackground(Color.CYAN);
		b3.setPreferredSize(new Dimension(120,50));
		b3.setBorderPainted(false);//ȥ�߿�
		p1.add(b1);
		p2.add(b2);
		p3.add(b3);
		p4.setLayout(new GridLayout(3,1));//
		p4.add(p1);
		p4.add(p2);
		p4.add(p3);
		p6.add(b4);
		p6.add(b5);
		frame.addWindowListener(new winClosel());
		 b1.addActionListener(new ActionListener(){//ѡ��
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
			   map.clear();
			   map1.clear();
			   ArrayList asw=choosefile();//
			   for(int i=0;i<asw.size();i++){
				   listmodel.addElement(asw.get(i).toString());
			   }
				}
			});
		 
		list1=new JList(listmodel);
		 b4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if((String) list1.getSelectedValue()==null){
						   //System.out.println("Ϊѡ��");
						   JOptionPane.showMessageDialog(null,"δѡ��!");
					   }
					else{
					// TODO Auto-generated method stub
					String text=(String) list1.getSelectedValue();
					//System.out.println(choosedStr);
					String type=combox1.getSelectedItem().toString();
					//System.out.println(type);
					q1.insert(text);
					map.put(text,type);
					//System.out.println(map);
					File tempFile=new File(text.trim());//��ȡ�ļ���
					String name=tempFile.getName();
					//System.out.println("name="+name);
					String name1=s1+name;
					int i=text.lastIndexOf("\\"); 
					//System.out.println(i);
					String s3=text.substring(0, i)+"\\";
					//System.out.println(s3+name1);
					q2.insert(s3+name1);
			   		map1.put(s3+name1, type);
			   		listmodel.remove(list1.getSelectedIndex());   
				}
				}
			});
		b2.addActionListener(new ActionListener()
		{
			
		   public void actionPerformed(ActionEvent e)
		   {
			   if((String) list1.getSelectedValue()==null&&map.isEmpty()){
				   //System.out.println("Ϊѡ��");
				   JOptionPane.showMessageDialog(null,"δѡ��!");
			   }
			   else{
			   try {
					  Queuetry q=new Queuetry();
					  q.que(1);
					  JOptionPane.showMessageDialog(null,"�������!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"����ʧ��!");
				}
			 
			}
		   }
		   
		});	
		
		b3.addActionListener(new ActionListener()
		{
			
		   public void actionPerformed(ActionEvent e)
		   {
			   if((String) list1.getSelectedValue()==null&&map.isEmpty()){
				   //System.out.println("Ϊѡ��");
				   JOptionPane.showMessageDialog(null,"δѡ��!");
			   }
			   else{
			   try {
					  Queuetry q=new Queuetry();
					  q.que(0);
					  JOptionPane.showMessageDialog(null,"�������!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"����ʧ��!");
				}
			}
		   }
		   
		});
		
		b5.addActionListener(new ActionListener()
		{
			
		   public void actionPerformed(ActionEvent e)
		   {
			   listmodel.remove(list1.getSelectedIndex());
			}
		   
		});
		
		p551.add(list1,BorderLayout.CENTER);
		p551.add(l1,BorderLayout.NORTH);
		p55.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p5.add(l2);
		p5.add(combox1);
		c.add(p4,BorderLayout.WEST);
		c.add(p55,BorderLayout.CENTER);
		c.add(p5,BorderLayout.EAST);
		c.add(p6,BorderLayout.SOUTH);
		frame.setLocation(0, 100);
		frame.setBounds(200,200,500,360);
		//frame.setResizable(false);
		frame.setVisible(true);
		
	}
	class winClosel extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			((DefaultListModel)list1.getModel()).removeAllElements();
			map.clear();
			map1.clear();
			(e.getWindow()).dispose();
			//System.exit(0);
			flag=0;
		}
	}
	/**
	 * @param args
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		en3.myframe();
	}

}
