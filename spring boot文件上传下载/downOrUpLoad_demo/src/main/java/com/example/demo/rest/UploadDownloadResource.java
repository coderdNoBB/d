package com.example.demo.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.rest.response.Response;
import com.example.demo.util.ExcelUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UploadDownloadResource {

	@PostMapping("/upload")
	public Response upload (@RequestParam(value="file",required=false) MultipartFile file){
		File fileLocal = multipartToFile(file);
		//TODO 解释文件并保存到数据库
		FileUtils.deleteQuietly(fileLocal);
		return Response.success();
	}
	
	@GetMapping("/download")
	public void download (HttpServletResponse response){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName =  dateFormat.format(date) + ".xls";

		try {
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
			response.setContentType("application/octet-stream");
			OutputStream outputSteam = null;
			outputSteam = new BufferedOutputStream(response.getOutputStream());
			ExcelUtil.excelFileDemo(outputSteam);
			outputSteam.flush();
			response.getOutputStream().close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File multipartToFile(MultipartFile multipartFile) 
	{
//		String folderPath = "tmp";
//		File folder = new File(folderPath);
//		if(!folder.exists()){
//			folder.mkdir();
//		}
		String filePath = System.currentTimeMillis()+multipartFile.getOriginalFilename();
		File convFile = new File(filePath);
		log.info("开始保存本地图片： {}",convFile.getAbsolutePath());
		try {
			FileOutputStream os = new FileOutputStream(convFile);
			os.write(multipartFile.getBytes());
			os.close();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
}
