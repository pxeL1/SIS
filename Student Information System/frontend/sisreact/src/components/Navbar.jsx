import {Link} from "react-router-dom";
import logo from "../assets/sis.jpg"
import {BoxOut, House} from "../assets/Icons.jsx"
import { User } from "../assets/Icons.jsx"
import { Envelope } from "../assets/Icons.jsx"
import { ChevronLeft } from "../assets/Icons.jsx"
import {ChevronRight} from "../assets/Icons.jsx";
import {Cap} from "../assets/Icons.jsx";
import {useState} from "react";

export function Navbar({handleLogout}) {
    const [open, setOpen] = useState(true);

    return (
        <>
            <div className={open ? "top-0 left 0 h-screen flex flex-col min-w-60 bg-black text-white shadow-2xl transition-all duration-100" : "top-0 left 0 h-screen flex justify-center min-w-7 bg-black text-white hover:text-blue-500 shadow-2xl transition-all duration-100"}>
                <img className={`w-full h-60 ${open ? "" : "hidden"}`} src={logo} alt="logo"/>
                <div className="flex flex-grow">
                    <div className={`flex flex-grow flex-col align-center pl-7 ${open ? "" : "hidden"}`}>
                        <Link to="/" className="flex justify-center mb-5 hover:text-blue-500"><House/></Link>
                        <Link to="/profile" className="flex justify-center mb-5 hover:text-blue-500"><User/></Link>
                        <Link to="/messages" className="flex justify-center mb-5 hover:text-blue-500"><Envelope/></Link>
                        <Link to="/courses" className="flex justify-center mb-5 hover:text-blue-500"><Cap/></Link>
                        <div className="border-2 border-white rounded mb-5"></div>
                        <div className="flex flex-col flex-grow justify-between">
                            <div className="flex flex-grow flex-col align-center"></div>
                            <button className="flex justify-center hover:text-blue-500 mb-8" onClick={handleLogout}><BoxOut/></button>
                        </div>
                    </div>
                    <div className="flex flex-col justify-center">
                        <button
                            className={`h-full opacity-0 hover:text-blue-500 hover:opacity-100 ${open ? "mb-60" : ""}`}
                            onClick={() => setOpen(!open)}>
                            {open ? <ChevronLeft/> : <ChevronRight/>}
                        </button>
                    </div>
                </div>
            </div>
        </>
    )
}