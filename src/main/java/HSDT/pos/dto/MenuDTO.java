package HSDT.pos.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private String menu_num;
    private String menu_name;
    private String menu_price;
    private String category_num;
}
