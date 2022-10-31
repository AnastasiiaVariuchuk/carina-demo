package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.apiTest.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class APIFirstTest implements IAbstractTest {
    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testPostCat() {
        PostCatMethod postUserMethod = new PostCatMethod();
        postUserMethod.setProperties("api/apiTest/postCats/cat.properties");
        postUserMethod.callAPI();
        postUserMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testGetCats() {
        GetCatsMethod getCatsMethod = new GetCatsMethod();
        getCatsMethod.callAPIExpectSuccess();
        getCatsMethod.validateResponseAgainstSchema("api/apiTest/getCats/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testGetForecast() {
        GetForecastMethod getWeatherMethod = new GetForecastMethod();
        getWeatherMethod.callAPIExpectSuccess();
        getWeatherMethod.validateResponseAgainstSchema("api/apiTest/getForecast/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testGetUser() {
        GetUserMethod getUserMethod = new GetUserMethod();
        getUserMethod.callAPIExpectSuccess();
        getUserMethod.validateResponseAgainstSchema("api/apiTest/getUser/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testPostUserMethod() {
        PostUserMethod postUserMethod = new PostUserMethod();
        postUserMethod.setProperties("api/apiTest/postUser/postUser.properties");
        postUserMethod.callAPI();
        postUserMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testPostPostMethod() {
        PostPostMethod postPostMethod = new PostPostMethod();
        postPostMethod.setProperties("api/apiTest/postPost/postPost.properties");
        postPostMethod.callAPI();
        postPostMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    public void testPostPhotoMethod() {
        PostPhotoMethod postPhotoMethod = new PostPhotoMethod();
        postPhotoMethod.setProperties("api/apiTest/postPhoto/postPhoto.properties");
        postPhotoMethod.callAPI();
        postPhotoMethod.validateResponse();
    }
}
