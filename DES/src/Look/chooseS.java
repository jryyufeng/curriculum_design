package Look;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.*;
import java.io.File;
public class chooseS implements ActionListener {
	JFrame frames=new JFrame();
	Container c=frames.getContentPane();
	String imgePath1="d:/XM/putice/fs.jpg";
	final Image img1=Toolkit.getDefaultToolkit().createImage(imgePath1);
	String imgePath2="d:/XM/putice/js.jpg";
	final Image img2=Toolkit.getDefaultToolkit().createImage(imgePath2);
	String imgePath3="d:/XM/putice/sc.jpg";//图片大小要改
	final Image img3=Toolkit.getDefaultToolkit().createImage(imgePath3);
	JPanel p1=new JPanel(){
		protected void paintChildren(Graphics g){
			g.drawImage(img1, 0, 0, this);
			super.paintChildren(g);
		}
	};
	JPanel p2=new JPanel(){
		protected void paintChildren(Graphics g){
			g.drawImage(img2, 0, 0, this);
			super.paintChildren(g);
		}
	};
	JPanel pp2=new JPanel();
	JPanel pp1=new JPanel();
	JPanel pp22=new JPanel(); 
	JPanel pp11=new JPanel();
	JPanel p3=new JPanel(){
		protected void paintChildren(Graphics g){
			g.drawImage(img3, 0, 0, this);
			super.paintChildren(g);
		}
	};;
	JButton b1=new JButton("发送文件");
	JButton b2=new JButton("接收文件");
	
	public void myframe(){
		 b1.setFont(new Font("华文行楷", Font.PLAIN, 16));
		 //b1.setBackground(Color.CYAN);
		 b1.setPreferredSize(new Dimension(120,50));
		 b1.setBorderPainted(false);//去边框
		 b1.addActionListener(new SocketClient());//发文件事件处理
		 b1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frames.dispose();
				}
				
			});
		 
		 b1.setForeground(Color.BLUE);
		 b2.setFont(new Font("华文行楷", Font.PLAIN, 16));
		 //b2.setBackground(Color.CYAN);
		 b2.setPreferredSize(new Dimension(120,50));
		 b2.setBorderPainted(false);//去边框
		 b2.addActionListener(new SocketServer());//接文件事件处理
		 b2.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frames.dispose();
				}
				
			});
		 b2.setForeground(Color.BLUE);
		 //b1.setBounds((p1.getWidth()-b1.getWidth()-5)/2,(p1.getHeight()-28-b1.getHeight())/2,b1.getWidth(),b1.getHeight());
		 p1.setLayout(new GridLayout(3,1));
		 p2.setLayout(new GridLayout(3,1));
		 pp2.setOpaque(false);
		 pp1.setOpaque(false);
		 pp22.setOpaque(false);
		 pp11.setOpaque(false);
		 p1.add(pp1);
		 p1.add(b1);
		 p1.add(pp2);
		 p2.add(pp11);
		 p2.add(b2);
		 p2.add(pp22);
		 p3.setLayout(new BorderLayout());
		 p3.add(p1,BorderLayout.WEST);
		 p3.add(p2,BorderLayout.EAST);
		 c.add(p3);
		 frames.setLocation(0, 100);
		 frames.setBounds(200,200,500,360);
			//frame.setResizable(false);
		 frames.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		chooseS sc=new chooseS();
		sc.myframe();
		 
	}
	
}
