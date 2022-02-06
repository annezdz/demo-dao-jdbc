package model.dao;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDaoJDBC implements DepartmentDao{

    private Connection conn;

    private static PreparedStatement st = null;
    private static ResultSet rs = null;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Department department) {
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES "
                            + "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,department.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()) {
                Department dep = DaoUtils.instantiateDepartmentEntity(rs);
                return dep;
            }
            return null;
        }
        catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Id");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while(rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
            }
            return list;
        }
        catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
//  try {
//          st = conn.prepareStatement(
//          "SELECT seller.*,department.Name as DepName "
//          + "FROM seller INNER JOIN department "
//          + "ON seller.DepartmentId = department.Id "
//          + "ORDER BY Name");
//          rs = st.executeQuery();
//          List<Seller> list = new ArrayList<>();
//        Map<Integer, Department> map = new HashMap<>();
//        while(rs.next()) {
//        Department dep = map.get(rs.getInt("DepartmentId"));
//        if(dep == null) {
//        dep = DaoUtils.instantiateDepartment(rs);
//        map.put(rs.getInt("DepartmentId"),dep);
//        }
//        Seller obj = DaoUtils.instantiateSeller(rs,dep);
//        list.add(obj);
//        }
//        return list;
//        }
//        catch (SQLException e) {
//        throw new DbException(e.getMessage());
//        }
//        finally {
//        DB.closeStatement(st);
//        DB.closeResultSet(rs);
//        }
