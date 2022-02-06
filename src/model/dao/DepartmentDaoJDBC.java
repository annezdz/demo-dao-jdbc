package model.dao;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DepartmentDaoJDBC implements DepartmentDao{

    private Connection conn;

    private static PreparedStatement st = null;
    private static ResultSet rs = null;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Department obj) {

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
                Department dep = new Department();
                dep = DaoUtils.instantiateDepartmentEntity(rs);
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
        return null;
    }
}
