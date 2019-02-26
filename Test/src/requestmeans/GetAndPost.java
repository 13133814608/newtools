package requestmeans;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.LoginInfo;
import net.sf.json.JSONObject;

public class GetAndPost {
	 static JSONObject json;
     static String url=null;
     static String result;
     static String param=null;
   //get请求方法
     @SuppressWarnings({ "unused", "resource", "deprecation" })
 	public static String RunByGET(String url) throws ClientProtocolException, IOException {
         String response = null;
         @SuppressWarnings("deprecation")
 		DefaultHttpClient httpclient = null;
         HttpResponse httpresponse = null;
         httpclient = new DefaultHttpClient();
         HttpGet httpGet = new HttpGet(url);
         httpresponse = httpclient.execute(httpGet);
         response = EntityUtils.toString(httpresponse.getEntity());
        return response;
      }  //获取各个文件字段的值
     
	 @SuppressWarnings("unused")
	 
	    private static String getValue(HSSFCell cell) {
	        if (cell.getCellType() == CellType.BOOLEAN) {
	            // 返回布尔类型 值
	            return String.valueOf(cell.getBooleanCellValue());
	        } else if (cell.getCellType() == CellType.NUMERIC) {
	            //返回数值类型的值
	            return String.valueOf(cell.getNumericCellValue());
	        } else {
	            //返回字符串类型的值
	            return cell.getStringCellValue();
	        }
	    }
	//post请求方法
	public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
	//拿到结果
	public static  ArrayList<Integer> getResult() throws Exception {
		ArrayList<Integer> errors=new ArrayList<Integer>();
		 List<LoginInfo> list =null;
	        for(LoginInfo info : list){	           
	            //System.out.println(info.getUrl());
	            url=info.getUrl();
	            param=info.getUsername()+"&"+info.getPassword();
	            System.out.println(param+"3");
	              result=sendPost(url,param);	   
	             //解析json代码
	             json = JSONObject.fromObject(result);	        
	             errors.add(Integer.parseInt(json.getString("errno")));	            
	        }
			return errors;
	}
	public static List<LoginInfo> readXls() throws Exception {
        InputStream is = new FileInputStream("D:/info.xls");
        HSSFWorkbook excel = new HSSFWorkbook(is);
        LoginInfo info = null;
        List<LoginInfo> list = new ArrayList<LoginInfo>();	        
        System.out.println(excel.getNumberOfSheets()+"数据行数");
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            HSSFSheet sheet = excel.getSheetAt(numSheet);
            if (sheet == null)
                continue;
            // 循环行Row
            System.out.println(sheet.getLastRowNum()+"数据行数");
            for (int rowNum = 1; rowNum <=sheet.getLastRowNum(); rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if (row == null)
                    continue;
                
                info = new LoginInfo();
                HSSFCell cell0 = row.getCell(0);
                if (cell0 == null)
                    continue;
                info.setId((cell0.getNumericCellValue()));
                HSSFCell cell1 = row.getCell(1);
                if (cell1 == null)
                    continue;
                info.setUrl(cell1.getStringCellValue());
                HSSFCell cell2 = row.getCell(2);
                if (cell2 == null)
                    continue;
                cell2.setCellType(CellType.STRING);
                info.setUsername(cell2.getStringCellValue());
                HSSFCell cell3 = row.getCell(3);
                if (cell3 == null)
                    continue;
                info.setPassword(String.valueOf(cell3.getNumericCellValue()));
                HSSFCell cell4 = row.getCell(4);
                if (cell4== null)
                    continue;
                info.setExcepedresult(cell4.getStringCellValue());
                HSSFCell cell5 = row.getCell(5);
                if (cell5== null)
                    continue;
                info.setReallyresult(cell5.getStringCellValue());
                HSSFCell cell6 = row.getCell(6);
                if(cell6==null)
                	 continue;
                info.setReallyresult(cell6.getStringCellValue());
                HSSFCell cell7 = row.getCell(7);
                if(cell7==null)
                	 continue;
                info.setReallyresult(cell7.getStringCellValue());
                list.add(info);
            }
        }

        return list;
    }

    public static ArrayList<String> readfromxml() throws DocumentException{
    	ArrayList<String> listdata=new ArrayList<String>();
    	SAXReader reader = new SAXReader();
        Document document = reader.read(new File("C:\\Users\\20433\\Desktop\\xmllogin.xml"));  
        //获取文档根节点
        Element root = document.getRootElement();
        //输出根标签的名字
        System.out.println(root.getName());
        //获取根节点下面的所有子节点（不包过子节点的子节点）
        List<Element> list = root.elements() ;
        //遍历List的方法
        for (Element e:list){
           listdata.add(e.getStringValue());
        }
        for(int i=0;i<listdata.size();i++) {
        	System.out.println(listdata.get(i));
        }
		return listdata;
    }
    public  static String RequestRpc() {
    	 return null;
    }
}
