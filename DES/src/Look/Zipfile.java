package Look;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Zipfile implements ActionListener{
	
	JFrame frame=new JFrame();
	Container c=frame.getContentPane();
	JLabel l1=new JLabel("ԭ�ļ���:");
	JLabel l2=new JLabel("���ļ���(ֻ������):");
	JLabel l3=new JLabel("ѹ���ļ�:���ļ���չ��Ϊzip");
	JLabel l4=new JLabel("��ѹ�ļ���ѡ�����ļ���ֱ�ӵ����ѹ����");
	static JTextField f1=new JTextField(15);
	static JTextField f2=new JTextField(15);

	JButton b1=new JButton("ѡ���ļ�");
	JButton b2=new JButton("���ѹ��");
	JButton b3=new JButton("�����ѹ");
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	//ѡ���ѡ���ļ�
	public String choosefile(){
			String filestr=new String();
			JFileChooser filechoose=new JFileChooser();
			filechoose.setMultiSelectionEnabled(true);
			filechoose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//��ѡ���ļ��к��ļ�
			int result=filechoose.showDialog(filechoose, "���");
			if(result==JFileChooser.APPROVE_OPTION){
				File selectfile=filechoose.getSelectedFile();//��ȡѡ����ļ�
				filestr=selectfile.toString();
			}
			//System.out.println(as);
			return filestr;
	}
	public void zipframe(){
		p1.add(l1);
		p1.add(f1);
		p1.setBackground(Color.orange);
		p1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p1.setBackground(Color.BLUE);
		p2.add(l2);
		p2.add(f2);
		p2.setBackground(Color.orange);
		p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p2.setBackground(Color.BLUE);
		p4.add(b1);
		p4.add(b2);
		p4.add(b3);
		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filestr=choosefile();
				f1.setText(filestr);
			}
			
		});
		b2.addActionListener(new Zipyasuo());
		b3.addActionListener(new Zipjieya());
		p3.setLayout(new GridLayout(2,1));
		p3.add(l3);
		p3.add(l4);
		p3.setBackground(Color.orange);
		p3.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p3.setBackground(Color.YELLOW);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		c.add(p5,BorderLayout.CENTER);
		frame.setTitle("ѹ���ļ�");
		frame.setSize(360,249);//����Ϊ����
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Zipfile zip1=new Zipfile();
		zip1.zipframe();
		
	}

}
