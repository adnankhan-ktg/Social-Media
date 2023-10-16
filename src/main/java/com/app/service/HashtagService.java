package com.app.service;

import com.app.model.response.CommonResponse;

public interface HashtagService {

    CommonResponse searchHashtag(String hashtagName);
}
