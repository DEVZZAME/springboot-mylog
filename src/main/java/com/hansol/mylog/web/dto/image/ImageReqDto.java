package com.hansol.mylog.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.hansol.mylog.domain.image.image;
import com.hansol.mylog.domain.user.user;

import lombok.Data;

@Data
public class ImageReqDto {

	private MultipartFile file;
	private String caption;
	private String tags;
	
	public image toEntity(String postImageUrl, user userEntity) {
		return image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(userEntity)
				.build();
	}
}
