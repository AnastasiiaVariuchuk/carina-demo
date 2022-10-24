package com.qaprosoft.carina.demo.apiTest;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "http://api.weatherapi.com/v1/current.json?key=50d04e169bdf46d6b3d130416222410&q=London&aqi=no", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/apiTest/forecast._get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetForecastMethod extends AbstractApiMethodV2 {
    public GetForecastMethod() {
        replaceUrlPlaceholder("http://api.weatherapi.com/v1/current.json?key=50d04e169bdf46d6b3d130416222410&q=London&aqi=no", Configuration.getEnvArg("api_url"));
    }
}
