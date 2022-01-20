package severstal.zorin.homework.zipfiles;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;


public class TestZipFiles {

    @Test
    void zipTest() throws Exception {

        ZipFile zipFile = new ZipFile("src//test//resources//fromTests.zip");

        ZipEntry zipPdfEntry = zipFile.getEntry("oferta.pdf");
        try (InputStream parsePdfFile = zipFile.getInputStream(zipPdfEntry)) {
            PDF parsed = new PDF(parsePdfFile);
            assertThat(parsed.text).contains("Публичная оферта на возмездное оказание услуг");
            assertThat(parsed.numberOfPages).isEqualTo(6);

        }

        ZipEntry zipCsvEntry = zipFile.getEntry("CSV.csv");
        try (InputStream parseCsvFile = zipFile.getInputStream(zipCsvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(parseCsvFile));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(3)
                    .contains(new String[]{"1", "2"},
                            new String[]{"3", "4"},
                            new String[]{"5", "6"});
        }

        ZipEntry zipXlsxEntry = zipFile.getEntry("Bench press programm.xlsx");
        try (InputStream parseXlsxFile = zipFile.getInputStream(zipXlsxEntry)) {
            XLS parsed = new XLS(parseXlsxFile);
            assertThat(parsed.excel.getSheetAt(0)
                    .getRow(1).getCell(4)
                    .getStringCellValue()).isEqualTo("Ф. Бржыки");
        }
    }
}

