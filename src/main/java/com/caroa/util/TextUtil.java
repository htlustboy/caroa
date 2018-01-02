package com.caroa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.caroa.annotcaion.Export;
import com.caroa.model.TT;
import com.caroa.model.export.TTVo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;


public class TextUtil {
	
	/**
	 * 将txt文件转换成pdf文件
	 * @param textFilePath txt文件全路径
	 * @param pdfFilePath  pdf文件全路径
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public static void txtFile2Pdf(String textFilePath,String pdfFilePath) throws DocumentException, IOException{
		
		Document document = new Document(PageSize.A4, 80, 80, 60, 30);
		PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
		document.open();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(bfChinese, 18, Font.NORMAL);
		Paragraph t = new Paragraph("aliness", fontChinese);
		t.setAlignment(Element.ALIGN_CENTER);
		t.setLeading(30.0F);
		document.add(t);
		fontChinese = new Font(bfChinese,11,Font.NORMAL);
		InputStreamReader inputStreamReader = null;
		BufferedReader read = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(textFilePath), "GBK");
			read = new BufferedReader(inputStreamReader);
			String line = null;
			while((line = read.readLine())!=null){
				System.out.println(line);
				t = new Paragraph(line,fontChinese);
				t.setAlignment(Element.ALIGN_LEFT);
				t.setLeading(20.0F);
				document.add(t);
			}
		} catch (Exception e) {
			System.out.println("目标文件不存在或者不可读！");
			e.printStackTrace();
		}finally{
			try {
				read.close();
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("===============执行成功================");
	}
	
	
	/**
	 * 将txt文件转为word文档
	 * @param textFilePath
	 * @param wordFilePath
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static void txtFile2Word(String textFilePath,String wordFilePath) throws DocumentException, IOException{
		Document document = new Document(PageSize.A4, 80, 80, 60, 30);
		RtfWriter2.getInstance(document, new FileOutputStream(wordFilePath));
		document.open();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(bfChinese, 18, Font.NORMAL);
		Paragraph t = new Paragraph("aliness", fontChinese);
		t.setAlignment(Element.ALIGN_CENTER);
		t.setLeading(30.0F);
		document.add(t);
		fontChinese = new Font(bfChinese,11,Font.NORMAL);
		InputStreamReader inputStreamReader = null;
		BufferedReader read = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(textFilePath), "GBK");
			read = new BufferedReader(inputStreamReader);
			String line = null;
			while((line = read.readLine())!=null){
				System.out.println(line);
				t = new Paragraph(line,fontChinese);
				t.setAlignment(Element.ALIGN_LEFT);
				t.setLeading(20.0F);
				document.add(t);
			}
		} catch (Exception e) {
			System.out.println("目标文件不存在或者不可读！");
			e.printStackTrace();
		}finally{
			try {
				read.close();
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("===============执行成功================");
	}
	
	/**
	 * 导出excel数据
	 * @param <T>
	 * @param typeClazz 目标类
	 * @param proxyClazz 目标代理类
	 * @param list 查询结果
	 */
	@SuppressWarnings("resource")
	public static void exportExcel(Class<?> typeClazz,Class<?> proxyClazz,Collection<?> list){
		//声明一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//声明一个单子并且命名
		HSSFSheet sheet = wb.createSheet(String.valueOf(System.currentTimeMillis()));
		//设置长度
		sheet.setDefaultColumnWidth(30);
		//生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		//创建行号
		int rowIndex = 0;
		//创建表头
		HSSFRow row = sheet.createRow(rowIndex);
		//表头右对齐
		style.setAlignment(HorizontalAlignment.LEFT);
		//表头依次创建单元格
		HSSFCell cell = null;
		
		//通过反射获取表头的字段名
		Field[] fields = proxyClazz.getDeclaredFields();
		for(Field f : fields){
			boolean isHasExport = f.isAnnotationPresent(Export.class);
			if(isHasExport){
				Export e = f.getAnnotation(Export.class);
				int index = e.index();
				String name = e.name();
				cell = row.createCell(index);
				cell.setCellValue(name);
				cell.setCellStyle(style);
			}
		}
		rowIndex++;
		
		//判断数据是否为空
		if(!list.isEmpty()){
			for(Object obj : list){
				if(!obj.getClass().equals(typeClazz)){
					System.out.println(typeClazz.getName()+" 的类型与 "+obj.getClass()+" 的类型不匹配");
					System.out.println("导出失败！");
					return;
				}
				HSSFRow r = sheet.createRow(rowIndex);
				for(Field f : fields){
					boolean isHasExport = f.isAnnotationPresent(Export.class);
					if(isHasExport){
						Export e = f.getAnnotation(Export.class);
						int number = e.index();
						String type = e.type();
						//设置单元格
						cell = r.createCell(number);
						//通过反射获取具体的值
						Method method;
						try {
							method = obj.getClass().getDeclaredMethod("get"+type.substring(0,1).toUpperCase()+type.substring(1), null);
							String value = null;
							value = method.invoke(obj, null).toString();
							if(method.getReturnType().getName().equals("java.util.Date")){
								value = BaseUtil.date2String((Date)method.invoke(obj, null));
							}
							cell.setCellValue(value);
							cell.setCellStyle(style);
						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException e1) {
							System.out.println("导出失败！");
							e1.printStackTrace();
							return;
						} catch (NoSuchMethodException | SecurityException e1) {
							System.out.println("没有找到 "+"get"+type.substring(0,1).toUpperCase()+type.substring(1)+" 方法！");
							e1.printStackTrace();
							return;
						}
					}
				}
				rowIndex++;
			}
		}
		
		//开始导出
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("C:\\Users\\gaojk\\Downloads\\"+String.valueOf(System.currentTimeMillis())+".xls"));
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在！");
			e.printStackTrace();
			return;
		}catch (IOException e) {
			System.out.println("导出失败！");
			e.printStackTrace();
			return;
		}
		System.out.println("导出成功");
	}
	
	
	public static void main(String[] args) throws DocumentException, IOException {
//		txtFile2Pdf("C:\\Users\\gaojk\\Desktop\\bug.txt", "D:\\测试.pdf");
//		txtFile2Word("C:\\Users\\gaojk\\Desktop\\bug.txt", "D:\\测试.word");
		List<TT> list = initData();
		exportExcel(TT.class, TTVo.class, list);
	}
	
	public static List<TT> initData(){
		List<TT> list = new ArrayList<>();
		TT t = new TT();
		t.setId("1");
		t.setUsername("hutao");
		t.setPassword("123456");
		t.setDate(new java.util.Date());
		
		TT t1 = new TT();
		t1.setId("2");
		t1.setUsername("lustby");
		t1.setPassword("666666");
		t1.setDate(new java.util.Date());
		
		TT t2 = new TT();
		t2.setId("3");
		t2.setUsername("胡涛");
		t2.setPassword("4303105");
		t2.setDate(new java.util.Date());
		list.add(t);
		list.add(t1);
		list.add(t2);
		return list;
	}
}
