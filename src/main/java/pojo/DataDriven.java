package pojo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static void main(String[] args) throws IOException {
		
	}

	public ArrayList<String> getData(String testcaseName) throws IOException {
		ArrayList<String> a=new ArrayList<String>();
		FileInputStream fis = new FileInputStream("src/test/resources/NEgativeScenario.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets =workbook.getNumberOfSheets();
        for(int i=0;i<sheets;i++) {
        	if(workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
        		XSSFSheet sheet=workbook.getSheetAt(i);
        		
        //identify the test case column to scan the entire first row
        		Iterator<Row>  rows= sheet.iterator();
        		Row firstrow =rows.next();
        		Iterator<Cell> cell=firstrow.cellIterator();
        		int k=0;
        		int column = 0;
        		while(cell.hasNext())
        		{
        		Cell value=cell.next();
        		if(value.getStringCellValue().equalsIgnoreCase("Scenario"))
        		{
        			column =k;
        		}
        		k++;
        		}
        		System.out.println(column);
        		
        	//column is identified now we have to search in the column	
        		while(rows.hasNext()) {
        			Row r=rows.next();
        			if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
        			{
        				Iterator<Cell>ce=r.cellIterator();
        				while(ce.hasNext()) {
        					   Cell c =ce.next();
        					   if(c.getCellType()==CellType.STRING) {
        					a.add(c.getStringCellValue());
        					   }
        					   else a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
        				}
        			}
        			
        		}
        		
        }
	}
		return a;

}
		
	}
		

