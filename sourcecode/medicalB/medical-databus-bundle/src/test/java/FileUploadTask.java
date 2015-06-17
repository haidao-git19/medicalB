

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 文件上传任务
 * 
 * @author user
 * 
 */
public class FileUploadTask {

	static HttpURLConnection connection = null;
	static DataOutputStream outputStream = null;
	static DataInputStream inputStream = null;

	static String dialgMessag = "数据上传中......";

	static String pathToOurFile = "mobile2.jpg";

	static String lineEnd = "\r\n";
	static String twoHyphens = "--";
	static String boundary = "*****";
	
	static File uploadFile = null;
	static long totalSize = 0;
	static String type = "";
	static String userid = "2012332";
	static String musicid = "332222";
	static String musicName = "邵婷";
	static String singername = "";

	protected static void doInBackground() {

		long length = 0;
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 256 * 1024;// 256KB

		try {
			FileInputStream fileInputStream = new FileInputStream(new File("G:\\mobile2.jpg"));

			URL url = new URL("http://localhost:8086/dbs/anon/upload");
			connection = (HttpURLConnection) url.openConnection();

			// Set size of every block for post
			connection.setChunkedStreamingMode(256 * 1024);// 256KB

			// Allow Inputs & Outputs
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// Enable POST method
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);

			outputStream = new DataOutputStream(connection.getOutputStream());

			// 发送userid(传参)
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"userid\""
							+ lineEnd);
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(userid);
			outputStream.writeBytes(lineEnd);

			// 发送userid(传参)
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"musicid\""
							+ lineEnd);
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(musicid);
			outputStream.writeBytes(lineEnd);

			// 发送type(传参)
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"type\""
							+ lineEnd);
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(type);
			outputStream.writeBytes(lineEnd);

			// 发送type(传参)
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"musicname\""
							+ lineEnd);
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(URLEncoder.encode(musicName,"UTF-8"));
			outputStream.writeBytes(lineEnd);

			// 发送type(传参)
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"singername\""
							+ lineEnd);
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(URLEncoder.encode(singername,"UTF-8"));
			outputStream.writeBytes(lineEnd);

			// 发送文件
			outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			outputStream
					.writeBytes("Content-Disposition: form-data; name=\"upload\";filename=\""
							+ pathToOurFile + "\"" + lineEnd);
			outputStream.writeBytes(lineEnd);

			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// Read file
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				// System.out.println("bytesRead=" + bytesRead);
				outputStream.write(buffer, 0, bufferSize);
				length += bufferSize;

				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			}
			outputStream.writeBytes(lineEnd);
			outputStream.writeBytes(twoHyphens + boundary + twoHyphens
					+ lineEnd);

			fileInputStream.close();
			outputStream.flush();
			outputStream.close();

			InputStream inputStream = connection.getInputStream();
			while (inputStream.available() < 0) {

			}

			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader reader = new BufferedReader(inputStreamReader);// 读字符串用的。
			String result = "";
			String inputLine = null;
			// 使用循环来读取获得的数据，把数据都村到result中了
			while (((inputLine = reader.readLine()) != null)) {
				// 我们在每一行后面加上一个"\n"来换行
				result += inputLine + "\n";
			}
			reader.close();// 关闭输入流
			System.out.println(result);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		doInBackground();
	}
}