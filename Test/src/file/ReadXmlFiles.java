package file;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXmlFiles {
   public static void main(String[] args) throws DocumentException {
	ReadXmlFiles.readfromxml();
}
   public static ArrayList<String> readfromxml() throws DocumentException{
	    	ArrayList<String> listdata=new ArrayList<String>();
	    	SAXReader reader = new SAXReader();
	        Document document = reader.read(new File("C:\\Users\\20433\\Desktop\\readdemo.xml"));  
	        //获取文档根节点
	        Element root = document.getRootElement();
	        //输出根标签的名字
	        System.out.println(root.getName());
	        //获取根节点下面的所有子节点（不包过子节点的子节点）
	        @SuppressWarnings("unchecked")
			List<Element> list = root.elements() ;
	        //遍历List的方法
	        for (Element e:list){
	           listdata.add(e.getStringValue());
	        }
	        System.out.print("+\"-----------------------------------\""+"这个是新增的数据");
	        String url=root.element("request").getStringValue();//首先要知道自己要操作的节点。 
	        System.out.println(url);
	        String pwd=root.element("pwd").getStringValue();//首先要知道自己要操作的节点。 
	        System.out.println(pwd);
	        for(int i=0;i<listdata.size();i++) {
	        	System.out.println(listdata.get(i));
	        }
			return listdata;		
	    }
}
