package com.siemens.allkindsoftest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simons on 2017/6/1.
 */
public class TestExcelWrite {

    public static void main(String[] args) throws IOException, WriteException, BiffException {

        File file = new File("C:\\Users\\Simons\\Desktop\\targetPath\\20170529_Ladders Report_DE1038TR_12546.xlsx");

        Workbook wb = Workbook.getWorkbook(file);

        WritableWorkbook wwb = Workbook.createWorkbook(file,wb);

        Sheet[] sheets = wb.getSheets();

        wwb.removeSheet(0);

        wwb.removeSheet(1);

        WritableSheet ws = wwb.getSheet(0);

        WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLUE);

        WritableCellFormat wcfFC = new WritableCellFormat(wfc);

        Label labelCF =new Label(4,20,"人物（新）",wcfFC);

        ws.addCell(labelCF);

        wwb.write();

        wwb.close();

        wb.close();


    }


}
