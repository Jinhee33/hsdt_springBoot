package HSDT.pos.service;

import HSDT.pos.dto.MenuDTO;

import java.util.List;

public interface IMenuService {
    //리스트
    List<MenuDTO> getMenuList() throws Exception;

    //등록
    void insertMenu(MenuDTO mDTO) throws Exception;

    //수정
    void updateMenu(MenuDTO mDTO) throws Exception;

    //삭제
    int deleteMenu(MenuDTO mDTO) throws Exception;
}

