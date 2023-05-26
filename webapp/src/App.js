import { BrowserRouter, Routes, Route } from "react-router-dom";
import Shop from "./Pages/Shop";
import Home from "./Pages/Home";
import LoginPage from "./Pages/LoginPage";
import RegisterPage from "./Pages/RegisterPage";
import SettingsPage from "./Pages/SettingsPage";
import ProductPage from "./Pages/ProductPage";
import ShoppingCart from "./Pages/ShoppingCart";
import AdminRegPage from "./Pages/AdminRegPage";
import AdminPanelPage from "./Pages/AdminPanelPage";

  function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/settings" element={<SettingsPage />} />
          <Route path="/product" element={<ProductPage />} />
          <Route path="/cart" element={<ShoppingCart />} />
          <Route path="/registerAdmin" element={<AdminRegPage />} />
          <Route path="/admin-panel" element={<AdminPanelPage />} />

        </Routes>
      </BrowserRouter>
    );
  }
  
  export default App;