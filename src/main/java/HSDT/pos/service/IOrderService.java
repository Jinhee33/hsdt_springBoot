package HSDT.pos.service;

import HSDT.pos.dto.OrderDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> OrderList() throws Exception;
    void insertOrder(OrderDTO oDTO) throws Exception;
    // 조회
    OrderDTO getOrderView(OrderDTO oDTO) throws Exception;

}
