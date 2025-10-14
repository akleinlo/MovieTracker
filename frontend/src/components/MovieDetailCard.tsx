import type { OMDbMovie } from "../model/OMDbMovie";
import styles from "../css/MovieDetailCard.module.css";
import btnStyles from "../css/Button.module.css";


type Props = {
    movie: OMDbMovie;
};

export default function MovieDetailCard({ movie }: Props) {

    const handleAddMovie = async () => {
        try {
            const response = await fetch('/api/movies/add', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(movie)
            });
            const savedMovie = await response.json();
            alert(`Film gespeichert: ${savedMovie.Title}`);
        } catch (error) {
            console.error(error);
            alert('Fehler beim Speichern des Films');
        }
    };

    return (
        <div className={styles.card}>
            <h2>{movie.Title} ({movie.Year})</h2>
            {movie.Poster ? (
                <img className={styles.poster} src={movie.Poster} alt={movie.Title} />
            ) : null}

            <p><strong>Released:</strong> {movie.Released}</p>
            <p><strong>Runtime:</strong> {movie.Runtime}</p>
            <p><strong>Genre:</strong> {movie.Genre}</p>
            <p><strong>Director:</strong> {movie.Director}</p>
            <p><strong>Writer:</strong> {movie.Writer}</p>
            <p><strong>Actors:</strong> {movie.Actors}</p>
            <p><strong>Plot:</strong> {movie.Plot}</p>
            <p><strong>Language:</strong> {movie.Language}</p>
            <p><strong>IMDB Rating:</strong> {movie.imdbRating}</p>

            <button className={btnStyles.button} onClick={handleAddMovie}>
                In DB speichern
            </button>
        </div>
    );
}
