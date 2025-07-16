package com.sagar.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_orders")
public class StudentOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId; // ✅ corrected spelling
    
    private String name;
    private String email;
    private String phno;     // ✅ also fixed getter/setter below
    private String course;
    private Integer amount;
    private String orderStatus;
    private String razorpayOrderid; // used to match with Razorpay order

    // Getters and Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRazorpayOrderid() {
        return razorpayOrderid;
    }

    public void setRazorpayOrderid(String razorpayOrderid) {
        this.razorpayOrderid = razorpayOrderid;
    }

    @Override
    public String toString() {
        return "StudentOrder [orderId=" + orderId + ", name=" + name + ", email=" + email + ", phno=" + phno
                + ", course=" + course + ", amount=" + amount + ", orderStatus=" + orderStatus + ", razorpayOrderid="
                + razorpayOrderid + "]";
    }
}
