
package com.transaction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.transaction.model.Transaction;
import com.transaction.util.DBConnection;

public class TransactionDAO {

    // CREATE: Insert a new transaction
    public boolean addTransaction(Transaction t) {

        String sql = "INSERT INTO `Transaction` "
                + "(`date`, amount, description, `type`, related_entity_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, t.getDate());
            ps.setBigDecimal(2, t.getAmount());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getType());

            if (t.getRelatedEntityId() != null) {
                ps.setInt(5, t.getRelatedEntityId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ: Retrieve all transactions
    public List<Transaction> getAllTransactions() {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM `Transaction` "
                + "ORDER BY `date` DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Transaction t = new Transaction();

                t.setTransactionId(rs.getInt("transaction_id"));
                t.setDate(rs.getDate("date"));
                t.setAmount(rs.getBigDecimal("amount"));
                t.setDescription(rs.getString("description"));
                t.setType(rs.getString("type"));

                int entityId = rs.getInt("related_entity_id");

                if (rs.wasNull()) {
                    t.setRelatedEntityId(null);
                } else {
                    t.setRelatedEntityId(entityId);
                }

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // READ: Retrieve a transaction by ID
    public Transaction getTransactionById(int id) {

        String sql = "SELECT * FROM `Transaction` "
                + "WHERE transaction_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Transaction t = new Transaction();

                    t.setTransactionId(rs.getInt("transaction_id"));
                    t.setDate(rs.getDate("date"));
                    t.setAmount(rs.getBigDecimal("amount"));
                    t.setDescription(rs.getString("description"));
                    t.setType(rs.getString("type"));

                    int entityId = rs.getInt("related_entity_id");

                    if (rs.wasNull()) {
                        t.setRelatedEntityId(null);
                    } else {
                        t.setRelatedEntityId(entityId);
                    }

                    return t;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE: Modify an existing transaction
    public boolean updateTransaction(Transaction t) {

        String sql = "UPDATE `Transaction` SET "
                + "`date` = ?, amount = ?, description = ?, "
                + "`type` = ?, related_entity_id = ? "
                + "WHERE transaction_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, t.getDate());
            ps.setBigDecimal(2, t.getAmount());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getType());

            if (t.getRelatedEntityId() != null) {
                ps.setInt(5, t.getRelatedEntityId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setInt(6, t.getTransactionId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE: Remove a transaction
    public boolean deleteTransaction(int id) {

        String sql = "DELETE FROM `Transaction` "
                + "WHERE transaction_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
