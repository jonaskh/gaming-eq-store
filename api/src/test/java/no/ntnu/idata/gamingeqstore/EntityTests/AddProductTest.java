 package no.ntnu.idata.gamingeqstore.EntityTests;


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
 @DataJpaTest
 @Import(TestConfiguration.class)
 public class AddProductTest {

     @Autowired
     private ProductService productService;

     @Test
     public void PositiveAddProductTest() {
         Product product = new Product("Gaming Mouse", 199, "Product 1.png","A mouse", 1);

         productService.save(product);

         System.out.println(productService.findByName("Gaming Mouse").get());
         assertNotNull(productService.findByName("Gaming Mouse"));

     }

     @Test
     public void NegativeFindProductByNameTest() {
         Product product = new Product("Gaming Mouse", 199, "Product 1.png","A mouse", 1);

         productService.save(product);
         assertNull(productService.findByName("Gaming House"));
     }
 }
