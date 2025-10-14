import type { OMDbMovieShort } from "../model/OMDbMovieShort";
import styles from "../css/MovieCard.module.css";

type Props = {
    movie: OMDbMovieShort;
};

export default function MovieCard({ movie }: Props) {
    return (
        <div className={styles.card}>
            <h2>{movie.Title} ({movie.Year})</h2>
            {movie.Poster ? (
                <img className={styles.poster} src={movie.Poster} alt={movie.Title} />
            ) : (
                <div className={styles.noPoster}>
                    Kein Poster
                </div>
            )}
        </div>
    );
}
