import React from "react";
import { Link } from "react-router-dom";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <h2>Navigation</h2>
      <ul>
        <li>
          <Link to="/admin/clients">Clients</Link>
        </li>
        <li>
          <Link to="/admin/products">Produits</Link>
        </li>
        <li>
          <Link to="/admin/categories">Catégories</Link>
        </li>
        <li>
          <Link to="/catalogue">Return To Catalogue</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
