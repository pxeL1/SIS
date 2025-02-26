export default function CourseCard({name}) {
    return (
        <>
            <div className="flex h-44 w-[900px] border-gray-400 rounded-xl shadow-lg shadow-gray-400 hover:h-48 hover:w-[918px] transition-all duration-300">
                <div className="flex flex-col bg-blue-500 rounded-l-xl w-1/3 py-6 px-8">
                    <div className="opacity-45 text-sm">COURSE</div>
                    <div className="text-white text-2xl mt-2">{name}</div>
                </div>
                <div className="flex flex-col flex-grow  rounded-r-xl">

                </div>
            </div>
        </>
    )
}