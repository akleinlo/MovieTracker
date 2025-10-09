import { useState } from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import type {OMDbMovie} from "../model/OMDbMovie";

export default function MovieSearch() {
    const [movieName, setMovieName] = useState("");
    const [movie, setMovie] = useState<OMDbMovie | null>(null);
    const [error, setError] = useState("");

    const handleSearch = () => {
        setError("");
        axios.get<OMDbMovie>(`/api/movies/${encodeURIComponent(movieName)}`)
            .then(response => setMovie(response.data))
            .catch(err => {
                console.error(err);
                setError("Film nicht gefunden oder Serverfehler");
                setMovie(null);
            });
    };

    return (
        <div style={{ padding: "2rem", fontFamily: "Arial" }}>
            <input
                type="text"
                placeholder="Filmtitel eingeben"
                value={movieName}
                onChange={(e) => setMovieName(e.target.value)}
                style={{ padding: "0.5rem", width: "300px" }}
            />
            <button onClick={handleSearch} style={{ marginLeft: "1rem", padding: "0.5rem 1rem" }}>
                Suchen
            </button>
            {error && <p style={{ color: "red" }}>{error}</p>}

            <MovieCard movie={movie} />
        </div>
    );
}
