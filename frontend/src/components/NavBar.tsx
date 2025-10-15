import { Link, useLocation } from "react-router-dom";
import styles from "../css/NavBar.module.css";

export default function NavBar() {
    const location = useLocation();

    return (
        <nav className={styles.navbar}>
            <ul className={styles.navList}>
                <li className={`${styles.navItem} ${location.pathname === "/" ? styles.active : ""}`}>
                    <Link to="/">Home</Link>
                </li>
                <li className={`${styles.navItem} ${location.pathname.startsWith("/tracked") ? styles.active : ""}`}>
                    <Link to="/tracked">Tracked Movies</Link>
                </li>
            </ul>
        </nav>
    );
}
