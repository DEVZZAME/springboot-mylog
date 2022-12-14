package com.hansol.mylog.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.mylog.config.auth.PrincipalDetails;
import com.hansol.mylog.domain.image.image;
import com.hansol.mylog.domain.image.ImageRepository;
import com.hansol.mylog.domain.tag.tag;
import com.hansol.mylog.domain.tag.TagRepository;
import com.hansol.mylog.utils.TagUtils;
import com.hansol.mylog.web.dto.image.ImageReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	private final TagRepository tagRepository;
	
	@Transactional(readOnly = true)
	public List<image> 인기사진(int principalId){
		return imageRepository.mExplore(principalId);
	}
	
	
	@Value("${file.path}")
	private String uploadFolder;
	
	public Page<image> 피드이미지(int principalId, Pageable pageable){
		
		// 1. principalId 로 내가 팔로우하고 있는 사용자를 찾아야 됨. (한개이거나 컬렉션이거나)
		// select * from image where userId in (select toUserId from follow where fromUserId = 1);
		
		Page<image> images = imageRepository.mFeed(principalId, pageable);
		
		// 좋아요 하트 색깔 로직 + 좋아요 카운트 로직
		images.forEach((image)-> {
			
			int likeCount = image.getLikes().size();
			image.setLikeCount(likeCount);
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) {
					image.setLikeState(true);
				}
			});
		});
		
		return images;
	}
	
	@Transactional
	public void 사진업로드(ImageReqDto imageReDto, PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageReDto.getFile().getOriginalFilename();
		//System.out.println("파일명 : "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		//System.out.println("파일패스 : "+imageFilePath);
		try {
			Files.write(imageFilePath, imageReDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 참고 :  Image 엔티티에 Tag는 주인이 아니다. Image 엔티티로 통해서 Tag를 save할 수 없다.
		
		// 1. Image 저장
		image image = imageReDto.toEntity(imageFileName, principalDetails.getUser());
		com.hansol.mylog.domain.image.image imageEntity = imageRepository.save(image);
		
		// 2. Tag 저장
		List<tag> tags = TagUtils.parsingToTagObject(imageReDto.getTags(), imageEntity);
		tagRepository.saveAll(tags);
		
	}
}







