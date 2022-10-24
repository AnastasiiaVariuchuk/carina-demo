package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.apiTest.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APIFirstTest implements IAbstractTest {

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testCreateCat() {
        PostCatMethod postUserMethod = new PostCatMethod();
        postUserMethod.setProperties("api/apiTest/cats._post/cat.properties");
        postUserMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        postUserMethod.callAPI();
        postUserMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testGetCats() {
        GetCatsMethod getCatsMethod = new GetCatsMethod();
        getCatsMethod.callAPIExpectSuccess();
        getCatsMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCatsMethod.validateResponseAgainstSchema("api/apiTest/cats._get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testCreateRegistation() {
        PostUserMethod postRegisterMethod = new PostUserMethod();
        postRegisterMethod.setProperties("api/apiTest/users._post/users.properties");
        postRegisterMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        postRegisterMethod.callAPI();
        postRegisterMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testGetForecast() {
        GetForecastMethod getWeatherMethod = new GetForecastMethod();
        getWeatherMethod.callAPIExpectSuccess();
        getWeatherMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherMethod.validateResponseAgainstSchema("api/apiTest/forecast._get/rs.schema");
    }
}
