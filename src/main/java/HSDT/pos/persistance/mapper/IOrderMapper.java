package HSDT.pos.persistance.mapper;

import HSDT.pos.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOrderMapper {

    List<OrderDTO> OrderList() throws Exception;
    //게시글 insert
    void insertOrder(OrderDTO oDTO) throws Exception;
    //게시글 상세내용 조회
    OrderDTO getOrderView(OrderDTO oDTO) throws Exception;

}
