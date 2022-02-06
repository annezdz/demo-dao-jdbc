package application;

import jdk.swing.interop.SwingInterOpUtils;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program2 {
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



    }
}
