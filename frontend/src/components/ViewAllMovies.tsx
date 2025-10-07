import {useEffect, useState} from "react";
import type {Movie} from "../model/Movie.tsx";
import axios from "axios";


export default function ViewAllMovies() {
    const [movies, setMovies] = useState<Movie[]>([])

    useEffect(() => {
        axios.get("/api/movies")
            .then(response => setMovies(response.data))
            .catch(error => console.error("Fehler beim Laden der Filme", error));
    }, []);

    return (
        <>
            <h1>Meine Filme</h1>
            {movies.length === 0 ? (
                <p>Keine Filme gefunden</p>
            ) : (
                <ul className="movies-list">
                    {
                        movies.map(movie =>
                            <li key={movie.id}>
                                {movie.author} – <strong>{movie.title}</strong>
                            </li>)
                    }
                </ul>
            )}
        </>
    )
}