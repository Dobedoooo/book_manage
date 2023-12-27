package xing_ming_pin_yin.JDBC;

import xing_ming_pin_yin.pojo.Book;

import java.sql.*;
import java.util.Vector;

public class MyJDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT%2B8";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";

    private static Connection openConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }
    public static void query(){
        Connection connention = null;
        PreparedStatement pstmt = null;
        String sql = "select * from book";
        try{
            connention = openConn();
            pstmt = connention.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setIsbn(String.valueOf(rs.getString("isbn")));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("language"));
                book.setPrice(rs.getDouble("price"));
                System.out.println(book);
            }
            rs.close();
            pstmt.close();
            connention.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            if(connention!=null){
                try {
                    connention.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt!=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insert(Book book){
        Connection connention = null;
        PreparedStatement pstmt = null;
        String sql = "insert into book values (?,?,?,?,?)";
        try {
            connention = openConn();
            pstmt = connention.prepareStatement(sql);
            pstmt.setString(1,book.getIsbn());
            pstmt.setString(2,book.getTitle());
            pstmt.setString(3,book.getPublisher());
            pstmt.setString(4,book.getLanguage());
            pstmt.setDouble(5,book.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(connention!=null){
                try {
                    connention.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt!=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static Vector<String> queryAsPublisher(String publisher) {
        Connection connention = null;
        PreparedStatement pstmt = null;
        String sql = "select * from book where publisher = ?";
        Vector<String> vector = new Vector<>();
        try{
            connention = openConn();
            pstmt = connention.prepareStatement(sql);
            pstmt.setString(1,publisher);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setIsbn(String.valueOf(rs.getString("isbn")));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("language"));
                book.setPrice(rs.getDouble("price"));
                vector.add(book.toString());
            }
            rs.close();
            pstmt.close();
            connention.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            if(connention!=null){
                try {
                    connention.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt!=null){
                try {
                    pstmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return vector;
    }

//    public static void main(String[] args) {
//        Vector<String> vector = queryAsPublisher("高等教育出版社");
//        System.out.println(vector.get(0));
//    }
}
