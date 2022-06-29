package me.eun.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
	}
	
	@PostMapping("uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model){
		for (MultipartFile file : uploadFile ) {
			System.out.println("======================================");
			System.out.println("파일 이름: " + file.getOriginalFilename());
			System.out.println("파일 크기: " + file.getSize());
			File saveFile = new File("C://storage",file.getOriginalFilename());
			try {
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@GetMapping("/uploadAjax")
	public void uploadAjax() {}
		
	
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public void uploadAjax(MultipartFile[] uploadFile) {
		File uploadPath = new File("C:/storage",getFolder());
		if(!uploadPath.exists()) {
			uploadPath.mkdirs(); //C:\storage\\2022\\06\\28
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File savefile = new File(uploadPath,uploadFileName);
			try {
				multipartFile.transferTo(savefile);
				if(checkImageType(savefile)) {
					FileOutputStream tumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator
					.createThumbnail(multipartFile.getInputStream(), tumbnail, 100,100);
				}
				checkImageType(savefile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str=sdf.format(new Date());
		return str.replace("-",File.separator);
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
} //클래스 닫는 태그
