package Look;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class Delete1 extends Lookone implements ActionListener{
	/**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
     static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            //System.out.println("Successfully deleted empty directory: " + dir);
        } else {
           // System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
     static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    /**
     *测试
     */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object[] options={"确认","取消"};
		int a=JOptionPane.showOptionDialog(null,"是否注销","Information",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		Delete1 d1=new Delete1();
		//System.out.print(s1);
		if(a==0)
		{
			d1.doDeleteEmptyDir("D:\\XM\\"+s1);
			String newDir2 = "D:\\XM\\"+s1;
			boolean success = d1.deleteDir(new File(newDir2));
			
			
			File filen=new File("D://XM//2.txt");
			InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(filen),"utf-8");
				BufferedReader bufferedReader = new BufferedReader(read);
			
				String lineTxt = null;
			String wenben=null;
			String nwenben=null;
			try {
				while((lineTxt = bufferedReader.readLine()) != null){
					//System.out.println(lineTxt);
					//ajl.add(lineTxt);
					wenben=wenben+lineTxt+"\n";
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(ajl);
			try {
				read.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(wenben);
			if(filen.exists())
			filen.delete();
			
			File file1=new File("D://XM//2.txt");
			file1.createNewFile();
			String aw[]=wenben.split("\n");
			FileOutputStream fos = new FileOutputStream(file1,true);
		    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			for(int i=0;i<aw.length;i++){
				String asd[]=aw[i].split(" ");
				//System.out.println(asd[asd.length-1]);
				if(aw[i].contains(s1)&&asd[asd.length-1].equals(sp)){
					continue;
				}
				else{
					bw.write(aw[i]);
					 bw.newLine();
				}
					 
			}
			//System.out.println(nwenben);
			bw.close();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		
			
        if (success) {
        	JOptionPane.showMessageDialog(null,"注销成功！");
            //System.out.println("Successfully deleted populated directory: " + newDir2);
        } else {
        	JOptionPane.showMessageDialog(null,"注销失败！");
            //System.out.println("Failed to delete populated directory: " + newDir2);
        }   
        
		}
		else if(a==1)
		{
			d1.dispose();
		}
	}
}
