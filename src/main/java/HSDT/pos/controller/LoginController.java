package HSDT.pos.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody JSONObject data) throws JSONException {
        // Extract email and password from JSON data
        String email = data.getString("email");
        String password = data.getString("password");

        // Authenticate user
        // ...

        // Return successful response
        JSONObject response = new JSONObject();
        response.put("message", "Login successful");
        return ResponseEntity.ok(response.toString());
    }
}