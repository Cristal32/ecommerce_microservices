import { useEffect, useState } from "react";
import axios from "axios";
import CosImg from "../assets/cosmetics.jpeg";
import { Link } from "react-router-dom";


function Cosmetics (){

    const [cosmetics, setCosmetics] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    const [filteredCosmetics, setFilteredCosmetics] = useState([]);

    useEffect(() => {
        const fetchCosmetics = async () => {
          try {
            const response = await axios.get(
              //"http://localhost:8222/api/product/getAll"
              "http://localhost:8222/api/product/getByCategory/2"
            );
            setCosmetics(response.data);
            setFilteredCosmetics(response.data);
          } catch (error) {
            console.error("Erreur lors de la récupération des Cosmetics :", error);
          }
        };
    
        fetchCosmetics();
      }, []);

      const handleSearchInputChange = (event) => {
        setSearchInput(event.target.value);
      }

      const handleSearch = () => {
        const filtered = cosmetics.filter((piece) =>
          piece.name.toLowerCase().includes(searchInput.toLowerCase())
        );
        setFilteredCosmetics(filtered);
      }

    return (
        <>
            <div className="cosmetics-section">

                <div className="cosmetics-section-up">
                  <img src={CosImg} alt="cosmetics" />
                      <div className="cosmetics-section-up-text">
                          <h1>Here you can navigate and discover Our cosmetics Section</h1>
                          <h2>Enjoy your shopping !</h2>
                      </div>
                </div>

                <div className="search-div">
                    <input type="search" name="Cosmetics-Search" id="Cosmetics-Search" placeholder="Looking for ..."
                    value={searchInput}
                    onChange={handleSearchInputChange}
                    />
                    <button onClick={handleSearch}>Search</button>
                </div>
                
                <div className="cosmetics-list">
                    {filteredCosmetics.map((piece) => (
                    <div className="cosmetics-item" key={piece.id}>
                      <Link to={`/catalogue/product/${piece.id}` } className="product-link">
                        <img
                          src={`data:image/jpeg;base64,${piece.image}`}
                          alt={piece.name}
                        />
                        <div className="cosmetics-info">
                            <h3>{piece.name}</h3>
                            <h4>{piece.description}</h4><br />
                            <h4>{piece.price} MAD</h4>
                        </div>
                      </Link>
                    </div>
                    ))}
                </div>
            </div>
        </>

    );
}

export default Cosmetics;