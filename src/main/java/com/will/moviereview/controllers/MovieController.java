package com.will.moviereview.controllers;

import com.will.moviereview.models.Movie;
import com.will.moviereview.models.Review;
import com.will.moviereview.repositories.MovieRepo;
import com.will.moviereview.repositories.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    //lists all movies in table
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("myMovies", movieRepo.findAll());
        return "index";
    }

    //takes you to create movie page
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "add";
    }

    //creates movie
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String index(@ModelAttribute Movie movie) {
        System.out.println(movie);
        movieRepo.save(movie);
        return "redirect:/";
    }

    //get to movie detail/update page
    @RequestMapping(value = "/movie/{id}")
    public String updateMovie(Model model,
                             @PathVariable("id") Long id) {
        Movie myMovie = movieRepo.findOne(id);
        model.addAttribute("movie", myMovie);
        System.out.println(id);
        return "update";
    }

    //updates movie
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.POST)
    public String updateMovieForm(@ModelAttribute Movie movie) {
        movieRepo.save(movie);
        return "redirect:/";
    }

    //lists all reviews
    @RequestMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("myReviews", reviewRepo.findAll());
        return "review";
    }

    //takes you to create review page
    @RequestMapping("/createReview/{id}")
    public String createReviewForm(Model model,
                                   @PathVariable("id") long id) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        return "create";
    }

    //creates review
    @RequestMapping(value = "/createReview/{id}", method = RequestMethod.POST)
    public String createReview(@PathVariable("id") long id,
                               @ModelAttribute Review review) {

        Movie movie = movieRepo.findOne(id);
        review.setMovie(movie);
        reviewRepo.save(review);

        return "redirect:/";

    }

    //lists all reviews for a particular movie
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") long id) {
        Movie movie = movieRepo.findOne(id);
        model.addAttribute("myReviews", movie.getReviews());
        return "detail";
    }

}
