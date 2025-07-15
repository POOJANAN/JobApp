package com.jobapplication.JobApp.review;

import java.util.List;

public interface ReviewService {

	 List<Review> getAllReviews(Long companyId);
	  
	 Review getReviewById(Long companyId , Long reviewId);
	 
	 Boolean deleteReviewById(Long companyId, Long reviewId);
	 
	 Boolean updateReview(Long companyId,Long reviewId, Review review);
	 
	 Boolean createReview(Long companyId,Review review);
}
