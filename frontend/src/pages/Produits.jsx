import React, { useState, useEffect } from "react";
import axiosInstance from "../axiosInstance";
import Sidebar from "../components/SideBar";

const ProductPage = () => {
  const [products, setProducts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalType, setModalType] = useState("");
  const [selectedItem, setSelectedItem] = useState(null);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetchProducts();
    fetchCategories();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axiosInstance.get("/product/getAll");
      setProducts(response.data);
    } catch (error) {
      console.error("Erreur lors de la récupération des produits :", error);
    }
  };

  const fetchCategories = async () => {
    try {
      const response = await axiosInstance.get("/category/getAll");
      setCategories(response.data);
    } catch (error) {
      console.error("Erreur lors de la récupération des catégories :", error);
    }
  };

  const handleAddClick = () => {
    setModalType("add-product");
    setIsModalOpen(true);
    setSelectedItem(null);
  };

  const handleEditClick = (item) => {
    setModalType("edit-product");
    setIsModalOpen(true);
    setSelectedItem(item);
  };

  const handleDeleteClick = async (id) => {
    try {
      await axiosInstance.delete(`/product/delete/${id}`);
      alert("Produit supprimé avec succès");
      fetchProducts();
    } catch (error) {
      console.error("Erreur lors de la suppression du produit :", error);
    }
  };

  const handleModalSubmit = async (data) => {
    try {
      if (modalType === "add-product") {
        const formData = new FormData();
        formData.append("category", data.category);
        formData.append("name", data.name);
        formData.append("description", data.description);
        formData.append("image", data.image);
        formData.append("price", data.price);
        formData.append("stockQuantity", data.stockQuantity);

        await axiosInstance.post("/product/add", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
      } else if (modalType === "edit-product") {
        const formData = new FormData();
        formData.append("category", data.category);
        formData.append("name", data.name);
        formData.append("description", data.description);
        if (data.image instanceof File) {
          formData.append("image", data.image);
        }
        formData.append("price", data.price);
        formData.append("stockQuantity", data.stockQuantity);

        await axiosInstance.put(
          `/product/update/${selectedItem.id}`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );
      }
      alert(
        `${modalType === "add-product" ? "Produit ajouté" : "Produit modifié"} avec succès`
      );
      fetchProducts();
      setIsModalOpen(false);
    } catch (error) {
      console.error(
        `Erreur lors de ${
          modalType === "add-product" ? "l'ajout" : "la modification"
        } du produit :`,
        error
      );
    }
  };

  const renderTable = () => {
    return (
      <>
        <div className="tableau-produits">
        <Sidebar/>
          <table>
          <thead>
            <tr>
              <th>Nom</th>
              <th>Description</th>
              <th>Catégorie</th>
              <th>Image</th>
              <th>Prix</th>
              <th>Quantité en stock</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>{product.category.name}</td>
                <td>
                  <img
                    src={`data:image/jpeg;base64,${product.image}`}
                    alt="product"
                    style={{ width: "100px", height: "100px" }}
                  />
                </td>
                <td>{product.price}</td>
                <td>{product.stockQuantity}</td>
                <td>
                  <button onClick={() => handleEditClick(product)}>
                    Modifier
                  </button>
                  <button onClick={() => handleDeleteClick(product.id)}>
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
            Ajouter Produit
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
      image: "",
      price: "",
      stockQuantity: "",
      category: categories.length > 0 ? categories[0].id : "",
    };

    return (
      <div className="modal">
        <div className="modal-content">
          <h2>
            {modalType === "add-product" ? "Ajouter" : "Modifier"} Produit
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
              Catégorie:
              <select
                name="category"
                defaultValue={initialData.category}
                required
              >
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
            </label>
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
            <label>
              Image:
              <input type="file" name="image" accept="image/*" />
            </label>
            <label>
              Prix:
              <input
                type="number"
                name="price"
                step="0.01"
                defaultValue={initialData.price}
                required
              />
            </label>
            <label>
              Quantité en stock:
              <input
                type="number"
                name="stockQuantity"
                defaultValue={initialData.stockQuantity}
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
    <div className="page-produits">
      {renderTable()}
      {renderModal()}
    </div>
  );
};

export default ProductPage;
