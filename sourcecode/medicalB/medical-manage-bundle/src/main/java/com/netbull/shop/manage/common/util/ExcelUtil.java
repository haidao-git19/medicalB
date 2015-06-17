package com.netbull.shop.manage.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;

/**
 * Excel解析工具
 * @author Administrator
 *
 */
@Component
public class ExcelUtil{
	
	private static final Logger logger=Logger.getLogger(ExcelUtil.class);
	private static String realPath = ConfigLoadUtil.loadConfig().getPropertie("excelExportPath");
	//String saveToFolder = System.getenv("USERPROFILE")+"\\Desktop";
	private static File dir=new File(realPath);
	private static File file=null;

	public static ExcelUtil getInstance(ExcelUtil excelUtil){
		if(excelUtil==null){
			excelUtil=new ExcelUtil();
		}
		return excelUtil;
	}
	
	public Integer buildExcelDocument(List<Map> list,final String fileName){
		HSSFWorkbook workbook=new HSSFWorkbook();
		//产生Excel表头
		int index=1;
		HSSFSheet sheet= workbook.createSheet("sheet"+index++);
		
		//设置表列宽---(列号，宽度)
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 8000);
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 6000);
		
		HSSFRow header=sheet.createRow(0);
		//产生标题列
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("卡片主题");
		header.createCell(2).setCellValue("卡号");
		header.createCell(3).setCellValue("卡片类型");
		header.createCell(4).setCellValue("卡片状态");
		header.createCell(5).setCellValue("生效日期");
		header.createCell(6).setCellValue("失效日期");
		header.createCell(7).setCellValue("卡密");
		header.createCell(8).setCellValue("可兑换数");
		header.createCell(9).setCellValue("备注");
		
		//设置表单元的格式
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		//cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd"));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
		for(int i=0;i<10;i++){
			header.getCell(i).setCellStyle(cellStyle);
		}
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			Date date=new Date();
			String timeStamp=sdf.format(date);
			if(!dir.exists()){
				dir.mkdir();
			}
			file=new File(dir,fileName+".xls");
			
			//填充数据
			int rowNum=1;//行序号
			Map<Object,Object> map=null;
			for(Iterator<Map> iter=list.iterator();iter.hasNext();){
				map= iter.next();
				HSSFRow row=sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(map.get("id").toString());
				row.createCell(1).setCellValue(map.get("cardTitle").toString());
				row.createCell(2).setCellValue(map.get("cardCode").toString());
				row.createCell(3).setCellValue(map.get("cardType")==null?"":map.get("cardType").toString());
				row.createCell(4).setCellValue(map.get("cardStatus").toString());
				row.createCell(5).setCellValue(map.get("cardStartDate").toString());
				row.createCell(6).setCellValue(map.get("cardEndDate").toString());
				row.createCell(7).setCellValue(map.get("cardPass")==null?"":map.get("cardPass").toString());
				row.createCell(8).setCellValue(map.get("validNum")==null?"":map.get("validNum").toString());
				row.createCell(9).setCellValue(map.get("remark")==null?"":map.get("remark").toString());
				
				//设置单元格格式
					row.getCell(0).setCellStyle(cellStyle);
					row.getCell(1).setCellStyle(cellStyle);
					row.getCell(2).setCellStyle(cellStyle);
					row.getCell(3).setCellStyle(cellStyle);
					row.getCell(4).setCellStyle(cellStyle);
					row.getCell(5).setCellStyle(cellStyle);
					row.getCell(6).setCellStyle(cellStyle);
					row.getCell(7).setCellStyle(cellStyle);
					row.getCell(8).setCellStyle(cellStyle);
					row.getCell(9).setCellStyle(cellStyle);
			}
				//将workbook写入文件
				FileOutputStream fos=new FileOutputStream(file);
				workbook.write(fos);
				fos.flush();
				fos.close();
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer buildExcelDocumentForAgentPrivilege(List<Map> list,final String fileName){
		HSSFWorkbook workbook=new HSSFWorkbook();
		//产生Excel表头
		int index=1;
		HSSFSheet sheet= workbook.createSheet("sheet"+index++);
		
		//设置表列宽---(列号，宽度)
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 8000);
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 8000);
		
		HSSFRow header=sheet.createRow(0);
		//产生标题列
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("代理商名称");
		header.createCell(2).setCellValue("代理商编号");
		header.createCell(3).setCellValue("合同名称");
		header.createCell(4).setCellValue("合同编号");
		header.createCell(5).setCellValue("卡序");
		header.createCell(6).setCellValue("卡号");
		header.createCell(7).setCellValue("创建时间");
		header.createCell(8).setCellValue("状态");
		
		//设置表单元的格式
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		//cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd"));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
		for(int i=0;i<9;i++){
			header.getCell(i).setCellStyle(cellStyle);
		}
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			Date date=new Date();
			String timeStamp=sdf.format(date);
			if(!dir.exists()){
				dir.mkdir();
			}
			file=new File(dir,fileName+"_"+timeStamp+".xls");
			
			//填充数据
			int rowNum=1;//行序号
			Map<Object,Object> map=null;
			for(Iterator<Map> iter=list.iterator();iter.hasNext();){
				map= iter.next();
				HSSFRow row=sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(map.get("id").toString());
				row.createCell(1).setCellValue(map.get("agentName").toString());
				row.createCell(2).setCellValue(map.get("agentCode").toString());
				row.createCell(3).setCellValue(map.get("contractName").toString());
				row.createCell(4).setCellValue(map.get("contractCode").toString());
				row.createCell(5).setCellValue(map.get("cardNo").toString());
				row.createCell(6).setCellValue(map.get("cardCode").toString());
				row.createCell(7).setCellValue(map.get("createDate").toString());
				row.createCell(8).setCellValue(map.get("status").toString());
				
				//设置单元格格式
					row.getCell(0).setCellStyle(cellStyle);
					row.getCell(1).setCellStyle(cellStyle);
					row.getCell(2).setCellStyle(cellStyle);
					row.getCell(3).setCellStyle(cellStyle);
					row.getCell(4).setCellStyle(cellStyle);
					row.getCell(5).setCellStyle(cellStyle);
					row.getCell(6).setCellStyle(cellStyle);
					row.getCell(7).setCellStyle(cellStyle);
					row.getCell(8).setCellStyle(cellStyle);
			}
				//将workbook写入文件
				FileOutputStream fos=new FileOutputStream(file);
				workbook.write(fos);
				fos.flush();
				fos.close();
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		
	}
	
	public Integer buildExcelDocumentForAgentDeveloper(List<Map> list,String fileName){
		HSSFWorkbook workbook=new HSSFWorkbook();
		//产生Excel表头
		int index=1;
		HSSFSheet sheet= workbook.createSheet("sheet"+index++);
		
		//设置表列宽---(列号，宽度)
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 8000);
		
		HSSFRow header=sheet.createRow(0);
		//产生标题列
		header.createCell(0).setCellValue("序号");
		header.createCell(1).setCellValue("用户昵称");
		header.createCell(2).setCellValue("联系电话");
		header.createCell(3).setCellValue("特权卡号");
		header.createCell(4).setCellValue("卡类型");
		header.createCell(5).setCellValue("注册时间");
		header.createCell(6).setCellValue("性别");
		header.createCell(7).setCellValue("年龄");
		
		//设置表单元的格式
		HSSFCellStyle cellStyle=workbook.createCellStyle();
		//cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd"));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
		for(int i=0;i<8;i++){
			header.getCell(i).setCellStyle(cellStyle);
		}
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			Date date=new Date();
			String timeStamp=sdf.format(date);
			if(!dir.exists()){
				dir.mkdir();
			}
			file=new File(dir,fileName+".xls");
			
			//填充数据
			int rowNum=1;//行序号
			Map<Object,Object> map=null;
			for(Iterator<Map> iter=list.iterator();iter.hasNext();){
				map= iter.next();
				HSSFRow row=sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(StringUtil.getString(map.get("id")));
				row.createCell(1).setCellValue(StringUtil.getString(map.get("nickName")));
				row.createCell(2).setCellValue(StringUtil.getString(map.get("phone")));
				row.createCell(3).setCellValue(StringUtil.getString(map.get("exchangeCard")));
				row.createCell(4).setCellValue(StringUtil.getString(map.get("cardType")));
				row.createCell(5).setCellValue(StringUtil.getString(map.get("createTime")));
				row.createCell(6).setCellValue(StringUtil.getString(map.get("sexCN")));
				row.createCell(7).setCellValue(StringUtil.getString(map.get("age")));
				
				//设置单元格格式
					row.getCell(0).setCellStyle(cellStyle);
					row.getCell(1).setCellStyle(cellStyle);
					row.getCell(2).setCellStyle(cellStyle);
					row.getCell(3).setCellStyle(cellStyle);
					row.getCell(4).setCellStyle(cellStyle);
					row.getCell(5).setCellStyle(cellStyle);
					row.getCell(6).setCellStyle(cellStyle);
					row.getCell(7).setCellStyle(cellStyle);
			}
				//将workbook写入文件
				FileOutputStream fos=new FileOutputStream(file);
				workbook.write(fos);
				fos.flush();
				fos.close();
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		
	}
	
	
	
	/**
	 * 创建工作簿对象
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Workbook createWorkbook(String filePath) throws FileNotFoundException, IOException {
		if(!StringUtil.isEmpty(filePath)){
			throw new IllegalArgumentException("参数错误");
		}
		if(filePath.trim().toLowerCase().endsWith("xls")){
			return new HSSFWorkbook(new FileInputStream(filePath));
		}else{
			throw new IllegalArgumentException("不支持除:xls以外的文件格式!!");
		}
	}
	
	public static Sheet getSheet(Workbook wb,String sheetName){
		return wb.getSheet(sheetName);
	}
	
	public static Sheet getSheet(Workbook wb,int index){
		return wb.getSheetAt(index);
	}
	
	public static List<Object[]> listFromSheet(Sheet sheet){
		int rowTotal = sheet.getPhysicalNumberOfRows()-1;//第一条记录为标题行，所以数据总记录要减去1
		if(logger.isDebugEnabled()){
			logger.debug("工作表"+sheet.getSheetName()+"共有"+rowTotal+"条记录.");
		}
		List<Object[]> list=new ArrayList<Object[]>();
		for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
			Row row=sheet.getRow(i);
			if(NullUtil.isNull(row)){
				continue;
			}
			//不能用row.getPhysicalNumberOfCells(),可能会有空cell导致索引溢出
			//使用row.getLastCellNum()至少可以保证索引不溢出,但会有很多null值,如果使用集合的话，就不说了
			Object[] cells=new Object[row.getLastCellNum()];
			for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
				Cell cell=row.getCell(j);
				if(NullUtil.isNull(cell)){
					continue;
				}
				cells[j]=getValueFromCell(cell);
			}
			list.add(cells);
		}
		return list;
	}
	
	/**
	 * 获取单元格内文本信息
	 * @param cell
	 * @return
	 */
	public static String getValueFromCell(Cell cell){
		if(NullUtil.isNull(cell)){
			if(logger.isDebugEnabled()){
				logger.debug("cell is null.");
			}
			return "";
		}
		String value=null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC://数字
				if(HSSFDateUtil.isCellDateFormatted(cell)){//如果是日期型
					value=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
				}else{
					value=String.valueOf(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING://字符串
				value=cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA://公式
				double numericValue=cell.getNumericCellValue();
				if(HSSFDateUtil.isValidExcelDate(numericValue)){//如果是日期类型
					value=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
				}else{
					value=String.valueOf(numericValue);
				}
				break;
			case Cell.CELL_TYPE_BLANK://空白
				value=StringUtils.EMPTY;
				break;
			case Cell.CELL_TYPE_BOOLEAN://Boolean
				value=String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR://Error 返回错误码
				value=String.valueOf(cell.getErrorCellValue());
				break;
			default:
				value=StringUtils.EMPTY;
				break;
		}
		//使用[]记录坐标
		return value;
	}
}
