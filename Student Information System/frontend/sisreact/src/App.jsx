import './App.css'
import {Routes, Route, useNavigate} from "react-router-dom";
import {Home} from './pages/Home.jsx';
import {Profile} from './pages/Profile';
import {Course} from './pages/Course.jsx';
import {Messages} from './pages/Messages';
import {Layout} from "./Layout.jsx";
import {LoginPage} from "./pages/LoginPage.jsx";
import {UserContext} from "./contexts/UserContext.js";
import {useEffect, useState} from "react";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import get from "./services/fetching/Get.js";

function App() {
    const [currentUser, setCurrentUser] = useState();
    const navigate = useNavigate();

    function handleLogin(user, token) {
        setCurrentUser(user);
        localStorage.setItem("token", token);
        localStorage.setItem("user", JSON.stringify(user));
        localStorage.setItem("roles", JSON.stringify(user.role))
        navigate("/");
    }

    function handleLogout() {
        setCurrentUser(null);
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        localStorage.removeItem("roles");
        navigate("/login");
    }

    useEffect(() => {
        const token = localStorage.getItem("token");
        if(token) {
            const url = "auth/validate";
            get(url).then(response => {
                if(response.status) {
                    setCurrentUser(JSON.parse(localStorage.getItem("user")));
                } else {
                    localStorage.removeItem("token");
                    localStorage.removeItem("user");
                    localStorage.removeItem("roles");
                }
            })
        }
    }, [])

    return (
        <UserContext.Provider value={currentUser}>
            <Routes>
                <Route path="/login" element={<LoginPage handleLogin={handleLogin} />} />
                <Route element={<ProtectedRoute/>}>
                    <Route element={<Layout handleLogout={handleLogout} />}>
                        <Route path="/" element={<Home/>} />
                        <Route path="/profile" element={<Profile/>} />
                        <Route path="/courses" element={<Course/>} />
                        <Route path="/messages" element={<Messages/>} />
                    </Route>
                </Route>
            </Routes>
        </UserContext.Provider>
    )
}

export default App
