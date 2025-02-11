import {ProfileImg} from "../assets/Icons.jsx";
import {useContext, useEffect, useState} from "react";
import {UserContext} from "../contexts/UserContext.js";
import Button from "../components/Button.jsx";
import useFetchData from "../hooks/useFetchData.jsx";
import put from "../services/fetching/Put.js";
import {useNavigate} from "react-router-dom";

export function Profile() {
    const userContext = useContext(UserContext);
    const url = localStorage.getItem("roles").includes("PROFESSOR") ? "user/professor/" + JSON.parse(localStorage.getItem("user")).id : "user/student/" + JSON.parse(localStorage.getItem("user")).id;
    const [newPassword, setNewPassword] = useState("");
    const [passwordRepeat, setPasswordRepeat] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [index, setIndex] = useState("");
    const [enrollmentYear, setEnrollmentYear] = useState(null);
    const [isAdmin, setIsAdmin] = useState(false);
    const { data, isLoading, error } = useFetchData(url);
    const navigate = useNavigate();

    useEffect(() => {
        if(localStorage.getItem("roles").includes("ADMIN")){
            setIsAdmin(true);
        }
        else if(localStorage.getItem("roles").includes("PROFESSOR")){
            setFirstName(data.firstName);
            setLastName(data.lastName);
        }
        else {
            setFirstName(data.firstName);
            setLastName(data.lastName);
            setIndex(data.index);
            setEnrollmentYear(data.enrollmentYear);
        }
    },[data, isLoading])

    if(isLoading){
        return <div>Loading data...</div>;
    }

    if(error){
        return <div>Error while loading data...</div>
    }

    function handleSave() {
        const url = userContext.role.includes("PROFESSOR") ? "user/professor/" + userContext.id : "user/student/" + userContext.id;
        const updateData = {
            firstName: firstName,
            lastName: lastName,
            index: index,
            enrollmentYear: enrollmentYear,
        }

        put(url, updateData);
    }

    function handleSavePassword() {
        if(newPassword === passwordRepeat) {
            const url = "user";
            const updatePassword = {
                password: newPassword
            }

            put(url, updatePassword);
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            localStorage.removeItem("roles");
            navigate("/login");
        } else {
            alert("Passwords don't match");
        }
    }

    return (
        <>
            <div className="flex h-full flex-col justify-center items-center pl-10 pr-10 pt-5">
                <div className="flex flex-col w-1/2 h-[690px] rounded border-blue-500 border-t-2 shadow-lg shadow-gray-600">
                    <div className="m-4 text-2xl font-bold">
                        Details:
                    </div>
                    <hr/>
                    <div className="flex justify-center items-center my-2">
                        <ProfileImg/>
                    </div>
                    <div className="flex mt-2 mx-2 p-2">
                        <table className="flex flex-col flex-grow w-full">
                            <thead></thead>
                            <tbody className="w-full">
                            <tr className="flex">
                                <td className="border-x border-y w-full p-2 font-bold text-right">Email</td>
                                <td className="border-r border-y w-full p-2">{userContext.email}</td>
                            </tr>
                            <tr className="flex">
                                <td className="border-x border-b w-full p-2 font-bold text-right">Password change</td>
                                <td className="border-r border-b w-full p-2"><input type="password"
                                                                                    placeholder="Enter new password"
                                                                                    className="flex pl-1 border w-full"
                                                                                    onChange={(e) => setNewPassword(e.target.value)}/>
                                </td>
                            </tr>
                            <tr className="flex">
                                <td className="border-x border-b w-full p-2 font-bold text-right">Repeat password</td>
                                <td className="border-r border-b w-full p-2"><input type="password"
                                                                                    placeholder="Repeat new password"
                                                                                    className="flex pl-1 border w-full"
                                                                                    onChange={(e) => setPasswordRepeat(e.target.value)}/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div className="flex justify-center px-4">
                        <Button onClick={handleSavePassword} text={"Save"}/>
                    </div>
                    {!isAdmin && (
                        <>
                            <div className="flex mx-2 p-2">
                                <table className="flex flex-col flex-grow w-full">
                                <thead></thead>
                                <tbody className="w-full">
                                <tr className="flex">
                                    <td className="border-x border-y w-full p-2 font-bold text-right">First name</td>
                                    <td className="border-r border-y w-full p-2"><input type="text"
                                                                                        value={firstName}
                                                                                        className="flex pl-1 border w-full placeholder-black" onChange={(e) => setFirstName(e.target.value)}/>
                                    </td>
                                </tr>
                                <tr className="flex">
                                    <td className="border-x border-b w-full p-2 font-bold text-right">Last name</td>
                                    <td className="border-r border-b w-full p-2"><input type="text"
                                                                                        value={lastName}
                                                                                        className="flex pl-1 border w-full placeholder-black" onChange={(e) => setLastName(e.target.value)}/>
                                    </td>
                                </tr>
                                <tr className="flex">
                                    <td className="border-x border-b w-full p-2 font-bold text-right">Index</td>
                                    <td className="border-r border-b w-full p-2"><input type="text" value={index}
                                                                                        className="flex pl-1 border w-full placeholder-black" onChange={(e) => setIndex(e.target.value)}/>
                                    </td>
                                </tr>
                                <tr className="flex">
                                    <td className="border-x border-b w-full p-2 font-bold text-right">Enrollment year
                                    </td>
                                    <td className="border-r border-b w-full p-2"><input type="number"
                                                                                        value={enrollmentYear}
                                                                                        className="flex pl-1 border w-full placeholder-black" onChange={(e) => setEnrollmentYear(e.target.value)}/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div className="flex justify-center px-4">
                            <Button onClick={handleSave} text={"Save"}/>
                        </div>
                        </>
                    )}
                </div>
            </div>
        </>
    )
}