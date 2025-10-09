import type { OMDbMovie } from "../model/OMDbMovie";

type Props = {
    movie: OMDbMovie;
};

export default function MovieDetailCard({ movie }: Props) {
    return (
        <div style={{
            border: "1px solid gray",
            padding: "1rem",
            borderRadius: "8px",
            maxWidth: "600px",
            margin: "0 auto",
            textAlign: "center",
            backgroundColor: "#111",
            color: "#fff"
        }}>
            <h2>{movie.Title} ({movie.Year})</h2>
            {movie.Poster && (
                <img src={movie.Poster} alt={movie.Title} style={{ maxWidth: "300px", margin: "1rem 0" }} />
            )}
            <p><strong>Released:</strong> {movie.Released}</p>
            <p><strong>Runtime:</strong> {movie.Runtime}</p>
            <p><strong>Genre:</strong> {movie.Genre}</p>
            <p><strong>Director:</strong> {movie.Director}</p>
            <p><strong>Writer:</strong> {movie.Writer}</p>
            <p><strong>Actors:</strong> {movie.Actors}</p>
            <p><strong>Plot:</strong> {movie.Plot}</p>
            <p><strong>Language:</strong> {movie.Language}</p>
            <p><strong>IMDB Rating:</strong> {movie.imdbRating}</p>
        </div>
    );
}
