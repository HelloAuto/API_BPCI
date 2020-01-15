package com.bpci.bdd;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class GetRequestBDD {
	@Test
	public void getTc001() {
		given()
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
		//	.assertThat().body("City", equalTo("Hyderabad"))
			.log().body();
	}
}
