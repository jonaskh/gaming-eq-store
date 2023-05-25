 package no.ntnu.idata.gamingeqstore;

 import no.ntnu.idata.gamingeqstore.Services.CategoryService;
 import no.ntnu.idata.gamingeqstore.Services.ProductService;
 import org.springframework.context.annotation.Bean;

 public class TestConfiguration {

     @Bean
     public ProductService productService() {
         return new ProductService();
     }

     @Bean
     public CategoryService categoryService() {
         return new CategoryService();
     }

 }