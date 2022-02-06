package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUtils {

    protected static Seller seller = new Seller();
    protected static Department dep = new Department();

    protected static Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dep);
        return seller;
    }

    protected static Department instantiateDepartment(ResultSet rs) throws SQLException {
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    protected static Department instantiateDepartmentEntity(ResultSet rs) throws SQLException {
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
