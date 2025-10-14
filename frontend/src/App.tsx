import './App.css';
import MovieSearch from "./components/MovieSearch";
import TrackedMovies from "./components/TrackedMovies";
import Login from "./components/Login.tsx";
import "./css/variables.css";
import './index.css';


export default function App() {
    return (
        <main>
            <h1 style={{ textAlign: "center", marginBottom: "2rem" }}>Movie App</h1>
            <MovieSearch />
            <h1>Movie Tracker</h1>
            <TrackedMovies />
            <Login/>
        </main>
    );
}
