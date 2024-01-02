package cse416.server.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
// @CrossOrigin
@RequestMapping("/about")
public class AboutController {

    @PostMapping("/registerEmail")
    public String registerMailingList(@RequestBody Map<String, Object> payload) throws IOException {
        String s = (String)payload.get("email");
        System.out.println("email = " + s);

        if (!s.contains("@")) {
            return "invalid email";
        }else {
            return s + " is now registered!";
        }
    }
}
