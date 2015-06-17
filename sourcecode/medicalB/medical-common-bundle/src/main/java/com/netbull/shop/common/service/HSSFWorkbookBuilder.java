package com.netbull.shop.common.service;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.netbull.shop.common.log.LoggerFactory;


public class HSSFWorkbookBuilder {
	
	private static Logger logger = LoggerFactory.getLogger(HSSFWorkbookBuilder.class);
	/**
	 * 生成Excel模型对象
	 * 
	 * @param title
	 *            大标题
	 * @param heads
	 *            表头
	 * @param cellValues
	 *            单元格数据, 二维结构
	 * @return
	 */
	public HSSFWorkbook buildHSSFWorkbook() {
		HSSFWorkbook wb = new HSSFWorkbook();

		//标题样式
		HSSFFont fontTitle = wb.createFont();
		HSSFCellStyle styleTitle = wb.createCellStyle();
		fontTitle.setFontHeightInPoints((short) 12);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//黑体居中
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTitle.setFont(fontTitle);
		
		//表头样式
		HSSFFont fontHeader = wb.createFont();
		HSSFCellStyle styleHeader = wb.createCellStyle();
		fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//黑体居中
		styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleHeader.setFont(fontHeader);

		
		int sheetCount = cellValues.size() / sheeRowCount;
		if (cellValues.size() % sheeRowCount != 0) {
			sheetCount++;
		}

		HSSFSheet sh;
		for (int i = 0; i < sheetCount; i++) {
			sh = wb.createSheet("sheet-" + (i + 1));
			currentRowIndex = 0;
			this.addTitleRow(sh, styleTitle);
			this.addHeadRow(sh, styleHeader);
			this.addValueRows(sh, null, i * sheeRowCount, (i + 1)
					* sheeRowCount);
		}

		return wb;
	}

	private void addTitleRow(HSSFSheet sheet, HSSFCellStyle style) {
		// 标题
		if (null != title && (title = title.trim()).length() > 0) {
			int countT = currentRowIndex;
			HSSFRow row = sheet.createRow(countT);
			sheet.addMergedRegion(new CellRangeAddress(countT, countT, 0,
					(heads == null || heads.size() < 1) ? 1 : heads.size()-1));
			HSSFCell cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(title);
			cell.setCellValue(text);
			currentRowIndex = ++countT;
		}
	}

	private void addHeadRow(HSSFSheet sheet, HSSFCellStyle style) {
		// 表头
		if (null != heads && heads.size() > 0) {
			int countT = currentRowIndex;
			int f = 0;
			HSSFRow row = sheet.createRow(countT);
			while (f < heads.size()) {
				HSSFCell cell = row.createCell(f);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				if (null != style) {
					cell.setCellStyle(style);
				}
				HSSFRichTextString text = new HSSFRichTextString(null == heads.get(f) ? "" : heads.get(f));
				cell.setCellValue(text);
				f++;
			}
			currentRowIndex = ++countT;
		}
	}

	public void addValueRows(HSSFSheet sheet, HSSFCellStyle style) {
		this.addValueRows(sheet, style, 0, cellValues.size());
	}

	/**
	 * 
	 * @param sheet
	 * @param style
	 * @param beginIndex
	 *            开始下标(包含beginIndex)
	 * @param endIndex
	 *            结束下标(不包含endIndex)
	 * 
	 */
	private void addValueRows(HSSFSheet sheet, HSSFCellStyle style,
			int beginIndex, int endIndex) {
		// 单元格数据
		if (cellValues != null) {
			int countT = currentRowIndex;

			for (int index = beginIndex; index < endIndex
					&& index < cellValues.size(); index++) {
				int h = 0;
				
				HSSFRow row = sheet.createRow(countT);
				List cellRow = cellValues.get(index);
				for (Object cellValue : cellRow) {
					HSSFCell cell = row.createCell(h);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					if (null != style) {
						cell.setCellStyle(style);
					}
					HSSFRichTextString text = new HSSFRichTextString(cellValue == null ? "" : cellValue
							.toString());
					cell.setCellValue(text);
					h++;
				}
				countT++;
			}
			currentRowIndex = ++countT;
		}
	}
	
