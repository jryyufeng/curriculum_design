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
			ArrayList a1 = new ArrayList();//�б�
	        File file = new File(path);
	        if (file.exists()) {
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
	        	JOptionPane.showMessageDialog(null,"�ļ��в�����!");
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
			Iterator<String>iter=a2.iterator();//��������Ӧ��
			final DES des1=new DES();
			while(iter.hasNext()){
				String s1=(String) iter.next();
				//System.out.println(s1);
	        	File tempFile=new File(s1.trim());
	    		String name=tempFile.getName();
	    		//System.out.println("name="+name);
	    		String name1="�ܡ���"+name;
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
		
			/** �ļ��б���
			* ���������ļ������� 
			* @param oldPath String ԭ�ļ�·�� �磺c:/fqf 
			* @param newPath String ���ƺ�·�� �磺f:/fqf/ff 
			* @return boolean 
			*/ 
			public void copyFolder(String oldPath,String newPath){ 

			try { 
			(new File(newPath)).mkdirs(); //����ļ��в����� �������ļ��� 
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
			if(temp.isDirectory()){//��������ļ��� 
			copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
			} 
			} 
			} 
			catch (Exception e) { 
			System.out.println("���������ļ������ݲ�������"); 
			e.printStackTrace(); 
			} 

			}
			
			//������ʾ
			public class TreeFrame extends JFrame{
				private static final long serialVersionUID = 1L;
				private JProgressBar processBar;
				public TreeFrame(){
					setTitle("���ܽ���");		//���ô������
					this.dispose(); // ���ô����˳��Ĳ���
					setBounds(100, 100, 250, 100);// ���ô����λ�úʹ�С
					JPanel contentPane = new JPanel();   // �����������
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �����������߿�
					setContentPane(contentPane);// Ӧ��(ʹ��)�������
					contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));// ����Ϊ��ʽ����
					processBar = new JProgressBar();// ����������
					processBar.setStringPainted(true);// ���ý������ϵ��ַ�����ʾ��false������ʾ
					processBar.setBackground(Color.GREEN);
					// �����߳���ʾ����
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
									copyFolder(s11,"D://XM//"+s1+"//�ļ���"+foldname);
								}
									//System.out.println(1);
								 try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}  
								//copyFolder("D:\\XM3","D:\\XM\\admin");
								 //   �õ�ǰ�߳�����0.1ms
								
								processBar.setValue(iii);	// ���ý�������ֵ
							}
							processBar.setString("�������");// ������ʾ��Ϣ
						}
					}.start(); //  �����������߳�
					
					contentPane.add(processBar);// �������ӽ��ȿؼ�
				}
			}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Encrypt2 en2=new Encrypt2();
		TreeFrame JPBD = new TreeFrame();
		JPBD.setVisible(true);	
		
	}
}
