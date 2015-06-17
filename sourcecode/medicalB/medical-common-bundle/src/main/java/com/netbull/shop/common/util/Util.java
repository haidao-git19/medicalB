package com.netbull.shop.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math.random.JDKRandomGenerator;
import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;

import com.netbull.shop.common.vo.Constant;

public class Util {

	 private static final int BUFFEREDSIZE = 1024;
	 
	  public static String[] split(String source, String div) {
		    int arynum = 0, intIdx = 0, intIdex = 0, div_length = div.length();
		    if (source.compareTo("") != 0) {
		      if (source.indexOf(div) != -1) {
		        intIdx = source.indexOf(div);
		        for (int intCount = 1; ; intCount++) {
		          if (source.indexOf(div, intIdx + div_length) != -1) {
		            intIdx = source.indexOf(div, intIdx + div_length);
		            arynum = intCount;
		          }
		          else {
		            arynum += 2;
		            break;
		          }
		        }
		      }
		      else {
		        arynum = 1;
		      }
		    }
		    else {
		      arynum = 0;

		    }
		    intIdx = 0;
		    intIdex = 0;
		    String[] returnStr = new String[arynum];

		    if (source.compareTo("") != 0) {
		      if (source.indexOf(div) != -1) {
		        intIdx = (int) source.indexOf(div);
		        returnStr[0] = (String) source.substring(0, intIdx);
		        for (int intCount = 1; ; intCount++) {
		          if (source.indexOf(div, intIdx + div_length) != -1) {
		            intIdex = (int) source.indexOf(div, intIdx + div_length);
		            returnStr[intCount] = (String) source.substring(intIdx + div_length,
		                intIdex);
		            intIdx = (int) source.indexOf(div, intIdx + div_length);
		          }
		          else {
		            returnStr[intCount] = (String) source.substring(intIdx + div_length,
		                source.length());
		            break;
		          }
		        }
		      }
		      else {
		        returnStr[0] = (String) source.substring(0, source.length());
		        return returnStr;
		      }
		    }
		    else {
		      return returnStr;
		    }
		    return returnStr;
		  }
	  public static String dealNull(String str) {
		    String returnstr = null;
		    if (str == null) {
		      returnstr = "";
		    }
		    else {
		      returnstr = str;
		    }
		    return returnstr;
		  }

	 public static Object dealNull(Object obj) {
		Object returnstr = null;
		if (obj == null) {
		  returnstr = (Object) ("");
		}
		else {
		  returnstr = obj;
		}
		return returnstr;
	}

		  static int dealEmpty(String s) {
		    s = dealNull(s);
		    if (s.equals("")) {
		      return 0;
		    }
		    return Integer.parseInt(s);
		  }
		  
	  public static String replace(String str, String substr, String restr) {
		    String[] tmp = split(str, substr);
		    String returnstr = null;
		    if (tmp.length != 0) {
		      returnstr = tmp[0];
		      for (int i = 0; i < tmp.length - 1; i++) {
		        returnstr = dealNull(returnstr) + restr + tmp[i + 1];
		      }
		    }
		    return dealNull(returnstr);
		  }
//	  public static boolean isAllowFile(String flag, String fileName) {
//		    boolean isAllow = false;
//		    if (flag.equals("pic")) {
//		      fileName = fileName.toLowerCase(Sys.getLocale());
//		      if (fileName.endsWith(".jpg") || fileName.endsWith(".gif")) {
//		        isAllow = true;
//		      }
//		    }
//		    if (flag.equals("flash")) {
//		      fileName = fileName.toLowerCase(Sys.getLocale());
//		      if (fileName.endsWith(".swf") ) {
//		        isAllow = true;
//		      }
//		    }
//		    if (flag.equals("media")) {
//		      fileName = fileName.toLowerCase(Sys.getLocale());
//		      if (fileName.endsWith(".wmv") || fileName.endsWith(".avi") ||fileName.endsWith(".asf") || fileName.endsWith(".mpg") || fileName.endsWith(".wma") || fileName.endsWith(".mp3") || fileName.endsWith(".wav")) {
//		        isAllow = true;
//		      }
//		    }
//		    if (flag.equals("real")) {
//		      fileName = fileName.toLowerCase(Sys.getLocale());
//		      if (fileName.endsWith(".rm") || fileName.endsWith(".rmvb") ) {
//		        isAllow = true;
//		      }
//		    }
//		    return isAllow;
//		  }

//		  public static String getFileExt(String fileName) {
//		    String fileExt = "";
//		    fileName = fileName.toLowerCase(Sys.getLocale());
//		    int index = fileName.lastIndexOf(".");
//		    fileExt = fileName.substring(index, fileName.length());
//		    return fileExt;
//		  }
		  public static long getaLongTime() {
			    return System.currentTimeMillis();
		  }
			
