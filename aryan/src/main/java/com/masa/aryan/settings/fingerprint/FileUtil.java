package com.masa.aryan.settings.fingerprint;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @author maxw
 * 
 */
public class FileUtil {

	private static final String TAG = "FileOperation";
	private static final boolean D = true;
	/**
	 * 默认缓存大小
	 */
	private static final int DefaultBufferSize = 100;

	/**
	 * 以字节为单位读取文件，常用于读取二进制文件，如图片、声音、影像等文件

	 *            文件名
	 * @return 字节数组
	 */
	public static byte[] readFileByBytes(File file) {

		if (D)
			Log.e(TAG, "以字节为单位读取文件");

		BufferedInputStream bufferedInputStream = null;
		byte[] tempBuf = new byte[DefaultBufferSize];
		int byteRead = 0;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(
					file));
			while ((byteRead = bufferedInputStream.read(tempBuf)) != -1) {
				byteArrayOutputStream.write(tempBuf, 0, byteRead);
			}
			bufferedInputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 以字节为单位读取文件，常用于读取assets目录下的文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return 字节数组
	 */
	public static byte[] readFileByBytes(String fileName) {

		if (D)
			Log.e(TAG, "以字节为单位读取文件，常用于读取assets目录下的文件");

		BufferedInputStream bufferedInputStream = null;
		byte[] tempBuf = new byte[DefaultBufferSize];
		int byteRead = 0;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			bufferedInputStream = new BufferedInputStream(
					FileUtil.class.getResourceAsStream(fileName));
			while ((byteRead = bufferedInputStream.read(tempBuf)) != -1) {
				byteArrayOutputStream.write(tempBuf, 0, byteRead);
			}
			bufferedInputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 以字节为单位写文件，常用于生成二进制文件
	 * 
	 * @param file
	 *            文件
	 * @param contentBytes
	 *            要写入文件的字节数组
	 * @param append
	 *            是否以追加的方式写文件
	 */
	public static void writeFileByBytes(File file, byte[] contentBytes,
                                        boolean append) {
		if (D)
			Log.e(TAG, "以字节为单位写文件");

		BufferedOutputStream bufferedOutputStream = null;

		try {
			if(file.exists()){
				file.delete();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(file, append));
			bufferedOutputStream.write(contentBytes);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读取文本、数字等类型的文件
	 *            文件名
	 * @return 字符数组
	 */
	public static char[] readFileByChars(File file) {

		BufferedReader bufferedReader = null;
		CharArrayWriter charArrayWriter = new CharArrayWriter();
		char[] tempBuf = new char[DefaultBufferSize];
		int charRead = 0;

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			while ((charRead = bufferedReader.read(tempBuf)) != -1) {
				charArrayWriter.write(tempBuf, 0, charRead);
			}
			bufferedReader.close();
			return charArrayWriter.toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeFileByChars(File file, char[] content,
                                        boolean append) {

		BufferedWriter bufferedWriter = null;

		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file)));
			bufferedWriter.write(content);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 以行为单位读取文件，常用于读取以行为单位为格式的文件
	 *
	 *            文件名
	 * @return 文件内容
	 */
	public static String readFileByLines(File file) {

		if (file == null || !file.exists())
			return null;
		String charset = getFileCharset(file);
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuilder.append(tempString).append("\r\n");
			}
			bufferedReader.close();
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读取以行为单位为格式的文件
	 *
	 *            文件名
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String readFileByLines2(File file) throws IOException {

		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {
				stringBuilder.append(tempString).append("\n");
			}
			bufferedReader.close();
			return stringBuilder.toString();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以字符流方式写文件，常用于保存文本文件
	 */
	public static boolean writeFileByString(File file, String content,
                                         boolean append) {

		BufferedWriter bufferedWriter = null;

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, append)));
			bufferedWriter.write(content);
			bufferedWriter.flush();
			bufferedWriter.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * 拷贝文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param dstFile
	 *            目标文件
	 * @return
	 */
	public static boolean copyFileByBytes(File srcFile, File dstFile) {

		int byteRead = 0;

		// 目标文件已经存在就不进行复制
		if (dstFile.exists()) {
			return true;
		} else {
			if (srcFile.exists()) {
				if (D)
					System.out.println("Begin to move file!");

				try {
					InputStream is = new FileInputStream(srcFile);
					FileOutputStream fos = new FileOutputStream(dstFile);

					byte[] buffer = new byte[4096];
					while ((byteRead = is.read(buffer)) != -1) {
						fos.write(buffer, 0, byteRead);
					}
					if (D)
						System.out.println("Move file end!");

				} catch (Exception e) {
					e.printStackTrace();
					if (D)
						Log.i("TfcardDetect", "文件移动失败");

					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件编码
	 * 
	 * @param file
	 *            文件
	 * @return 编码
	 */
	public static String getFileCharset(File file) {

		String fileCharset = "UTF-8";

		return fileCharset;
	}

	/********************************************************/
	// bt字节参考量
	public static final long SIZE_BT = 1024L;
	// KB字节参考量
	public static final long SIZE_KB = SIZE_BT * 1024L;
	// MB字节参考量
	public static final long SIZE_MB = SIZE_KB * 1024L;
	// GB字节参考量
	public static final long SIZE_GB = SIZE_MB * 1024L;
	// TB字节参考量
	public static final long SIZE_TB = SIZE_GB * 1024L;
	public static final int SACLE = 2;

	/**
	 * 以用户可读方式获取文件大小
	 * 
	 * @param size
	 * @return
	 */
	public static String getFileSize(long size) {
		if (size >= 0 && size < SIZE_BT) {
			return size + "B";
		} else if (size >= SIZE_BT && size < SIZE_KB) {
			return size / SIZE_BT + "KB";
		} else if (size >= SIZE_KB && size < SIZE_MB) {
			return size / SIZE_KB + "MB";
		} else if (size >= SIZE_MB && size < SIZE_GB) {
			BigDecimal longs = new BigDecimal(Double.valueOf(size + "")
					.toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			return result + "GB";
		} else {
			BigDecimal longs = new BigDecimal(Double.valueOf(size + "")
					.toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			return result + "TB";
		}
	}

	/*************************************************/
	public static List<String> getFileXml() {
		// String sdPath = Environment.getExternalStorageDirectory().getPath();
		String path = "/newland/system/parameters.xml";
		// ParseXmlData parseData;
		List<String> list = new ArrayList<String>();
		File xmlFile = new File(path);
		if (xmlFile.exists()) {
			InputStream slideInputStream;
			try {
				slideInputStream = new FileInputStream(path);
				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				xpp.setInput(slideInputStream, "UTF-8");
				int eventType = xpp.getEventType();
//				int j = 0;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_DOCUMENT) {

					} else if (eventType == XmlPullParser.START_TAG) {
						String startName = xpp.getName();

						int count = xpp.getAttributeCount();
						for (int i = 0; i < count; i++) {
							String name = xpp.getAttributeName(i);
							String value = xpp.getAttributeValue(i);
							if (name.equals("exist") && value.equals("no")) {
								
								list.add(getName(xpp.getAttributeValue(i - 1)));
								// mString[j]=xpp.getAttributeValue(i-1);
//								j++;
							}
						}
					}
					eventType = xpp.next();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/***********************************************************************/
	public static String getName(String attributevalue){
		String name = null;
		if(attributevalue.equals("调制解调器"))
			name="Modem";
		else if(attributevalue.equals("前摄像头"))
			name="照相机";
		else if(attributevalue.equals("后摄像头"))
			name="后摄像头";
//		if(attributevalue.equals("光传感器"))
//			name="Modem";
		else if(attributevalue.equals("USB接口"))
			name="USB";
		else if(attributevalue.equals("LCD屏"))
			name="液晶";
		else if(attributevalue.equals("触摸屏"))
			name="触摸屏";
		else if(attributevalue.equals("蓝牙"))
			name="蓝牙";
		else if(attributevalue.equals("HDMI接口"))
			name="HDMI";
		else if(attributevalue.equals("音频"))
			name="音频";
		else if(attributevalue.equals("以太网"))
			name="以太网";
		else if(attributevalue.equals("WIFI"))
			name="WIFI";
		else if(attributevalue.equals("移动网络"))
			name="3G";
		else if(attributevalue.equals("TF卡"))
			name="SD卡";
		else if(attributevalue.equals("外接串口"))
			name="串口";
		else if(attributevalue.equals("射频卡座"))
			name="射频卡";
		else if(attributevalue.equals("IC卡座"))
			name="IC卡";
		else if(attributevalue.equals("SAM卡座"))
			name="SAM卡";
		else if(attributevalue.equals("磁条卡座"))
			name="磁卡";
		else if(attributevalue.equals("LED指示灯"))
			name="LED灯";
		else if(attributevalue.equals("密码键盘"))
			name="键盘";
		else if(attributevalue.equals("蜂鸣器"))
			name="蜂鸣器";
		else if(attributevalue.equals("打印机"))
			name="打印";
		else if(attributevalue.equals("钱箱"))
			name="钱箱";
		return name;
		
	}
}
