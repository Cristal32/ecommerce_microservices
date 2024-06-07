import React, { useEffect, useState } from 'react';
import axiosInstance from '../axiosInstance';

function CategorySelector({ onCategoryChange }) {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await axiosInstance.get('/category/getAll');
                setCategories(response.data);
            } catch (error) {
                console.log('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);

    const handleCategoryChange = (event) => {
        const categoryId = event.target.value;
        if (categoryId) {
            onCategoryChange(categoryId);
        }
    };

    return (
        <div className='category-selector'>
            <select onChange={handleCategoryChange}>
                <option value=''>Select Category</option>
                {categories.map((category) => (
                    <option key={category.id} value={category.id}>
                        {category.name}
                    </option>
                ))}
            </select>
        </div>
    );
}

export default CategorySelector;
