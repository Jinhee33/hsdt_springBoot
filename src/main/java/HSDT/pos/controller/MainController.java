package HSDT.pos.controller;

import HSDT.pos.dto.MenuDTO;
import HSDT.pos.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Resource(name = "MenuService")
    private IMenuService menuService;

    @RequestMapping(value = "main")
    public String Main(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName() + ".MAINPAGE GO!! ");

        List<MenuDTO> mList = menuService.getMenuList();

        if (mList == null) {
            mList = new ArrayList<>();
        }

        log.info("mList : " + mList);
        model.addAttribute("mList", mList);
        log.info(this.getClass().getName() + ".List End!");

        return "/main/mainPage";
    }

}
