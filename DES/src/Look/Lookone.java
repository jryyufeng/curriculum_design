package Look;
import DESone.DES;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
public class Lookone extends JFrame{
	
	public static Lookone one=new Lookone();
	static JTextField f1;//�ı���
	JTextField f2;
	JButton b1;
	JButton b2;
	JButton b3;
	String power;//��ʾȨ��
	String imgePath="./putice/get.jpg";//����ͼƬ
	JPanel p5;//���
	Image img=Toolkit.getDefaultToolkit().createImage(imgePath);
	public static String s1;
	static String sp;
	public static byte[] saes;
	DES daes=new DES();
	JComboBox combox1p=new JComboBox();
	
	protected Lookone(){
		Container cp=getContentPane();
		JLabel helloLabel=new JLabel();
		//helloLabel.setIcon(new ImageIcon());
		//helloLabel.setBackground(Color.BLUE);
		//helloLabel.setBounds(0,0,354,220);
		//cp.add(helloLabel);
		String a[]={"����Ա","��ͨ�û�"};
		combox1p=new JComboBox(a);
		combox1p.setSelectedIndex(0);//��0��Ԫ����Ϊ��ǰ�ɼ�
		Label l1=new Label("�û���");//��ǩ
		Label l2=new Label("���룺");//��ǩ
		Label l3=new Label("��¼��ݣ�");//��ǩ
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p6=new JPanel();
		p5=new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}	
		};//?
		f1=new JTextField(15);
		f2=new JPasswordField(15);
		b1=new JButton("��¼");
		b2=new JButton("����");
		b3=new JButton("ע��");
		p1.setBackground(Color.orange);
		p2.add(l1);
		p2.add(f1);
		p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p2.setBackground(Color.ORANGE);
		p3.add(l2);
		p3.add(f2);
		p3.setBackground(Color.ORANGE);
		p3.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p4.add(b1);
		p4.add(b2);
		p4.add(b3);
		p4.setBorder(new MatteBorder(-3,-3,-3,-3,Color.CYAN));
		p4.setBackground(Color.ORANGE);
		p6.setBorder(new MatteBorder(-3,-3,-3,-3,Color.CYAN));
		p6.setBackground(Color.ORANGE);
		p6.add(l3);
		p6.add(combox1p);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		p5.add(p2);
		p5.add(p3);
		p5.add(p6);
		p5.add(p4);
		cp.add(p5,BorderLayout.CENTER);
		b1.addActionListener(new Enter());//addActionListener
		b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					s1=f1.getText();
					Lookone.s1=s1;
					sp=(String) combox1p.getSelectedItem();
					Lookone.sp=sp;
					Lookone.saes=saes;
					System.out.println(saes);
				}
			});
		b2.addActionListener(new ReWrite());
		b3.addActionListener(new Register());
		//b3.addActionListener(new SocketServer());
		addWindowListener(new winClose());	
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		/*
		DES des=new DES();
		des.saveDesKey();
		des.encrypt("D:\\XM\\13.mp4","D:\\XM\\132.mp4");
		des.decrypt("D:\\XM\\132.mp4","D:\\XM\\133.mp4");
		*/
		//Lookone one=new Lookone();
		one.setTitle("�����¼");
		one.setSize(360,249);//����Ϊ����
		one.setLocationRelativeTo(null); 
		one.setResizable(false);
		one.setVisible(true);		
		File filepath2=new File("D://XM");
		if(!filepath2.exists()&&!filepath2.isDirectory()){
			filepath2.mkdir();//�½�·��
		}
		else{}
		File filepath=new File("D://XM//client");
		if(!filepath.exists()&&!filepath.isDirectory()){
			filepath.mkdir();//�½�·��
		}
		else{}
		File filepathj=new File("D://XM//��ѹ");
		if(!filepathj.exists()&&!filepathj.isDirectory()){
			filepathj.mkdir();//�½�·��
		}
		else{}
		File filepath1=new File("D://XM//server");
		if(!filepath1.exists()&&!filepath1.isDirectory()){
			filepath1.mkdir();//�½�·��
		}
		else{}
		
		String fileName="3.txt";
		File file = new File(filepath2,fileName);
		if(!file.exists()){
		try {
		file.createNewFile();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		}
		File file1 = new File("D://XM//2.txt");
		if(!file1.exists()){
		try {
		file1.createNewFile();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		
		
	}
	public void create1(){
		ArrayList a1 = new ArrayList();
		a1.add("��Ƶ");
		a1.add("��Ƶ");
		a1.add("�ĵ�");
		a1.add("ͼƬ");
		a1.add("�ļ���");
		File file=new File("D:\\XM\\"+f1.getText());
		if(!file.exists()&&!file.isDirectory())
		{
			file.mkdir();
			try {   
		    	   String string=" attrib +R  "+file.getAbsolutePath();  
		    	   Runtime.getRuntime().exec(string);  //
		    	   String string1=" attrib +H  "+file.getAbsolutePath();//�����ļ�����Ϊ����
		    	   Runtime.getRuntime().exec(string1);
	         } catch (IOException e) {   
	         e.printStackTrace();   
			       }   
			for(int i=0;i<a1.size();i++)
			{
				//System.out.print(a1.size());
				String child1=(String) a1.get(i);
				System.out.print(child1);
				File child = new File(file,child1);
				child.mkdir();
				try {   
			    	   String string=" attrib +R  "+child.getAbsolutePath();  
			    	   Runtime.getRuntime().exec(string);  //
			    	   String string1=" attrib +H  "+child.getAbsolutePath();//�����ļ�����Ϊ����
			    	   Runtime.getRuntime().exec(string1);
		         } catch (IOException e) {   
		         e.printStackTrace();   
				       }   
			}
			
			
		}
		else
		{
			System.out.print("ddw");
		}
         
	}
	
	 public void choose(){
		 final JFrame frame=new JFrame("����ѡ��"); 
		 Container c=frame.getContentPane();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //String imgePath1="d:/XM/putice/time.jpg" ;
		 ImageIcon img = new ImageIcon("d:/XM/putice/9.jpg");
			//Ҫ���õı���ͼƬ
			JLabel imgLabel = new JLabel(img);
			//������ͼ���ڱ�ǩ�
			frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
			//��������ǩ��ӵ�jfram��LayeredPane����
			imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			// ���ñ�����ǩ��λ��
			((JPanel) c).setOpaque(false); 
			// �����������Ϊ͸������LayeredPane����еı�����ʾ������
		 JPanel p1=new JPanel();
		 JPanel p2=new JPanel();
		 JPanel p3=new JPanel();
		 JPanel p4=new JPanel();
		 JPanel p5=new JPanel();
		 JPanel p6=new JPanel();
		 JPanel p7=new JPanel();
		 final JTextArea jta = new JTextArea("��ӭʹ��",10, 15);
		 //JPanel p8=new JPanel();
		 //p8.setLayout(new BoxLayout(p8,BoxLayout.Y_AXIS));
		 Box p8=Box.createVerticalBox();
		 //ImageIcon image1=new ImageIcon("D:\\XM\\putice\\-1.psd");
		 final JButton b1=new JButton("���ؼӽ���");
		 //b1.setIcon(image1);
		 b1.setFont(new Font("�����п�", Font.PLAIN, 16));
		 b1.setForeground(Color.BLUE);
		 //b1.setBackground(Color.CYAN);
		 b1.setPreferredSize(new Dimension(120,50));
		 b1.setBorderPainted(false);//ȥ�߿�
		 b1.addActionListener(new Choosetry());	
		 b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if (e.getSource()==b1)jta.setText("  �˹������ڼ��ܻ�����ļ���" +
							"�ɴ���һ�������ļ�," +
							"�ļ���಻����ʮ����"+
							"Ҳ����ֱ����������ܵ��ļ��У�" +
							"���������ļ��С�");
					
				}
			});
		 b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
					
				}
			});
		 JButton b2=new JButton("�û���Ϣ");
		 b2.setFont(new Font("�����п�", Font.PLAIN, 16));
		 b2.setForeground(Color.BLUE);
		 b2.setPreferredSize(new Dimension(120,50));
		 b2.setBorderPainted(false);
		 b2.addActionListener(new Looktree());
		 b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jta.setText("  �˹������ڲ鿴�û���������ļ���Ϣ��" +
							"�����ļ������ļ����ơ�");
				}
			}); 
		 JButton b3=new JButton("�ļ�����");
		 b3.setFont(new Font("�����п�", Font.PLAIN, 16));
		 b3.setForeground(Color.BLUE);
		 b3.setPreferredSize(new Dimension(120,50));
		 b3.setBorderPainted(false);
		 b3.addActionListener(new chooseS());
		 b3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					jta.setText("  �˹�����������P2P�����ļ���" +
							"��ʵ���ļ��İ�ȫ����"+
							"�����ļ�����ѹ���ļ�����");
				}
			});

		 JButton b4=new JButton("�û�ע��");
		 b4.setFont(new Font("�����п�", Font.PLAIN, 16));
		 b4.setForeground(Color.BLUE);
		 b4.setPreferredSize(new Dimension(120,50));
		 b4.setBorderPainted(false);
		 b4.addActionListener(new Delete1());
		 b4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					jta.setText("  �˹��������û�ע����" +
							"���û�����ʹ�����ʱ����ע�������û���Ϣ��" +
							"����������Ϣ���ļ���Ϣ��");
				}
			});
		 
		 p5.setLayout(new GridLayout(4,1));
		 p1.add(b1);
		 p1.setOpaque(false);
		 p2.add(b2);
		 p2.setOpaque(false);
		 p3.add(b3);
		 p3.setOpaque(false);
		 p4.add(b4);
		 p4.setOpaque(false);
		 p5.add(p1);
		 p5.add(p2);
		 p5.add(p3);
		 p5.add(p4);
		 p5.setOpaque(false);
		 c.add(p5,BorderLayout.WEST);
		 Label l=new Label("���ܽ���");
		 l.setFont(new java.awt.Font("Dialog",1,15));
		 jta.setTabSize(4);
		 jta.setFont(new Font("�꿬��", Font.BOLD, 16));
		 jta.setLineWrap(true);// �����Զ����й���
		 jta.setWrapStyleWord(true);// ������в����ֹ���
		 jta.setBackground(Color.cyan);
		 jta.setEditable(false);
		 p6.add(l);
		 p6.setOpaque(false);
		 p7.add(jta);
		 p7.setOpaque(false);
		 p8.add(p6);
		 p8.add(p7);
		 p8.setOpaque(false);
		 c.add(p8,BorderLayout.CENTER);
		 frame.setLocation(0, 100);
		 frame.setBounds(200,200,500,360);
		 frame.setResizable(false);
		 frame.setVisible(true);
	 }
	
	class Enter implements ActionListener{
		
		public ArrayList findStringInFile() throws IOException{
			int j=1;
			File file = new File("D://XM//2.txt");
			ArrayList a2=new ArrayList();
	        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");//���ǵ������ʽ
	        BufferedReader bufferedReader = new BufferedReader(read);
			String line = new String();
			String line1 = null;
			String line2 = null;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.startsWith("#")){
					continue;
				}
				//ָ���ַ����жϴ�
				if (line.contains(f1.getText())) {
					//System.out.println(f1.getText());
					//System.out.println(line);
					//String[] a=line.split(" ");
					line1=line+" "+j;
					line2=line;
					a2.add(line1);
					j++;
					//System.out.println(a[0]);
				}
			}
			if(j==2)
			{
				a2.set(0, line2);
			}
			//System.out.println(j);
			return a2;
		}
		
