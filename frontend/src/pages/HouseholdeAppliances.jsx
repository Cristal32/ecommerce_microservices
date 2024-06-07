import { useEffect, useState } from "react";
import axios from "axios";
import householdApp from "../assets/household_appliances.jpg";
import { Link } from "react-router-dom";

function HouseholdeAppliances (){

    const [householdeAppliances, setHouseholdeAppliances] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    const [filteredHouseholdeAppliances, setFilteredHouseholdeAppliances] = useState([]);

    useEffect(() => {
        const fetchHouseholdeAppliances = async () => {
          try {
            const response = await axios.get(
              //"http://localhost:8222/api/product/getAll"
              "http://localhost:8222/api/product/getByCategory/4"
            );
            setHouseholdeAppliances(response.data);
            setFilteredHouseholdeAppliances(response.data);
          } catch (error) {
            console.error("Erreur lors de la récupération des householde Appliances :", error);
          }
        };
    
        fetchHouseholdeAppliances();
      }, []);

      const handleSearchInputChange = (event) => {
        setSearchInput(event.target.value);
      }

      const handleSearch = () => {
        const filtered = householdeAppliances.filter((piece) =>
          piece.name.toLowerCase().includes(searchInput.toLowerCase())
        );
        setFilteredHouseholdeAppliances(filtered);
      }

    return (
        <>
            <div className="householdeAppliances-section">
                <div className="householdeAppliances-section-up">
                  <img src={householdApp} alt="householdeAppliances" />
                      <div className="householdeAppliances-section-up-text">
                          <h1>Here you can navigate and discover Our HouseHolde Appliances Section</h1>
                          <h2>Enjoy your shopping !</h2>
                      </div>
                </div>

                <div className="search-div">
                    <input type="search" name="householdeAppliances-Search" id="householdeAppliances-Search" placeholder="Looking for ..."
                    value={searchInput}
                    onChange={handleSearchInputChange}
                    />
                    <button onClick={handleSearch}>Search</button>
                </div>

                
                <div className="householdeAppliances-list">
                    {filteredHouseholdeAppliances.map((piece) => (
                    <div className="householdeAppliances-item" key={piece.id}>
                      <Link to={`/catalogue/product/${piece.id}` } className="product-link">
                        <img
                          src={`data:image/jpeg;base64,${piece.image}`}
                          alt={piece.name}
                        />
                        <div className="householdeAppliances-info">
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

export default HouseholdeAppliances;