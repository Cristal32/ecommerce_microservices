import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axiosInstance from "../axiosInstance";
import Nav from "../components/Nav";
import { useNavigate } from "react-router-dom";

function ProductDetails() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentClient, setCurrentClient] = useState(null);
  const [quantity, setQuantity] = useState();
  let navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axiosInstance.get(`/product/getById/${id}`);
        setProduct(response.data);
      } catch (error) {
        console.error("Error fetching product:", error);
      }
    };

    fetchProduct();
  }, [id]);

  useEffect(() => {
    const fetchClient = async () => {
      try {
        const currentUser = sessionStorage.getItem("currentUser");
        if (currentUser) {
          const response = await axiosInstance.get(`/client/getByUsername/${currentUser}`);
          setCurrentClient(response.data);
        }
      } catch (error) {
        console.error("Error fetching client:", error);
      }
    };

    fetchClient();
  }, []);

  const handleOrder = () => {
    const isVisitor = sessionStorage.getItem("isVisitor") === "true";
    if (isVisitor) {
      alert("Visitors cannot place orders. Please sign in or register to place an order.");
      return;
    }
    setIsModalOpen(true);
  };

  const handleConfirmOrder = async () => {
    try {
      const response = await axiosInstance.post("/order/add", {
        clientId: currentClient.id,
        productId: product.id,
        amount: quantity,
        clientEmail: currentClient.email
      });
      console.log("Order placed:", response.data);
      alert("Order placed successfully!");
      location.reload();
    } catch (error) {
      console.error("Error placing order:", error);
    }
    setIsModalOpen(false);
  };

  const handleCancelOrder = () => {
    alert("Order cancelled.");
    setIsModalOpen(false);
  };

  if (!product || !currentClient) return <div>Loading...</div>;

  return (
    <>
      <Nav />
      <div className="product-details">
        <img
          src={`data:image/jpeg;base64,${product.image}`}
          alt={product.name}
        />
        <div className="product-info">
          <h1>{product.name}</h1>
          <p>{product.description}</p>
          <h2>{product.price} MAD</h2>
          <h2>Quantity in stock: {product.stockQuantity}</h2>
          <button onClick={handleOrder}>Order Now !</button>
        </div>
      </div>
      {isModalOpen && (
        <div className="modal">
          <div className="modal-content">
            <h2>Are you sure you want to buy {product.name} for {product.price} MAD?</h2>
            <h3>How many do you want to order?</h3>
            <input
              type="number"
              name="quantity"
              placeholder="Quantity"
              onChange={(e) =>
                setQuantity(e.target.value)
              }
            />
            <div className="modal-buttons">
              <button onClick={handleConfirmOrder}>Yes</button>
              <button onClick={handleCancelOrder}>No</button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default ProductDetails;
