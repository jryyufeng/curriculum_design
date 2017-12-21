package Look;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.*;
import DESone.DES;
import DESone.Classxm;
import Look.chooseS;

public class SocketClient implements ActionListener{
	DES desclinet=new DES();
	Classxm xmclient=new Classxm();
	JFrame frame=new JFrame();
	Container c=frame.getContentPane();
	JLabel l1=new JLabel("文件名:");
	JLabel l2=new JLabel("IP地址:");
	//JLabel l3=new JLabel("端口号:");
	static JTextField f1=new JTextField(15);
	static JTextField f2=new JTextField(15);
	static JTextField f3=new JTextField(10);
	JButton b1=new JButton("选择文件");
	JButton b2=new JButton("点击发送");
	JButton b3=new JButton("文件压缩处理");
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JPanel p4=new JPanel();
	JPanel p5=new JPanel();
	public void fileclient(String path){
		int length=0;
		byte[] sendBytes=null;
		Socket socket=null;
		DataOutputStream dos=null;
		FileInputStream fis=null;
		//加自定义通信协议
		//目前业界主要采取的协议定义方式是：包头+包体长度+包体
		try{
			try{
				socket=new Socket();
				socket.connect(new InetSocketAddress(f2.getText(),32456),10*1000);
				dos=new DataOutputStream(socket.getOutputStream());
				File file=new File(path);//需要传输文件的路径
				fis=new FileInputStream(file);
				sendBytes=new byte[1024];
				while((length=fis.read(sendBytes,0,sendBytes.length))>0){
					dos.write(sendBytes,0,length);
					dos.flush();
				}
			}finally{
				if(dos!=null)
					dos.close();
				if(fis!=null)
					fis.close();
				if(socket!=null)
					socket.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Clientframe(){
		p1.add(l1);
		p1.add(f1);
		p1.setBackground(Color.orange);
		p1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p1.setBackground(Color.RED);
		p2.add(l2);
		p2.add(f2);
		p2.setBackground(Color.orange);
		p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		p2.setBackground(Color.ORANGE);
		//p3.add(l3);
		//p3.add(f3);
		//p3.setBackground(Color.orange);
		//p3.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
		//p3.setBackground(Color.YELLOW);
		p4.add(b1);
		p4.add(b2);
		p4.add(b3);
		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				File filepath=new File("D://XM//client");
				if(!filepath.exists()&&!filepath.isDirectory()){
					filepath.mkdir();//新建路径
				}
				else{}
				String filestr=choosefile();
				f1.setText(filestr);
			}
			
		});
		b3.addActionListener(new Zipfile());
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String filestr1=f1.getText();
				File tempFile=new File(filestr1.trim());
				
				String name=tempFile.getName();
				String namec="send-"+name;
				
				String path3="D://XM//client//"+namec;
				try {
					//desclinet.encrypt(filestr1, path3);
					xmclient.encrypt(filestr1, "hello");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"发送失败!");
				}
				
				fileclient(filestr1);
				
			}
		});
	
		p5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		c.add(p5,BorderLayout.CENTER);
		frame.setTitle("传送文件");
		frame.setSize(360,249);//参数为像素
		frame.setLocationRelativeTo(null); 
		frame.setResizable(false);
		frame.setVisible(true);	
	}
	//选择框选择文件
	public String choosefile(){
			String filestr=new String();
			JFileChooser filechoose=new JFileChooser();
			filechoose.setMultiSelectionEnabled(true);
			int result=filechoose.showDialog(filechoose, "添加");
			if(result==JFileChooser.APPROVE_OPTION){
				File selectfile=filechoose.getSelectedFile();//获取选择的文件
				filestr=selectfile.toString();
			}
			//System.out.println(as);
			return filestr;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		SocketClient soc=new SocketClient();
		soc.Clientframe();
		
		
	}

}
