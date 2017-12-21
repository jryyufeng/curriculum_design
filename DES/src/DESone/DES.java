package DESone;
import Look.Lookone;
import java.io.*;
import javax.crypto.*;

import java.security.*;
/**
 *  
 * @author 
 *
 */
//单线程加密速度，1.91G视频加密时间为3分钟
public class DES {
	

	//使用用户信息调用AES生成密钥2 增加安全性
	public static SecretKey getkeyAES(String password){
		//String password=" ";
		byte[] b = null;
		SecretKey sk = null;
		try{
			KeyGenerator kg=KeyGenerator.getInstance("DES");
			kg.init(128,new SecureRandom(password.getBytes()));
			sk=kg.generateKey();
			//b=sk.getEncoded();
			//System.out.print(b);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return sk;
	}

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

	public static int kAes(char[] s, char[] t) {
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
		} else
			return 0; // 返回模式串在主串中的头下标
	}

	
	
	public static String keyfileName="D:\\XM\\DesKey.xml";
	/**
	 * <p>DES 解密文件
	 * @param file 需解密的文件
	 * @param dest 解密后的文件
	 * @param args
	 */
	public static void decrypt(String file,String dest)throws Exception{
		Cipher cipher=Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, getKey());
		InputStream is=new FileInputStream(file);
		OutputStream out=new FileOutputStream(dest);
		CipherOutputStream cos=new CipherOutputStream(out,cipher);
		byte[] buffer=new byte[1024];
		//byte[] buffer=byteMerger(buffer1,getkeyAES());
		int r;
		while((r=is.read(buffer))>=0){
			cos.write(buffer,0,r);
		}
		cos.close();
		out.close();
		is.close();
	}
	/**
	 * <p>加密文件
	 * @param file 源文件
	 * @param destfile 加密后的文件
	 * @param args
	 */
	public static void encrypt(String file,String destfile)throws Exception{
		//Cipher对象实际完成加密操作
		Cipher cipher=Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, getKey());
		InputStream is=new FileInputStream(file);
		OutputStream out=new FileOutputStream(destfile);
		CipherInputStream cis=new CipherInputStream(is,cipher);
		byte[] buffer=new byte[1024];
		//byte[] buffer=byteMerger(buffer1,getkeyAES());
		int r;
		while((r=cis.read(buffer))>0){
			out.write(buffer,0,r);
		}
		cis.close();
		is.close();
		out.close();
	}
	private static Key getKey(){
		Key kp=null;
		
		try{
			String fileName=keyfileName;
			InputStream is=new FileInputStream(fileName);
			ObjectInputStream oos=new ObjectInputStream(is);
			kp=(Key)oos.readObject();
			oos.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return kp;
	}
	/*
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		DES.saveDesKey();
		DES.encrypt("D:\\XM\\13.mp4","D:\\XM\\132.mp4");
		DES.decrypt("D:\\XM\\132.mp4","D:\\XM\\133.mp4");
	}*/
	public static void saveDesKey(){
		try{
			//可信的随机数源
			SecureRandom sr=new SecureRandom();
			//创建一个DESkeySpace对象
			KeyGenerator kg=KeyGenerator.getInstance("DES");
			//初始化随机数源
			kg.init(sr);
			FileOutputStream fos=new FileOutputStream(keyfileName);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			Key key=kg.generateKey();
			//Key key=getkeyAES("123");
			oos.writeObject(key);
			//oos.writeObject(key1);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
}