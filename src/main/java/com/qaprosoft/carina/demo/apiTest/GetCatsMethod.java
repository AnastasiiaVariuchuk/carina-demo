package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://api.thecatapi.com/v1/images/search?limit=10", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/apiTest/cats._get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class GetCatsMethod extends AbstractApiMethodV2{
    public GetCatsMethod() {
        replaceUrlPlaceholder("https://api.thecatapi.com/v1/images/search?limit=10", Configuration.getEnvArg("api_url"));
    }
}
