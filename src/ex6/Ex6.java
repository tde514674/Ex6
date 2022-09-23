/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstulate file, choose Tools | Tstulates
 * and open the tstulate in the editor.
 */
package ex6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tinip
 */
public class Ex6 {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        String derbyClientDriver = "org.apache.derby.jdbc.ClientDriver";
        //String mySqlDriver = "com.mysql.cj.jdbc.Driver";
        //load driver
        Class.forName(derbyClientDriver);
        String url = "jdbc:derby://localhost:1527/student";
        String user = "app";
        String passwd = "app";

        Connection con = DriverManager.getConnection(url, user, passwd);
        
        Statement stmt = con.createStatement();
        Student stu1 = new Student(1234, "John", 3.50);
        Student stu2 = new Student(5678, "Thomas", 3.00);
        insertStudentPreparedStatement(con, stu1);
        insertStudentPreparedStatement(con, stu2);
        
        stmt.close();
        con.close();
        
    }
    
    public static void insertStudentPreparedStatement(Connection con, Student stu) throws SQLException {
       String sql = "insert into student (id, name, gpa)" + 
               " values (?,?,?)";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, stu.getId());
       ps.setString(2, stu.getName());
       ps.setDouble(3, stu.getGpa());
       int result = ps.executeUpdate();
        //display result
        System.out.println("Insert " + result + " row");
   }
   public static void deleteStudentPreparedStatement(Connection con, Student stu) throws SQLException {
       String sql ="delete from student where id = ?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, stu.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("Delete " + result + " row");
   }
   public static void updateStudentGpaPreparedStatement(Connection con, Student stu) throws SQLException {
       String sql = "update student set gpa  = ? where id = ? ";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setDouble(1, stu.getGpa());
       ps.setInt(2, stu.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("update " + result + " row");
   }
   public static void updateStudentNamePreparedStatement(Connection con, Student stu) throws SQLException {
       String sql = "update student set name  = ? where id = ? ";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setString(1, stu.getName());
       ps.setInt(2, stu.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("update " + result + " row");
   }
   public static Student getStudentByIdPreparedStatement(Connection con, int id) throws SQLException {
       Student stu = null;
       String sql = "select * from student where id = ?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if (rs.next()) {
           stu = new Student();
           stu.setId(rs.getInt("id"));
           stu.setName(rs.getString("name"));
           stu.setGpa(rs.getInt("gpa"));
       }
       return stu;
   }
    
}
