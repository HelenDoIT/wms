// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileUploadUtil.java

package com._520it.wms.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.UUID;

public class FileUploadUtil
{

	public static final String suffix = "_small";

	public FileUploadUtil()
	{
	}

	public static String uploadFile(File file, String fileName)
		throws Exception
	{
		String uuid = UUID.randomUUID().toString();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		fileName = (new StringBuilder()).append(uuid).append(fileType).toString();
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		File targetFile = new File(path, fileName);
		FileUtils.copyFile(file, targetFile);
		String smallImg = (new StringBuilder()).append(uuid).append("_small").append(fileType).toString();
		File smallTargetFile = new File(path, smallImg);
		Thumbnails.of(new File[] {
			targetFile
		}).scale(0.40000000596046448D).toFile(smallTargetFile);
		return (new StringBuilder()).append("/upload/").append(fileName).toString();
	}

	public static void deleteFile(String imagePath)
	{
		String path = (new StringBuilder()).append(ServletActionContext.getServletContext().getRealPath("/")).append(imagePath).toString();
		File file = new File(path);
		if (file.exists())
			file.delete();
		path = (new StringBuilder()).append(ServletActionContext.getServletContext().getRealPath("/")).append(imagePath.substring(0, imagePath.indexOf("."))).append("_small").append(imagePath.substring(imagePath.indexOf("."))).toString();
		file = new File(path);
		if (file.exists())
			file.delete();
	}
}
