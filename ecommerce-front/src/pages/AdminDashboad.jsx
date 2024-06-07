import Sidebar from "../components/SideBar";

function AdminDashboad(){
    return (
        <>
            <div className="admin-page">
                <Sidebar/>
                <div className="content">
                    <h1>Welcome Admin !</h1>
                    <h2>Here you can manage the clients, products and categories of our e-commerce app</h2>
                </div>
            </div>
        </>
    )
}

export default AdminDashboad;