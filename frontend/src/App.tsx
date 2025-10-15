import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MovieSearch from "./components/MovieSearch";
import TrackedMoviesPage from "./page/TrackedMoviesPage.tsx";
import Navigation from "./components/NavBar.tsx"; // 👈 neue Nav-Komponente
import Login from "./components/Login.tsx";
import "./css/variables.css";
import './index.css';


export default function App() {
    return (
        <Router>
            <main>
                <h1 style={{ textAlign: "center", marginBottom: "1rem" }}>Movie App</h1>
                <Navigation /> {/* 👈 immer sichtbar */}

                <Routes>
                    <Route path="/" element={<MovieSearch />} />
                    <Route path="/tracked/*" element={<TrackedMoviesPage />} />
                </Routes>

                <Login />
            </main>
        </Router>
    );
}
