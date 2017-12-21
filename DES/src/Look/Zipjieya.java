package Look;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.zip.*;

import javax.swing.JOptionPane;

public class Zipjieya extends Zipfile implements ActionListener{
	
	public void jieya() {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();
		try {
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(
					f1.getText()));//输入源zip路径
			BufferedInputStream Bin=new BufferedInputStream(Zin);
			String Parent="D:\\XM\\解压"; //输出路径（文件夹目录）
			File Fout=null;
			ZipEntry entry;
			try {
				while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
					Fout=new File(Parent,entry.getName());
					if(!Fout.exists()){
						(new File(Fout.getParent())).mkdirs();
					}
					FileOutputStream out=new FileOutputStream(Fout);
					BufferedOutputStream Bout=new BufferedOutputStream(out);
					int b;
					while((b=Bin.read())!=-1){
						Bout.write(b);
					}
					Bout.close();
					out.close();
					JOptionPane.showMessageDialog(null,Fout+"解压成功");	
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Zipjieya zipjy=new Zipjieya();
		zipjy.jieya();
	}

}
