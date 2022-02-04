package model.dao;

import db.DB;

public class DaoFactory {


    /**
     * Retorna o tipo da interface mas internamente instancia uma implementação,
     * assim não expomos a implementação, mas apenas a interface
     * */
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
