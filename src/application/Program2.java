package application;

import jdk.swing.interop.SwingInterOpUtils;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program2 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1 : Department findById ===");
        Department department = departmentDao.findById(1);
        System.out.println(department);

        System.out.println();
        System.out.println("=== TEST 2 : Department findAll ===");

        List<Department> list  = departmentDao.findAll();
        for(Department obr : list) {
            System.out.println(obr);
        }
        System.out.println();

        System.out.println("=== TEST 4 : Department insert  ===");
         department = new Department(null,"Drinks");

        departmentDao.insert(department);
        System.out.println("Inserted! New id " + department.getId());

        System.out.println();
        System.out.println("=== TEST 5 : Department update  ===");
        System.out.println("Input the id for update: ");
        int id = scanner.nextInt();
        department = departmentDao.findById(id);
        System.out.println("Input a new Department Name: ");
        String newDepartment = scanner.next();
        department.setName(newDepartment);
        departmentDao.update(department);
        System.out.println("Updtate completed");

        System.out.println();
        System.out.println("=== TEST 6 : Department delete  ===");
        System.out.println("Enter id for delete test = ");
        id = scanner.nextInt();
        departmentDao.deleteById(id);
        System.out.println(id + " deleted successfully");
        scanner.close();
    }
}
