package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static Scanner scanner = new Scanner(System.in);

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

        System.out.println();
        System.out.println("=== TEST 3 : seller findAll ===");

        list = sellerDao.findAll();
        for(Seller obr : list) {
            System.out.println(obr);
        }

        System.out.println("=== TEST 4 : seller insert  ===");
        Seller newSeller = new Seller(null,"Greg","greg@gmail.com",
                new Date(), 400.00,department1);

        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id " + newSeller.getId());

        System.out.println();
        System.out.println("=== TEST 5 : seller update  ===");
        newSeller = sellerDao.findById(1);
        newSeller.setName("Eduardo Dudu");
        sellerDao.update(newSeller);
        System.out.println("Updtate completed");

        System.out.println();
        System.out.println("=== TEST 6 : seller delete  ===");
        System.out.println("Enter id for delete test = ");
        int id = scanner.nextInt();
        sellerDao.deleteById(id);
        System.out.println(id + " deleted successfully");
        scanner.close();

    }
}
