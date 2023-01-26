package HSDT.pos.controller;

import HSDT.pos.dto.MenuDTO;
import HSDT.pos.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @SuppressWarnings({ "unchecked", "null" })  									    // 코드의 노란줄 없앰(검증되지 않은 연산자, null 분석관련 경고 제거)
    @ResponseBody                                                                       // 리턴되는 값이 View(jsp파일 등)를 통해 출력이 아닌 http body에 직접 쓰여지게 됨
    @RequestMapping(value="/json.do" , produces="application/json; charset=utf-8")   // json.do 라는 객체로
    public JSONObject json() throws JSONException {
        JSONObject jsonMain=new JSONObject();										    // json 객체 [{변수명:값, 변수명:값}]

        JSONArray jArray=new JSONArray();											    // json 배열
        JSONObject row = new JSONObject();
        row.put("name", "naver");
        row.put("email", "naver@naver.com");
        jArray.put(0, row);

        JSONObject row2 = new JSONObject();
        row2.put("name", "kokanry");
        row2.put("email", "kokanry@naver.com");
        jArray.put(1, row2);              // jArray = members에 들어갈 변수명,값들

        jsonMain.put("members", jArray);  // jsonMain = members, books, items 같은거 여러개 가능
        return jsonMain;
    }

}

