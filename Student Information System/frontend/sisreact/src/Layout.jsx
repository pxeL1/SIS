import {Navbar} from "./components/Navbar.jsx";
import {Outlet} from "react-router-dom";

export function Layout() {
    return (
        <>
            <Navbar />
            <main className="flex-grow">
                <Outlet />
            </main>
        </>
    )
}