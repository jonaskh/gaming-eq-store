import { BrowserRouter, Routes, Route } from "react-router-dom";
import Shop from "./Shop";
import Home from "./Home";
  
  function App() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
        </Routes>
      </BrowserRouter>
    );
  }
  
  export default App;