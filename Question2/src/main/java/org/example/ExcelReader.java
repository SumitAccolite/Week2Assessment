package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static List<DataModel> readExcel(String filePath) throws IOException {
        List<DataModel> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            // Assuming the first row contains headers, skip it
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Cell dateCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String date = dateCell != null ? dateCell.toString() : "";

                Cell monthCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String month = monthCell != null ? monthCell.toString() : "";

                Cell teamCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String team = teamCell != null ? teamCell.toString() : "Unknown Team";

                Cell panelNameCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String panelName = panelNameCell != null ? panelNameCell.toString() : "";

                Cell roundCell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String round = roundCell != null ? roundCell.toString() : "";

                Cell skillCell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String skill = skillCell != null ? skillCell.toString() : "";

                Cell timeCell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String time = timeCell != null ? timeCell.toString() : "";

                Cell candidateCurrentLocCell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String candidateCurrentLoc = candidateCurrentLocCell != null ? candidateCurrentLocCell.toString() : "";

                Cell candidatePreferredLocCell = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String candidatePreferredLoc = candidatePreferredLocCell != null ? candidatePreferredLocCell.toString() : "";

                Cell candidateNameCell = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String candidateName = candidateNameCell != null ? candidateNameCell.toString() : "";

                // Create DataModel instance and add to the list
                DataModel dataModel = new DataModel(date, month, team, panelName, round, skill, time, candidateCurrentLoc, candidatePreferredLoc, candidateName);
                data.add(dataModel);
            }
        }

        return data;
    }

    // Other methods (getCellValue, getNumericCellValue) remain the same as in the previous responses
}
