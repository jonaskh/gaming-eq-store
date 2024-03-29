import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';
import '../css/AdminPanel.css';
import APIService from "../Services/APIService";

const AdminPanelPage = () => {
    const navigate = useNavigate();
    const [userRoles, setUserRoles] = useState([]);
    const [products, setProducts] = useState([]);
    const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
    const [deleteProductId, setDeleteProductId] = useState(null);
    const [editingProductId, setEditingProductId] = useState(null);
    const [editedProducts, setEditedProducts] = useState({});
    const [error, setError] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [newProduct, setNewProduct] = useState({
        productName: '',
        price: '',
        productDescription: '',
        category: 'Gaming'
    });

    useEffect(() => {
        const token = localStorage.getItem('jwt');

        if (!token) {
            navigate('/login');
        } else {
            try {
                const decodedToken = jwt_decode(token);
                const roles = decodedToken.roles.split(',');
                setUserRoles(roles);
            } catch (err) {
                setError('Invalid token');
                localStorage.removeItem('jwt');
                navigate('/login');
            }
        }
    }, [navigate]);

    useEffect(() => {
        APIService.getProducts()
            .then(response => {
                setProducts(response.data);
                console.log(response.data);
            })
            .catch(error => console.log(error));
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('jwt');
        navigate('/login');
    };

    if (error) {
        return <div className="error">{error}</div>;
    }
    const isAdmin = userRoles.includes('ADMIN');
    if (!isAdmin) {
        navigate('/login');
        return null; 
    }

    const handleReloadClick = () => {
        window.location.reload();
    };

    const handleDeleteProduct = (productId) => {
        console.log(productId)
        APIService.deleteSelectedProduct(productId).then(() => {handleReloadClick()});
        setShowDeleteConfirmation(false);
    };

    const handleEditProduct = (productId, product) => {
        setEditingProductId(productId);
        setEditedProducts({ ...editedProducts, [productId]: { ...product } });
    };

    const handleCancelEdit = (productId) => {
        setEditingProductId(null);
        setEditedProducts({ ...editedProducts, [productId]: undefined });
    };

    const handleUpdateProduct = (productId, editedProducts) => {
        console.log(editedProducts)
        APIService.updateSelectedProduct(productId, editedProducts[productId]?.productName, editedProducts[productId]?.price).then(() => {handleReloadClick()});
        setEditingProductId(null);
    };

    const handleShowForm = () => {
        setShowForm(!showForm);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct({ ...newProduct, [name]: value });
    };

    const resetFormInput = () => {
        setNewProduct({
            productName: '',
            price: '',
            productDescription: '',
            category: ''
        });
        setShowForm(false);
    }

    const handleSubmitForm = () => {
        console.log(newProduct);
        APIService.createNewProduct(
            newProduct.productName,
            newProduct.price,
            newProduct.productDescription,
            newProduct.category).then(handleReloadClick);
        resetFormInput();
    };

    const handleCancelForm = () => {
        resetFormInput();
    };


    return (
        <div className="admin-panel-container">
            <div className="admin-panel-content">
                <h2>Admin Panel</h2>


                <button onClick={handleShowForm}>Add Product</button>
                <p></p>
                {showForm && (
                    <div className="overlay">
                        <form className="form-container" onSubmit={handleSubmitForm}>
                            <h2>Add a product</h2>
                            <input
                                type="text"
                                name="productName"
                                value={newProduct.productName}
                                onChange={handleInputChange}
                                placeholder="Product Name"
                            />
                            <input
                                type="number"
                                name="price"
                                value={newProduct.price}
                                onChange={handleInputChange}
                                placeholder="Price"
                            />
                            <input
                                type="text"
                                name="productDescription"
                                value={newProduct.productDescription}
                                onChange={handleInputChange}
                                placeholder="Product Description"
                            />
                            <label for={"category"}>Choose a category:</label>
                            <select
                                id={"category"}
                                name="category"
                                value={newProduct.category}
                                onChange={handleInputChange}
                            >
                                <option value="Gaming">Gaming</option>
                                <option value="Office">Office</option>
                                <option value="Headsets">Headsets</option>
                                <option value="Mouse">Mouse</option>
                                <option value="Keyboards">Keyboards</option>
                                <option value="Consoles">Consoles</option>
                                <option value="Controllers">Controllers</option>
                            </select>
                            <div className="form-buttons">
                                <button type="submit">Submit</button>
                                <button type="button" onClick={handleCancelForm}>Cancel</button>
                            </div>
                        </form>
                    </div>
                )}



                <section className="products">
                    {products.map((product) => (
                        <div className="admin-product-card" id={product.product_id} key={product.product_id}>
                            <div className="delete-wrapper">
                                <button className="delete-button" onClick={() => {
                                    console.log(product.product_id)
                                    setDeleteProductId(product.product_id);
                                    setShowDeleteConfirmation(true);
                                }}>Delete</button>
                            </div>
                            <img src={`${process.env.PUBLIC_URL}/${product.productImage}`} alt={product.productName} />
                            <div className="productCard-details">
                                {editingProductId === product.product_id ? (
                                    <>
                                        <input
                                            type="text"
                                            value={editedProducts[product.product_id]?.productName || product.productName}
                                            onChange={(e) => setEditedProducts({ ...editedProducts, [product.product_id]: { ...editedProducts[product.product_id], productName: e.target.value } })}
                                        />
                                        <input
                                            type="text"
                                            value={editedProducts[product.product_id]?.price || product.price}
                                            onChange={(e) => setEditedProducts({ ...editedProducts, [product.product_id]: { ...editedProducts[product.product_id], price: e.target.value } })}
                                        />
                                    </>
                                ) : (
                                    <>
                                        <h3>{product.productName}</h3>
                                        <p>{product.price} kr</p>
                                    </>
                                )}
                            </div>
                            <div className="button-group">
                                {editingProductId === product.product_id ? (
                                    <>
                                        <button className="update-button" onClick={() => handleUpdateProduct(product.product_id, editedProducts)}>Update</button>
                                        <button className="cancel-button" onClick={() => handleCancelEdit(product.product_id)}>Cancel</button>
                                    </>
                                ) : (
                                    <button className="edit-button" onClick={() => handleEditProduct(product.product_id, product)}>Edit</button>
                                )}
                            </div>
                        </div>
                    ))}
                    {showDeleteConfirmation && (
                        <div className="overlay">
                            <div className="delete-confirmation">
                                <p>Are you sure you want to delete this from the database?</p>
                                <div className="delete-buttons">
                                    <button className="confirm-delete" onClick={() => handleDeleteProduct(deleteProductId)}>Yes</button>
                                    <button className="cancel-delete" onClick={() => setShowDeleteConfirmation(false)}>No</button>
                                </div>
                            </div>
                        </div>
                    )}
                </section>


                <button onClick={handleLogout} className="logout-button">
                    Logout
                </button>
            </div>
        </div>
    );
};

export default AdminPanelPage;
