package HSDT.pos.service.impl;

import HSDT.pos.dto.MenuDTO;
import HSDT.pos.persistance.mapper.IMenuMapper;
import HSDT.pos.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "MenuService")
public class MenuService implements IMenuService {

    private final IMenuMapper menuMapper;

    @Autowired
    public MenuService(IMenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    //리스트
    @Override
    public List<MenuDTO> getMenuList() throws Exception {
        return menuMapper.getMenuList();
    }

    //등록
    @Override
    public void insertMenu(MenuDTO mDTO) throws Exception {
        menuMapper.insertMenu(mDTO);
    }

    //수정
    @Override
    public void updateMenu(MenuDTO mDTO) throws Exception {
        menuMapper.updateMenu(mDTO);
    }

    //삭제
    @Override
    public int deleteMenu(MenuDTO mDTO) throws Exception {
        int res = menuMapper.deleteMenu(mDTO);
        return res;
    }

}
