import type { OMDbMovieShort } from "../model/OMDbMovieShort"; // neues Interface für ShortDto

type Props = {
    movie: OMDbMovieShort;
};

export default function MovieCard({ movie }: Props) {
    return (
        <div style={{
            border: "1px solid gray",
            padding: "1rem",
            marginTop: "1rem",
            borderRadius: "8px",
            maxWidth: "300px",
            textAlign: "center",
            backgroundColor: "black"
        }}>
            <h2>{movie.Title} ({movie.Year})</h2>
            {movie.Poster ? (
                <img src={movie.Poster} alt={movie.Title} style={{ maxWidth: "200px", marginTop: "1rem" }} />
            ) : (
                <div style={{
                    width: "200px",
                    height: "300px",
                    backgroundColor: "#ddd",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    marginTop: "1rem",
                    color: "#666"
                }}>
                    Kein Poster
                </div>
            )}
        </div>
    );
}
