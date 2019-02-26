import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

public class GetSelect {
	 /**
     * 读取excel
     * 
     *
     * @param filepath
     */
	public static void main(String[] args) {
		GetSelect.select("D:/info.xls");
	}
	
    public static String[]  select(String filepath) {
    	String[] strs = null;
        try {
            //2003读取方式 , 2007请用SSFWorkbook
            //读取默认模板Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filepath)) ;
            //获取Sheet页
            
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<HSSFDataValidation> validations = sheet.getDataValidations();
           for(HSSFDataValidation validation : validations){
                CellRangeAddressList addressList = validation.getRegions();
 
                if(null == addressList || addressList.getSize() == 0){
                    continue;
                }
                //获取单元格行位置
                int row = addressList.getCellRangeAddress(0).getFirstColumn();
                //获取单元格列位置
                int column = addressList.getCellRangeAddress(0).getFirstColumn();
                //根据位置信息判断是不是自己想要获取的单元格位置
                if( column == 2){
                    DataValidationConstraint constraint = validation.getValidationConstraint();
                    //获取单元格数组
                     strs= constraint.getExplicitListValues();
                    //输出数组
                    for(String s:strs) {
                    	System.out.println(s);
                    	System.out.println(s);
                    }
                }
            }
 
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return strs;
    }
}
