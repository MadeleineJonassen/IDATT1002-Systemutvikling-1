package no.ntnu.idatt1002.demo.data;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class RegisterController {
    public static void loadData(String jsonPath) throws IOException, URISyntaxException{
        // Default database should be in resources/database
        File file = new File(Register.class.getResource(jsonPath).toURI());
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject json = new JSONObject(content);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        loadData("/database/register.json");
    }
}
