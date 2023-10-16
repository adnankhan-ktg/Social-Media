package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.dto.LatestFeedDto;
import com.app.model.interfacedto.PostDtoInterface;
import com.app.model.response.CommonResponse;
import com.app.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.service.FeedService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger log = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommonResponse loadLatestFeed(int userId, int limit) {
        log.info("FeedServiceImpl :: loadLatestFeed - START");
        CommonResponse response = new CommonResponse();
        try {
            List<PostDtoInterface> postDtoInterface = this.postRepository.loadLatestPost(userId, limit);

            List<LatestFeedDto> latestFeedDtos = postDtoInterface.stream().map(e -> new LatestFeedDto(e.getFollowing(), e.getPostId(), e.getContentType(), e.getCaption(),
                    e.getPostUrl(), e.getTimestamp())).toList();

            if (latestFeedDtos.isEmpty()) {
                response.setStatusCode(404);
                response.setMsg("No latest feed found");
            } else {
                response.setData(latestFeedDtos);
                response.setMsg("Latest feed successfully loaded");
                response.setStatusCode(200);
            }
        } catch (Exception ex) {
            log.error("HashtagController :: loadLatestFeed - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FeedServiceImpl :: loadLatestFeed - END");
        return response;
    }
}
