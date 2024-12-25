import {Link} from "react-router-dom";
import logo from "../assets/sis.jpg"
import { House } from "../assets/Icons.jsx"
import { User } from "../assets/Icons.jsx"
import { Envelope } from "../assets/Icons.jsx"
import { ChevronLeft } from "../assets/Icons.jsx"
import {ChevronRight} from "../assets/Icons.jsx";
import {Cap} from "../assets/Icons.jsx";
import {useState, useEffect} from "react";


export function Navbar() {
    const [open, setOpen] = useState(true);
    const [enrollments, setEnrollments] = useState([]);

    useEffect( () => {
        const fetchEnrollments = async () => {

            const response = await fetch("http://localhost:8080/api/v1/enrollment/student/1");
            let fetchedEnrollments = await response.json();
            fetchedEnrollments.map(enrollment => {setEnrollments([...enrollments, enrollment])})
        }
        fetchEnrollments();
    }, []);

    return (
        <>
            <div className={open ? "top-0 left 0 h-screen flex flex-col w-60 bg-black text-white shadow-2xl transition-all duration-300" : "top-0 left 0 h-screen flex justify-center w-7 bg-black text-white hover:text-blue-500 shadow-2xl transition-all duration-300"}>
                <img className={`w-full h-60 ${open ? "" : "hidden"}`} src={logo} alt="logo"/>
                <div className={"flex flex-grow"}>
                    <div className={`flex flex-grow flex-col align-center pl-7 ${open ? "" : "hidden"}`}>
                        <Link to="/" className={"flex justify-center mb-5 hover:text-blue-500"}>{<House/>}</Link>
                        <Link to="/profile" className={"flex justify-center mb-5 hover:text-blue-500"}>{<User/>}</Link>
                        <Link to="/messages" className={"flex justify-center mb-5 hover:text-blue-500"}>{<Envelope/>}</Link>
                        <div className={"border-2 border-white rounded mb-5"}></div>
                        {enrollments.map((enrollment, index) => {
                            return (
                                <Link to="/course" className={"flex justify-center items-center mb-5 hover:text-blue-500"} state={enrollment} key={index}><span className={"mr-2"}><Cap/></span>{enrollment.course.name}</Link>
                            )
                        })}
                    </div>
                    <div className={"flex flex-col justify-center"}>
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