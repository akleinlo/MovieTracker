import { useState } from "react";
import axios from "axios";
import type { OMDbMovie } from "../model/OMDbMovie";
import MovieCard from "./MovieCard";
import styles from "../css/TrackedMovies.module.css";
import btnStyles from "../css/Button.module.css";


export default function TrackedMovies() {
    const [movies, setMovies] = useState<OMDbMovie[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleLoadTrackedMovies = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get("/api/movies/trackedMovies", { params: { trackedID: "user123" } });
            setMovies(response.data);
        } catch (err) {
            console.error(err);
            setError("Fehler beim Laden der getrackten Filme.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className={styles.container}>
            <button onClick={handleLoadTrackedMovies} className={btnStyles.button}>
                Getrackte Filme laden
            </button>

            {loading && <p className={styles.loading}>Lade getrackte Filme...</p>}
            {error && <p className={styles.error}>{error}</p>}

            <div className={styles.movieList}>
                {movies.map(movie => (
                    <div key={movie.imdbID}>
                        <MovieCard movie={movie} />
                    </div>
                ))}
            </div>
        </div>
    );
}
