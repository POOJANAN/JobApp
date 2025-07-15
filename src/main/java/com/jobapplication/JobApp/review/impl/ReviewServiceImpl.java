package com.jobapplication.JobApp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobapplication.JobApp.company.Company;
import com.jobapplication.JobApp.company.CompanyService;
import com.jobapplication.JobApp.review.Review;
import com.jobapplication.JobApp.review.ReviewRepository;
import com.jobapplication.JobApp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	private CompanyService companyService;

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
	public Review getReviewById(Long companyId, Long reviewId) {
		// TODO Auto-generated method stub
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);

		return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public Boolean deleteReviewById(Long companyId, Long reviewId) {
		// TODO Auto-generated method stub
		if (companyService.getCompany(companyId) != null && reviewRepository.existsById(reviewId)) {
			Review review = reviewRepository.findById(reviewId).orElse(null);
			Company company = review.getCompany();
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(companyId, company);
			reviewRepository.delete(review);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateReview(Long companyId, Long reviewId, Review review) {
		// TODO Auto-generated method stub

		if (companyService.getCompany(companyId) != null) {
			review.setCompany(companyService.getCompany(companyId));
			review.setId(reviewId);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Boolean createReview(Long companyId, Review review) {
		// TODO Auto-generated method stub
		Company company = companyService.getCompany(companyId);
		if (company != null) {
			review.setCompany(company);
			reviewRepository.save(review);
			return true;
		} else {
			return false;
		}

	}

}
