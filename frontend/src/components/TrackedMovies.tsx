import { useState } from "react";
import axios from "axios";
import type { OMDbMovie } from "../model/OMDbMovie";
import MovieCard from "./MovieCard";

export default function TrackedMovies() {
    const [movies, setMovies] = useState<OMDbMovie[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleLoadTrackedMovies = async () => {
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get("/api/movies/trackedMovies", {
                params: { trackedID: "user123" } // TODO: falls du ein echtes Tracking-System hast, hier dynamisch setzen
            });
            setMovies(response.data);
        } catch (err) {
            console.error("Fehler beim Laden der getrackten Filme:", err);
            setError("Fehler beim Laden der getrackten Filme.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ textAlign: "center", padding: "1rem" }}>
            <button
                onClick={handleLoadTrackedMovies}
                style={{
                    padding: "0.5rem 1rem",
                    borderRadius: "8px",
                    backgroundColor: "#3b82f6",
                    color: "white",
                    border: "none",
                    cursor: "pointer",
                    marginBottom: "1rem"
                }}
            >
                Getrackte Filme laden
            </button>

            {loading && <p>Lade getrackte Filme...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            <div
                style={{
                    display: "flex",
                    flexWrap: "wrap",
                    gap: "1rem",
                    justifyContent: "center"
                }}
            >
                {movies.map((movie) => (
                    <div key={movie.imdbID}>
                        <MovieCard movie={movie} />
                    </div>
                ))}
            </div>
        </div>
    );
}
