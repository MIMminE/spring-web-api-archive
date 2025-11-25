package nuts.study.webapiarchive.simpleorderservice.controller;

import nuts.study.webapiarchive.simpleorderservice.controller.dto.OrderCreateRequest;
import nuts.study.webapiarchive.simpleorderservice.domain.order.ItemList;
import nuts.study.webapiarchive.simpleorderservice.domain.order.Order;
import nuts.study.webapiarchive.simpleorderservice.domain.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void create_shouldReturnCreated_andBody() throws Exception {
        // arrange
        when(orderService.create(eq(ItemList.ITEM_A), eq(2)))
                .thenReturn(Order.of(ItemList.ITEM_A, 2));

        OrderCreateRequest request = new OrderCreateRequest(ItemList.ITEM_A, 2);
        String json = objectMapper.writeValueAsString(request);

        // act & assert
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.product").value("ITEM_A"))
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    void create_withNullProduct_shouldReturnBadRequest() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest(null, 1);
        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}