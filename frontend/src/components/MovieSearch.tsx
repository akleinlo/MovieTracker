import { useState } from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import MovieDetailCard from "./MovieDetailCard";
import type { OMDbMovieShort } from "../model/OMDbMovieShort";
import type { OMDbMovie } from "../model/OMDbMovie";
import styles from "../css/MovieSearch.module.css";

export default function MovieSearch() {
    const [movieName, setMovieName] = useState("");
    const [movies, setMovies] = useState<OMDbMovieShort[]>([]);
    const [selectedMovie, setSelectedMovie] = useState<OMDbMovie | null>(null);
    const [error, setError] = useState("");

    const handleSearch = () => {
        if (!movieName.trim()) return;
        setError("");
        setMovies([]);
        setSelectedMovie(null);

        axios.get<OMDbMovieShort[]>(`/api/movies/search?title=${encodeURIComponent(movieName)}`)
            .then(response => {
                if (response.data.length === 0) setError("Keine Filme gefunden");
                else setMovies(response.data);
            })
            .catch(() => setError("Serverfehler beim Abrufen der Filme"));
    };

    const handleSelectMovie = (imdbID: string) => {
        setError("");
        axios.get<OMDbMovie>(`/api/movies/id/${imdbID}`)
            .then(response => setSelectedMovie(response.data))
            .catch(() => setError("Fehler beim Laden des Filmdetails"));
    };

    return (
        <div className={styles.searchContainer}>
            <div className={styles.searchBar}>
                <input
                    type="text"
                    placeholder="Filmtitel eingeben"
                    value={movieName}
                    onChange={(e) => setMovieName(e.target.value)}
                    className={styles.input}
                />
                <button onClick={handleSearch} className={styles.button}>Suchen</button>
            </div>

            {error && <p className={styles.error}>{error}</p>}

            {selectedMovie ? (
                <MovieDetailCard movie={selectedMovie} />
            ) : (
                <div className={styles.moviesList}>
                    {movies.map(movie => (
                        <div key={movie.imdbID} onClick={() => handleSelectMovie(movie.imdbID)}>
                            <MovieCard movie={movie} />
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
