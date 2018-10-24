package com.xcy.util;

import com.xcy.controller.ExcelController;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author xcy
 * @date 2018/09/28 15:14
 * @description Excel导出
 * @since V1.0.0
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static void exportExcelByList(List<Map<String, Object>> list, String sheetName, String[] keys, String columnNames[],
                                         String fileName, HttpServletResponse response) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Workbook workbook = createWorkBook(list, sheetName, keys, columnNames);

        try {
            workbook.write(os);
        } catch (IOException e) {
            logger.info("excel 文件创建异常: {}", e.getMessage());
            e.printStackTrace();
        }

        exportExcel(fileName, response, os);

    }


    private static Workbook createWorkBook(List<Map<String, Object>> list, String sheetName, String[] keys, String columnNames[]) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);
        for (int i = 0; i < keys.length; i++) {
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        Row row = sheet.createRow((short) 0);

        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        Font f = wb.createFont();
        Font f2 = wb.createFont();

        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBold(true);

        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        cs.setFont(f);
        cs.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        //cs.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        //cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //cs.setBorderLeft(CellStyle.BORDER_THIN);
        //cs.setBorderRight(CellStyle.BORDER_THIN);
        //cs.setBorderTop(CellStyle.BORDER_THIN);
        //cs.setBorderBottom(CellStyle.BORDER_THIN);
        //cs.setAlignment(CellStyle.ALIGN_CENTER);
        //
        //cs2.setFont(f2);
        //cs2.setBorderLeft(CellStyle.BORDER_THIN);
        //cs2.setBorderRight(CellStyle.BORDER_THIN);
        //cs2.setBorderTop(CellStyle.BORDER_THIN);
        //cs2.setBorderBottom(CellStyle.BORDER_THIN);
        //cs2.setAlignment(CellStyle.ALIGN_CENTER);

        //设置列
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }

        //设置值
        for (short i = 1; i < list.size() + 1; i++) {
            Row row1 = sheet.createRow((short) i);
            for (short j = 0; j < keys.length; j++) {
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i - 1).get(keys[j]) == null ? " " : list.get(i - 1).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    private static void exportExcel(String fileName, HttpServletResponse response, ByteArrayOutputStream os) {

        try {
            byte[] bytes = os.toByteArray();
            InputStream is = new ByteArrayInputStream(bytes);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String((fileName + ".xls").getBytes(), "utf-8") + "\"");

            ServletOutputStream out = null;
            try {
                out = response.getOutputStream();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null)
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (bos != null)
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
