import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../Components/Navbar';
import jwt_decode from 'jwt-decode';
import '../css/AdminPanel.css';
import ProductCard from "../Components/ProductCard";
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


    return (
        <div className="admin-panel-container">
            <Navbar />
            <div className="admin-panel-content">
                <h2>Admin Panel</h2>
                <p>Legg til noe kult her</p>

                <section className="products">
                    {products.map((product) => (
                        <div className="product-card" id={product.id} key={product.id}>
                            <div className="delete-wrapper">
                                <button className="delete-button" onClick={() => {
                                    setDeleteProductId(product.id);
                                    setShowDeleteConfirmation(true);
                                }}>Delete</button>
                            </div>
                            <img src={`${process.env.PUBLIC_URL}/${product.productImage}`} alt={product.productName} />
                            <div className="productCard-details">
                                {editingProductId === product.id ? (
                                    <>
                                        <input
                                            type="text"
                                            value={editedProducts[product.id]?.productName || product.productName}
                                            onChange={(e) => setEditedProducts({ ...editedProducts, [product.id]: { ...editedProducts[product.id], productName: e.target.value } })}
                                        />
                                        <input
                                            type="text"
                                            value={editedProducts[product.id]?.price || product.price}
                                            onChange={(e) => setEditedProducts({ ...editedProducts, [product.id]: { ...editedProducts[product.id], price: e.target.value } })}
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
                                {editingProductId === product.id ? (
                                    <>
                                        <button className="update-button" onClick={() => handleUpdateProduct(product.id, editedProducts)}>Update</button>
                                        <button className="cancel-button" onClick={() => handleCancelEdit(product.id)}>Cancel</button>
                                    </>
                                ) : (
                                    <button className="edit-button" onClick={() => handleEditProduct(product.id, product)}>Edit</button>
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
