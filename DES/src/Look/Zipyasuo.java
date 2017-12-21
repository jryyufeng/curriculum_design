package Look;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.zip.*;

import javax.swing.JOptionPane;
public class Zipyasuo extends Zipfile implements ActionListener {
	private int k = 1; // 定义递归次数变量

	public Zipyasuo() {
		// TODO Auto-generated constructor stub
	}
	private void zip(String zipFileName, File inputFile) throws Exception {
		JOptionPane.showMessageDialog(null,"压缩中...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
		JOptionPane.showMessageDialog(null,"压缩完成");
	}

	private void zip(ZipOutputStream out, File f, String base,
			BufferedOutputStream bo) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
			}
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
			k++;
		} else {
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
			System.out.println(base);
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bi.close();
			in.close(); // 输入流关闭
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Zipyasuo book = new Zipyasuo();
		try {
			book.zip("D:\\XM\\"+f2.getText(),
					new File(f1.getText()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

