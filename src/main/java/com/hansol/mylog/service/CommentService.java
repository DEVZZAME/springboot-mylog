package com.hansol.mylog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansol.mylog.domain.comment.comment;
import com.hansol.mylog.domain.comment.CommentRepository;
import com.hansol.mylog.domain.image.image;
import com.hansol.mylog.domain.user.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	
	@Transactional
	public comment 댓글쓰기(user principal, String content, int imageId) {
		
		image image = com.hansol.mylog.domain.image.image.builder()
				.id(imageId)
				.build();
				
		// Save할 때 연관관계가 있으면 오브젝트로 만들어서 id값만 넣어주면 된다.
		comment comment = com.hansol.mylog.domain.comment.comment.builder()
				.content(content)
				.image(image)
				.user(principal)
				.build();
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제(int id, int principalId) {
		
		comment commentEntity = commentRepository.findById(id).get();
		if(commentEntity.getUser().getId() == principalId) {
			commentRepository.deleteById(id);
		}else {
			// 스로우 익센션 날려서 ControllAdvice 처리
		}
	}
}