		  public static String fotmatDate7(Date myDate) {
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			    String strDate = formatter.format(myDate);
			    return strDate;
			  }
		  public static String fotmatDate8(Date myDate) {
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			    String strDate = formatter.format(myDate);
			    return strDate;
			  }
		public static String getDesc() {
			Random ran=new Random();
            int ranInt;
            do{
               ranInt = ran.nextInt(99999);
            }while (ranInt<10000);
            return String.valueOf(ranInt);
		}
		
		  public static String fotmatDate3(Date myDate) {
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String strDate = formatter.format(myDate);
			    return strDate;
			  }
			public static String getDesc2() {
	               Random ran=new Random();
	               int ranInt;
	               do{
	                  ranInt = ran.nextInt(99999999);
	               }while (ranInt<10000000);
			return String.valueOf(ranInt);
		}

	public static String getNumber() {
		String strM = String.valueOf(System.currentTimeMillis());
		return strM.substring(3, 13);
	}
			
			/**
			 * 小数点左移两�?
			 * 
			 * @param str
			 * @return
			 */
			public static String turnLeft(String str) {
				String returnstr = "";
				if (str.indexOf("-") == -1)// 如果不为负数
				{
					if (str.length() > 2)// 如果为百位数
					{
						returnstr = str.substring(0, str.length() - 2) + "."
								+ str.substring(str.length() - 2, str.length());// 直接左移两位
					} else if (str.length() == 2) {
						returnstr = "0." + str;
					} else {
						returnstr = "0.0" + str;
					}
				} else {
					if (str.length() > 3) {
						returnstr = str.substring(0, str.length() - 2) + "."
								+ str.substring(str.length() - 2, str.length());// 直接左移两位
					} else if (str.length() == 3) {
						returnstr = "-0." + str.substring(1, str.length());
					} else if (str.length() < 3) {
						returnstr = "-0.0" + str.substring(1, str.length());
					}
				}
				return returnstr;
			}

			/**
			 * 小数点右移两�?
			 * 
			 * @param str
			 * @return
			 */
			public static String turnRight(String str) {
				String returnstr = "";
				// 如果不为负数
				if (str.indexOf(".") == -1) {
					returnstr = str + "00";
				} else {
					returnstr = str.substring(0, str.indexOf("."));

					if (str.length() - str.indexOf(".") > 2) {
						returnstr = returnstr
								+ str.substring(str.indexOf(".") + 1,
										str.indexOf(".") + 3);
						if ((str.length() - (str.indexOf(".") + 3)) > 0) {
							returnstr = returnstr + "."
									+ str.substring(str.indexOf(".") + 3, str.length());
						}
					} else if (str.length() - str.indexOf(".") > 1) {
						returnstr = returnstr
								+ str.substring(str.indexOf(".") + 1,
										str.indexOf(".") + 2);
						returnstr = returnstr + "0";
					} else if (str.length() - str.indexOf(".") > 0) {
						returnstr = returnstr
								+ str.substring(str.indexOf(".") + 1,
										str.indexOf(".") + 1);
						returnstr = returnstr + "00";
					}

				}
				return returnstr;

			}
			
   /**
	 * 生成随机ID
	 * @return String
	 * fwh date:2009-08-30
	 */
	public static String getRandomID() {
		RandomData random = new RandomDataImpl(new JDKRandomGenerator());
		long min = 1000000L;
		long max = 9999999L;
		String str = "" + random.nextSecureLong(min, max)
				+ random.nextSecureLong(min, max);
		return str;
	}
	
	
	/**
	 * 获取去除格式符的字符�?
	 * Description�?
	 * @param str
	 * @return
	 * methodName：getNoFormatCharStr
	 * ClassName�?Util
	 */
	 public static String getNoFormatCharStr(String str){
		   Pattern p = Pattern.compile("\\t|\r");   
		   Matcher m = p.matcher(str);
		   String afterStr = m.replaceAll("");
		   return afterStr;
	 }
	 
//	 public static void main(String[] args) {
//		  try {
//			  unzip("F:\\workspace\\mboss\\mboss-webapp-bundle\\src\\main\\webapp\\broadcast\\media\\2191\\ppt\\30000329\\aa.rar", "F:\\workspace\\mboss\\mboss-webapp-bundle\\src\\main\\webapp\\broadcast\\media\\2191\\ppt\\30000329");
//		  } catch (IOException e) {
//		   e.printStackTrace();
//		  }
//		 }
	 
