import './App.css';
import MovieSearch from "./components/MovieSearch";
import TrackedMovies from "./components/TrackedMovies";

export default function App() {
    return (
        <div style={{
            fontFamily: "Arial, sans-serif",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            padding: "2rem"
        }}>
            <h1 style={{ textAlign: "center", marginBottom: "2rem" }}>Movie App</h1>
            <MovieSearch />
            <h1>Movie Tracker</h1>
            <TrackedMovies />
        </div>
    );
}
