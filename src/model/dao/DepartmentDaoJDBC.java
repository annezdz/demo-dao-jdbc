//package model.dao;
//
//import model.entities.Department;
//import model.entities.Seller;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//
//public class DepartmentDaoJDBC implements DepartmentDao{
//
//    private Connection conn;
//
//    private static PreparedStatement st = null;
//    private static ResultSet rs = null;
//    private static Seller seller = new Seller();
//
//
//    public DepartmentDaoJDBC(Connection conn) {
//        this.conn = conn;
//    }
//
//
//    @Override
//    public void insert(Department obj) {
//
//    }
//
//    @Override
//    public void update(Department obj) {
//
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//
//    }
//
//    @Override
//    public Department findById(Integer id) {
//        try {
//            st = conn.prepareStatement(
//                    "SELECT department.*,department.Name as DepName "
//                    +   "FROM department "
//                    +   "WHERE department.Id = ?");
//            st.setInt(1,id);
//            rs = st.executeQuery();
//            if(rs.next()) {
//                Department dep = instantiateDepartment(rs);
//            }
//
//        }
//    }
//
//    @Override
//    public List<Department> findAll() {
//        return null;
//    }
//}
////  try {
////          st = conn.prepareStatement(
////          "SELECT seller.*,department.Name as DepName "
////          + "FROM seller INNER JOIN department "
////          + "ON seller.DepartmentId = department.Id "
////          + "WHERE seller.Id = ?");
////          st.setInt(1,id);
////          rs = st.executeQuery();
////
////          if(rs.next()) {
////          Department dep = instantiateDepartment(rs);
////          Seller obj = instantiateSeller(rs,dep);
////          return obj;
////          }
////          return null;
////          }
////          catch (SQLException e) {
////          throw new DbException(e.getMessage());
////          }
////          finally {
////          DB.closeStatement(st);
////          DB.closeResultSet(rs);
////          }