	/* *//**
	  * 解压zip或者rar包的内容到指定的目录下，可以处理其文件夹下包含子文件夹的情况
	  * @param zipFilename
	  *            要解压的zip或者rar包文件
	  * @param outputDirectory
	  *            解压后存放的目录
	  *//*
	 public static synchronized void unzip(String zipFilename, String outputDirectory)
	   throws Exception {
		File outFile = new File(new String(outputDirectory.getBytes(),Constant.FILE_CHARA_ENCODE));
		if (!outFile.exists()) {
			outFile.mkdirs();
		}

		ZipFile zipFile = new ZipFile(zipFilename,"gbk");
		System.out.println("zipFile:"+zipFile);
		Enumeration en = zipFile.getEntries();
		ZipEntry zipEntry = null;
		int fileNum =0;
		while (en.hasMoreElements()) {
			zipEntry = (ZipEntry) en.nextElement();
			if (zipEntry.isDirectory()) {
				// mkdir directory
				String dirName = new String(zipEntry.getName().getBytes(),Constant.FILE_CHARA_ENCODE);
				// System.out.println("=dirName is:=" + dirName + "=end=");
				dirName = dirName.substring(0, dirName.length() - 1);
				File f = new File(new String((outFile.getPath() + File.separator + dirName).getBytes(),Constant.FILE_CHARA_ENCODE));
				f.mkdirs();
			} else {
				// unzip file
				String strFilePath = outFile.getPath() + File.separator  + "slide-"+fileNum+".jpg";
				File f = new File(new String(strFilePath.getBytes(),Constant.FILE_CHARA_ENCODE));

				System.out.println("strFilePath:"+strFilePath);
				// the codes remedified by can_do on 2010-07-02 =begin=
				// /////begin/////
				// 判断文件不存在的话，就创建该文件所在文件夹的目录
				if (!f.exists()) {
					String[] arrFolderName = zipEntry.getName().split("/");
					String strRealFolder = "";
					for (int i = 0; i < (arrFolderName.length - 1); i++) {
						strRealFolder += arrFolderName[i] + File.separator;
					}
					strRealFolder = outFile.getPath() + File.separator
							+ strRealFolder;
					File tempDir = new File(new String(strRealFolder.getBytes(),Constant.FILE_CHARA_ENCODE));
					// 此处使用.mkdirs()方法，而不能用.mkdir()
					tempDir.mkdirs();
				}
				// /////end///
				// the codes remedified by can_do on 2010-07-02 =end=
				fileNum++;
				f.createNewFile();
				InputStream in = zipFile.getInputStream(zipEntry);
				FileOutputStream out = new FileOutputStream(f);
				try {
					int c;
					byte[] by = new byte[BUFFEREDSIZE];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					// out.flush();
				} catch (Exception e) {
					System.out.println("出现问题:"+e.getMessage());
					throw e;
				} finally {
					out.close();
					in.close();
				}
			}
		}
	 }
	 */
	 
	 public static boolean find(String matcher, String text)
	  {
	    if ((matcher == null) || (text == null)) {
	      return false;
	    }

	    Pattern p = Pattern.compile(matcher);
	    Matcher m = p.matcher(text);
	    return m.find();
	  }
	 
//	 /**
//	 * 生成随机ID
//	 * @return String
//	 * fwh date:2009-08-30
//	 */
//	public static String getRandomID1() {
//		RandomData random = new RandomDataImpl(new JDKRandomGenerator());
//		long min = 1000L;
//		long max = 9999L;
//		String str = "" + random.nextSecureLong(min, max)
//				+ random.nextSecureLong(min, max);
//		return str;
//	}
//	/**
//	 * test
//	 * @param arge
//	 */
//	public static void main(String[] arge) {
//		for (int i = 0; i < 10; i++) {
//			System.out.println(getRandomID1());
//			Thread tr = new Thread();
//			try {
//				tr.sleep((long) 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
