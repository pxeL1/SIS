import {Navbar} from "./components/Navbar.jsx";
import {Outlet} from "react-router-dom";

export function Layout({handleLogout}) {
    return (
        <>
            <Navbar handleLogout={handleLogout} />
            <main className="flex-grow">
                <Outlet />
            </main>
        </>
    )
}