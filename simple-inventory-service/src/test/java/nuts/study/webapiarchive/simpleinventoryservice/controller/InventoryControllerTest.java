package nuts.study.webapiarchive.simpleinventoryservice.controller;

import nuts.study.webapiarchive.simpleinventoryservice.controller.dto.DeductRequest;
import nuts.study.webapiarchive.simpleinventoryservice.controller.dto.DeductResponse;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.Inventory;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.InventoryRepository;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.InventoryService;
import nuts.study.webapiarchive.simpleinventoryservice.domain.inventory.ItemList;
import nuts.study.webapiarchive.simpleinventoryservice.service.InventoryAppService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private InventoryAppService inventoryAppService;

    @InjectMocks
    private InventoryController inventoryController;

    /**
     * @Mock 은 가짜 객체를 생성하는 어노테이션이다. 실제 서비스의 동작을 모방하는 객체이며 독립적인 테스트를 가능하게 해준다.
     * @InjectMocks 는 @Mock 으로 생성된 가짜 객체들을 주입하여 테스트 대상 객체를 생성하는 어노테이션이다.
     */

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
        objectMapper = new ObjectMapper();
    }

    // deduct 메서드에 대한 성공 요청에 대한 테스트
    @Test
    void deduct_Success() throws Exception {

        DeductResponse expectedResponse = new DeductResponse(true, "ITEM_A", 2, 1000);

        when(inventoryAppService.deductInventory(ItemList.ITEM_A, 2))
                .thenReturn(expectedResponse);

        DeductRequest deductRequest = new DeductRequest("ITEM_A", 2);
        String jsonRequest = objectMapper.writeValueAsString(deductRequest);

        mockMvc.perform(post("/inventory")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

}