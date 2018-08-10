package com.excel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.excel.dao.ProductDao;
import com.excel.dao.UserDao;
import com.excel.entity.ItemName;
import com.excel.entity.ItemParameter;
import com.excel.entity.Prod;
import com.excel.entity.Product;
import com.excel.service.ImportService;
import com.excel.util.ReadExcel;
import com.excel.util.ReadProExcel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private ProductDao productDao;
    @Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        //ReadExcel readExcel = new ReadExcel();
        ReadProExcel readExcel = new ReadProExcel();
        //解析excel，获取上传的事件单
        List<Product> productList = readExcel.getExcelInfo(file);
//        System.out.println("***********************productList************************"+productList);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
//        for(Product product:productList){
//            int ret = productDao.insertProduct(product);
//            if(ret == 0){
//                result = "插入数据库失败";
//            }
//        }
        List<ItemName> itemNameList = new ArrayList<>();
        if (productList.size() > 0) {
            ItemName itemName = new ItemName();
            itemName.setItemName(productList.get(0).getItemName());
            itemName.setProductNumber(productList.get(0).getBarcodeNumber());
            itemName.setProductName(productList.get(0).getProductName());
            itemName.setProductInfo(productList.get(0).getProductInfo());

            List<ItemParameter> itemParameters = new ArrayList<>();
            ItemParameter itemParameter = new ItemParameter();
            itemParameter.setItemParameter(productList.get(0).getItemParameter());
            itemParameter.setMethod(productList.get(0).getMethod());
            itemParameter.setTool(productList.get(0).getTool());
            itemParameter.setDetail(productList.get(0).getDetail());
            itemParameters.add(itemParameter);

            for (int i = 0; i < productList.size()-1; i++) {
                if (itemName.getProductNumber().equals(productList.get(i + 1).getBarcodeNumber())
                        && itemName.getItemName().equals(productList.get(i + 1).getItemName())) {
                    ItemParameter itemParameter1 = new ItemParameter();
                    itemParameter1.setItemParameter(productList.get(i + 1).getItemParameter());
                    itemParameter1.setMethod(productList.get(i + 1).getMethod());
                    itemParameter1.setTool(productList.get(i + 1).getTool());
                    itemParameter1.setDetail(productList.get(i + 1).getDetail());
                    itemParameters.add(itemParameter1);
                } else {
                    break;
                }
            }
            itemName.setItemParameterList(itemParameters);

            itemNameList.add(itemName);
        }
        for (int i = 0; i < productList.size()-1; i++) {
            if (productList.get(i).getItemName().equals(productList.get(i + 1).getItemName())) {

            } else {
                ItemName itemName = new ItemName();
                itemName.setItemName(productList.get(i+1).getItemName());
                itemName.setProductNumber(productList.get(i+1).getBarcodeNumber());
                itemName.setProductName(productList.get(i+1).getProductName());
                itemName.setProductInfo(productList.get(i+1).getProductInfo());

                List<ItemParameter> itemParameters = new ArrayList<>();

                for (int j = 1; j < productList.size(); j++) {
                    if (itemName.getProductNumber().equals(productList.get(j).getBarcodeNumber())
                            && itemName.getItemName().equals(productList.get(j).getItemName())) {
                        ItemParameter itemParameter1 = new ItemParameter();
                        itemParameter1.setItemParameter(productList.get(j).getItemParameter());
                        itemParameter1.setMethod(productList.get(j).getMethod());
                        itemParameter1.setTool(productList.get(j).getTool());
                        itemParameter1.setDetail(productList.get(j).getDetail());
                        itemParameters.add(itemParameter1);
                    } else {

                    }
                }
                itemName.setItemParameterList(itemParameters);

                itemNameList.add(itemName);
            }
        }
//        System.out.println("**********************itemNameList*************************");
        Gson gson = new Gson();
        System.out.println(gson.toJson(itemNameList));

        List<Prod> prodList = new ArrayList<>();
        if (itemNameList.size() > 0) {
            Prod prod = new Prod();
            prod.setBarcodeNumber(itemNameList.get(0).getProductNumber());
            prod.setProductName(itemNameList.get(0).getProductName());
            prod.setProductInfo(itemNameList.get(0).getProductInfo());
            List<ItemName> itemNames = new ArrayList<>();
            itemNames.add(itemNameList.get(0));
            for (int i = 0; i < itemNameList.size()-1; i++) {
                if (prod.getBarcodeNumber().equals(itemNameList.get(i + 1).getProductNumber())) {
                    itemNames.add(itemNameList.get(i + 1));
                } else {
                    break;
                }
            }
            prod.setItemNameList(itemNames);
            prodList.add(prod);
        }
        for (int i = 0; i < itemNameList.size()-1; i++) {
            if (itemNameList.get(i).getProductNumber().equals(itemNameList.get(i + 1).getProductNumber())) {

            } else {
                Prod prod = new Prod();
                prod.setBarcodeNumber(itemNameList.get(i+1).getProductNumber());
                prod.setProductName(itemNameList.get(i+1).getProductName());
                prod.setProductInfo(itemNameList.get(i+1).getProductInfo());

                List<ItemName> itemNames = new ArrayList<>();

                for (int j = 1; j < itemNameList.size(); j++) {
                    if (prod.getBarcodeNumber().equals(itemNameList.get(j).getProductNumber())) {
                        itemNames.add(itemNameList.get(j));
                    }
                }
                prod.setItemNameList(itemNames);

                prodList.add(prod);
            }
        }
        System.out.println("**********************prodList*************************");
        System.out.println(gson.toJson(prodList));

        productDao.insertProdList(prodList);
        if(productList != null && !productList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }

}
