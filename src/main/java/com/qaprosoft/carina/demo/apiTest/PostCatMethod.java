package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/api/cats._post", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/apiTest/cats._post/rq.json")
@ResponseTemplatePath(path = "api/apiTest/cats/.post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostCatMethod extends AbstractApiMethodV2 {
    public PostCatMethod() {
        super("api/apiTest/cats._post/rq.json", "api/apiTest/cats._post/rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}


