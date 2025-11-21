package nuts.study.webapiarchive.simpleorderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        OrderController controller = new OrderController();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(converter)
                .build();
    }

    @Test
    void postOrders_withValidItem_returnsCreated_andEchoesProduct() throws Exception {
        OrderCreateRequest req = new OrderCreateRequest(ItemList.ITEM_B, 5);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.product").value("ITEM_B"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    void postOrders_withInvalidItem_returnsBadRequest() throws Exception {
        String body = "{ \"product\": \"INVALID_ITEM\", \"quantity\": 1 }";

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}

