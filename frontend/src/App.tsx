import './App.css';
import MovieSearch from "./components/MovieSearch";

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
        </div>
    );
}
