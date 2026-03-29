package com.carbooking.api.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification request,
                           FilterableResponseSpecification response,
                           FilterContext ctx) {

        System.out.println("===== API REQUEST =====");
        System.out.println(request.getMethod() + " " + request.getURI());
        System.out.println("Headers: " + request.getHeaders());
        System.out.println("Body: " + request.getBody());

        Response res = ctx.next(request, response);

        System.out.println("\n===== API RESPONSE =====");
        System.out.println("Status code: " + res.getStatusCode());
        System.out.println("Headers: " + res.getHeaders());
        System.out.println("Body: " + res.getBody().asString());

        return res;
    }
}