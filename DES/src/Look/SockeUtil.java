package Look;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * ���ķ���
 */
public class SockeUtil {
    Socket socket = null;
    public SockeUtil(String ip,int port) throws UnknownHostException, IOException{
        socket = new Socket(ip, port);
    }
    //
    public SocketPackage sentSocket(SocketPackage socketPacket) throws UnsupportedEncodingException, IOException{
        SocketPackage sPacket = new SocketPackage();
        OutputStream output=null;
        InputStream input =null;
        // ͬ���ַ���(3byte)
        byte[] sync = null; //
        byte[] bodyLen = null; // 8λ����
        byte[] body = null; // ����
        byte[] md5 = null;  // MD5
        output = socket.getOutputStream();
        //д���ݷ��ͱ���
        output.write(socketPacket.getByteStream());
        //��÷���˷��ص�����
        input = socket.getInputStream();
        sync = this.streamToBytes(input,3);
        bodyLen = this.streamToBytes(input, 8);
        String lenString = new String(bodyLen);
        int len = Integer.valueOf(lenString);
        body = this.streamToBytes(input, len);
        md5 = this.streamToBytes(input, 32);
        sPacket.setSyncStr(new String(sync,Charset.forName("gbk")));
        socketPacket.setBodyLen(new String(bodyLen,Charset.forName("gbk")));
        socketPacket.setBody(new String(body,Charset.forName("gbk")));
        socketPacket.setMd5(new String(md5,Charset.forName("gbk")));
        return sPacket;
    }
    
    public byte[] streamToBytes(InputStream inputStream,int len){
        /**
         * inputStream.read(Ҫ���Ƶ����ֽ�����,��ʼλ���±�,Ҫ���Ƶĳ���)
         * �÷�����ȡ��input���±���Զ��ĺ��ƣ��´ζ�ȡ��ʱ���Ǵ��ϴζ�ȡ���ƶ������±꿪ʼ��ȡ
         * ����ÿ�ζ�ȡ��Ͳ���Ҫ���ƶ���ʼ���±���
         */
        byte [] bytes= new byte[len];
        try {
            inputStream.read(bytes, 0, len);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }
    public String socket(SocketPackage socketPacket) throws UnsupportedEncodingException{

	SockeUtil socketUtil = null;;
    Object log;
	try {
    	socketUtil = new SockeUtil("172.29.153.161", 8080);
    } catch (UnknownHostException e) {
        //log.error("socket�����쳣,������Ϣ��");
        e.printStackTrace();
    } catch (IOException e) {
       // log.error("socket IO�쳣");
        e.printStackTrace();
    }
    SocketPackage s = null;
    try {
        s = socketUtil.sentSocket(socketPacket);
    } catch (Exception e) {
        e.printStackTrace();
    }
    String result = "";
    try {
         result = new String(s.getByteStream(), "GBK");
    } catch (UnsupportedEncodingException e) {
        //log.error("socket��socketPackageתΪ�ַ����쳣��socketPackage��Ϣ��" + socketPacket.getByteStream());
        e.printStackTrace();
    }
    return result ;
}

}
