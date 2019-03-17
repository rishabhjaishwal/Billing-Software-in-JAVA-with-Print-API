/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstbilling.employee;

/**
 *
 * @author Shivam Kumar
 */
public class User {
    int sno;
    private double cgst,sgst,igst,price,qty,amount,discount;
    private String product,unit;

    public int getSno() {
        return sno;
    }

    public double getCgst() {
        return cgst;
    }
    public double getdiscount() {
        return discount;
    }

    public double getSgst() {
        return sgst;
    }

    public double getIgst() {
        return igst;
    }

    public double getPrice() {
        return price;
    }

    public double getQty() {
        return qty;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
public Double getDiscount() {
        return discount;
    }

    public String getProduct() {
        return product;
    }

    public User(int sno, double cgst, double sgst, double igst, double price, double qty, double amount, String unit, String product,Double discount) {
        this.sno = sno;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
        this.price = price;
        this.qty = qty;
        this.amount = amount;
        this.unit = unit;
        this.product = product;
        this.discount=discount;
    }
    
}
