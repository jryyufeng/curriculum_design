package Look;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import DESone.DES;
import DESone.Queuetry;
import Look.Encrypt2.TreeFrame;
//ע���࣬�ļ�����
public class Encrypt1 extends Lookone implements ActionListener{
    	static String s11;
    	public static Encrypt1 en1=new Encrypt1();

    	String strjl = null;
    	ArrayList ajl = new ArrayList();
    	
    	
    	
    	//ѡ���ѡ���ļ�
    	public String choosefile(){
    			String filestr=new String();
    			JFileChooser filechoose=new JFileChooser();
    			filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    			filechoose.setMultiSelectionEnabled(true);
    			int result=filechoose.showDialog(filechoose, "���");
    			if(result==JFileChooser.APPROVE_OPTION){
    				File selectfile=filechoose.getSelectedFile();//��ȡѡ����ļ�
    				filestr=selectfile.getAbsolutePath();
    			}
    			System.out.println(filestr);
    			return filestr;
    	}
    	
    	
    	
    	
    	
    	
	//���ܲ���
	 public void Looktwo() {
		String imgePath1="d:/XM/putice/index.jpg";
		final Image img1=Toolkit.getDefaultToolkit().createImage(imgePath1);
		JPanel p = new JPanel();
		JPanel pt1 = new JPanel();
		JPanel pt2 = new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img1, 0, 0, this);
				super.paintChildren(g);
			}
		};

		JPanel pt4=new JPanel();
		JPanel pt5=new JPanel();

		final JButton b3=new JButton("˫������");
		//JButton b4=new JButton("ȷ��");
		JButton b5=new JButton("˫������");
		JButton b55=new JButton("ѡ���ļ�");
		final JTextField f=new JTextField(15);
		//JComboBox f=new JComboBox();
		f.setSize(15,1);
		final JTextField ft1=new JTextField(15);
		final JTextField ft2=new JTextField(15);
		Label lt1=new Label("�Ѵ����ļ�·����");
		Label lt2=new Label("�������ļ��У�");
		JFrame frame=new JFrame("����");
		String a[]={"�ĵ�","ͼƬ","��Ƶ","��Ƶ"};
			Container c=frame.getContentPane();
			pt4.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			pt4.setBackground(Color.LIGHT_GRAY);
			pt4.add(lt2);
			pt4.add(ft2);
			pt5.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			pt5.setBackground(Color.LIGHT_GRAY);
			//pt5.add(b4);
			pt5.add(b3);
			pt5.add(b5);
			pt5.add(b55);
			pt2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			pt2.add(pt4);
			pt2.add(pt5);
			c.add(pt2,BorderLayout.CENTER);
			frame.setSize(420,320);
			frame.setLocationRelativeTo(null); 
			frame.setResizable(false);
			frame.setVisible(true);
		final DES des=new DES();
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s11=ft2.getText();
				Encrypt1.s11=s11;
				int flagl=0;
				if(s11==null||s11.isEmpty()){
					JOptionPane.showMessageDialog(null,"δ�����κ��ļ��У�");
					flagl=1;
				}
				else{
					b3.addActionListener(new Encrypt2());//�����ļ���
				}
			}
		});
		//b3.addActionListener(new Encrypt2());//�����ļ���
		b55.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String filestr=choosefile();
				ft2.setText(filestr);
			}
		});
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList a1 = new ArrayList();//�б�
		        File file = new File(ft2.getText());
		        int flagh=0;
		        s11=ft2.getText();
				Encrypt1.s11=s11;
				int flagl=0;
		        if(s11==null||s11.isEmpty()){
		        	JOptionPane.showMessageDialog(null,"δ�����ļ��У�");
		        	flagh=1;
		        }
		        if(flagh==0){
		        if (file.exists()&&flagh==0) {
		            LinkedList<File> list = new LinkedList<File>();
		            File[] files = file.listFiles();
		            for (File file2 : files) {
		                if (file2.isDirectory()&&file2.isHidden()==false) {
		                    list.add(file2);
		                } else {
		                    //System.out.println("�ļ�:" + file2.getAbsolutePath());
		                    String s1=file2.getAbsolutePath();
		                	//s1 = s1.replaceAll("\\\\", "//");//������ʽת��
		                   // System.out.println("�ļ�:" + s1);
		                    a1.add(s1);
		                }
		            }
		            File temp_file;
		            while (!list.isEmpty()) {
		                temp_file = list.removeFirst();
		                files = temp_file.listFiles();
		                for (File file2 : files) {
		                    if (file2.isDirectory()&&file2.isHidden()==false) {//�ж��Ƿ�Ϊ·��
		                    	
		                        list.add(file2);
		                      
		                    } else {
		                    	String s1=file2.getAbsolutePath();
		                    	//s1 = s1.replaceAll("\\\\", "//");//������ʽת��
		                        a1.add(s1);
		                        
		                    }
		                }
		            }
		        } else {
		        	if(flagh==0)
		        	JOptionPane.showMessageDialog(null,"�ļ��в�����!");
		        }
		        int i;
				Iterator<String>iter=a1.iterator();//��������Ӧ��
				final DES des1=new DES();
				while(iter.hasNext()){
					String s1=(String) iter.next();
		        	File tempFile=new File(s1.trim());
		    		String name=tempFile.getName();
		    		String name1="�⡪"+name;
		    		i=s1.lastIndexOf("\\"); 
		    		String s3=s1.substring(0, i)+"\\";
					try {
						
						des1.decrypt(s1,s3+name1);
						(new File(s1)).delete();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//iter.next();
					//System.out.print(iter.next());
				}
				JOptionPane.showMessageDialog(null,"���ܳɹ���");
		        }
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		en1.Looktwo();
		
	}

}
