package com.glodon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.util.Base64Utils;


/**
 * FileUtils
 * 
 * @author mz
 *
 *         2015年1月30日 下午4:20:31
 */
public class FileUtils {

	/**
	 * 把客户端传过来的图片保存在本地
	 * 
	 * @param imageStr
	 * @param imageName
	 * @return
	 */
	public static boolean saveImage(String path, String imageStr,
			String imageName) {
		FileOutputStream fos = null;
		try {
			// 对android传过来的图片字符串进行解码
			byte[] buffer = Base64Utils.decodeFromString(imageStr);
			File destDir = new File(path);
			if (!destDir.exists())
				destDir.mkdir();
			// 保存图片
			fos = new FileOutputStream(new File(destDir, imageName));
			fos.write(buffer);
			fos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 从本地取出图片并返回base64传
	 * 
	 * @param imageName
	 * @return
	 */
	public static String getImage(String path, String imageName) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(path + imageName);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = fis.read(buffer)) >= 0) {
				baos.write(buffer, 0, count);
			}
			String imageStr = new String(Base64Utils.encode(baos.toByteArray())); // 进行Base64编码
			return imageStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
