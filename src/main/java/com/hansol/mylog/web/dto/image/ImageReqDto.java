package com.hansol.mylog.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.hansol.mylog.domain.image.Image;
import com.hansol.mylog.domain.user.User;

import lombok.Data;

@Data
public class ImageReqDto {

	private MultipartFile file;
	private String caption;
	private String tags;
	
	public Image toEntity(String postImageUrl, User userEntity) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(userEntity)
				.build();
	}
}
