package gmail.asteroster.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTests {

    ClassLoader cl = FilesParsingTests.class.getClassLoader();

    @Test
    void pdfParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloadedPdf = $(By.linkText("PDF download")).download();
        PDF content = new PDF(downloadedPdf);
        assertThat(content.author).contains("Sam Brannen");
    }

    @Test
    void xlsParsingTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("xls/sortovojprokat.xls")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(4).getCell(0).getStringCellValue())
                    .contains("АРМАТУРА");
        }
    }

    @Test
    void csvParsingTest() throws Exception {
        try (InputStream csvResource = cl.getResourceAsStream("csv/csvexample.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(csvResource))
        )
        {
            List<String[]> content = reader.readAll();
            assertThat(content.get(1)[1]).contains("Files");
        }
    }
}
