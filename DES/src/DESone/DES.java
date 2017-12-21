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
//���̼߳����ٶȣ�1.91G��Ƶ����ʱ��Ϊ3����
public class DES {
	

	//ʹ���û���Ϣ����AES������Կ2 ���Ӱ�ȫ��
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
			return 0; // ����ģʽ���������е�ͷ�±�
	}

	
	
	public static String keyfileName="D:\\XM\\DesKey.xml";
	/**
	 * <p>DES �����ļ�
	 * @param file ����ܵ��ļ�
	 * @param dest ���ܺ���ļ�
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
	 * <p>�����ļ�
	 * @param file Դ�ļ�
	 * @param destfile ���ܺ���ļ�
	 * @param args
	 */
	public static void encrypt(String file,String destfile)throws Exception{
		//Cipher����ʵ����ɼ��ܲ���
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
			//���ŵ������Դ
			SecureRandom sr=new SecureRandom();
			//����һ��DESkeySpace����
			KeyGenerator kg=KeyGenerator.getInstance("DES");
			//��ʼ�������Դ
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