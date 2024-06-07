import { useEffect, useState } from "react";
import axios from "axios";
import booksimg from "../assets/books.jpg";
import { Link } from "react-router-dom";


function Books (){

    const [books, setBooks] = useState([]);
    const [searchInput, setSearchInput] = useState("");
    const [filteredBooks, setFilteredBooks] = useState([]);

    useEffect(() => {
        const fetchBooks = async () => {
          try {
            const response = await axios.get(
              //"http://localhost:8222/api/product/getAll"
              "http://localhost:8222/api/product/getByCategory/3"
            );
            setBooks(response.data);
            setFilteredBooks(response.data);
          } catch (error) {
            console.error("Erreur lors de la récupération des Books :", error);
          }
        };
    
        fetchBooks();
      }, []);

      const handleSearchInputChange = (event) => {
        setSearchInput(event.target.value);
      }

      const handleSearch = () => {
        const filtered = books.filter((piece) =>
          piece.name.toLowerCase().includes(searchInput.toLowerCase())
        );
        setFilteredBooks(filtered);
      }


    return (
        <>
            <div className="books-section">

            <div className="books-section-up">
              <img src={booksimg} alt="books" />
              <div className="books-section-up-text">
                <h1>Here you can navigate and discover Our Books Section</h1>
                <h2>Enjoy your shopping !</h2>
              </div>
            </div>

                <div className="search-div">
                    <input type="search" name="books-Search" id="books-Search" placeholder="Looking for ..."
                    value={searchInput}
                    onChange={handleSearchInputChange}
                    />
                    <button onClick={handleSearch}>Search</button>
                </div>
                
                <div className="books-list">
                    {filteredBooks.map((book) => (
                    <div className="books-item" key={book.id}>
                      <Link to={`/catalogue/product/${book.id}` } className="product-link">
                        <img
                        src={`data:image/jpeg;base64,${book.image}`}
                        alt={book.name}
                        />
                        <div className="books-info">
                            <h3>{book.name}</h3>
                            <h4>{book.description}</h4><br />
                            <h4>{book.price} MAD</h4>
                        </div>
                      </Link>
                    </div>
                    ))}
                </div>
            </div>
        </>

    );
}

export default Books;