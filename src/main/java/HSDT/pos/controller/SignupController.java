package HSDT.pos.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody JSONObject data) throws JSONException {
        // Extract email, password, and confirmPassword from JSON data
        String email = data.getString("email");
        String password = data.getString("password");
        String confirmPassword = data.getString("confirmPassword");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JSONObject error = new JSONObject();
            error.put("message", "Passwords do not match");
            return ResponseEntity.badRequest().body(error.toString());
        }

        // Create new user
        // ...

        // Return successful response
        JSONObject response = new JSONObject();
        response.put("message", "Signup successful");
        return ResponseEntity.ok(response.toString());
    }
}