package com.yhy.hzzll.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.lidroid.xutils.http.client.multipart.MIME;

import android.util.Log;
import android.webkit.MimeTypeMap;

/**
 * 文件处理
 * 
 * @author Yang
 * 
 */
public class FileUtils {

	/**
	 * 递归删除文件和文件夹
	 * 
	 * @param file
	 *            要删除的根目录
	 */
	public static void DeleteFile(File file) {
		if (file.exists() == false) {
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					DeleteFile(f);
				}
				file.delete();
			}
		}
	}

	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static String getAutoFileOrFilesSize(String filePath) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("获取文件大小", "获取失败!");
		}
		return FormetFileSize(blockSize);
	}
	
	/**
	 * 调用此方法自动计算指定文件或指定文件夹的大小 long值
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static long getAutoFilesSize(String filePath) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("获取文件大小", "获取失败!");
		}
		return blockSize;
	}

	/**
	 * 获取指定文件夹
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
			Log.e("获取文件大小", "文件不存在!");
		}
		return size;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		String wrongSize = "0B";
		if (fileS == 0) {
			return wrongSize;
		}
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}

	/**
	 * 
	 * @return void
	 * @throws IOException
	 * @throws
	 * @since CodingExample　Ver 1.1
	 */
	public static void upLoadByCommonPost(String uploadUrl, String path)
			throws IOException {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		URL url = new URL(uploadUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();
		httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
		// 允许输入输出流
		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setUseCaches(false);
		// 使用POST方法
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
		httpURLConnection.setRequestProperty("Charset", "UTF-8");
		httpURLConnection.setRequestProperty("Content-Type",
				"multipart/form-data;boundary=" + boundary);

		DataOutputStream dos = new DataOutputStream(
				httpURLConnection.getOutputStream());
		dos.writeBytes(twoHyphens + boundary + end);
		dos.writeBytes("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
				+ path.substring(path.lastIndexOf("/") + 1) + "\"" + end);
		dos.writeBytes(end);

		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[8192]; // 8k
		int count = 0;
		// 读取文件
		while ((count = fis.read(buffer)) != -1) {
			dos.write(buffer, 0, count);
		}
		fis.close();
		dos.writeBytes(end);
		dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
		dos.flush();
		InputStream is = httpURLConnection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String result = br.readLine();
		Log.e("result", result);
		dos.close();
		is.close();
	}


	   public static  String  renameTo(File file) {
	  
	    String filename=file.getAbsolutePath();
	    if(filename.indexOf(".")>=0)
	    {
	      filename = filename.substring(0, filename.lastIndexOf("."));
	    }
	    file.renameTo(new File(filename+".jpg"));
		return filename;
	  }
	
	public static String getMimeType(String url) {
		
//		 File file = new File(url);
//		 String filename=file.getAbsolutePath();
//		    if(filename.indexOf(".")>=0)
//		    {
//		      filename = filename.substring(0, filename.lastIndexOf("."));
//		    }
//		    file.renameTo(new File(filename+".jpeg"));
//		    
//		 String urll =    file.getPath();
		
		
		String type = null ;
		String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		Log.e("++++++++++++++++", extension);
		if (extension != null) {
			MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension);
		}
		return type;
	}
}
