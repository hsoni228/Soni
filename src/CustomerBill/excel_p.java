package CustomerBill;

import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;
import jxl.*;
import jxl.write.*;
import jxl.SheetSettings.*;
import static jxl.write.WritableFont.BOLD;

public class excel_p {

    void fillData(String bill, String name, String date, JTable table, File file, String total, String bal) {
        WritableWorkbook workbook1;
        WritableSheet sheet1;
        try {
            if (!file.exists()) {
                workbook1 = Workbook.createWorkbook(file);
                sheet1 = workbook1.createSheet("First Sheet", 0);
            } else {
                Workbook work = Workbook.getWorkbook(file);
                workbook1 = Workbook.createWorkbook(file, work);
                sheet1 = workbook1.getSheet(0);
            }

            //--------------------------------------------------------------------
            int w = 0;
            for (int l = 0; l >= 0; l = l + 37) {
                if ("".equals(sheet1.getCell(0, l + 1).getContents())) {
                    System.out.println(l + 1);
                    w = l;
                    break;
                }
            }

            int n = w, m = 0;

            TableModel model = table.getModel();
            int left = model.getRowCount();
            System.out.println(left);
            while (left > 0) {
                System.out.println(n + " here it is " + m);
                n++;

                WritableFont cellFont, cellFont1;
                WritableCellFormat cellFormat;

                sheet1.mergeCells(0, n - 1, 2, n - 1);

                cellFont = new WritableFont(WritableFont.ARIAL, 11);
                cellFont.setBoldStyle(BOLD);

                cellFormat = new WritableCellFormat(cellFont);
                cellFormat.setAlignment(jxl.format.Alignment.LEFT);
                cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

                Label label = new Label(m, n - 1, "Bill No. " + bill + "      " + name, cellFormat);
                sheet1.addCell(label);

                label = new Label(m + 3, n - 1, "Soni Agency", cellFormat);
                sheet1.addCell(label);

                cellFormat = new WritableCellFormat(cellFont);
                cellFormat.setAlignment(jxl.format.Alignment.RIGHT);
                cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

                label = new Label(m + 4, n - 1, date, cellFormat);
                sheet1.addCell(label);

                cellFont = new WritableFont(WritableFont.ARIAL, 10);
                cellFont.setBoldStyle(BOLD);

                for (int i = m; i < m + model.getColumnCount(); i++) {
                    if (i == 0 || i == 1) {
                        cellFormat = new WritableCellFormat(cellFont);
                        cellFormat.setAlignment(jxl.format.Alignment.LEFT);
                    } else {
                        cellFormat = new WritableCellFormat(cellFont);
                        cellFormat.setAlignment(jxl.format.Alignment.RIGHT);
                    }
                    String col_name = model.getColumnName(i - m);
                    Label column = new Label(i, n, col_name, cellFormat);
                    sheet1.addCell(column);
                }
                sheet1.setColumnView(0, 11);
                sheet1.setColumnView(1, 30);
                sheet1.setColumnView(2, 16);
                sheet1.setColumnView(3, 14);
                sheet1.setColumnView(4, 14);

                int j = m;
                cellFont1 = new WritableFont(WritableFont.ARIAL, 10);

                int end;
                if (left > 24) {
                    end = 24;
                } else {
                    end = left;
                }

                for (int i = n; i < n + end; i++) {
                    for (j = m; j < m + model.getColumnCount(); j++) {
                        String col_val = model.getValueAt(i - n + model.getRowCount() - left, j - m).toString();

                        if (j == 0 || j == 1) {
                            cellFormat = new WritableCellFormat(cellFont1);
                            cellFormat.setAlignment(jxl.format.Alignment.LEFT);
                        } else {
                            cellFormat = new WritableCellFormat(cellFont1);
                            cellFormat.setAlignment(jxl.format.Alignment.RIGHT);
                        }

                        Label row = new Label(j, i + 1, col_val, cellFormat);
                        sheet1.addCell(row);

                    }
                }

                int i = n + end;

                cellFormat = new WritableCellFormat(cellFont);
                cellFormat.setAlignment(jxl.format.Alignment.RIGHT);

                if (left - 24 <= 0) {
                    System.out.println("here left is " + left);
                    Label row = new Label(m + 3, n + 25, "Total :- ", cellFormat);
                    sheet1.addCell(row);

                    row = new Label(m + 4, n + 25, total, cellFormat);
                    sheet1.addCell(row);

                    cellFormat = new WritableCellFormat(cellFont);
                    cellFormat.setAlignment(jxl.format.Alignment.LEFT);
                    row = new Label(m, n + 25, "Net Bal.", cellFormat);
                    sheet1.addCell(row);

                    row = new Label(m + 1, n + 25, bal, cellFormat);
                    sheet1.addCell(row);

                } else {
                    System.out.println("here");
                    Label row = new Label(m + 2, n + 25, "Cont...  ", cellFormat);
                    sheet1.addCell(row);
                }
                System.out.println("val of n = " + n);
                n = n + 36;
                m = 0;

                m = 0;
                left = left - 24;
            }
            //--------------------------------------------------------------------
            workbook1.write();

            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
