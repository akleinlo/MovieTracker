import React from "react";
import type {OMDbMovie} from "../model/OMDbMovie";

type Props = {
    movie: OMDbMovie | null;
};

export default function MovieCard({ movie }: Props) {
    if (!movie) return null;

    return (
        <div style={{ border: "1px solid gray", padding: "1rem", marginTop: "1rem" }}>
            <h2>{movie.Title} ({movie.Year})</h2>
            <p><strong>Director:</strong> {movie.Director}</p>
            <p><strong>Actors:</strong> {movie.Actors}</p>
            <p><strong>Genre:</strong> {movie.Genre}</p>
            <p><strong>Plot:</strong> {movie.Plot}</p>
            <img src={movie.Poster} alt={movie.Title} style={{ maxWidth: "200px", marginTop: "1rem" }} />
        </div>
    );
}
