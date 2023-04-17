package gmail.asteroster.homework;


import com.fasterxml.jackson.databind.ObjectMapper;
import gmail.asteroster.model.Doc;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;


public class JacksonParseTest {

    ClassLoader cl = JacksonParseTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();
    Doc doc = new Doc();

    @Test
    void jsonParseTestJackson () throws Exception {
        try (
                InputStream jsonResource = cl.getResourceAsStream("json/doc.json");
                InputStreamReader reader = new InputStreamReader(jsonResource)
        ) {
            Doc doc = mapper.readValue(reader, Doc.class);
            assertThat(doc.id).contains("file");
            assertThat(doc.value).isEqualTo("File");
            assertThat(doc.popup.menuitem).contains("value");
            assertThat(doc.popup.menuitem).contains("onclick");
        }
    }
}