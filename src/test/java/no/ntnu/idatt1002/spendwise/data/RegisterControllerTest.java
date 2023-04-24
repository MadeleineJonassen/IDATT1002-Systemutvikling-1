package no.ntnu.idatt1002.spendwise.data;

import no.ntnu.idatt1002.spendwise.exceptions.ConformityException;
import no.ntnu.idatt1002.spendwise.exceptions.DuplicateNameException;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for data operations with the RegisterController class.
 */
@SuppressWarnings("VulnerableCodeUsages")
public class RegisterControllerTest {
    @Test
    public void readWriteTest() throws IOException, DuplicateNameException, URISyntaxException, ConformityException {
        // Compare the JSONObject read and the JSONObject written
        URL url = Objects.requireNonNull(getClass().getClassLoader().getResource("database/register.json"));

        File file = new File(url.toURI());
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject readJson = new JSONObject(content);
        Register readRegister = RegisterController.readData(url);

        JSONObject writeJson = RegisterController.writeData(readRegister);

        assertEquals(readJson.toString(), writeJson.toString());
    }
}
