package com.sagar.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sagar.dto.StudentOrder;

@Repository
public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer> {

    // Correctly matches the field name in StudentOrder entity
    StudentOrder findByRazorpayOrderid(String razorpayOrderid);
}
