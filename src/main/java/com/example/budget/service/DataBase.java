package com.example.budget.service;
import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBase {
    final static String URL = "jdbc:mysql://127.0.0.1:3306/budget" +
            "?serverTimezone=Europe/Moscow&useSSL = false";
    final static String LOGIN = "root";
    final static String PASSWORD = "Ltwbvf";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;

    }


    public static ArrayList<BalanceObject> select() {
        ArrayList<BalanceObject> resultList = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM budget.balance_object");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getNString("name");



               BalanceType type = switch (resultSet.getInt("type")){

                    case 1 -> BalanceType.EXPENSE;
                   default -> BalanceType.INCOME;
                };
                

                
                 

                LocalDate date = resultSet.getDate("date").toLocalDate();
                double amount = resultSet.getDouble("amount");
                resultList.add(new BalanceObject(id, name, date, amount, type));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
            return resultList;
    }

    public static void insert(BalanceObject balanceObject){
        String sql = "INSERT INTO `balance_object`(name, date, type, amount) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, balanceObject.getName());
            preparedStatement.setDate(2,Date.valueOf(balanceObject.getDate()));
            preparedStatement.setInt(3,balanceObject.getType().ordinal());
            preparedStatement.setDouble(4, balanceObject.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update (BalanceObject balanceObject){
        String sql = "UPDATE `budget`.`balance_object` SET `name` = ?, `date` = ?, type = ?, `amount` = ? WHERE (`id` = ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, balanceObject.getName());
            preparedStatement.setDate(2,Date.valueOf(balanceObject.getDate()));
            preparedStatement.setInt(3,balanceObject.getType().ordinal());
            preparedStatement.setDouble(4, balanceObject.getAmount());
            preparedStatement.setInt(5,balanceObject.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            }
            public static void delete (BalanceObject balanceObject){
        String sql = "DELETE FROM `budget`.`balance_object` WHERE (`id` = ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, balanceObject.getId());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
}


