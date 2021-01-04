package com;

import com.google.common.reflect.TypeToken;
import constants.ApiPathConstants;
import dataProvider.DataProviderUtil;
import dto.TaskPerUserDto;
import dto.UserDetailsDto;
import dto.commonMongoDto.LatLongTestData;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ApiHelper;
import utils.BaseClass;
import utils.GenericHelper;

import java.util.ArrayList;
import java.util.List;

import static utils.BaseClass.*;
import static utils.GenericHelper.convertFromJson;

/**
 * Created By: Ankit Agarwal
 **/

public class TestClass {

    //posts
    //comments
    //albums
    //photos

    @BeforeClass
    public void setup() throws Exception {
        initializeProperty();
    }

    @Test(description = "Using property file for lat long info")
    public void getTaskCompletedUser(){
        ApiHelper apiHelper = new ApiHelper();
        List<Integer> user = new ArrayList<>();
        List<TaskPerUserDto> a = new ArrayList<>();
        String url = baseurl;
        List<UserDetailsDto> response = convertFromJson(apiHelper.getResponse(null,url+ ApiPathConstants.users).asString(),
                new TypeToken<List<UserDetailsDto>>(){}.getType());

        for (UserDetailsDto dto : response){
            if (Float.parseFloat(dto.getAddress().getGeo().getLat())>=Float.parseFloat(groferLatLower) &&
                    Float.parseFloat(dto.getAddress().getGeo().getLat())<=Float.parseFloat(groferLatUpper)){
                if (Float.parseFloat(dto.getAddress().getGeo().getLng())>=Float.parseFloat(groferLongLower) &&
                        Float.parseFloat(dto.getAddress().getGeo().getLng())<=Float.parseFloat(groferLongUpper)){
                    user.add(dto.getId());
                }
            }
        }

        List<TaskPerUserDto> todos = convertFromJson(apiHelper.getResponse(null,url+ ApiPathConstants.todos).asString(),
                new TypeToken<List<TaskPerUserDto>>(){}.getType());
        for (TaskPerUserDto taskPerUserDto : todos){
            if (user.contains(taskPerUserDto.userId)){
                a.add(taskPerUserDto);
            }
        }
        GenericHelper.getUserPercentage(a, user,Integer.parseInt(percentageOfTodos));
    }

    @Test(description = "Using data provider for test data, multiple test cases based on lat long and any other factor added later",
        dataProvider = "groferCityUsers", dataProviderClass = DataProviderUtil.class)
    public void getTaskCompletedUsingDataProvider(String input){
        ApiHelper apiHelper = new ApiHelper();
        List<Integer> user = new ArrayList<>();
        List<TaskPerUserDto> a = new ArrayList<>();
        LatLongTestData latLongTestData = convertFromJson(input,new TypeToken<LatLongTestData>(){}.getType());
        String url = baseurl;
        List<UserDetailsDto> response = convertFromJson(apiHelper.getResponse(null,url+ ApiPathConstants.users).asString(),
                new TypeToken<List<UserDetailsDto>>(){}.getType());

        for (UserDetailsDto dto : response){
            if (Float.parseFloat(dto.getAddress().getGeo().getLat())>=Float.parseFloat(latLongTestData.getGroferLatLower()) &&
                    Float.parseFloat(dto.getAddress().getGeo().getLat())<=Float.parseFloat(latLongTestData.getGroferLatUpper())){
                if (Float.parseFloat(dto.getAddress().getGeo().getLng())>=Float.parseFloat(latLongTestData.getGroferLongLower()) &&
                        Float.parseFloat(dto.getAddress().getGeo().getLng())<=Float.parseFloat(latLongTestData.getGroferLongUpper())){
                    user.add(dto.getId());
                }
            }
        }

        List<TaskPerUserDto> todos = convertFromJson(apiHelper.getResponse(null,url+ ApiPathConstants.todos).asString(),
                new TypeToken<List<TaskPerUserDto>>(){}.getType());
        for (TaskPerUserDto taskPerUserDto : todos){
            if (user.contains(taskPerUserDto.userId)){
                a.add(taskPerUserDto);
            }
        }
        GenericHelper.getUserPercentage(a, user,latLongTestData.getPercentage());
    }
}
