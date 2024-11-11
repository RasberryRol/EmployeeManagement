package com.rolson.employeemanagement.DAO;

import com.rolson.employeemanagement.models.Employee;
import com.rolson.employeemanagement.utils.DBConnection;

import java.sql.*;
import java.util.Random;

public class UserDAO {
    DBConnection dbConnection = new DBConnection();
    Random random = new Random();

    public int saveUser(Employee employee){
        int execution = 0;

        String query = "insert into user (id, name, email, phone, role, username, password) values(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, employee.getEmpID());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getRole());
            stmt.setString(6, employee.getUsername());
            //password should be temporary. They'll need access to change it later
            stmt.setString(7, employee.getPassword());

            execution = stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            int empID = Integer.parseInt(String.format("%04d", random.nextInt(10000)));
            employee.setEmpID(empID);
            saveUser(employee);
            execution = 1;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return execution;
    }

    public Boolean updateUser(Employee employee, int id){
        boolean userSaved = false;

        String query = "update user set id=?, name=?, email=?, phone=?, role=?, username=?, password=? where id=?;";

        try (Connection conn = dbConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, employee.getEmpID());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getRole());
            stmt.setString(6, employee.getUsername());
            stmt.setString(7, employee.getPassword());
            stmt.setInt(8, id);
            userSaved = stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return userSaved;
    }

    public Employee checkManagerAndEmployeeTables(String username, String password) {
        Employee emp = null;

        try(Connection conn = dbConnection.getConnection()){

            String query = "select id, name, email, phone, role, username, password from user where username=? and password = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int empID = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                String uName = rs.getString("username");
                String pass = rs.getString("password");

                emp = new Employee(empID, name, email, phone, role, uName, pass);
            }

            if(emp == null){
                emp = checkManagerTable(username, password);
            }else {
                return emp;
            }

            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
       return emp;
    }


    public Employee checkManagerTable(String username, String password) {
        //change everything labeled employee to manager once model is created
        Employee emp = null;

        try(Connection conn = dbConnection.getConnection()){

            String query = "select id, name, email, phone, role, username, password from manager where username=? and password = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int empID = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                String uName = rs.getString("username");
                String pass = rs.getString("password");

                emp = new Employee(empID, name, email, phone, role, uName, pass);
            }

            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return emp;
    }


    public Employee checkEmployeeTable(String username, String password) {
        //change everything labeled employee to manager once model is created
        Employee emp = null;
        System.out.println("came to check emp table " + username + "space" +password);

        try(Connection conn = dbConnection.getConnection()){

            String query = "select id, name, email, phone, role, username, password from user where username=? and password = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int empID = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");
                String uName = rs.getString("username");
                String pass = rs.getString("password");

                emp = new Employee(empID, name, email, phone, role, uName, pass);
            }

            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return emp;
    }


    public int SaveEmployeeMessage(String message, String status, String username, String password) {
        System.out.println(username + "space" + password);
        Employee emp = checkEmployeeTable(username, password);
        String query = "insert into employee_message (employeeId, message, status) values(?, ?, ?)";

        int saved = 0;

        try(Connection conn = dbConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, emp.getEmpID());
            stmt.setString(2, message);
            stmt.setString(3, status);

            saved = stmt.executeUpdate();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return saved;
    }
}
