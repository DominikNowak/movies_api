package pl.deen.movies_api.service;

import org.springframework.stereotype.Service;
import pl.deen.movies_api.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    List<Movie> movieList;

    public MovieService() {
        movieList = new ArrayList<>();
        movieList.add(new Movie(1, "Nietykalni", "Quad Productions", 2012));
        movieList.add(new Movie(2, "Forrest Gump", "Paramount Pictures", 1994));
        movieList.add(new Movie(3, "Wilk z Wall Street", "Paramount Pictures", 2013));
    }

    public Optional<Movie> findById(long id) {
        return movieList.stream().filter(movie -> movie.getId() == id).findFirst();
    }

    public boolean addMovie(Movie newMovie) {
        Optional<Movie> optionalMovie = movieList.stream().filter(movie -> movie.getId() == newMovie.getId()).findFirst();
        if (optionalMovie.isPresent()) {
            return false;
        }
        return movieList.add(newMovie);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
