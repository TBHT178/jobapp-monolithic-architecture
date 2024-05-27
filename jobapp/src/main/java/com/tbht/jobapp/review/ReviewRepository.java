/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tbht.jobapp.review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DELL
 */
public interface ReviewRepository extends JpaRepository<Review, Long>{

    public List<Review> findByCompanyId(Long companyId);
    
}
