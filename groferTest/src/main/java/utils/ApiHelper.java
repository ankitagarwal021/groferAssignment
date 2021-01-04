package utils;

import enums.RequestType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

/**
 * Created By: Ankit Agarwal
 **/

public class ApiHelper {

    private RequestSpecification prepareRequestParams(Map<String, String> queryParams, List<Header> headers, String body, ContentType contentType, Map<String, String> formParams) {
        RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().redirects().follow(false);
        if (null != queryParams) {
            request.queryParams(queryParams);
        }

        if (null != formParams) {
            request.formParams(formParams);
        }

        if (headers != null) {
            request.headers(new Headers(headers));
        }

        if (body != null && !body.isEmpty()) {
            request.body(body);
        }

        if (contentType != null) {
            request.contentType(contentType);
        }

        return request;
    }

    public Response fetchApiResponse(String requestUrl, String requestType, String body, Map<String, String> queryParams, List<Header> headers, ContentType contentType, boolean checkStatus, Map<String, String> formParams) {
        Response apiResponse = null;
        RequestSpecification apiRequest = this.prepareRequestParams(queryParams, headers, body, contentType, formParams);
        try {
            switch(RequestType.valueOf(requestType.toUpperCase())) {
                case GET:
                    apiResponse = (Response)apiRequest.get(requestUrl, new Object[0]);
                    break;
                case POST:
                    apiResponse = (Response)apiRequest.post(requestUrl, new Object[0]);
                    break;
                case PUT:
                    apiResponse = (Response)apiRequest.put(requestUrl, new Object[0]);
                    break;
                case PATCH:
                    apiResponse = (Response)apiRequest.patch(requestUrl, new Object[0]);
                    break;
                case DELETE:
                    apiResponse = (Response)apiRequest.delete(requestUrl, new Object[0]);
                    break;
                default:
                    apiResponse = (Response)apiRequest.head(requestUrl, new Object[0]);
            }
        } catch (Exception var17) {
            System.out.println(var17);
        }

        return apiResponse;
    }

    public Response getResponse(Map<String, String> queryParams, String url) {
        return this.getResponseWithHeaders(queryParams, (List)null, url, true);
    }

    public Response getResponseWithHeaders(Map<String, String> queryParams, List<Header> headers, String url, boolean checkStatus) {
        return this.fetchApiResponse(url, "GET", (String)null, queryParams, headers, (ContentType)null, checkStatus, (Map)null);
    }

}
