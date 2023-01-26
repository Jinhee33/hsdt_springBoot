package HSDT.pos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private String tableNum;
    private String restaurantFloor;
    private String orderListNum;
    private String menuNum;
    private String totalPrice;
    private String totalCount;
    private String orderDate;
    private String id;
    private String menuName;
    private String menuPrice;
    private String categoryNum;
    private String categoryName;
}
