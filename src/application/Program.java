package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

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

        System.out.println("=== TEST 2 : seller findByDepartment ===");

        Department department1 = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department1);
        for(Seller obr : list) {
            System.out.println(obr);
        }



    }
}
