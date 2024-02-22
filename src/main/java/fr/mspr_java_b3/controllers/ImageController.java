package fr.mspr_java_b3.controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import fr.mspr_java_b3.controllers.responses.ImageResponse;
import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@SecurityRequirement(name = "bearer")
public class ImageController {


    @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ImageResponse postImage(@RequestParam(name = "image") MultipartFile file) throws IOException, InterruptedException, UnirestException {

        String url = String.format("https://api.imgbb.com/1/upload?key=%s", Dotenv.load().get("image_api_key"));

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post(url)
                .field("image", file.getInputStream(), file.getOriginalFilename()).asJson();

        return new ImageResponse(response.getBody().getObject().getJSONObject("data").getString("display_url"));

    }


}
