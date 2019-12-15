package org.spring.cloud.multiple.demo.article.provider.controller;

import org.spring.cloud.multiple.demo.article.api.ArticleAPI;
import org.spring.cloud.multiple.demo.article.api.response.ArticleResponse;
import org.spring.cloud.multiple.demo.common.base.Response;
import org.spring.cloud.multiple.demo.common.base.ResponseData;
import org.spring.cloud.multiple.demo.user.api.UserAPI;
import org.spring.cloud.multiple.demo.user.api.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleRestController implements ArticleAPI {

	@Autowired
	private UserAPI userAPI;
	
	@Override
	public ResponseData<ArticleResponse> getArticle(int id) {
		String author = "";
		ResponseData<UserResponse> userResponse = userAPI.getUser(1001);
		if (userResponse.isOk()) {
			author = userResponse.getData().getName();
		}
		return Response.ok(
				ArticleResponse.builder()
					.id(1)
					.title("微服务架构的未来")
					.content("猜猜看")
					.author(author)
					.build());
	}

}
