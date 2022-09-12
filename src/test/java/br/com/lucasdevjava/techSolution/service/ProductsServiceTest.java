package br.com.lucasdevjava.techSolution.service;


import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.model.Products;
import br.com.lucasdevjava.techSolution.repository.ProductsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("ProductsServiceImpl")
public class ProductsServiceTest extends AplicationConfingTest {

    @MockBean
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsService productsService;

    @Test
    public void save(){
         var product = new Products(null,"Xadrez","americanas","98,03",null);
         product = productsService.save(product);

    }


}
