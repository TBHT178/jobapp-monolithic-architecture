/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tbht.jobapp.review.impl;

import com.tbht.jobapp.company.Company;
import com.tbht.jobapp.company.CompanyService;
import com.tbht.jobapp.review.Review;
import com.tbht.jobapp.review.ReviewRepository;
import com.tbht.jobapp.review.ReviewService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updateReview.setCompany(companyService.getCompanyById(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            if (companyService.getCompanyById(companyId) != null 
                    && reviewRepository.existsById(reviewId)) 
            {
                Review review  = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                company.getReviews().remove(review);
                review.setCompany(null);
                companyService.updateCompany(companyId, company);
                reviewRepository.deleteById(reviewId);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
