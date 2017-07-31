package com.kosmo.web.util;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kosmo.web.board.controller.BoardController;
import com.kosmo.web.board.dto.BoardDto;

public class WriteListToExcelFile {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@SuppressWarnings("resource")
	public static void WriteListToExcelFile(String fileName, List<BoardDto> list) throws Exception{
	 	Workbook workbook = null;
	 	
	 	if(fileName.endsWith("xlsx")){
	 		workbook = new XSSFWorkbook();
	 	}else if(fileName.endsWith("xls")){
	 		workbook = new HSSFWorkbook();
	 	}else{
	 		throw new Exception("invalid file name, should be xls or xlsx");
	 	}
	 	
	 	Sheet sheet = workbook.createSheet("board_list");
	 	
	 	Iterator<BoardDto> iterator = list.iterator();

	 	int rowIndex = 0;
	 	int excelname=0; //�������� �ֱ� ���� ����� ����

	 	do{
	 		BoardDto boardDto = iterator.next();
	 		Row row = sheet.createRow(rowIndex++);
	 		
	 		if(excelname==0){ //������ 
	 			Cell cell0 = row.createCell(0);
	 			cell0.setCellValue("ID");
	 			Cell cell1 = row.createCell(1);
	 			cell1.setCellValue("����");
	 			Cell cell2 = row.createCell(2);
	 			cell2.setCellValue("�۾���");
	 			Cell cell3 = row.createCell(3);
	 			cell3.setCellValue("�ۼ���");
	 			Cell cell4 = row.createCell(4);
	 			cell4.setCellValue("��ȸ��");
	 			excelname++;
	 			
	 		}else{ //���������� ���� ��
	 			Cell cell0 = row.createCell(0);
	 			cell0.setCellValue(boardDto.getB_num());
	 			Cell cell1 = row.createCell(1);
	 			cell1.setCellValue(boardDto.getSubject());
	 			Cell cell2 = row.createCell(2);
	 			cell2.setCellValue(boardDto.getName());
	 			Cell cell3 = row.createCell(3);
	 			cell3.setCellValue(boardDto.getWrite_date());
	 			Cell cell4 = row.createCell(4);
	 			cell4.setCellValue(boardDto.getHit());
	 		}
	 	}while(iterator.hasNext());
	 	
	 	//lets write the excel data to file now
	 	FileOutputStream fos = new FileOutputStream(fileName);
	 	workbook.write(fos);
	 	fos.close();
	 	
	 	logger.info("{} written successfully", fileName);
	}
}
