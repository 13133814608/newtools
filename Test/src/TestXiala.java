import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class TestXiala {
	static List<String> oneData;
	public static ArrayList<String> GetXiala() throws BindException, IOException, BiffException {
		// TODO Auto-generated method stub
		// 创建输入流，读取Excel
        InputStream is = new FileInputStream("D:/info.xls");
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
             oneData = new ArrayList<String>();
            // 得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
                oneData.add(cells[2].getContents());
            for(int i=0;i<oneData.size();i++) {
            	System.out.println(oneData.get(i).equals("post"));
            }
	}
		return  (ArrayList<String>) oneData;
}
}