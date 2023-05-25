 package no.ntnu.idata.gamingeqstore.EntityTests;


 import no.ntnu.idata.gamingeqstore.Entities.CartProduct;
 import no.ntnu.idata.gamingeqstore.Entities.Product;
 import no.ntnu.idata.gamingeqstore.Repositories.ProductRepository;
 import no.ntnu.idata.gamingeqstore.Services.ProductService;
 import no.ntnu.idata.gamingeqstore.TestConfiguration;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.context.annotation.Import;
 import org.springframework.test.context.junit.jupiter.SpringExtension;

 import static org.junit.jupiter.api.Assertions.*;


 //Test that add product will add a product to the database.
 public class AddProductTest {

     @Test
     public void PositiveAddProductTest() {
         Product product = new Product("Gaming Mouse", 199, "Product 1.png","A mouse");
         CartProduct cartProduct = new CartProduct(product);
         System.out.println(cartProduct);
     }

     @Test
     public void NegativeFindProductByNameTest() {
         Product product = new Product("Gaming Mouse", 199, "Product 1.png","A mouse");

     }
 }
