import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import MovieSearch from "./components/MovieSearch";
import TrackedMovies from "./components/TrackedMovies";
import Login from "./components/Login";
import MovieDetailPage from "./pages/MovieDetailPage.tsx";


export default function App() {
    return (
        <Router>
            <div style={{
                fontFamily: "Arial, sans-serif",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                padding: "2rem"
            }}>
                <h1 style={{ textAlign: "center", marginBottom: "2rem" }}>Movie App</h1>

                {/* Navigation */}
                <nav style={{ marginBottom: "2rem" }}>
                    <Link to="/" style={{ marginRight: "1rem" }}>Home</Link>
                    <Link to="/tracked">Tracked Movies</Link>
                </nav>

                <Routes>
                    <Route path="/" element={
                        <>
                            <MovieSearch />
                            <Login />
                        </>
                    }/>
                    <Route path="/tracked" element={<TrackedMovies />} />
                    <Route path="/movies/:imdbID" element={<MovieDetailPage />} />
                </Routes>
            </div>
        </Router>
    );
}
