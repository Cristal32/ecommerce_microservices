import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import WelcomePage from "./pages/WelcomePage.jsx";
import Catalogue from "./pages/Catalogue.jsx";
import Clothes from "./pages/Clothes.jsx";
import Cosmetics from "./pages/Cosmetics.jsx";
import Books from "./pages/Books.jsx";
import HouseholdeAppliances from "./pages/HouseholdeAppliances.jsx";
import ProductDetails from "./pages/ProductDetails.jsx";
import Clients from "./pages/Clients.jsx";
import Products from "./pages/Produits.jsx";
import Categories from "./pages/Categories.jsx";
import AdminDashboad from "./pages/AdminDashboad.jsx";

function App() {
    return (
      <Router>
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/catalogue" element={<Catalogue />} />
          <Route path="/catalogue/product/:id" element={<ProductDetails />} />
          <Route path="/admin" element={<AdminDashboad />} />
          <Route path="/admin/clients" element={<Clients />} />
          <Route path="/admin/products" element={<Products />} />
          <Route path="/admin/categories" element={<Categories />} />
        </Routes>
      </Router>
    );
  }
  
  export default App;