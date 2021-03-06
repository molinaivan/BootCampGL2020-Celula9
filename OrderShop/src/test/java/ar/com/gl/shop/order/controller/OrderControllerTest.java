package ar.com.gl.shop.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.com.gl.shop.order.dto.OrderDTO;
import ar.com.gl.shop.order.model.Order;
import ar.com.gl.shop.order.service.impl.OrderServiceImpl;
import ar.com.gl.shop.order.utils.OrderDTOConverter;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	OrderServiceImpl orderService;

	@Mock
	OrderDTOConverter orderDTOConverter;

	@InjectMocks
	OrderController orderController;

	Order order;
	Order order2;
	List<Order> orders = new ArrayList<>();
	List<OrderDTO> ordersDTO = new ArrayList<>();
	OrderDTO orderDTO;
	Pageable pageable;
	Page<Order> pageOrder;
	ResponseEntity<OrderDTO> responseDTO;
	ResponseEntity<List<OrderDTO>> responseDTOList;

	@BeforeEach
	void setUp() {
		order = new Order(4L, 2L, 10);
		order.setId(1L);
		order2 = order;
		order2.setDisable(false);
		order2.setTotalPrice(20.0);
		orders.add(order2);
		orders.add(order);
		orderDTO = new OrderDTO(1L, 4L, 2L, 10, 20.0, false,LocalDate.now());
		ordersDTO.add(orderDTO);
		ordersDTO.add(orderDTO);
		pageable = PageRequest.of(1, 10);
		pageOrder = new PageImpl<>(orders);
	}

	@Test
	@DisplayName("post")
	void testCase_1() {
		when(orderDTOConverter.toEntity(orderDTO)).thenReturn(order);
		when(orderService.create(order)).thenReturn(order2);
		when(orderDTOConverter.toDTO(order2)).thenReturn(orderDTO);
		responseDTO = new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
		assertEquals(responseDTO, orderController.create(orderDTO));
	}

	@Test
	@DisplayName("getAll")
	void testCase_2() {
		when(orderDTOConverter.toDTOList(orders)).thenReturn(ordersDTO);
		when(orderService.getAll()).thenReturn(orders);
		responseDTOList = new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		assertEquals(responseDTOList, orderController.getAll());
	}

	@Test
	@DisplayName("getAll pageable")
	void testCase_3() {
		when(orderDTOConverter.toDTOList(orders)).thenReturn(ordersDTO);
		when(orderService.getAll(pageable)).thenReturn(orders);
		responseDTOList = new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		assertEquals(responseDTOList, orderController.getAll(pageable));
	}

	@Test
	@DisplayName("getById")
	void testCase_4() {
		lenient().when(orderDTOConverter.toDTO(order2)).thenReturn(orderDTO);
		when(orderService.getById(1L)).thenReturn(order2);
		responseDTO = new ResponseEntity<>(orderDTO, HttpStatus.OK);
		assertEquals(responseDTO, orderController.getById(1L));
	}

	@Test
	@DisplayName("getOrdersByCustomer")
	void testCase_5() {
		when(orderDTOConverter.toDTOList(orders)).thenReturn(ordersDTO);
		when(orderService.getOrdersByCustomer(2L)).thenReturn(orders);
		responseDTOList = new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		assertEquals(responseDTOList, orderController.getOrdersByCustomer(2L));
	}

	@Test
	@DisplayName("getOrdersByProduct")
	void testCase_6() {
		when(orderDTOConverter.toDTOList(orders)).thenReturn(ordersDTO);
		when(orderService.getOrdersByProduct(4L)).thenReturn(orders);
		responseDTOList = new ResponseEntity<>(ordersDTO, HttpStatus.OK);
		assertEquals(responseDTOList, orderController.getOrdersByProduct(4L));
	}

	@Test
	@DisplayName("update")
	void testCase_7() {
		when(orderDTOConverter.toDTO(order2)).thenReturn(orderDTO);
		when(orderService.update(orderDTO, 1L)).thenReturn(order2);
		responseDTO = new ResponseEntity<>(orderDTO, HttpStatus.OK);
		assertEquals(responseDTO, orderController.update(1L, orderDTO));
	}

	@Test
	@DisplayName("delete")
	void testCase_8() {
		orderController.delete(1L);
		verify(orderService).delete(1L);
		ResponseEntity<String> response = new ResponseEntity<>("Orden eliminada", HttpStatus.OK);
		assertEquals(response, orderController.delete(1L));
	}
	
	@Test
	@DisplayName("softDelete")
	void testCase_9() {
		when(orderDTOConverter.toDTO(order2)).thenReturn(orderDTO);
		when(orderService.softDelete(1L)).thenReturn(order2);
		responseDTO = new ResponseEntity<>(orderDTO, HttpStatus.OK);
		assertEquals(responseDTO, orderController.softDelete(1L));
	}


}
