package Look;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
public class SocketPackage {
    private String bodyLen;
    private String body;
    private String syncStr;
    private String md5;
    public String getBodyLen() {
        return bodyLen;
    }
    public String getBody() {
        return body;
    }
    public String getSyncStr() {
        return syncStr;
    }
    public String getMd5() {
        return md5;
    }
    public void setBodyLen(String bodyLen) {
        this.bodyLen = bodyLen;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setSyncStr(String syncStr) {
        this.syncStr = syncStr;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public byte[] getByteStream() throws UnsupportedEncodingException{
        byte[] bodyBytes = this.body.getBytes("gbk");//获得body的字节数组
        int bodyLength = bodyBytes.length;
        int socketLength = 3+bodyLength+8+32;
        byte [] soc = new byte[socketLength];
        //添加校验数据
        int index = 0;
        soc[0]=0x11;
        soc[1]=0x12;
        soc[2]=0x13;
        index+=3;
        //添加8位报文长度
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(8);
        numberFormat.setGroupingUsed(false);
        byte [] num = numberFormat.format(socketLength).getBytes();
        for(int i = 0;i<8;i++){
            soc[index++]= num[i];
        }
        //添加body内容
        for(int i = 0;i<bodyLength;i++){
            soc[index++] = bodyBytes[i];
        }
        //添加md5校验码
        byte [] md5Bytes = this.md5.getBytes();
        for (int i = 0; i < num.length; i++) {
            soc[index++] = md5Bytes[i];
        }
        return soc;
    }  
    //字节装转报文string
    public String getString(byte [] socketBytes){
        String syncStr = this.bytesToString(socketBytes, 0, 3);
        String socketLength = this.bytesToString(socketBytes, 3, 3+8);
        String body = this.bytesToString(socketBytes, 3+8, socketBytes.length-32);
        String md5 = this.bytesToString(socketBytes,socketBytes.length-32,socketBytes.length);
        return syncStr+socketLength+body+md5;
    }
    //将字节数组转化为string
    public String bytesToString(byte [] bytes,int start,int end){
        String str = "";
        if(bytes.length<end-start){
            return str;
        }
        byte [] bs = new byte[end-start];
        for(int i = 0;i<end-start;i++){
            bs[i] = bytes[start++];
        }
        str = new String(bs);
        return str;
    }
    public String toString(){
        return this.syncStr+this.bodyLen+this.body+this.md5;
    }
    
}




