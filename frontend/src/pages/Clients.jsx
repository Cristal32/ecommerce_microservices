import React, { useState, useEffect } from "react";
import axiosInstance from "../axiosInstance";
import Sidebar from "../components/SideBar";

const ClientPage = () => {
    const [clients, setClients] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalType, setModalType] = useState("");
    const [selectedItem, setSelectedItem] = useState(null);

    useEffect(() => {
        fetchClients();
    }, []);

    const fetchClients = async () => {
        try {
            const response = await axiosInstance.get("/client/getAll");
            setClients(response.data);
        } catch (error) {
            console.error("Erreur lors de la récupération des clients :", error);
        }
    };

    const handleAddClick = () => {
        setModalType("add-client");
        setIsModalOpen(true);
        setSelectedItem(null);
    };

    const handleEditClick = (client) => {
        setModalType("edit-client");
        setIsModalOpen(true);
        setSelectedItem(client);
    };

    const handleDeleteClick = async (id) => {
        try {
            await axiosInstance.delete(`/client/delete/${id}`);
            alert("Client supprimé avec succès");
            fetchClients();
        } catch (error) {
            console.error("Erreur lors de la suppression du client :", error);
        }
    };

    const handleModalSubmit = async (data) => {
        try {
            if (modalType === "add-client") {
                await axiosInstance.post("/client/add", data);
            } else if (modalType === "edit-client") {
                await axiosInstance.put(`/client/update/${selectedItem.id}`, data);
            }
            alert(
                `${modalType === "add-client" ? "Client ajouté" : "Client modifié"} avec succès`
            );
            fetchClients();
            setIsModalOpen(false);
        } catch (error) {
            console.error(
                `Erreur lors de ${
                    modalType === "add-client" ? "l'ajout" : "la modification"
                } du client :`,
                error
            );
        }
    };

    const renderTable = () => {
        return (
            <>
            <div className="tableau-clients">
                <Sidebar />
                <table>
                    <thead>
                        <tr>
                            <th>Prénom</th>
                            <th>Nom</th>
                            <th>Email</th>
                            <th>Téléphone</th>
                            <th>Id Utilisateur</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {clients.map((client) => (
                            <tr key={client.id}>
                                <td>{client.firstName}</td>
                                <td>{client.lastName}</td>
                                <td>{client.email}</td>
                                <td>{client.tel}</td>
                                <td>{client.userId}
                                </td>
                                <td>
                                    <button onClick={() => handleEditClick(client)}>
                                        Modifier
                                    </button>
                                    <button onClick={() => handleDeleteClick(client.id)}>
                                        Supprimer
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                </div>
                <div>
                    <button className="add-button" onClick={handleAddClick}>
                        Ajouter Client
                    </button>
                </div>
        </>
        );
    };

    const renderModal = () => {
        if (!isModalOpen) return null;

        const initialData = selectedItem || {
            id: "",
            firstName: "",
            lastName: "",
            email: "",
            tel: "",
        };

        return (
            <div className="modal">
                <div className="modal-content">
                    <h2>
                        {modalType === "add-client" ? "Ajouter" : "Modifier"} Client
                    </h2>
                    <form
                        onSubmit={(e) => {
                            e.preventDefault();
                            const formData = new FormData(e.target);
                            const data = Object.fromEntries(formData.entries());
                            handleModalSubmit(data);
                        }}
                    >
                        <label>
                            Prénom:
                            <input
                                type="text"
                                name="firstName"
                                defaultValue={initialData.firstName}
                                required
                            />
                        </label>
                        <label>
                            Nom:
                            <input
                                type="text"
                                name="lastName"
                                defaultValue={initialData.lastName}
                                required
                            />
                        </label>
                        <label>
                            Email:
                            <input
                                type="email"
                                name="email"
                                defaultValue={initialData.email}
                                required
                            />
                        </label>
                        <label>
                            Téléphone:
                            <input
                                type="tel"
                                name="tel"
                                defaultValue={initialData.tel}
                                required
                            />
                        </label>
                        <button type="submit">Enregistrer</button>
                        <button type="button" onClick={() => setIsModalOpen(false)}>
                            Annuler
                        </button>
                    </form>
                </div>
            </div>
        );
    };

    return (
        <div className="page-client">
            {renderTable()}
            {renderModal()}
        </div>
    );
};

export default ClientPage;
