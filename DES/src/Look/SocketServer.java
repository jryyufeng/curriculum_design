package Look;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.math.*;
import DESone.Classxm;
import DESone.DES;
//һ���о�Ӧ���Զ����ɴ����ļ��ĵ�ַ
public class SocketServer extends Lookone implements ActionListener{
	Classxm xm=new Classxm();
	DES xm1=new DES();
	JFrame frame=new JFrame();
	Container c=frame.getContentPane();
	JLabel l3=new JLabel("�ļ���:");
	static JTextField f3=new JTextField(10);
	JButton b1=new JButton("�������");
	JButton b2=new JButton("�����ļ�");
	JButton b3=new JButton("ֹͣ����");
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	 public String getMd5(String plainText) {  
	        try {  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            md.update(plainText.getBytes());  
	            byte b[] = md.digest();  
	            int i;  
	            StringBuffer buf = new StringBuffer("");  
	            for (int offset = 0; offset < b.length; offset++) {  
	                i = b[offset];  
	                if (i < 0)  
	                    i += 256;  
	                if (i < 16)  
	                    buf.append("0");  
	                buf.append(Integer.toHexString(i));  
	            }  
	            //32λ����  
	            return buf.toString();  
	            // 16λ�ļ���  
	            //return buf.toString().substring(8, 24);  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	  
	    }  

	public void server1(final String path){
		try{
			
			final ServerSocket server=new ServerSocket(32456);
			Thread th=new Thread(new Runnable(){
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						try{
							JOptionPane.showMessageDialog(null,"��ʼ����!");
							Socket socket=server.accept();
							JOptionPane.showMessageDialog(null,"������!"+"����"+socket.getInetAddress());
							receiveFile(socket,path);
						}catch(Exception e){
							
						}
					}
				}
				
			});
			th.run();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		 
	}
	public static void receiveFile(Socket socket,String path){
		byte[]inputByte=null;
		int length=0;
		DataInputStream dis=null;
		FileOutputStream fos=null;
		try{
			try{
				dis=new DataInputStream(socket.getInputStream());
				fos=new FileOutputStream(new File("D://XM//server//"+s1+path));
				inputByte=new byte[1024];
				System.out.println("��ʼ����");
				while((length=dis.read(inputByte,0,inputByte.length))>0){
					System.out.println(length);
					fos.write(inputByte,0,length);
					fos.flush();
				}
				//System.out.println("��ɽ���");
				JOptionPane.showMessageDialog(null,"��ɽ���!");
				System.exit(0);
			}finally{
				if(fos!=null)
					fos.close();
				if(dis!=null)
					dis.close();
				if(socket!=null)
					socket.close();
			}
		}catch(Exception e){
			
		}
		
	}
	public void Serverframe(){
		p1.add(l3);
		p1.add(f3);
		p1.add(b1);
		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 server1(f3.getText());
			}
			
		});
		b2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 //System.exit(0);
				String md5key=getMd5(s1);
				char[] sw=s1.toCharArray();
				char[] name11=f3.getText().toCharArray();
				if(xm1.kAes(name11,sw)==-1){
					System.exit(0);
					}
				else{
				try {
					xm.decrypt("D://XM//server//"+f3.getText(),"D://XM//serverd//"+f3.getText(),6);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
			
		});
		b3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 System.exit(0);
			}
			
		});
		p1.setBackground(Color.orange);
		p1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p1.setBackground(Color.RED);
		p2.add(b2);
		p2.add(b3);
		p2.setBackground(Color.orange);
		p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p2.setBackground(Color.ORANGE);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		p3.add(p1);
		p3.add(p2);
		c.add(p3,BorderLayout.CENTER);
		frame.setTitle("�����ļ�");
		frame.setSize(360,249);//����Ϊ����
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SocketServer socs=new SocketServer();
		socs.Serverframe();
	}

}
