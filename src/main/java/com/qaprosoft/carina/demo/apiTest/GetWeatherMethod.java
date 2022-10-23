package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "http://api.weatherapi.com/v1/current.json?key=1b1b398b09b8425f97d211103222310&q=London&aqi=no", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/apiTest/weather/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWeatherMethod extends AbstractApiMethodV2 {
    public GetWeatherMethod() {
        replaceUrlPlaceholder("http://api.weatherapi.com/v1/current.json?key=1b1b398b09b8425f97d211103222310&q=London&aqi=no", Configuration.getEnvArg("api_url"));
    }
}
