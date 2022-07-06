package me.eun.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.eun.mapper.BoardAttachMapper;
import me.eun.model.BoardAttachVO;

@Component
public class FileCheckTask {

	@Autowired
	private  BoardAttachMapper attachMapper;
		
	@Scheduled (cron = "0 0 2 * * *")
	public void checkFile() throws Exception {
		System.out.println("Check files ....!!!!!!!!!!!!!!");
		
		
		List<BoardAttachVO> fileList = attachMapper.getOldfiles();  //string 객체 생성 법 
		System.out.println(fileList);
		 
		List<Path> fileListPaths = fileList.stream()
		.map(vo -> Paths.get(   //map 메소드
				"C:\\storage", vo.getUploadPath(),vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());
		
		fileList     //fileList 메소드
		.stream()
		.filter(vo -> vo.isFileType()==true)
		.map(vo -> Paths.get("C:\\storage",vo.getUploadPath(),"s_"+vo.getUuid()+"_"+vo.getFileName()))
		.forEach(p-> fileListPaths.add(p));  //forEach 메소드
		
		File targetDir = Paths.get("c:/storage",getFolderYesterDay()).toFile();  
		File[] removeFiles
		 = targetDir.listFiles(file -> fileListPaths.contains(file.toPath())==false);
		
		System.out.println(Arrays.toString(removeFiles));
		
		//Arrays.stream(removeFiles).forEach(f->f.delete());    아래와 같은 코드 
		Arrays.asList(removeFiles).stream().forEach(f->f.delete());
		//Arrays.asList(removeFiles).forEach(f->f.delete());     이렇게 작성 해도 된다
		
	}
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd"); //2022-07-06
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,-1);  //하루전날 정리 되는 코드     2022-07-05 
		return sdf.format(cal.getTime()).replace("-", File.separator);
	}

}


