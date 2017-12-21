package Look;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
public class Choosetry implements ActionListener{
	
	public void choose(){
		String imgePath1="d:/XM/putice/choose.jpg";
		final Image img1=Toolkit.getDefaultToolkit().createImage(imgePath1);
		final JFrame frame=new JFrame("加密类型");
		Container c=frame.getContentPane();
		JPanel p1=new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img1, 0, 0, this);
				super.paintChildren(g);
			}
		};
		JButton b1=new JButton("加密文件");
		
		b1.addActionListener(new Encrypt3());
		 b1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		JButton b2=new JButton("加密文件夹");
		b2.addActionListener(new Encrypt1());
		b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		 b1.setFont(new Font("华文行楷", Font.PLAIN, 16));
		 b1.setForeground(Color.BLUE);
		 //b1.setBackground(Color.CYAN);
		 b1.setPreferredSize(new Dimension(120,50));
		 b1.setBorderPainted(false);//去边框
		 b2.setFont(new Font("华文行楷", Font.PLAIN, 16));
		 b2.setForeground(Color.BLUE);
		 //b1.setBackground(Color.CYAN);
		 b2.setPreferredSize(new Dimension(120,50));
		 b2.setBorderPainted(false);//去边框
		 p1.setLayout(new BorderLayout());
		 p1.add(b1,BorderLayout.WEST);
		 p1.add(b2,BorderLayout.EAST);
		 c.add(p1);
		 frame.setLocation(0, 100);
		 frame.setBounds(200,200,500,360);
			//frame.setResizable(false);
		 frame.setVisible(true);
		
	}
	/**
	 * @param args
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Choosetry c1=new Choosetry();
		c1.choose();
		
	}
}
