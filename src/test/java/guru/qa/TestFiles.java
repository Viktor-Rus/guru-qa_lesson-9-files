package guru.qa;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import guru.qa.domain.VolleyballPlayer;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class TestFiles {
    ClassLoader classLoader = TestFiles.class.getClassLoader();
    String zipName = "test-resources.zip";
    String zipPath = "src/test/resources/";
    String xlsFileName = "file_example_XLS_10.xls";
    String pdfFileName = "sample.pdf";
    String csvFileName = "addresses.csv";

    @Test
    void testZipCsv() throws Exception {
        InputStream is = classLoader.getResourceAsStream(zipName);
        ZipInputStream zis = new ZipInputStream(is);
        ZipFile zfile = new ZipFile(new File(zipPath + zipName));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if(entry.getName().equals(csvFileName)) {
                try (InputStream stream = zfile.getInputStream(entry);
                     CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                    List<String[]> csv = reader.readAll();
                    assertThat(csv).contains(
                            new String[]{"test","java","qa","guru"},
                            new String[]{"lesson","files", "homework"}
                    );
                }
            }
        }
        if (is != null) {
            is.close();
            zis.close();
        }
    }

    @Test
    void testZipPdf() throws Exception {
        InputStream is = classLoader.getResourceAsStream(zipName);
        ZipInputStream zis = new ZipInputStream(is);
        ZipFile zfile = new ZipFile(new File(zipPath + zipName));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if(entry.getName().equals(pdfFileName)){
                try (InputStream stream = zfile.getInputStream(entry)) {
                    PDF pdf = new PDF(stream);
                    assertThat(pdf.text).contains("And more text. And more text. And more text.");
                }
            }

        }
        if (is != null) {
            is.close();
            zis.close();
        }
    }

    @Test
    void testZipXls() throws Exception {
        InputStream is = classLoader.getResourceAsStream(zipName);
        ZipInputStream zis = new ZipInputStream(is);
        ZipFile zfile = new ZipFile(new File(zipPath + zipName));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if(entry.getName().equals(xlsFileName)){
                try (InputStream stream = zfile.getInputStream(entry)) {
                    XLS xls = new XLS(stream);
                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(6)
                                    .getCell(5)
                                    .getStringCellValue()
                    ).contains("24");
                }
            }

        }
        if (is != null) {
            is.close();
            zis.close();
        }
    }

    @Test
    void jsonTestPlayer() throws IOException {
        InputStream is = classLoader.getResourceAsStream("player.json");
        ObjectMapper objectMapper = new ObjectMapper();
        VolleyballPlayer player = objectMapper.readValue(is, VolleyballPlayer.class);
        assertThat(player.getSport()).isEqualTo("volleyball");
    }
}
