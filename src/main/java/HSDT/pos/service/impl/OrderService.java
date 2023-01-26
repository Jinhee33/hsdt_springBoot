package HSDT.pos.service.impl;

import HSDT.pos.dto.OrderDTO;
import HSDT.pos.persistance.mapper.IOrderMapper;
import HSDT.pos.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service(value = "OrderService")
public class OrderService implements IOrderService {

    private final IOrderMapper orderMapper;

    @Autowired
    public OrderService(IOrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    // 조회
    @Override
    public List<OrderDTO> OrderList() throws Exception{
        return orderMapper.OrderList();
    }

    // 작성
    @Transactional
    @Override
    public void insertOrder(OrderDTO oDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertOrder!");
        orderMapper.insertOrder(oDTO);
    }

    // 내용 조회
    @Override
    public OrderDTO getOrderView(OrderDTO oDTO) throws Exception {
        log.info(this.getClass().getName() + ".OrderViewService");
        return orderMapper.getOrderView(oDTO);
    }

}
