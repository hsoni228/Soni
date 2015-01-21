package Stock;

import java.io.File;
import javax.swing.*;
import javax.swing.table.*;
import jxl.*;
import jxl.write.*;
import jxl.SheetSettings.*;
import static jxl.write.WritableFont.BOLD;

public class excel {

    void fillData(JTable table, File file) {
        file.delete();
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
            int n = 0, m = 0;
            TableModel model = table.getModel();
            int left = model.getRowCount();

            WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
            cellFont.setBoldStyle(BOLD);
            for (int i = 0; i < model.getColumnCount(); i++) {

                String col_name = model.getColumnName(i);
                Label column = new Label(i, 0, col_name, new WritableCellFormat(cellFont));
                sheet1.addCell(column);
            }
            sheet1.setColumnView(0, 11);
            sheet1.setColumnView(1, 30);
            sheet1.setColumnView(2, 16);

            int k=1;
            for (int i = 0; i < left; i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    String col_val = model.getValueAt(i, j).toString();
//                    if((Float.parseFloat(model.getValueAt(i, 2).toString())==0))
//                        break;
                    if(j!=0){
                    Label row = new Label(j, k-1, col_val);
                    sheet1.addCell(row);
                    }
                    else
                    {
                    Label row = new Label(j, k, k+++"");
                    sheet1.addCell(row);
                    }

                }
            }

            //--------------------------------------------------------------------
            workbook1.write();

            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
