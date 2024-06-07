import "../App.css";
import { useEffect, useState } from "react";
import axiosInstance from "../axiosInstance";
import { Link, useNavigate } from "react-router-dom";
import CategorySelector from "../components/CategorySelector";
import ProductImg from "../assets/productImage.jpg";
import { VscDebugBreakpointLogUnverified } from "react-icons/vsc";

function Catalogue() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [currentUser, setCurrentUser] = useState("");
  const [currentClient, setCurrentClient] = useState(null);
  const [isAdmin, setIsAdmin] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const isVisitor = sessionStorage.getItem("isVisitor") === "true";

  useEffect(() => {
    fetchProducts();
  }, []);

  useEffect(() => {
    const user = sessionStorage.getItem("currentUser");
    const isAdmin = localStorage.getItem("isAdmin");
    if (user) {
      setCurrentUser(user);
    }
    if (isAdmin === "true") {
      setIsAdmin(true);
    } else {
      setIsAdmin(false);
    }
  }, []);

  useEffect(() => {
    const fetchClient = async () => {
      try {
        if (!isVisitor && currentUser) {
          const response = await axiosInstance.get(
            `/client/getByUsername/${currentUser}`
          );
          setCurrentClient(response.data);
        }
      } catch (error) {
        console.error("Error fetching client:", error);
      }
    };

    fetchClient();
  }, [isVisitor, currentUser]);

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        if (!isVisitor && currentClient) {
          const response = await axiosInstance.get(
            `/notification/getByClientId/${currentClient.id}`
          );
          setNotifications(response.data);
        }
      } catch (error) {
        console.error("Error fetching notifications:", error);
      }
    };

    fetchNotifications();
  }, [isVisitor, currentClient]);

  const fetchProducts = async (categoryId) => {
    try {
      const url = categoryId
        ? `/product/getByCategory/${categoryId}`
        : `/product/getAll`;

      const response = await axiosInstance.get(url);
      setProducts(response.data);
      setFilteredProducts(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const handleSearchInputChange = (event) => {
    setSearchInput(event.target.value);
  };

  const handleSearch = () => {
    const filtered = products.filter((piece) =>
      piece.name.toLowerCase().includes(searchInput.toLowerCase())
    );
    setFilteredProducts(filtered);
  };

  return (
    <>
      <div className="catalogue-page">
        <div className="catalogue-up">
          <img src={ProductImg} alt="products" />
          <div className="catalogue-up-text">
            <h1>Here you can navigate and discover our newest products</h1>
            <h2>
              Hey, {isVisitor ? "Visitor" : currentUser}, enjoy your shopping !
            </h2>
            
              <div className="search-div">
                <input
                  type="search"
                  name="householdeAppliances-Search"
                  id="householdeAppliances-Search"
                  placeholder="Looking for ..."
                  value={searchInput}
                  onChange={handleSearchInputChange}
                />
                <button onClick={handleSearch}>Search</button>
              </div>
            
          </div>
        </div>
        <div className="catalogue-down">
          {!isVisitor && (
            <div className="filter-by-category">
              <h2> Dear {currentUser}, you can filter the product here by category !</h2>
              <CategorySelector onCategoryChange={fetchProducts} />
            </div>
          )}
          {!isVisitor && isAdmin && (
            <div className="admin-actions">
              <button onClick={() => navigate("/admin")}>Go to Admin Dashboard</button>
            </div>
          )}
          {!isVisitor && (
            <div className="msgs-client">
              <h3>Dear {currentUser}, here are your notifications</h3>
              {notifications.map((notification) => (
                <div className="notification" key={notification.id}>
                  <VscDebugBreakpointLogUnverified />
                  <h4>{notification.msg}</h4>
                  <h5>Sent At : {notification.sentAt}</h5>
                </div>
              ))}
            </div>
          )}
        </div>
        <div className="products-list">
          {filteredProducts.map((product) => (
            <div className="products-item" key={product.id}>
              <Link to={`/catalogue/product/${product.id}`} className="product-link">
                <img
                  src={`data:image/jpeg;base64,${product.image}`}
                  alt={product.name}
                />
                <div className="product-info">
                  <h3>{product.name}</h3>
                  <h4>{product.description}</h4>
                  <br />
                  <h4>{product.price} MAD</h4>
                </div>
              </Link>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

export default Catalogue;
