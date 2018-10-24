package com.xcy.controller;

import com.xcy.entity.Commodity;
import com.xcy.service.CommodityService;
import com.xcy.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xcy
 * @date 2018/09/18 16:26
 * @description ExcelController
 * @since V1.0.0
 */
@Controller
@RequestMapping("excel")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    CommodityService commodityService;


    //解析上传的Excel
    @ResponseBody
    @RequestMapping(value = "insertEntryByExcel", method = RequestMethod.POST)
    public String insertEntryByExcel(MultipartFile excelUploadFile) {
        String result = "success";
        try {

            //for03(excelUploadFile);

            //for07(excelUploadFile);

            for03And07(excelUploadFile);

        } catch (Exception e) {
            logger.info("流读取错误:{}", e.getMessage());
            result = "error: " + e.getMessage();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("test")
    public String test(String name) {
        System.out.println(name);
        logger.info("输入:{}", name);
        return "hello";
    }

    //从数据库导出数据成Excel
    //http://localhost:8123/excel/exportCommodityList
    @RequestMapping(value = "exportCommodityList")
    public void exportCommodityList(HttpServletResponse response) {
        List<Commodity> commodityList = commodityService.list();
        logger.info("controller层返回的条数:{}", commodityList.size());

        ArrayList<Map<String, Object>> newArrayList = new ArrayList<>();

        for (Commodity commodity : commodityList) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("commodityName", commodity.getCommodityName());
            map.put("commodityModel", commodity.getCommodityModel());
            map.put("commodityNum", commodity.getCommodityNum());
            map.put("commodityUnit", commodity.getCommodityUnit());
            map.put("commodityUnitPrice", commodity.getCommodityUnitPrice());
            map.put("commodityBrand", commodity.getCommodityBrand());
            map.put("commodityPlace", commodity.getCommodityPlace());
            map.put("commodityCode", commodity.getCommodityCode());
            map.put("commodityNetWeight", commodity.getCommodityNetWeight());
            map.put("commodityGrossWeight", commodity.getCommodityGrossWeight());
            map.put("commodityBoxNum", commodity.getCommodityBoxNum());
            map.put("commodityPo", commodity.getCommodityPo());
            map.put("commodityBatchNum", commodity.getCommodityBatchNum());
            map.put("commodityReceiveAddress", commodity.getCommodityReceiveAddress());
            map.put("commodityLogisticsDealer", commodity.getCommodityLogisticsDealer());

            newArrayList.add(map);
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "物料信息" + sdf.format(new Date());
        String sheetName = "物料信息Sheet1";
        String[] keys = {"commodityName", "commodityModel", "commodityNum", "commodityUnit", "commodityUnitPrice", "commodityBrand", "commodityPlace",
                "commodityCode", "commodityNetWeight", "commodityGrossWeight", "commodityBoxNum", "commodityPo", "commodityBatchNum",
                "commodityReceiveAddress", "commodityLogisticsDealer"};
        String[] columnNames = {"名称", "型号", "数量", "单位", "单价", "品牌", "产地", "编码", "净重", "毛重", "箱数", "PO", "批号", "收货地址", "物流商"};

        ExcelUtil.exportExcelByList(newArrayList, sheetName, keys, columnNames, fileName, response);

    }




    //只支持2003版[.xls]的excel
    private static void for03(MultipartFile excelUploadFile) throws IOException {
        InputStream inputStream = excelUploadFile.getInputStream();
        //Excel2003以前（包括2003）[.xls]的版本没有问题，但读取Excel2007时发生异常
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            for (int i = 0; i < 30; i++) {
                logger.info("第" + (rowNum + 1) + "行,第" + i + "列的值是:{}", hssfRow.getCell(i));
                //获取到excel文件里的每个字段后,可以封装到实体调用dao将数据插入数据库.
            }
        }
    }

    //只支持2007版[.xlsx]的excel
    private static void for07(MultipartFile excelUploadFile) throws IOException {
        InputStream inputStream = excelUploadFile.getInputStream();
        //Excel2007[.xlsx]没有问题了，可是在读取Excel2003以前（包括2003）的版本时却发生异常
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

        for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfrow = xssfSheet.getRow(rowNum);
            for (int i = 0; i < 30; i++) {
                logger.info("第" + (rowNum + 1) + "行,第" + (i + 1) + "列的值是:{}", xssfrow.getCell(i));
                //获取到excel文件里的每个字段后,可以封装到实体调用dao将数据插入数据库.
            }
        }
    }

    //同时兼容2003和2007版的excel
    private static void for03And07(MultipartFile excelUploadFile) throws IOException {
        String fileName = excelUploadFile.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }

        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        InputStream inputStream = excelUploadFile.getInputStream();
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            for (int i = 0; i < 30; i++) {
                logger.info("第" + (rowNum + 1) + "行,第" + (i + 1) + "列的值是:{}", row.getCell(i));
                //获取到excel文件里的每个字段后,可以封装到实体调用dao将数据插入数据库.
            }

        }
    }
}