package com.Trello;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTest {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "https://api.trello.com";
        RestAssured.basePath = "/1/boards";

    }

    @Test
    public  void test1(){

        Response response = RestAssured.given().accept("application/json")
                .when()
                .queryParam("key","75bd80401a979209cb6a91cad4fd9225")
                .queryParam("token","ATTA5914335ba0ed29b5ea6c02b3296d90760a2baced36e50a34330dae416f750a815F8246B6")
                .get("/656babcd6da08a007e11e7ab");

        response.prettyPrint();
        Assertions.assertEquals("application/json; charset=utf-8",response.contentType());
    }

    @Test
    public void postGetPutDeleteTest(){


        Response post = RestAssured.given().contentType("application/json")
                .when()
                .queryParam("key", "75bd80401a979209cb6a91cad4fd9225")
                .queryParam("token", "ATTA5914335ba0ed29b5ea6c02b3296d90760a2baced36e50a34330dae416f750a815F8246B6")
                .queryParam("name", "Timus")
                .post();

        JsonPath jsonPath = post.jsonPath();

        System.out.println("jsonPath.getString(\"id\") = " + jsonPath.getString("id"));



    }

    @Test
    public void getTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .queryParam("key", "75bd80401a979209cb6a91cad4fd9225")
                .queryParam("token", "ATTA5914335ba0ed29b5ea6c02b3296d90760a2baced36e50a34330dae416f750a815F8246B6")
                .pathParam("id", "656c4631b28d8d4bd168392e")
                .get("/{id}");

        Assertions.assertEquals("656c4631b28d8d4bd168392e",response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));


    }

    @Test
    public void putTest(){

        RestAssured.given().accept(ContentType.JSON)
                .when()
                .queryParam("key", "75bd80401a979209cb6a91cad4fd9225")
                .queryParam("token", "ATTA5914335ba0ed29b5ea6c02b3296d90760a2baced36e50a34330dae416f750a815F8246B6")
                .queryParam("name","newTimus")
                .pathParam("id", "656c4631b28d8d4bd168392e")
                .put("/{id}");


    }

    @Test
    public void deleteTest(){

        RestAssured.given().accept(ContentType.JSON)
                .when()
                .queryParam("key", "75bd80401a979209cb6a91cad4fd9225")
                .queryParam("token", "ATTA5914335ba0ed29b5ea6c02b3296d90760a2baced36e50a34330dae416f750a815F8246B6")
                .pathParam("id", "656c4631b28d8d4bd168392e")
                .delete("/{id}")
                .then()
                .statusCode(200);


    }
}
