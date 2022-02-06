package model.dao;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;
    private static PreparedStatement st = null;
    private static ResultSet rs = null;
    private static DaoUtils daoUtils;
    private static Seller seller = new Seller();
    private static Department dep = new Department();


    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller obj) {
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
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
    public void update(Seller obj) {
        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                      + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                      + "WHERE Id = ? ");
            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ? ");
            st.setInt(1,id);
            int rows = st.executeUpdate();
            if(rows == 0) {
                throw new DbException("Id not existent!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Seller findById(Integer id) {
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE seller.Id = ?");

            st.setInt(1,id);

            rs = st.executeQuery();

            if(rs.next()) {
                Department dep = DaoUtils.instantiateDepartment(rs);
                Seller seller = DaoUtils.instantiateSeller(rs,dep);
                return seller;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        try {
            st = conn.prepareStatement(
                     "SELECT seller.*,department.Name as DepName "
                       + "FROM seller INNER JOIN department "
                       + "ON seller.DepartmentId = department.Id "
                       + "ORDER BY Name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()) {
                /**
                 * Nesse Map, se o Department existir pegamos ele com o get,
                 * senão, caso seja null, instanciamos ele.
                 * Nesse caso instanciamos todos os vendedores sem a repetição
                 * de Departamento
                 * */
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null) {
                    dep = DaoUtils.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }
                Seller obj = DaoUtils.instantiateSeller(rs,dep);
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                      + "FROM seller INNER JOIN department "
                      + "ON seller.DepartmentId = department.Id "
                      + "WHERE DepartmentId = ? "
                      + "ORDER BY Name");

            st.setInt(1, department.getId());

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()) {
                dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null) {
                    dep = DaoUtils.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }
                seller = DaoUtils.instantiateSeller(rs,dep);
                list.add(seller);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
