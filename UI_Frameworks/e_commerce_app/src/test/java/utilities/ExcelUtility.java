
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public final String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // Load workbook
    public XSSFWorkbook loadWorkbook() throws IOException {

        File file = new File(path);

        if (!file.exists()) {
            return new XSSFWorkbook();
        }

        try (FileInputStream fi = new FileInputStream(file)) {
            return new XSSFWorkbook(fi);
        }
    }

    // Get sheet or throw meaningful exception
    public XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) {

        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                "Sheet '" + sheetName + "' does not exist in " + path);
        }

        return sheet;
    }

    // Get last row index (zero-based)
    public int getRowCount(String sheetName) throws IOException {

        try (XSSFWorkbook workbook = loadWorkbook()) {

            XSSFSheet sheet = getSheet(workbook, sheetName);

            return sheet.getLastRowNum();
        }
    }

    // Get cell count in a particular row
    public int getCellCount(String sheetName, int rownum) throws IOException {

        try (XSSFWorkbook workbook = loadWorkbook()) {

            XSSFSheet sheet = getSheet(workbook, sheetName);

            XSSFRow row = sheet.getRow(rownum);

            if (row == null || row.getLastCellNum() == -1) {
                return 0;
            }

            return row.getLastCellNum();
        }
    }

    // Get cell data
    public String getCellData(String sheetName, int rownum, int colnum)
            throws IOException {

        try (XSSFWorkbook workbook = loadWorkbook()) {

            XSSFSheet sheet = getSheet(workbook, sheetName);

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(rownum >= 0 ? colnum : 0);

            if (cell == null) {
                return "";
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(cell);
        }
    }

    // Set cell data
    public void setCellData(String sheetName, int rownum, int colnum,
                            String data) throws IOException {

        try (XSSFWorkbook workbook = loadWorkbook()) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                row = sheet.createRow(rownum);
            }

            Cell cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }

            cell.setCellValue(data);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    // Reusable cell color method
    private void fillCellColor(String sheetName, int rownum, int colnum,
                               IndexedColors color) throws IOException {

        try (XSSFWorkbook workbook = loadWorkbook()) {

            XSSFSheet sheet = getSheet(workbook, sheetName);

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                throw new IllegalArgumentException(
                    "Row " + rownum + " does not exist in sheet " + sheetName);
            }

            Cell cell = row.getCell(colnum);

            if (cell == null) {
                throw new IllegalArgumentException(
                    "Cell at row " + rownum + ", column " + colnum
                    + " does not exist");
            }

            CellStyle style = workbook.createCellStyle();

            // Preserve existing cell formatting
            style.cloneStyleFrom(cell.getCellStyle());

            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            try (FileOutputStream fo = new FileOutputStream(path)) {
                workbook.write(fo);
            }
        }
    }

    // Fill green color
    public void fillGreenColor(String sheetName, int rownum, int colnum)
            throws IOException {

        fillCellColor(sheetName, rownum, colnum, IndexedColors.GREEN);
    }

    // Fill red color
    public void fillRedColor(String sheetName, int rownum, int colnum)
            throws IOException {

        fillCellColor(sheetName, rownum, colnum, IndexedColors.RED);
    }
}
