import { useState } from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import type { OMDbMovie } from "../model/OMDbMovie";

export default function MovieSearch() {
    const [movieName, setMovieName] = useState("");
    const [movies, setMovies] = useState<OMDbMovie[]>([]);
    const [error, setError] = useState("");

    const handleSearch = () => {
        if (!movieName.trim()) return;
        setError("");
        setMovies([]);

        axios.get<OMDbMovie[]>(`/api/movies/search?title=${encodeURIComponent(movieName)}`)
            .then(response => {
                if (response.data.length === 0) {
                    setError("Keine Filme gefunden");
                } else {
                    setMovies(response.data);
                }
            })
            .catch(err => {
                console.error(err);
                setError("Serverfehler beim Abrufen der Filme");
            });
    };

    return (
        <div style={{ padding: "2rem", fontFamily: "Arial" }}>
            <div style={{ marginBottom: "1rem" }}>
                <input
                    type="text"
                    placeholder="Filmtitel eingeben"
                    value={movieName}
                    onChange={(e) => setMovieName(e.target.value)}
                    style={{ padding: "0.5rem", width: "300px" }}
                />
                <button
                    onClick={handleSearch}
                    style={{ marginLeft: "1rem", padding: "0.5rem 1rem" }}
                >
                    Suchen
                </button>
            </div>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
                {movies.map(movie => (
                    <MovieCard key={movie.imdbID} movie={movie} />
                ))}
            </div>
        </div>
    );
}
