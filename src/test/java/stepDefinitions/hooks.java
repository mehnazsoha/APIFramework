package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// To generate place id and execute this code only when the place id is null
		stepDefinition m = new stepDefinition();
		if (stepDefinition.place_id == null) {
			m.add_place_payload_with("shetty", "french", "asia");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("shetty", "getPlaceAPI");
		}
	}
}
