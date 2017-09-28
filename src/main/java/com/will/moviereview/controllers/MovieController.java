package com.will.moviereview.controllers;

import com.will.moviereview.models.Movie;
import com.will.moviereview.repositories.MovieRepo;
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

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("myMovies", movieRepo.findAll());
        return "index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "add";
    }

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

}
