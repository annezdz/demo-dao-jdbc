package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department department = new Department(1,"books");

      //  Seller seller = new Seller(21,"Bob","bob@gmail.com",new Date(),3000.0,department);

        //Dessa forma com o DaoFactory o programa conhece apenas as interfaces, e não conhece a implementação
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1 : seller findById ===");
        Seller sellerFindById = sellerDao.findById(3);

      //  System.out.println(department);
        System.out.println(sellerFindById);


    }
}
