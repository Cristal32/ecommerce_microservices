import { useEffect, useState } from "react";
import axios from "axios";
import ClothesImg from "../assets/clothes.jpg";
import { Link } from "react-router-dom";

function Clothes (){

    const [clothes, setClothes] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    const [filteredClothes, setFilteredClothes] = useState([]);

    useEffect(() => {
        const fetchClothes = async () => {
          try {
            const response = await axios.get(
              //"http://localhost:8222/api/product/getAll"
              "http://localhost:8222/api/product/getByCategory/1"
            );
            setClothes(response.data);
            setFilteredClothes(response.data);
          } catch (error) {
            console.error("Erreur lors de la récupération des clothes :", error);
          }
        };
    
        fetchClothes();
      }, []);

      const handleSearchInputChange = (event) => {
        setSearchInput(event.target.value);
      }

      const handleSearch = () => {
        const filtered = clothes.filter((piece) =>
          piece.name.toLowerCase().includes(searchInput.toLowerCase())
        );
        setFilteredClothes(filtered);
      }

    return (
        <>
            <div className="clothes-section">

                <div className="clothes-section-up">
                  <img src={ClothesImg} alt="clothes" />
                  <div className="clothes-section-up-text">
                    <h1>Here you can navigate and discover Our Clothes Section</h1>
                    <h2>Enjoy your shopping !</h2>
                  </div>
                </div>

                <div className="search-div">
                    <input type="search" name="Clothes-Search" id="Clothes-Search" placeholder="Looking for ..."
                    value={searchInput}
                    onChange={handleSearchInputChange}
                    />
                    <button onClick={handleSearch}>Search</button>
                </div>
                
                <div className="clothes-list">
                    {filteredClothes.map((piece) => (
                    <div className="clothes-item" key={piece.id}>
                      <Link to={`/catalogue/product/${piece.id}` } className="product-link">
                        <img
                          src={`data:image/jpeg;base64,${piece.image}`}
                          alt={piece.name}
                        />
                        <div className="clothes-info">
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

export default Clothes;