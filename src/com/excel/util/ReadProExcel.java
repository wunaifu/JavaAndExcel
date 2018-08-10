package com.excel.util;

import com.excel.entity.ItemName;
import com.excel.entity.ItemParameter;
import com.excel.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hewangtong
 *
 */
public class ReadProExcel {
    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadProExcel() {
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @param mFile
     * @return
     */
    public List<Product> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
//        List<Map<String, Object>> userList = new LinkedList<Map<String, Object>>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<Product> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<Product> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        System.out.println("****************************totalRows="+totalRows);
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        System.out.println("****************************totalCells="+totalCells);
        List<Product> productList = new ArrayList<>();
        String number = "";
        String name = "";
        String info = "";
        String itme = "";
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Product product = new Product();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            if ("".equals(str.trim())) {
                                product.setBarcodeNumber(number);
                            } else {
                                number = str;
                                product.setBarcodeNumber(str);
                            }
                        } else {
                            String str = cell.getStringCellValue();
                            if ("".equals(str.trim())) {
                                product.setBarcodeNumber(number);
                            } else {
                                number = str;
                                product.setBarcodeNumber(str);
                            }
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            if ("".equals(str.trim())) {
                                product.setProductName(name);
                            } else {
                                name = str;
                                product.setProductName(str);
                            }
                        } else {
                            String str = cell.getStringCellValue();
                            if ("".equals(str.trim())) {
                                product.setProductName(name);
                            } else {
                                name = str;
                                product.setProductName(str);
                            }
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            if ("".equals(str.trim())) {
                                product.setProductInfo(info);
                            } else {
                                info = str;
                                product.setProductInfo(str);
                            }
                        } else {
                            String str = cell.getStringCellValue();
                            if ("".equals(str.trim())) {
                                product.setProductInfo(info);
                            } else {
                                info = str;
                                product.setProductInfo(str);
                            }
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            if ("".equals(str.trim())) {
                                product.setItemName(itme);
                            } else {
                                itme = str;
                                product.setItemName(str);
                            }
                        } else {
                            String str = cell.getStringCellValue();
                            if ("".equals(str.trim())) {
                                product.setItemName(itme);
                            } else {
                                itme = str;
                                product.setItemName(str);
                            }
                        }
                    } else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            product.setItemParameter(str);
                        } else {
                            String str = cell.getStringCellValue();
                            product.setItemParameter(str);
                        }
                    } else if (c == 5) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            product.setMethod(str);
                        } else {
                            String str = cell.getStringCellValue();
                            product.setMethod(str);
                        }
                    } else if (c == 6) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            product.setTool(str);
                        } else {
                            String str = cell.getStringCellValue();
                            product.setTool(str);
                        }
                    } else if (c == 7) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String str = String.valueOf(cell.getNumericCellValue());
                            product.setDetail(str);
                        } else {
                            String str = cell.getStringCellValue();
                            product.setDetail(str);
                        }
                    }
                }
            }
            // 添加到list
            productList.add(product);
        }
        return productList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
