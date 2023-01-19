package HSDT.pos.persistance.mapper;

import HSDT.pos.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMenuMapper {

    //리스트
    List<MenuDTO> getMenuList() throws Exception;

    //상세 조회
    MenuDTO getMenuView(MenuDTO mDTO) throws Exception;

    //등록
    void insertMenu(MenuDTO mDTO) throws Exception;

    //수정
    void updateMenu(MenuDTO mDTO) throws Exception;

    //삭제
    int deleteMenu(MenuDTO mDTO) throws Exception;

}
