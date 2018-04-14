import java.io.*;

/**
 * Created by 袁江超 on 2018/4/12.
 */
public class FileProcesser {

    public static int File2String(String path){
        int count = 0;
        try{
            InputStreamReader isr = new InputStreamReader(new FileInputStream(path),GetCharset(path));
            BufferedReader br = new BufferedReader(isr);
            StringBuilder result = new StringBuilder();
            String s = null;

            boolean isFirst = true;
            while((s=br.readLine())!=null){
//                if(isFirst){
//                    isFirst = false;
//                }else {
//                    s=System.lineSeparator()+s;
//                }
                count++;
                result.append(s);
            }
            br.close();
//            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }



    /**
     * 判断文本的编码格式
     * @param path 文本路径
     * @return 编码格式字符
     */
    public static String GetCharset(String path){
        String code ="GBK";
        try{
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(path));
            int p = (bin.read()<<8)+bin.read();

            switch (p){
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


}
