import './App.css';
import MovieSearch from "./components/MovieSearch";

export default function App() {
    return (
        <div id="root">
            <h1 style={{ textAlign: "center", marginTop: "2rem" }}>Movie App</h1>
            <MovieSearch />
        </div>
    );
}
