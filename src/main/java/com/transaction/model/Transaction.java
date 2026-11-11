
package com.transaction.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {

    private int transactionId;
    private Date date;
    private BigDecimal amount;
    private String description;
    private String type;
    private Integer relatedEntityId;

    public Transaction() {
    }

    public Transaction(int transactionId, Date date,
            BigDecimal amount, String description,
            String type, Integer relatedEntityId) {

        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.relatedEntityId = relatedEntityId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setRelatedEntityId(Integer relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }
}
