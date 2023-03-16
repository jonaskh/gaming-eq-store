import { BrowserRouter, Routes, Route } from "react-router-dom";
import Shop from "./Pages/Shop";
import Home from "./Pages/Home";
import LoginPage from "./Pages/LoginPage";
import ShoppingCart from "./Pages/ShoppingCart";
  
  function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/cart" element={<ShoppingCart />} />
        </Routes>
      </BrowserRouter>
    );
  }
  
  export default App;