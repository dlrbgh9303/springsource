package com.company.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.company.domain.AttachFileDTO;
import com.company.mapper.BoardAttachMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileCheckTask {
	
	@Autowired
	private BoardAttachMapper attach;
	
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFile() {
		log.info("File Check 실행......");
		
		//데이터 베이스 어제날짜 파일 리스트 가져오기
		List<AttachFileDTO> oldList = attach.getOldFiles();
		
		//AttachFileDTO 값들을 경로 개념으로 변경
	    List<Path> fileListPaths = oldList.stream()
	    								.map(dto -> Paths.get("c:\\upload", dto.getUploadPath(),dto.getUuid()+"_"+dto.getFileName()))
	    								.collect(Collectors.toList());
		
	    //썸네일 이미지 경로 추출한 후 리스트에 추가
	    oldList.stream()
	    		.filter(dto -> dto.isFileType() == true)
				.map(dto -> Paths.get("c:\\upload", dto.getUploadPath(),"s_"+dto.getUuid()+"_"+dto.getFileName()))
				.forEach(p -> fileListPaths.add(p));
	    
	    
	    // 폴더 목록과 데이터베이스 파일 목록을 비교
	    // 어제 날짜 폴더 구하기 c:\\upload\\2021\\12\\09
	    File targetDir = Paths.get("c:\\upload", getYesterDay()).toFile();
	    // targetDir에 접근 후 파일 목록을 추출하여 데이터베이스 파일 목록과 일치하지 않은 
	    // 파일 정보 담기
	    File[] removeFiles = targetDir.listFiles(f -> fileListPaths.contains(f.toPath()) == false); //일치하지 않는 파일들을 File[]에 담아라
	    
	    System.out.println("삭제 파일"+Arrays.toString(removeFiles));
	    
	    
	    // 파일 삭제
	    for(File file : removeFiles) {
	    	log.info(file.getAbsolutePath());
	    	file.delete();	
	    }
	    
	}

	//어제 날짜 구하기
	private String getYesterDay() {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return str.replace("-", File.separator); // 2021\\12\09 <= 어제 날짜를 구하라고 했기에 어제 날짜가 나옴
	}
	
	


}


