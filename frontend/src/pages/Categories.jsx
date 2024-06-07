import React, { useState, useEffect } from "react";
import axiosInstance from "../axiosInstance";
import Sidebar from "../components/SideBar";

const CategoryPage = () => {
  const [categories, setCategories] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalType, setModalType] = useState("");
  const [selectedItem, setSelectedItem] = useState(null);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const response = await axiosInstance.get("/category/getAll");
      setCategories(response.data);
    } catch (error) {
      console.error("Erreur lors de la récupération des catégories :", error);
    }
  };

  const handleAddClick = () => {
    setModalType("add-category");
    setIsModalOpen(true);
    setSelectedItem(null);
  };

  const handleEditClick = (category) => {
    setModalType("edit-category");
    setIsModalOpen(true);
    setSelectedItem(category);
  };

  const handleDeleteClick = async (id) => {
    try {
      await axiosInstance.delete(`/category/delete/${id}`);
      alert("Catégorie supprimée avec succès");
      fetchCategories();
    } catch (error) {
      console.error("Erreur lors de la suppression de la catégorie :", error);
    }
  };

  const handleModalSubmit = async (data) => {
    try {
      if (modalType === "add-category") {
        await axiosInstance.post("/category/add", data);
      } else if (modalType === "edit-category") {
        await axiosInstance.put(`/category/update/${selectedItem.id}`, data);
      }
      alert(
        `${
          modalType === "add-category" ? "Catégorie ajoutée" : "Catégorie modifiée"
        } avec succès`
      );
      fetchCategories();
      setIsModalOpen(false);
    } catch (error) {
      console.error(
        `Erreur lors de ${
          modalType === "add-category" ? "l'ajout" : "la modification"
        } de la catégorie :`,
        error
      );
    }
  };

  const renderTable = () => {
    return (
      <>
        <div className="tableau-categories">
          <Sidebar />
          <table>
            <thead>
              <tr>
                <th>Nom</th>
                <th>Description</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {categories.map((category) => (
                <tr key={category.id}>
                  <td>{category.name}</td>
                  <td>{category.description}</td>
                  <td>
                    <button onClick={() => handleEditClick(category)}>
                      Modifier
                    </button>
                    <button onClick={() => handleDeleteClick(category.id)}>
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
            Ajouter Catégorie
          </button>
        </div>
      </>
    );
  };

  const renderModal = () => {
    if (!isModalOpen) return null;

    const initialData = selectedItem || {
      id: "",
      name: "",
      description: "",
    };

    return (
      <div className="modal">
        <div className="modal-content">
          <h2>
            {modalType === "add-category" ? "Ajouter" : "Modifier"} Catégorie
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
              Nom:
              <input
                type="text"
                name="name"
                defaultValue={initialData.name}
                required
              />
            </label>
            <label>
              Description:
              <textarea
                name="description"
                defaultValue={initialData.description}
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
    <div className="page-categories">
      {renderTable()}
      {renderModal()}
    </div>
  );
};

export default CategoryPage;
