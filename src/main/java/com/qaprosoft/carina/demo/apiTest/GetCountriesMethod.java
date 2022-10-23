package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://restcountries.com/v2/region/europe?fields=name,capital", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/apiTest/countries/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetCountriesMethod extends AbstractApiMethodV2 {
    public GetCountriesMethod() {
        replaceUrlPlaceholder("https://restcountries.com/v2/region/europe?fields=name,capital", Configuration.getEnvArg("api_url"));
    }
}

