package com.excel.dao.impl;

import com.excel.dao.ProductDao;
import com.excel.dao.UserDao;
import com.excel.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductDaoImpl implements ProductDao {

    @Resource
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public int insertProduct(Product product) {
        System.out.println("*******************************************product="+product);
        Session session = this.getSession();
        try {
            session.save(product);
        }catch (Exception e){

        } finally {
            session.close();
        }
        return 1;
    }

    @Override
    public int insertProdList(List<Prod> productList) {
        Session session = this.getSession();
        try {
            for (Prod p : productList) {
//                session.save(p);
//                int pid = p.getId();
//                System.out.println("productID==============="+pid);
                List<ItemName> itemNameList = p.getItemNameList();
                for (ItemName itemName : itemNameList) {
//                    itemName.setProd(this.getProdById(pid));
//                    session.save(itemName);
//                    int iid = itemName.getId();
//                    System.out.println("itemNameID==============="+iid);
                    List<ItemParameter> itemParameterList = itemName.getItemParameterList();
                    for (ItemParameter itemPa : itemParameterList) {
//                        itemPa.setItemName(this.getItemName(iid));
                        session.save(itemPa);
                    }
                }
            }

        }catch (Exception e){

        } finally {
            session.close();
        }
        return 1;
    }

    @Override
    public Prod getProdById(int id) {
        Session session = this.getSession();
        Prod user = new Prod();
        try {
            user = (Prod) session.createQuery("from Prod where userAccount=?").setParameter(0, id).uniqueResult();
        }catch (Exception e){

        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public ItemName getItemName(int id) {
        Session session = this.getSession();
        ItemName user = new ItemName();
        try {
            user = (ItemName) session.createQuery("from ItemName where userAccount=?").setParameter(0, id).uniqueResult();
        }catch (Exception e){

        } finally {
            session.close();
        }
        return user;
    }
}
