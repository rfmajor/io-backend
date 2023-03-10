package com.example.iobackend.service.domain.export;

import com.example.iobackend.dto.ItemResultDto;
import com.example.iobackend.service.domain.util.ReflectionUtil;
import com.example.iobackend.service.domain.util.FileType;
import com.example.iobackend.service.domain.util.Headers;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvSearchHistoryExporter implements SearchHistoryExporter {
    @Override
    public FileType getFileType() {
        return FileType.CSV;
    }

    @Override
    public void export(List<ItemResultDto> searchHistory, OutputStream output) throws IOException {
        Writer writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);
        CsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        Headers fieldNamesToHeaderNames = ReflectionUtil.getHeaderValues(ItemResultDto.class);

        String[] csvHeader = fieldNamesToHeaderNames.getHeaderNames();
        String[] nameMapping = fieldNamesToHeaderNames.getFieldNames();

        csvWriter.writeHeader(csvHeader);

        for (ItemResultDto item : searchHistory) {
            csvWriter.write(item, nameMapping);
        }
        csvWriter.close();
    }
}
