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
	JLabel l1=new JLabel("原文件名:");
	JLabel l2=new JLabel("新文件名(只需名称):");
	JLabel l3=new JLabel("压缩文件:新文件扩展名为zip");
	JLabel l4=new JLabel("解压文件：选择完文件后直接点击解压即可");
	static JTextField f1=new JTextField(15);
	static JTextField f2=new JTextField(15);

	JButton b1=new JButton("选择文件");
	JButton b2=new JButton("点击压缩");
	JButton b3=new JButton("点击解压");
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	//选择框选择文件
	public String choosefile(){
			String filestr=new String();
			JFileChooser filechoose=new JFileChooser();
			filechoose.setMultiSelectionEnabled(true);
			filechoose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//可选择文件夹和文件
			int result=filechoose.showDialog(filechoose, "添加");
			if(result==JFileChooser.APPROVE_OPTION){
				File selectfile=filechoose.getSelectedFile();//获取选择的文件
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
		frame.setTitle("压缩文件");
		frame.setSize(360,249);//参数为像素
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
