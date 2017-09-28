package com.will.moviereview.repositories;

import com.will.moviereview.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review, Long> {
}
