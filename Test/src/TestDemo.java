import static org.testng.Assert.assertEquals;

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
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.testng.Assert;
import org.testng.annotations.Test;
import bean.LoginInfo;
import file.ReadFile;
import net.sf.json.JSONObject;
import requestmeans.GetAndPost;
public class TestDemo{		
	static HSSFDataValidation validation;
/*	static ArrayList<JSONObject> json;*/
	static boolean flag=false;
	
	    @Test
	    public static void runmain() throws Exception {	    		    
	    	System.out.println(GetAndPost.RunByGET("http://localhost:8080/say/add"));
	    }
	  /*  	System.out.println("数据结构");
            ArrayList<String> getdata=null;
            ArrayList<String> list=new ArrayList<String>();
            List<List<String>> data2=ReadFile.readExcel(new File("D:/info.xls"));
            try {
            for(int i=0;i<3;i++) {           	
            	if(data2.get(i).get(2).equals("post")) {
         
            	DjudeEqual(i,0,Integer.parseInt(JSONObject.fromObject(GetAndPost.sendPost(data2.get(i).get(1), 
             			"mobile="+data2.get(i).get(3)+"&"+"pwd="+data2.get(i).get(4))).getString("errno")));
            	}
            }
            }
            	catch (Exception e) {
					// TODO: handle exception
				}
                                             
            for(int j=0;j<list.size();j++) {
            	System.out.println(list.get(j));
            }
            //把文件的结果读取到其他的盘符的文件里面
          ReadFile.readandwriteExcel();       
}
		private static void DjudeEqual(int linenumber, int j,int k) {
			if(k!=j) {
				System.out.println("第"+linenumber+"行的数据不正确，拿到的数据是");
			}
			
		}
	    */

}
