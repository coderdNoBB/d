
package com.example.demo.util;

import java.io.OutputStream;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class ExcelUtil {

	/**
	 * 导出excel
	 * @param column 列名
	 * @param templatePath 模板路径 
	 * @param os  输出流
	 */
	public static <T> void excelFileDemo(OutputStream os) {
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet("1");
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 创建第一行（也可以称为表头）
		HSSFRow rowFirst = sheet.createRow(0);
		rowFirst.createCell(0).setCellValue("姓名");
		rowFirst.createCell(1).setCellValue("手机号");

		// 样式字体居中
		style.setAlignment(HorizontalAlignment.CENTER);

		for(int i=1;i<=10;i++) {
			HSSFRow row = sheet.createRow(i++);
			row.createCell(0).setCellValue(UUID.randomUUID().toString());
			row.createCell(1).setCellValue(UUID.randomUUID().toString());
		}
		try {
			wb.write(os);
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

