package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.LoginInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;
import requestmeans.GetAndPost;

public class ReadFile {
	static String sgetdata;
	static JSONObject json;
	static ArrayList<Integer> errors=new ArrayList<Integer>();
	//读取xml文件数据
	 public static void readxml() throws DocumentException {
		 SAXReader reader = new SAXReader();
		 Document doc = reader.read(new File("C:\\Users\\20433\\Desktop\\readdemo.xml"));
		 Element rootElem = doc.getRootElement();
		 System.out.println(rootElem.getName());
		 Iterator<Element> it= rootElem.elementIterator("person");
		 while(it.hasNext()){
		 Element personElem = it.next();
		 System.out.println(personElem.getName());
		 List<Attribute> list1 = personElem.attributes();
		 for(Attribute attr:list1){
		 System.out.println(attr.getName()+"="+attr.getValue());
		 }
		 List<Element> list2 = personElem.elements();
		 for(Element elems:list2){
		 System.out.println(elems.getName()+"="+elems.getText());
		}	
	}
}
	 //读取excel格式的文件
		 public static  void readandwriteExcel() {
		        // 读取Excel文件
		        File file = new File("D:/info.xls");
		        try {
		            //得到所有数据
		            List<List<String>> allData=readExcel(file);
		            //直接将它写到excel中
		            makeExcel(allData);
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		}
		//读取excel文件的数据	 
		 private static List<List<String>> readExcel(File file) throws Exception {
			        // 创建输入流，读取Excel
			        InputStream is = new FileInputStream(file.getAbsolutePath());
			        // jxl提供的Workbook类
			        Workbook wb = Workbook.getWorkbook(is);
			        // 只有一个sheet,直接处理
			        //创建一个Sheet对象
			        Sheet sheet = wb.getSheet(0);
			        // 得到所有的行数
			        int rows = sheet.getRows();
			        // 所有的数据
			        List<List<String>> allData = new ArrayList<List<String>>();
			        // 越过第一行 它是列名称
			        for (int j = 1; j < rows; j++) {

			            List<String> oneData = new ArrayList<String>();
			            // 得到每一行的单元格的数据
			            Cell[] cells = sheet.getRow(j);
			            for (int k = 0; k < cells.length; k++) {

			                oneData.add(cells[k].getContents().trim());
			            }
			            // 存储每一条数据
			            allData.add(oneData);
			            // 打印出每一条数据
			            System.out.println(oneData);

			        }
			        return allData;

			    }
		 //制作excel文件
			 public static  void makeExcel(List<List<String>> result) throws Exception {
			        //第一步，创建一个workbook对应一个excel文件
			        HSSFWorkbook workbook = new HSSFWorkbook();
			        //第二部，在workbook中创建一个sheet对应excel中的sheet
			        HSSFSheet sheet = workbook.createSheet("测试结果");
			        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
			        HSSFRow row = sheet.createRow(0);
			        //第四步，创建单元格，设置表头
			        HSSFCell cell = row.createCell(0);
			        cell.setCellValue("id号码");
			        cell = row.createCell(1);
			        
			        cell.setCellValue("请求地址");
			        cell = row.createCell(2);
			        
			        cell.setCellValue("用户名");
			        cell = row.createCell(3);
			        
			        cell.setCellValue("密码");
			        cell = row.createCell(4);
			        
			        cell.setCellValue("预期结果");
			        cell = row.createCell(5);
			        
			        cell.setCellValue("实际结果");
			        cell = row.createCell(6);
			        
			        cell.setCellValue( "通过与否");
			        cell = row.createCell(7);
			        //第五步，写入数据
			      //  System.out.println(result.size()+"asdas");
			        for(int i=0;i<result.size();i++) {
			            List<String> oneData = result.get(i);
			            HSSFRow row1 = sheet.createRow(i + 1);
			            for(int j=0;j<=oneData.size();j++) {
			                 //创建单元格设值
			            	if(j==4) {
			            		//System.out.println("读入第五列");
			            		
			            		 row1.createCell(4).setCellValue("errno==0");
			            	}
			            
			            	else if(j==5) {
			            		 List<LoginInfo> data=GetAndPost.readXls();
			     	               for(int k=0;k<data.size();k++) {
			     	            	 sgetdata=GetAndPost.sendPost(data.get(k).getUrl(), "mobile="+data.get(k).getUsername()+"&"+"pwd="+data.get(i).getPassword());         			            		
			     	            	 row1.createCell(5).setCellValue(sgetdata);
			            		     
			            	}
			            	}
			     	        else if(j==6) {
			     	        	int count=0;
			     	            	  List<LoginInfo> data=GetAndPost.readXls();
			     	            	  System.out.println(data.size()+"sssssssssssss");
				     	               for(int k=0;k<data.size();k++) {
				     	            	
			     	            	    json = JSONObject.fromObject(GetAndPost.sendPost(data.get(k).getUrl(), "mobile="+data.get(k).getUsername()+"&"+"pwd="+data.get(i).getPassword()));	        
			     		               if(Integer.parseInt(json.getString("errno"))==0){	 
			     		            	  sheet.getRow(1).createCell(6).setCellValue("通过");
			     		            	  count++;
			     		               }
			     		              /* else {
			     		            	  sheet.getRow(k+1).createCell(6).setCellValue("失败");
			     		            	  count++;
			     		               }*/
			     	               }
				     	               System.out.println(count);
			     	               }
			            	
			            	else {
			            		row1.createCell(j).setCellValue(oneData.get(j));
			            	}
			               
			            	
			        }
			        //将文件保存到指定的位置
			        try {
			            FileOutputStream fos = new FileOutputStream("E:\\result.xls");
			            workbook.write(fos);
			            System.out.println("写入成功");
			            fos.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			        }
 
			 //获取各个文件字段的值
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
}

