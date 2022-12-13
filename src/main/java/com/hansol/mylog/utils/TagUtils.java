package com.hansol.mylog.utils;

import java.util.ArrayList;
import java.util.List;

import com.hansol.mylog.domain.image.image;
import com.hansol.mylog.domain.tag.tag;

public class TagUtils {

	public static List<tag> parsingToTagObject(String tags, image imageEntity){
		String temp[] = tags.split("#"); // #여행 #바다
		List<tag> list = new ArrayList<>();
	
		// 도라에몽 : 파싱할 때 0번지에 공백이 들어와서 시작번지를 1로 함.
		for (int i=1; i<temp.length; i++) {
			tag tag = com.hansol.mylog.domain.tag.tag.builder()
					.name(temp[i].trim())
					.image(imageEntity)
					.build();
			list.add(tag);
		}
		
		return list;
	}
}
