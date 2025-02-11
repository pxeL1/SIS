export function NewsCard(){
    return (
        <>
            <div className="flex flex-col w-full h-28 bg-gray-200 p-2 rounded shadow-sm shadow-gray-500 hover:bg-gray-100 transition-all duration-300 mb-8">
                <div className="text-xl font-bold">Title</div>
                <div>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    Pellentesque efficitur turpis erat, ac consectetur nulla placerat ac.
                    Mauris non sem aliquam, eleifend est eu, rhoncus arcu. Nam erat mi, varius non molestie vitae, commodo et augue.
                </div>
            </div>
        </>
    )
}