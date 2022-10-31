package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;

@Endpoint(url = "https://jsonplaceholder.typicode.com/photos", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/apiTest/postPhoto/rq.json")
@ResponseTemplatePath(path = "api/apiTest/postPhoto/rs.json")
public class PostPhotoMethod extends AbstractApiMethodV2 {
    public PostPhotoMethod() {
    }
}
