package fr.mspr_java_b3.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {


    @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    String postImage(@RequestParam(name = "image") MultipartFile file) throws IOException, InterruptedException, UnirestException {

        String url = "https://api.imgbb.com/1/upload?key=368f4e7a790e6ddfd77a75fdf802a5bb";

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post("https://api.imgbb.com/1/upload?key=368f4e7a790e6ddfd77a75fdf802a5bb")
                .field("image", file.getInputStream(), file.getOriginalFilename()).asJson();

        JSONObject json = new JSONObject();
        json.put("url", response.getBody().getObject().getJSONObject("data").getString("display_url"));

        return json.toString();

    }


}
