import { Routes, Route, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import MovieCard from "../components/MovieCard";
import MovieDetailCard from "../components/MovieDetailCard";
import type { OMDbMovie } from "../model/OMDbMovie";
import styles from "../css/TrackedMovies.module.css";
import btnStyles from "../css/Button.module.css";

export default function TrackedMoviesPage() {
    const [movies, setMovies] = useState<OMDbMovie[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [selectedMovie, setSelectedMovie] = useState<OMDbMovie | null>(null);
    const navigate = useNavigate();

    const handleLoadTrackedMovies = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get("/api/movies/trackedMovies", {
                params: { trackedID: "user123" },
            });
            setMovies(response.data);
        } catch (err) {
            console.error(err);
            setError("Fehler beim Laden der getrackten Filme.");
        } finally {
            setLoading(false);
        }
    };

    const handleSelectMovie = (movie: OMDbMovie) => {
        setSelectedMovie(movie);
        navigate(`/tracked/${movie.imdbID}`);
    };

    return (
        <div className={styles.container}>
            <button onClick={handleLoadTrackedMovies} className={btnStyles.button}>
                Getrackte Filme laden
            </button>

            {loading && <p className={styles.loading}>Lade getrackte Filme...</p>}
            {error && <p className={styles.error}>{error}</p>}

            <Routes>
                <Route
                    path="/"
                    element={
                        <div className={styles.movieList}>
                            {movies.map((movie) => (
                                <div key={movie.imdbID} onClick={() => handleSelectMovie(movie)}>
                                    <MovieCard movie={movie} />
                                </div>
                            ))}
                        </div>
                    }
                />
                <Route
                    path=":imdbID"
                    element={
                        selectedMovie ? (
                            <MovieDetailCard movie={selectedMovie} />
                        ) : (
                            <p>Film nicht gefunden.</p>
                        )
                    }
                />
            </Routes>
        </div>
    );
}
