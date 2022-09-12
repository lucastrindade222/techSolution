package br.com.lucasdevjava.techSolution.resource;

import br.com.lucasdevjava.techSolution.config.AplicationConfingTest;
import br.com.lucasdevjava.techSolution.dto.ProductsNewDTO;
import br.com.lucasdevjava.techSolution.repository.ProductsRepository;
import br.com.lucasdevjava.techSolution.service.ProductsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("ProductsResource")
public class ProductsResourceTest extends AplicationConfingTest {
    @MockBean
    private ProductsRepository productsRepositor;
    @MockBean
    private ProductsService productsService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void testSalva() throws JsonProcessingException {
        var productsNewDTO = new ProductsNewDTO("Xadrez","americanas","98,03");


        try {
            mockMvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productsNewDTO))
                    )
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
