package pl.deen.movies_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.deen.movies_api.SendMail;
import pl.deen.movies_api.model.Movie;
import pl.deen.movies_api.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getMoviesList(){
        List<Movie> moviesList= movieService.getMovieList();
        if(moviesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        Optional<Movie> optionalMovie = movieService.findById(id);
        return optionalMovie.map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @SendMail
    @PostMapping("/")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        if (movieService.addMovie(movie)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.IM_USED);
    }
}
