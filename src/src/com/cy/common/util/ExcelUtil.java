package com.cy.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static Log log = LogFactory.getLog(ExcelUtil.class);
	
	public static List<Map<String,String>> ExcelToList(String filePath) throws Exception{
		
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		String filetype = filePath.substring(filePath.lastIndexOf(".") + 1);
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(fi == null)
			throw new Exception("文件不存在");
		//xls文件
		if("xls".equals(filetype.toLowerCase())){
			try {
				HSSFWorkbook wookbook = new HSSFWorkbook(fi);
				HSSFSheet sheet = wookbook.getSheet("Sheet1");
				int rows = sheet.getPhysicalNumberOfRows();
				
				//获取标题行
				HSSFRow title = sheet.getRow(0);
				log.info(title.getLastCellNum());
				int index = title.getFirstCellNum();
				int rowcount = title.getLastCellNum();
				for (int i = 1; i < rows; i++){
					
					HSSFRow row = sheet.getRow(i);
					if(isBlankRow(row, index, rowcount))
						continue;
					if (row != null){
						Map<String,String> map = new TreeMap<String,String>();
						int cells = title.getPhysicalNumberOfCells();
						
						for (int j = 0; j < cells; j++){
							String value = "";
							HSSFCell cell = row.getCell(j);
							if (cell != null){
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										value += cell.getStringCellValue().trim();
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										value += cell.getStringCellValue().trim();
										break;
									case HSSFCell.CELL_TYPE_STRING:
										value += cell.getStringCellValue().trim();
										break;
									default:
										value = "";
										break;
								}
							}
							//String key = title.getCell(j).getStringCellValue().trim();
							if(!StringUtil.isEmpty(value)){
								map.put(title.getCell(j).getStringCellValue().trim(), value);
							}
						}
						
						mapList.add(map);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
		}else if("xlsx".equals(filetype.toLowerCase())){
			//xlsx文件
			try {
				XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));
				XSSFSheet sheet = wookbook.getSheet("Sheet1");
				int rows = sheet.getPhysicalNumberOfRows();
				//获取标题行
				XSSFRow title = sheet.getRow(0);
				int index = title.getFirstCellNum();
				int rowcount = title.getLastCellNum();
				for (int i = 1; i < rows; i++){
					
					XSSFRow row = sheet.getRow(i);
//					if(isBlankRow(row, index, rowcount))
//						continue;
					if (row != null){
						Map<String,String> map = new TreeMap<String,String>();
						int cells = title.getPhysicalNumberOfCells();
						
						for (int j = 0; j < cells; j++){
							String value = "";
							XSSFCell cell = row.getCell(j);
							if (cell != null){
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										value += cell.getStringCellValue().trim();
										break;
									case HSSFCell.CELL_TYPE_STRING:
										value += cell.getStringCellValue().trim();
										break;
									default:
										value = "";
										break;
								}
							}
							map.put(title.getCell(j).getStringCellValue().trim(), value);
						}
						
						mapList.add(map);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fi != null)
			fi.close();
		
		return mapList;
	}
	
	public static boolean isBlankRow(HSSFRow row, int index, int rowCount){
		if(row == null)
			return true;
		for(int i=index; i < rowCount; i++){
			if(row.getCell(i) != null && 
					!"".equals(row.getCell(i).toString().trim())){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBlankRow(XSSFRow row, int index, int rowCount){
		if(row == null)
			return true;
		for(int i=index; i < rowCount; i++){
			if(row.getCell(i) != null || 
					!"".equals(row.getCell(i).getStringCellValue().trim())){
				return false;
			}
		}
		return true;
	}
	/**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
    	  // 创建excel工作簿
//        Workbook wb = new HSSFWorkbook();
        HSSFWorkbook wookbook = new HSSFWorkbook();
		HSSFSheet sheet1 = wookbook.createSheet("sheet1");
//		sheet1.setColumnView(1, 40);  
//        sheet1.setColumnView(1, 30);  
//        sheet1.setColumnView(2, 50);  
//        sheet1.setColumnView(3, 20);  
        // 创建第一个sheet（页），并命名
        //Sheet sheet = (Sheet) wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
//        for(int i=0;i<keys.length;i++){
//            ((org.apache.poi.ss.usermodel.Sheet) sheet).setColumnWidth((short) i, (short) (35.7 * 150));
//        }
        for(int i=0;i<keys.length;i++){
        	sheet1.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        // 创建第一行
       // Row row1 = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow((short) 0);
        Row row=sheet1.createRow(0);
        // 创建两种单元格格式
        CellStyle cs = wookbook.createCellStyle();
        CellStyle cs2 = wookbook.createCellStyle();
        
//        CellStyle cs = wb.createCellStyle();
//        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wookbook.createFont();
        Font f2 = wookbook.createFont();
//        WritableCellFormat cellFormat1 = new WritableCellFormat(f);  
//        //设置背景颜色;  
        HSSFCellStyle sheetStyle = wookbook.createCellStyle();   
        
     // 背景色的设定   
        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);   
        // 前景色的设定   
        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);   
        // 填充模式   
        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);   
        // 设置列的样式   
//        for (int i = 0; i <= 14; i++) {   
//          sheet.setDefaultColumnStyle((short) i, sheetStyle);   
//        }   
//      cellFormat1.setBackground(Colour.BLUE_GREY);  
//      Font f = wb.createFont();
//      Font f2 = wb.createFont();
     // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 14);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        //   sheet1.setColumnWidth(i, 40);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = (sheet1).createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?"暂无成绩 ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wookbook;
   
    }
}
