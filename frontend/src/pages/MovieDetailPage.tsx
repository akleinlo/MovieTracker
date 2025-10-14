import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import type { OMDbMovie } from "../model/OMDbMovie";
import MovieDetailCard from "../components/MovieDetailCard.tsx";

export default function MovieDetailPage() {
    const { imdbID } = useParams();
    const [movie, setMovie] = useState<OMDbMovie | null>(null);
    const [error, setError] = useState("");

    useEffect(() => {
        if (!imdbID) return;
        axios.get(`/api/movies/id/${imdbID}`)
            .then(res => setMovie(res.data))
            .catch(err => {
                console.error(err);
                setError("Fehler beim Laden der Filmdetails");
            });
    }, [imdbID]);

    if (error) return <p style={{ color: "red" }}>{error}</p>;
    if (!movie) return <p>Lade Details...</p>;

    return <MovieDetailCard movie={movie} />;
}
