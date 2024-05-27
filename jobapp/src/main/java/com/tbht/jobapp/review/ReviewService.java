/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tbht.jobapp.review;

import java.util.List;

/**
 *
 * @author DELL
 */
public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review  getReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review updateReview);
    boolean deleteReview(Long companyId, Long reviewId);
}
