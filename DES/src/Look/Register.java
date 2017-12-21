package Look;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
class Register implements ActionListener {
	final JTextField f=new JTextField(15);
	JComboBox combox1=new JComboBox();
	/**
	 * ����ַ�����next����ֵ
	 * 
	 * @param t
	 *            �ַ���
	 * @return next����ֵ
	 */
	public static int[] next(char[] t) {
		int[] next = new int[t.length];
		next[0] = -1;
		int i = 0;
		int j = -1;
		while (i < t.length - 1) {
			if (j == -1 || t[i] == t[j]) {
				i++;
				j++;
				if (t[i] != t[j]) {
					next[i] = j;
				} else {
					next[i] = next[j];
				}
			} else {
				j = next[j];
			}
		}
		return next;
	}

	/**
	 * KMPƥ���ַ���
	 * 
	 * @param s
	 *            ����
	 * @param t
	 *            ģʽ��
	 * @return ��ƥ��ɹ��������±꣬���򷵻�-1
	 */
	public static int KMP_Index(char[] s, char[] t) {
		int[] next = next(t);
		int i = 0;
		int j = 0;
		while (i <= s.length - 1 && j <= t.length - 1) {
			if (j == -1 || s[i] == t[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j < t.length) {
			return -1;
		} 
		else 
			return 0; // ����ģʽ���������е�ͷ�±�
		
	}
	public void zhuce(){
		final JFrame frame=new JFrame("�û�ע��");
		String imgePath1="d:/XM/putice/time.jpg";
		final Image img1=Toolkit.getDefaultToolkit().createImage(imgePath1);
		JPanel p = new JPanel();
		JPanel pt1 = new JPanel();
		JPanel pt4 = new JPanel();
		JPanel pt2 = new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img1, 0, 0, this);
				super.paintChildren(g);
			}
		};
		JPanel pt3 = new JPanel();
		JButton b=new JButton("ע��");
		final JPasswordField ft1=new JPasswordField(15);//�ɵõ�����
		final JPasswordField ft2=new JPasswordField(15);//
		Label l=new Label("�û�����");
		Label lt1=new Label("���룺");
		Label lt2=new Label("����ȷ�ϣ�");
		String a[]={"����Ա","��ͨ�û�"};
   	 	combox1=new JComboBox(a);
   	 	combox1.setSelectedIndex(0);//��0��Ԫ����Ϊ��ǰ�ɼ�
			Container c=frame.getContentPane();
			p.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			p.setBackground(Color.MAGENTA);
			p.add(l);
			p.add(f);
			pt3.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			pt3.setBackground(Color.MAGENTA);
			pt3.add(lt1);
			pt3.add(ft1);
			pt4.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			pt4.setBackground(Color.MAGENTA);
			pt4.add(lt2);
			pt4.add(ft2);
			pt1.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			pt1.setBackground(Color.BLUE);
			pt1.add(combox1);
			pt1.add(b);
			pt2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			pt2.add(p);
			pt2.add(pt3);
			pt2.add(pt4);
			pt2.add(pt1);
			c.add(pt2,BorderLayout.CENTER);
			frame.setSize(360,245);
			frame.setResizable(false);
			frame.setVisible(true);
			//final String pwd1=ft1.getText();
			b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					final String pwd2=new String(ft2.getPassword());
			        final String pwd1=new String(ft1.getPassword());
			       // System.out.print(pwd1);
						//System.out.print(pwd1);
						//д���ļ�
			        char[] pwd11=pwd1.toCharArray();
			        char[] pwd22=pwd2.toCharArray();
			      //���ļ��ж��û����Ƿ��ظ�
			        File filen=new File("D://XM//2.txt");
					InputStreamReader read = null;
					int flagh=0;
					try {
						read = new InputStreamReader(new FileInputStream(filen),"utf-8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					try {
						while((lineTxt = bufferedReader.readLine()) != null){
							String ass[]=lineTxt.split(" ");
							if(lineTxt.contains(f.getText())&&ass[ass.length-1].equals(combox1.getSelectedItem())){
								//System.out.println("���˴���");
								
								JOptionPane.showMessageDialog(null,"�û��Ѵ��ڣ�");
								flagh=1;
								//System.exit(0);
							}
							else{
									continue;
							}
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
						//System.out.println("���˲�����");
							if(flagh==0&&pwd1.length()==pwd2.length()&&KMP_Index(pwd11,pwd22)==0)
						{
								
								JOptionPane.showMessageDialog(null,"ע��ɹ���");
							try {
								
								writeFile1(f.getText()+' '+pwd1+' '+combox1.getSelectedItem());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else{
							if(flagh==0)
							JOptionPane.showMessageDialog(null,"���벻�ԣ�");
							//System.out.print(pwd2);
						}	
						frame.dispose();
						//JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ�");
				}
				
			});	
	}
	//д���ļ� 
	 public static void writeFile1(String str) throws IOException {
	        File fout = new File("D://XM//2.txt");
	        if(!fout.exists()){
	        	fout.createNewFile();
	        }
	      
	        FileOutputStream fos = new FileOutputStream(fout,true);//true��֤ÿһ�ж���д���������
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
	            bw.write(str);
	            bw.newLine();
	            bw.close();
	    }
	 
	 //��ȡ�ļ�
	 public static String w(String s){ 		 
	  String str = null;
	try {

          // read file content from file

          StringBuffer sb= new StringBuffer("");
          FileInputStream br0=new FileInputStream("D://XM//2.txt");
          InputStreamReader brr=new InputStreamReader(br0,"utf-8");
          BufferedReader br = new BufferedReader(brr);    
          int count=0;//name+" "+adress+"*"+code+"&"+number+"$"+tel+"|"+email+" "
          while((str=br.readLine())!=null){
        	  
        	  sb.append(str+"/n");
        	  String jg=str.substring(0,str.indexOf(" "));
        	  if(jg.equals(s)){
        		  
        		break;
        	  }
        	  else{
        		  s="���޴���";
        	  }
          }
                  
    }
    catch(FileNotFoundException e) {
                e.printStackTrace();
          }
          catch(IOException e) {
                e.printStackTrace();
          }
          //System.out.println(str);
          return str;
          
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Register register=new Register();
		register.zhuce();
	}	
}