	/**
	 * txt 导出帮助类
	 * 
	 * @param dataSource
	 * @param file
	 */
	public  String txtExportString() {
		List<String> listData = null;
		StringBuilder row = new StringBuilder();
		//项目
		if(heads!=null){
			for (String item : heads) {
				row.append(item);
				int length = 0;
				try {
					length = item.getBytes("GBK").length;
				} catch (UnsupportedEncodingException e) {
					logger.error("txt导出异常1",e);
				}
				if(length < 20){
					int t = 20-length;
					for(int i=0;i<t;i++){
						row.append(" ");
					}
				}
			}
			row.append(System.getProperty("line.separator"));
		}
		
		//数据
		if (cellValues != null) {
			for (int j = 0; j < cellValues.size(); j++) {
				listData = cellValues.get(j);
				for (Object key : listData) {
					String dataStr=key+"";
					row.append(dataStr);
					int length = 0;
					try {
						length = dataStr.getBytes("GBK").length;
					} catch (UnsupportedEncodingException e) {
						logger.error("txt导出异常2",e);
					}
					if(length < 20){
						int t = 20-length;
						for(int i=0;i<t;i++){
							row.append(" ");
						}
					}
				
				}
				row.append(System.getProperty("line.separator"));
			}
			
		}
		return row.toString();
	}

	/**
	 * 生成CSV模型, 即逗号分隔的字符串
	 * 
	 * @param title
	 *            大标题
	 * @param heads
	 *            表头
	 * @param cellValues
	 *            单元格数据, 二维结构
	 * @return
	 */
	public String getCSVString(String title, List<String> heads,
			List<List<Object>> cellValues) {
		StringBuilder rs = new StringBuilder();

		// 标题
		if (null != title && (title = title.trim()).length() > 0) {
			rs.append(title);
			rs.append("\n");
		}

		// 表头
		if (null != heads && heads.size() > 0) {
			for (String s : heads) {
				rs.append(s);
				rs.append(",");
			}
			rs.append("\n");
		}
		// 单元格数据
		if (cellValues != null) {
			for (List<Object> cellRow : cellValues) {
				for (Object cellValue : cellRow) {
					rs.append(cellValue == null ? "" : cellValue.toString());
					rs.append(",");
				}
				rs.append("\n");
			}
		}

		return rs.toString();
	}
	
	/**
	 * 得到单元格内容并转换为字符串
	 * 
	 * @param hssFCell
	 *            单元格
	 * @return 单元格内容
	 */
	public String ConvertExcelToString(HSSFCell hssFCell) {
		String value = "";
		if (hssFCell != null) {
			switch (hssFCell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				value = hssFCell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("####################");
				value = df.format(hssFCell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				value = hssFCell.getCellFormula();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = Boolean.toString(hssFCell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
			default:
				break;
			}
		}
		return value.trim();
	}
	
	public void exportMember(List<Map> dataSource,List<String> keys,List<String> items){

			//设置标题行
			List<String> heads = new ArrayList<String>();
			int f=0;
			//表头
			while(f<items.size()){
			heads.add(items.get(f));
			f++;
			}
			
			setHeads(heads);
			
			if (dataSource != null) {
				cellValues = new ArrayList<List>();
				for (Map detail : dataSource) {
					List row = new ArrayList();
					for(String key:keys){
						row.add(detail.get(key));
					}
					cellValues.add(row);
					}
			
			setCellValues(cellValues);
		}
	}
	
	private int currentRowIndex = 0; 

	private String title;// 标题

	private List<String> heads;// 表头

	private List<List> cellValues;// 单元格值

	private int sheeRowCount = DEFAULT_SHEET_ROW_COUNT;// 每个工作表的行数(值的行数)

	public static final int DEFAULT_SHEET_ROW_COUNT = 50000;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getHeads() {
		return heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

	public List<List> getCellValues() {
		return cellValues;
	}

	public void setCellValues(List<List> cellValues) {
		this.cellValues = cellValues;
	}
	/**
	 * 先调用 {@link #setHeads(List)}
	 * @param mapList
	 */
	public void inflateCellValuesFromMap(List<Map> mapList) {
		if(null == mapList){
			return;
		}
		List<List> values = new ArrayList<List>();
		List row;
		for(Map m : mapList){
			row = new ArrayList();
			for(Object k : this.heads){
				row.add(m.get(k));
			}
			values.add(row);
		}
		this.cellValues = values;
	}

	public int getSheeRowCount() {
		return sheeRowCount;
	}

	public void setSheeRowCount(int sheeRowCount) {
		this.sheeRowCount = sheeRowCount;
	}
	
	public static void main(String[] args) throws Exception{
		
	}
}
