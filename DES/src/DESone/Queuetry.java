package DESone;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import javax.swing.JOptionPane;
import Look.Encrypt1;
import Look.Encrypt3;
import Shuju.Queue;
//队列与多线程
public class Queuetry extends Encrypt3 {
	DES des=new DES();
	int flagdq=0;
	
	//快速排序的实现
	public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }
    
    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi); 
    }
	
	/** 
	* 复制单个文件 
	* @param oldPath String 原文件路径 如：c:/fqf.txt 
	* @param newPath String 复制后路径 如：f:/fqf.txt 
	* @return boolean 
	*/ 
	public void copyFile(String oldPath, String newPath) { 
	try { 
	int bytesum = 0; 
	int byteread = 0; 
	File oldfile = new File(oldPath); 
	if (oldfile.exists()) { //文件存在时 
	InputStream inStream = new FileInputStream(oldPath); //读入原文件 
	FileOutputStream fs = new FileOutputStream(newPath); 
	byte[] buffer = new byte[1444]; 
	int length; 
	while ( (byteread = inStream.read(buffer)) != -1) { 
	bytesum += byteread; //字节数 文件大小 
	//System.out.println(bytesum); 
	fs.write(buffer, 0, byteread); 
	} 
	inStream.close(); 
	} 
	} 
	catch (Exception e) { 
	System.out.println("复制单个文件操作出错"); 
	e.printStackTrace(); 
	} 
	}
	//标记类
	public void que(final int t){
		class MyCount{
			private int count;
			public MyCount(int count){
				this.count=count;
			}//BoundsMsg
			public synchronized void countdown(){
				count--;
			}
			public int getcount(){
				return count;
			}
			public synchronized boolean hasNext(){
				return (count>0);
				
			}
			public void setCount(int count){
				this.count=count;
			}
		}//也可用来做标记，用法：将类作为线程的标记，用来计数
		 final Queue<Thread> queue = new Queue<Thread>(map.size());
		//多线程加密的实现
		class Progress implements Runnable{
			private String i;
			private MyCount c;
			private CountDownLatch threadcount;//借助这个类在保证子线程并发进行条件下来判断子线程是否完全结束
			public Progress(String i, CountDownLatch threadcount) {
					// TODO Auto-generated constructor stub
				this.i=i;
				this.threadcount=threadcount;
				}
			public void run() {
				// TODO Auto-generated method stub
				int size=map.size();
				//System.out.println(map);
				Set<String> k=map.keySet();
				Iterator<String> it=k.iterator();//迭代器
				Set<String> k1=map1.keySet();
				Iterator<String> it1=k1.iterator();//迭代器
				while(it.hasNext()&&it1.hasNext()){
					String text=it.next();
					String type=map.get(text);
					String text1=it1.next();
					String type1=map1.get(text1);
					String old=q1.peek();//得到路径
					//q1.remove();
					String new1j=text;
					File tempFile1j=new File(new1j.trim());
		    		String name1j=tempFile1j.getName();
		    		   char[] name11=name1j.toCharArray();
				        char[] sw=s1.toCharArray();
					q2.remove();
					if(i==text){
						try {
							if(t==1){
							des.encrypt(text,text1);
							threadcount.countDown();
							}
							
							if(t==0){
							des.decrypt(text,text1);
							threadcount.countDown();
							}
							
							if(t==0&&des.kAes(name11,sw)==-1){
								System.exit(0);
								}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}//加密处理
						File tempFile=new File(text.trim());
			    		String name=tempFile.getName();
						copyFile(text,"D://XM//"+s1+"//"+type+"//"+name);//新路径必须具体到文件名称，否则报错
					}
				}
			}		
		}
		//生成线程
		CountDownLatch threadcount1 = new CountDownLatch(map.size());
		for(Entry<String, String>m:map.entrySet()){
			Progress calculator = new Progress(m.getKey(),threadcount1);
			Thread thread = new Thread(calculator);
			queue.insert(thread);
			thread.start();
		}
		try {
			threadcount1.await();//判断是否为空
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
