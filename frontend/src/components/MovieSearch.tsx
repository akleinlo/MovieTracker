import { useState } from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import MovieDetailCard from "./MovieDetailCard";
import type { OMDbMovieShort } from "../model/OMDbMovieShort";
import type { OMDbMovie } from "../model/OMDbMovie";

export default function MovieSearch() {
    const [movieName, setMovieName] = useState("");
    const [movies, setMovies] = useState<OMDbMovieShort[]>([]);
    const [selectedMovie, setSelectedMovie] = useState<OMDbMovie | null>(null);
    const [error, setError] = useState("");

    // Suche nach Filmen (Short)
    const handleSearch = () => {
        if (!movieName.trim()) return;
        setError("");
        setMovies([]);
        setSelectedMovie(null); // Detailansicht zurücksetzen

        axios.get<OMDbMovieShort[]>(`/api/movies/search?title=${encodeURIComponent(movieName)}`)
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

    // Klick auf eine ShortCard → Detail laden
    const handleSelectMovie = (imdbID: string) => {
        setError("");
        axios.get<OMDbMovie>(`/api/movies/id/${imdbID}`)
            .then(response => setSelectedMovie(response.data))
            .catch(err => {
                console.error(err);
                setError("Fehler beim Laden des Filmdetails");
            });
    };

    return (
        <div style={{ padding: "2rem", fontFamily: "Arial", width: "100%", maxWidth: "800px" }}>
            {/* Suchleiste */}
            <div style={{ marginBottom: "1rem", textAlign: "center" }}>
                <input
                    type="text"
                    placeholder="Filmtitel eingeben"
                    value={movieName}
                    onChange={(e) => setMovieName(e.target.value)}
                    style={{ padding: "0.5rem", width: "60%" }}
                />
                <button
                    onClick={handleSearch}
                    style={{ marginLeft: "1rem", padding: "0.5rem 1rem" }}
                >
                    Suchen
                </button>
            </div>

            {/* Fehleranzeige */}
            {error && <p style={{ color: "red", textAlign: "center" }}>{error}</p>}

            {/* Detailansicht */}
            {selectedMovie ? (
                <MovieDetailCard movie={selectedMovie} />
            ) : (
                // Liste der ShortCards
                <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem", justifyContent: "center" }}>
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
