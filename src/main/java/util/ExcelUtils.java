package util;


import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * Excel 操作工具类
 *
 * @author kubin
 * @version V1.0
 * @Package util
 * @date 2020/12/28 10:32
 */
public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String SEPARATOR = ";";

    /**
     * 将Excel转成对象列表
     *
     * @param filePath Excel所在的文件路径
     * @param sheetNo  Excel所在的Sheet
     * @return 返回对象列表
     */
    public static List<Map<Object, Object>> excelToListMap(String filePath, Integer sheetNo, String[] heads) {
        List<Map<Object, Object>> rows = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(sheetNo);
            // 获得表格中第一行数据的索引
            int firstRowNum = sheet.getFirstRowNum();
            // 获得表格中最后一行数据的索引
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum <= firstRowNum) {
                System.out.println("filePath=" + filePath + ",there is no row data");
                LOGGER.info("filePath={} ,there is no row data", filePath);
            }
            // 解析行数据
            for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    System.out.println("filePath=" + filePath + " row=" + i + " is null");
                    LOGGER.info("filePath={} row={} is null", filePath, i);
                    continue;
                }
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();
                if (firstCellNum >= lastCellNum) {
                    System.out.println("filePath=" + filePath + ",there is no cell data");
                    LOGGER.info("filePath={} ,there is no cell data", filePath);
                }
                Map<Object, Object> cells = new HashMap<>(lastCellNum - firstCellNum);
                // 解析列数据
                for (int j = firstCellNum; j < lastCellNum; j++) {
                    Object value = null;
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        // 获取单元格数据
                        value = getCellValue(wb, cell);
                    }
                    Object key = getCellValue(wb, sheet.getRow(firstRowNum).getCell(j));
                    key = exchangeKey(key, heads);
                    cells.put(key, value);
                }
                rows.add(cells);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }

    /**
     * 根据heads中的对应关系修改key值
     *
     * @param key   旧的key值
     * @param heads key值对应关系表
     * @return 返回heads中的对应key值
     */
    public static Object exchangeKey(Object key, String[] heads) {
        for (String head : heads) {
            if (head.contains(key + "")) {
                String[] strings = head.split(SEPARATOR);
                if (strings.length >= 2) {
                    key = strings[1];
                    break;
                }
            }
        }
        return key;
    }

    /**
     * 获得指定表格中指定单元格的值
     *
     * @param wb   指定表格
     * @param cell 指定单元格
     * @return 返回单元格的内容
     */
    public static Object getCellValue(XSSFWorkbook wb, XSSFCell cell) {
        Object value = null;
        if (wb != null) {
            if (cell != null) {
                CellType cellType = cell.getCellTypeEnum();
                XSSFCellStyle style = cell.getCellStyle();
                short format = style.getDataFormat();
                switch (cellType) {
                    // 数值
                    case NUMERIC:
                        double numTxt = cell.getNumericCellValue();
                        if (14 == format || 22 == format) {
                            value = HSSFDateUtil.getJavaDate(numTxt);
                        } else {
                            value = numTxt;
                        }
                        break;
                    // 布尔值
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    // 空白
                    case BLANK:
                        value = null;
                        break;
                    // 公式
                    case FORMULA:
                        XSSFFormulaEvaluator eval = new XSSFFormulaEvaluator(wb);
                        eval.evaluateInCell(cell);
                        value = getCellValue(wb, cell);
                        break;
                    // 字符串
                    case STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    default:
                }
            } else {
                LOGGER.info("cell is null");
            }
        } else {
            LOGGER.info("workbook is null");
        }
        return value;
    }

    /**
     * 根据对象列表和模板生成Excel文件
     *
     * @param data       对象列表
     * @param sourcePath 模板文件所在的路径
     * @param targetPath 生成的Excel的存储路径
     */
    public static void listToExcel(Map<String, Object> data, String sourcePath, String targetPath) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourcePath);
        } catch (FileNotFoundException e) {
            System.out.println("The source file was not found");
            LOGGER.error("The source file was not found");
            throw new RuntimeException("The source file was not found");
        }
        try {
            fos = new FileOutputStream(targetPath);
        } catch (FileNotFoundException e) {
            System.out.println("The target file was not found");
            LOGGER.error("The target file was not found");
            throw new RuntimeException("The target file was not found");
        }
        Context context = PoiTransformer.createInitialContext();
        if (MapUtils.isNotEmpty(data)) {
            data.forEach(context::putVar);
        }
        JxlsHelper instance = JxlsHelper.getInstance();
        try {
            instance.processTemplate(fis, fos, context);
        } catch (IOException e) {
            System.out.println("ListToExcel failed");
            LOGGER.error("ListToExcel failed");
            throw new RuntimeException("ListToExcel failed");
        }
    }

}
