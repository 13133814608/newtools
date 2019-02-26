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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;
import requestmeans.GetAndPost;
public class ReadFile {
	static String[] getdata=new String[3000];
	static String sgetdata;
	static JSONObject json;
	static ArrayList<Integer> errors=new ArrayList<Integer>();
	//读取xml文件数据
	 //读取excel格式的文件
		 public static  void readandwriteExcel() {
		        // 读取Excel文件
		        File file = new File("D:/info.xls");
		        try {
		            //得到所有数据
		            List<List<String>> allData=readExcel(file);
		            //直接将它写到另外的excel中
		            makeExcel(allData);
		        } catch (Exception e) {
		        	
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		}
		//读取excel文件的数据	 
		 public static List<List<String>> readExcel(File file) throws Exception {
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
			        cell.setCellValue("请求方法");
			        cell = row.createCell(3);
			        cell.setCellValue("用户名");
			        cell = row.createCell(4);			        
			        cell.setCellValue("密码");
			        cell = row.createCell(5);			        
			        cell.setCellValue("预期结果");
			        cell = row.createCell(6);			        
			        cell.setCellValue("实际结果");
			        cell = row.createCell(7);			        
			        cell.setCellValue( "通过与否");
			       //cell = row.createCell(8);
			        //第五步，写入数据
			       System.out.println(result.size()+"asdas----------------------------------------------------");
			        for(int i=0;i<result.size();i++) {
			            List<String> oneData = result.get(i);
			            HSSFRow row1 = sheet.createRow(i + 1);
			            System.out.println("大小"+oneData.size());
			            for(int j=0;j<oneData.size()+1;j++) {
			            	if(j<=5) {
			            	row1.createCell(j).setCellValue(oneData.get(j));
			            	}
			                 //创建单元格设值
			            	/*if(j==6) {
			            		//System.out.println("读入第五列");
			            		System.out.println("运行到了第4行");
			            		 row1.createCell(6).setCellValue("errno==0");
			            		 
			            	}*/
			            
			            	 if(j==6) {
			            		 //row1.createCell(5).setCellValue("errno==0");
			            		 List<List<String>> data2=ReadFile.readExcel(new File("D:/info.xls"));
			     	               for(int k=0;k<data2.size();k++) {
			     	            	sgetdata=GetAndPost.sendPost(data2.get(i).get(1), "mobile="+data2.get(i).get(3)+"&"+"pwd="+data2.get(i).get(4));         			            		
			     	            	System.out.println("运行到了第5行");
			     	            	sheet.getRow(i+1).createCell(6).setCellValue(sgetdata);
			            		     
			     	            	 
			     	               }
			            	}
			     	         if(j==7) {			     	    			     	        	
			     	        	List<List<String>> data2=ReadFile.readExcel(new File("D:/info.xls"));
			     	               for(int k=0;k<data2.size();k++) {
			     	            	getdata[k]=GetAndPost.sendPost(data2.get(k).get(1), "mobile="+data2.get(k).get(2)+"&"+"pwd="+data2.get(k).get(3));   
			     	            	    json = JSONObject.fromObject(getdata[k]);	        
			     		               if(Integer.parseInt(json.getString("errno"))==0){	 
			     		            	  sheet.getRow(1+i).createCell(7).setCellValue("通过");		
			     		            	 System.out.println("通过");
			     		               }else {
			     		            	  sheet.getRow(1+i).createCell(7).setCellValue("通过失败");
			     		            	  System.out.println("失败");
			     		               }
				     	           
			     	               }
				     	             
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

