package com.Trello;

import com.Beymen.Utilities.ConfigurationReader;
import com.github.dockerjava.transport.DockerHttpClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Practice {


    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = ConfigurationReader.get("baseURI");
        RestAssured.basePath = ConfigurationReader.get("basePath");
    }


    @Test
    public void test01(){


        String id = RestAssured.given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "656babcd6da08a007e11e7ab")
                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token"))


                .get("/boards/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo("656babcd6da08a007e11e7ab")).extract().path("id");


        System.out.println("id = " + id);


        ValidatableResponse body = RestAssured.given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "656babcd6da08a007e11e7ab")
                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token"))
                .get("/boards/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("test"))
                .body("id", equalTo(id))
                .body("prefs.background", equalTo("blue"));

        body.log().all();


        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .pathParam("id", "656babcd6da08a007e11e7ab")
                .queryParam("key", ConfigurationReader.get("key"))
                .queryParam("token", ConfigurationReader.get("token"))
                .get("/boards/{id}")
                .then().extract().response();

        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode(),"status code");
        Assertions.assertEquals("test",response.path("name"));
        Assertions.assertEquals("blue",response.path("prefs.background"));


    }

    @Test
    public void test02(){






    }

}
