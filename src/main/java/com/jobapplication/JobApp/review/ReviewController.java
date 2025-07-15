package com.jobapplication.JobApp.review;

import java.util.List;

import com.jobapplication.JobApp.review.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;

	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
		return new ResponseEntity(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}

	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<String> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
		return new ResponseEntity(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
		Boolean isReviewAdded = reviewService.createReview(companyId, review);
		if (isReviewAdded)
			return new ResponseEntity<>("review added succesfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("review not added, respective company not found", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId,
			@RequestBody Review review) {
		Boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
		if (isUpdated)
			return new ResponseEntity<String>("Review updated successfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Review not found", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
		Boolean isDeleted = reviewService.deleteReviewById(companyId, reviewId);
		if (isDeleted)
			return new ResponseEntity<String>("Review deleted successfully", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Review not found", HttpStatus.NOT_FOUND);

	}

}
