package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.apiTest.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class APIFirstTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test(description = "create an user")
    @MethodOwner(owner = "Dubynskyi Oleksii")
    public void testCreateUser() {
        PostUserMethod postUserMethod = new PostUserMethod();
        postUserMethod.setProperties("api/apiTest/users/_post/user.properties");
        postUserMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        postUserMethod.callAPI();
        postUserMethod.validateResponse();
    }

    @Test(description = "get a list of users from first page")
    @MethodOwner(owner = "Dubynskyi Oleksii")
    public void testGetUsers() {
        GetUsersMethod getUsersMethod = new GetUsersMethod();
        getUsersMethod.callAPIExpectSuccess();
        getUsersMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getUsersMethod.validateResponseAgainstSchema("api/apiTest/users/_get/rs.schema");
    }

    @Test(description = "make a registration")
    @MethodOwner(owner = "Dubynskyi Oleksii")
    public void testCreateRegistation() {
        PostRegisterMethod postRegisterMethod = new PostRegisterMethod();
        postRegisterMethod.setProperties("api/apiTest/registrations/_post/registration.properties");
        postRegisterMethod.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        postRegisterMethod.callAPI();
        postRegisterMethod.validateResponse();
    }

    @Test(description = "get a list of countries in Europe by name and capital")
    @MethodOwner(owner = "Dubynskyi Oleksii")
    public void testGetCountries() {
        GetCountriesMethod getCountriesMethod = new GetCountriesMethod();
        getCountriesMethod.callAPIExpectSuccess();
        getCountriesMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCountriesMethod.validateResponseAgainstSchema("api/apiTest/countries/_get/rs.schema");
    }

    @Test(description = "get an information about weather")
    @MethodOwner(owner = "Dubynskyi Oleksii")
    public void testGetWeatherInfo() {
        GetWeatherMethod getWeatherMethod = new GetWeatherMethod();
        getWeatherMethod.callAPIExpectSuccess();
        getWeatherMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherMethod.validateResponseAgainstSchema("api/apiTest/weather/_get/rs.schema");
    }
}
