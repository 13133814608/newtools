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
import org.testng.annotations.Test;

import bean.LoginInfo;
import file.ReadFile;
import net.sf.json.JSONObject;
import requestmeans.GetAndPost;

public class TestDemo{		
	    //使用TestNg框架来运行程序
	    @Test
	    public static void runmain() throws Exception {
	    	System.out.println("数据结构");
            String getdata=null;
            ArrayList<String> list=new ArrayList<String>();
            List<LoginInfo> data=GetAndPost.readXls();
            for(int i=0;i<data.size();i++) {
            	getdata=GetAndPost.sendPost(data.get(i).getUrl(), "mobile="+data.get(i).getUsername()+"&"+"pwd="+data.get(i).getPassword());
                list.add(getdata);
            }
            for(int j=0;j<list.size();j++) {
            	System.out.println(list.get(j));
            }
            //把文件的结果读取到其他的盘符的文件里面
            ReadFile.readandwriteExcel();       
}
}

