package com.netbull.shop.common.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import com.netbull.shop.common.vo.OperLogVO;
import com.netbull.shop.common.config.ServerInfo;
import com.netbull.shop.common.log.LoggerFactory;


public class StatDataToDBTimerTask {

	private static Logger logger = LoggerFactory
			.getLogger(StatDataToDBTimerTask.class);

	private OperLogService operLogService = null;

	public void setOperLogService(OperLogService operLogService) {
		this.operLogService = operLogService;
	}

	public void run() {

		System.out.println("------------------StatDataToDBTimerTask------");
		// 获取统计日志文件的位置
		String baseLogPath = ServerInfo.getSystemWorkPath()
				+ ServerInfo.FILE_SEP + "log" + ServerInfo.FILE_SEP;

		// 登录访问日志位置
		String visitLogPath = baseLogPath + "aopLog" + ServerInfo.FILE_SEP;

		// 登录访问日志入库
		try {
			insertVisitLog(visitLogPath);
		} catch (Exception e) {
			logger.info("StatDataToDBTimerTask类的run（）的insertVisitLog发生错误", e);
		}

	}

	private void insertVisitLog(String logPath) throws Exception {
		logger.info("登录访问日志文件入库操作开始");
		logger.info("登录访问日志文件的目录为" + logPath);

		// 取得文件目录
		File logDir = new File(logPath);

		// 如果此目录存在
		if (logDir.exists()) {
			// 取得此目录下的所有文件
			File[] logFiles = logDir.listFiles();

			int fileNum = 0; // 文件个数

			for (int i = 0; i < logFiles.length; i++) {
				// 取得文件名
				String logFileName = logFiles[i].getName();

				if (logFileName.lastIndexOf("bak") != -1) {
					fileNum++;
					logger.info("开始将第" + fileNum + "个登录访问日志文件入库，文件名为"
							+ logFileName);

					try {
						int recordNum = insertVisitService(logPath + logFileName);
						logger.info("登录访问日志文件" + logFileName + "入库成功，共"
								+ recordNum + "条记录");

						// 将日志文件移动到备份文件夹中
						bakLogFile(logPath, logFileName);
					} catch (Exception e) {

						logger.info(
								"登录访问日志文件"
										+ logFileName
										+ "入库失败,第"
										+ e.getMessage()
										+ "条记录有问题,此日志记录数据已经回滚，请手工将此日志文件入库，错误原因为",
								e);
					}
				}

			}

		}
		logger.info("登录访问日志文件入库操作结束");
	}
	
	/**
	 * 添加访问日志记录到表tb_ems_sys_log中；
	 * @return
	 * @throws Exception
	 */
	public int insertVisitService(String fileName) throws Exception {
		File file = new File(fileName);
		int recordNum = 0; // 记录的个数
		BufferedReader br = null;
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), "gbk");
			br = new BufferedReader(read);
			String lineData = br.readLine();

			while (lineData != null) {
				recordNum++;

				String[] tmp = lineData.split("%,_%");

				// 登陆成功长度为5
				if (tmp.length == 6 ) {
					String loginCode = tmp[1].trim(); //
					String ip = tmp[2].trim(); // 客户品牌类型（10位）
					String operationType = tmp[3].trim(); // 登录方式（5位）
					String operationDesc = tmp[4].trim(); // 登录类型（10位）
					String mDateTime = tmp[5].trim(); // 登录类型（10位）
					

					OperLogVO operLogVO = new OperLogVO();
				
					operLogVO.setLoginCode(loginCode!=null&&!"".equals(loginCode)?loginCode:"");
					operLogVO.setIp(ip);
					operLogVO.setOperationType(operationType);
					operLogVO.setOperationDesc(operationDesc);
					operLogVO.setmDateTime(mDateTime);
					
					operLogService.addOperLog(operLogVO);
				}

				lineData = br.readLine();

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(String.valueOf(recordNum), e);
		} finally {
			br.close();
			read.close();
		}
		return recordNum;
	}

	// 备份日志文件
	private void bakLogFile(String logPath, String logFileName)
			throws Exception {
		// 获取统计日志文件备份的位置
		String logBakPath = ServerInfo.getSystemWorkPath()
				+ ServerInfo.FILE_SEP + "log_bak" + ServerInfo.FILE_SEP;
		File oldFile = new File(logPath + logFileName);
		File tempFile = new File(logBakPath);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		File fnew = new File(logBakPath + logFileName);

		oldFile.renameTo(fnew);
	}

	public static void main(String[] args) throws Exception {
		StatDataToDBTimerTask task = new StatDataToDBTimerTask();
		task.insertVisitService("C:\\Users\\elvis\\Desktop\\ems\\ems\\ems-webapp-bundle\\servers\\ems\\log\\aop\\aopLog.log.2011-09-25-16.bak");
	}
}
