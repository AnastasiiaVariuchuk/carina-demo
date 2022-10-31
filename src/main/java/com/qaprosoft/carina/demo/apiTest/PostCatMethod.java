package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://api.thecatapi.com/v1/images/upload?api_key=live_vr6kKHHmGc4DE1bexLplLi8f0YXGXvhoRyxjgRuROPq0Jt21pSlIgfSiPQVO7rLv", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/apiTest/postCats/rq.json")
@ResponseTemplatePath(path = "api/apiTest/postCats/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostCatMethod extends AbstractApiMethodV2 {
    public PostCatMethod() {
    }
}


