package com.zqsign.common.utils.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.http.entity.ContentType;

/**
 * 文件元数据。
 * @author zzk
 * 2017年10月26日下午4:01:28
 */
public class FileItem implements Serializable{

	//上传文件时候的字段名称
	private String fieldName;
	//文件名称
	private String fileName;
	//文件类型
	private ContentType mimeType = ContentType.DEFAULT_BINARY;
	private byte[] content;
	private File file;

    /**
     * 基于字段名称和文件的构造器
     * 
     * @param fileName 文件名
     * @param content 文件字节流
     */
	public FileItem(String fieldName, File file) {
		this.fieldName = fieldName;
		this.file = file;
		init(fieldName, file);
	}
	/**
     * 基于字段名称和文件路径的构造器
     * 
     * @param fileName 文件名
     * @param content 文件字节流
     */
	public FileItem(String fieldName, String filePath) {
		this.fieldName = fieldName;
		this.file = new File(filePath);
		init(fieldName, file);
	}
	/**
	 * 基于字段名称、文件名称、文件内容的构造器
	 * @param fieldName
	 * @param fileName
	 * @param content
	 */
	public FileItem(String fieldName, String fileName, byte[] content) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.content = content;
	}
	/**
	 * 获取文件名称
	 * @return
	 * @auther zzk
	 * 2017年10月26日下午4:05:35
	 */
	public String getFileName() {
		if (this.fileName == null && this.file != null && this.file.exists()) {
			this.fileName = file.getName();
		}
		return this.fileName;
	}
	/**
	 * 获取文件上传的字段名
	 * @return
	 * @auther zzk
	 * 2017年10月26日下午4:05:20
	 */
	public String getFieldName() {
		return this.fieldName;
	}
	public ContentType getMimeType() {
		return mimeType;
	}

	public byte[] getContent() throws IOException {
		if (this.content == null && this.file != null && this.file.exists()) {
			InputStream in = null;
			ByteArrayOutputStream out = null;

			try {
				in = new FileInputStream(this.file);
				out = new ByteArrayOutputStream();
				int ch;
				while ((ch = in.read()) != -1) {
					out.write(ch);
				}
				this.content = out.toByteArray();
			} finally {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		}
		return this.content;
	}
	
	private void init(String fieldName,File file){
		this.fieldName = fieldName;
		this.file = file;
		this.fileName = file.getName();
	}

}
