package Look;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import DESone.DES;
public class Encrypt2 extends Encrypt1 implements ActionListener {
	int lg;
		public ArrayList getlist(String path){
			ArrayList a1 = new ArrayList();//列表
	        File file = new File(path);
	        if (file.exists()) {
	            LinkedList<File> list = new LinkedList<File>();
	            File[] files = file.listFiles();
	            for (File file2 : files) {
	                if (file2.isDirectory()&&file2.isHidden()==false) {
	                    list.add(file2);
	                   
	                } else {
	                    //System.out.println("文件:" + file2.getAbsolutePath());
	                    String s1=file2.getAbsolutePath();
	                	//s1 = s1.replaceAll("\\\\", "//");//正则表达式转换
	                   // System.out.println("文件:" + s1);
	                    a1.add(s1);
	                }
	            }
	            File temp_file;
	            while (!list.isEmpty()) {
	                temp_file = list.removeFirst();
	                files = temp_file.listFiles();
	                for (File file2 : files) {
	                    if (file2.isDirectory()&&file2.isHidden()==false) {//判断是否为路径
	                        list.add(file2);
	                    } else {
	                    	String s1=file2.getAbsolutePath();
	                    	//s1 = s1.replaceAll("\\\\", "//");//正则表达式转换
	                    	
	                        a1.add(s1);
	                    }
	                }
	            }
	            
	        } else {
	        	JOptionPane.showMessageDialog(null,"文件夹不存在!");
	        }
	        //a1.add(lg);
	        System.out.println(lg);
			return a1;
		}
		
		public int encryptfolder(){
			ArrayList a2=getlist(s11);
			for(int i=0;i<a2.size();i++){
				File f=new File((String) a2.get(i));
				lg+=f.length();
			}
			int i;
			//System.out.print(a2);
			Iterator<String>iter=a2.iterator();//迭代器的应用
			final DES des1=new DES();
			while(iter.hasNext()){
				String s1=(String) iter.next();
				//System.out.println(s1);
	        	File tempFile=new File(s1.trim());
	    		String name=tempFile.getName();
	    		//System.out.println("name="+name);
	    		String name1="密——"+name;
	    		i=s1.lastIndexOf("\\"); 
	    		//System.out.println(i);
	    		String s3=s1.substring(0, i)+"\\";
	    		//System.out.println(s1);
	    		//System.out.println(s3+name1);
	    		
				try {
					des1.encrypt(s1,s3+name1);
					(new File(s1)).delete();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//iter.next();
				//System.out.print(iter.next());
			}
			a2.clear();
			//System.out.println(lg);
			return lg;
			
		}
		
			/** 文件夹备份
			* 复制整个文件夹内容 
			* @param oldPath String 原文件路径 如：c:/fqf 
			* @param newPath String 复制后路径 如：f:/fqf/ff 
			* @return boolean 
			*/ 
			public void copyFolder(String oldPath,String newPath){ 

			try { 
			(new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
			File a=new File(oldPath); 
			String[] file=a.list(); 
			File temp=null; 
			for (int i = 0; i < file.length; i++) { 
			if(oldPath.endsWith(File.separator)){ 
			temp=new File(oldPath+file[i]); 
			} 
			else{ 
			temp=new File(oldPath+File.separator+file[i]); 
			} 

			if(temp.isFile()){ 
			FileInputStream input = new FileInputStream(temp); 
			FileOutputStream output = new FileOutputStream(newPath + "/" + 
			(temp.getName()).toString()); 
			byte[] b = new byte[1024 * 5]; 
			int len; 
			while ((len = input.read(b)) != -1) { 
			output.write(b, 0, len); 
			} 
			output.flush(); 
			output.close(); 
			input.close(); 
			} 
			if(temp.isDirectory()){//如果是子文件夹 
			copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
			} 
			} 
			} 
			catch (Exception e) { 
			System.out.println("复制整个文件夹内容操作出错"); 
			e.printStackTrace(); 
			} 

			}
			
			//进度显示
			public class TreeFrame extends JFrame{
				private static final long serialVersionUID = 1L;
				private JProgressBar processBar;
				public TreeFrame(){
					setTitle("加密进程");		//设置窗体标题
					this.dispose(); // 设置窗体退出的操作
					setBounds(100, 100, 250, 100);// 设置窗体的位置和大小
					JPanel contentPane = new JPanel();   // 创建内容面板
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置内容面板边框
					setContentPane(contentPane);// 应用(使用)内容面板
					contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));// 设置为流式布局
					processBar = new JProgressBar();// 创建进度条
					processBar.setStringPainted(true);// 设置进度条上的字符串显示，false则不能显示
					processBar.setBackground(Color.GREEN);
					// 创建线程显示进度
					new Thread(){
									
						public void run(){
							//int i=0;
							for (int iii = 0; iii < 101; iii++) 
							 {
								if(iii==32){
									int ii=s11.lastIndexOf("\\");
									int i=s11.length();
									String foldname=s11.substring(ii,i);
									encryptfolder();
									copyFolder(s11,"D://XM//"+s1+"//文件夹"+foldname);
								}
									//System.out.println(1);
								 try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
								//copyFolder("D:\\XM3","D:\\XM\\admin");
								 //   让当前线程休眠0.1ms
								
								processBar.setValue(iii);	// 设置进度条数值
							}
							processBar.setString("加密完成");// 设置提示信息
						}
					}.start(); //  启动进度条线程
					
					contentPane.add(processBar);// 向面板添加进度控件
				}
			}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Encrypt2 en2=new Encrypt2();
		TreeFrame JPBD = new TreeFrame();
		JPBD.setVisible(true);	
		
	}
}