//���
		public void actionPerformed(ActionEvent e) {
			one.create1();
			// TODO Auto-generated method stub
			String[] sa;
			int flag=0;
			Enter find1=new Enter();	 
			ArrayList al1=new ArrayList();
			try {
				al1 = find1.findStringInFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0;i<al1.size();i++){
				String[] a=((String) al1.get(i)).split(" ");
			if((f1.getText().equals(a[0]))&&(f2.getText().equals(a[1]))&&sp.equals("����Ա")){
				JOptionPane.showMessageDialog(null,"�Թ���Ա��ݵ�¼�ɹ���");//�¿ؼ�
				power="adminstrator";
				one.dispose();
				//one.Looktwo();
				one.choose();
				flag++;
				break;
			}
			else if((f1.getText().equals(a[0]))&&(f2.getText().equals(a[1]))&&sp.equals("��ͨ�û�")){
					JOptionPane.showMessageDialog(null, "��user��ݵ�¼�ɹ���");
					power="adminstrator";
					Encrypt1 encrypt=new Encrypt1();
					one.dispose();
					encrypt.Looktwo();
					flag++;
					break;
				}
			}
			if(flag==0){
				JOptionPane.showMessageDialog(null,"��¼ʧ�ܣ�");
			}
		}
	}
	class ReWrite implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			f1.setText("");
			f2.setText("");
			f1.requestFocus();//
		}
		
	}	 
	class winClose extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			(e.getWindow()).dispose();
			System.exit(0);
		}
	}
}
