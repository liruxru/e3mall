package com.bonc.controller;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.common.utils.MongoGridFSClient;

@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map<String,Object> fileUpload(MultipartFile uploadFile) {
		try {
			
			//1、取文件的扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String contentType = uploadFile.getContentType();
			
			//2、创建一个FastDFS的客户端
			MongoGridFSClient fastDFSClient = new MongoGridFSClient(IMAGE_SERVER_URL);
			//3、执行上传处理
			String fileId = fastDFSClient.uploadFile(uploadFile.getBytes(), extName,contentType);
			//4、拼接返回的url和ip地址，拼装成完整的url
			String url ="/pics/"+fileId;
			//5、返回map
			Map<String,Object> result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//5、返回map
			Map<String,Object> result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return result;
		}
	}
	
	@RequestMapping("/pics/{fileId}")
	@ResponseBody
	public ResponseEntity<byte[]> fileDownload(@PathVariable(name="fileId")String fileId) {
		HttpHeaders headers = new HttpHeaders();
		// 設置文件為圖片 
        headers.setContentType(MediaType.IMAGE_JPEG);
        MongoGridFSClient fastDFSClient = new MongoGridFSClient(IMAGE_SERVER_URL);
        byte[] downLoad = fastDFSClient.downLoad(fileId);
        return new ResponseEntity<byte[]>(downLoad,headers, HttpStatus.CREATED);
	}
}
