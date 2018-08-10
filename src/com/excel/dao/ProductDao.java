package com.excel.dao;

import com.excel.entity.ItemName;
import com.excel.entity.Prod;
import com.excel.entity.Product;

import java.util.List;

public interface ProductDao {
    public int insertProduct(Product product);
    public int insertProdList(List<Prod> productList);
    public Prod getProdById(int id);
    public ItemName getItemName(int id);
}
