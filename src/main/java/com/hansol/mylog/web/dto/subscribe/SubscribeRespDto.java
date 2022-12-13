package com.hansol.mylog.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeRespDto {
	private int userId;
	private String username;
	private String profileImageUrl;
	private BigInteger subscribeState; // mariadb에서는 Integer
	private BigInteger equalState;
}
