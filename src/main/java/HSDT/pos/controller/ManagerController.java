package HSDT.pos.controller;

import HSDT.pos.service.IManegerService;
import HSDT.pos.util.CmmUtil;
import HSDT.pos.util.EncryptUtil;
import HSDT.pos.dto.ManagerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class ManagerController {

    @Resource(name = "ManagerService")

    private IManegerService managerService;

    //---------------회원가입/로그인 페이지로 이동---------------
    @RequestMapping(value = "/managerLoginForm")
    public String managerLoginForm(){
        log.info(this.getClass().getName() + ".manager/managergLoginForm START!!!!");

        return "/login/login";
    }


    //---------------회원가입 로직---------------
    @PostMapping(value = "/insertManager")
    public String insertManager (HttpServletRequest request, HttpServletResponse response,
                                ModelMap model) throws Exception{

        log.info(this.getClass().getName() + ".insertManager START!!!!");

        String msg = "";
        String url = "";

        ManagerDTO pDTO = null;

        try {

            String id = CmmUtil.nvl(request.getParameter("id"));
            String pw = CmmUtil.nvl(request.getParameter("pw"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));

            log.info("id : " + id);
            log.info("pw : " + pw);
            log.info("user_name : " + user_name);

            pDTO = new ManagerDTO();

            pDTO.setId(id);
            pDTO.setPw(EncryptUtil.encHashSHA256(pw));
            pDTO.setUser_name(user_name);

            //데이터베이스에 회원정보 인서트
            int res = managerService.InsertManager(pDTO);

            if (res == 1){
                msg = "회원가입이 되었습니다.";
                url = "/managerLoginForm";

            }else{
                msg = "오류로 인해 회원가입에 실패하였습니다.";
                url = "/managerLoginForm";
            }

        }catch (Exception e){

            //저장 실패 시 사용자에게 보여줄 메세지
            msg = "실패하였습니다 : " + e.toString();
            url = "/managerLoginForm";
            log.info(e.toString());
            e.printStackTrace();

        }finally {
            log.info(this.getClass().getName() + ".insertManager END!!!");

            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            model.addAttribute("pDTO", pDTO);

            pDTO = null;
        }

        return "/redirect";
    }

    //--------------로그인 기능-----------------
    @PostMapping(value = "/getManagerLoginCheck")
    public String getManagerLoginCheck(HttpSession session, HttpServletResponse response, HttpServletRequest request,
                                   ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".getManagerLoginCheck START!!!");

        String msg = "";
        String url = "";

        try{

            String id = CmmUtil.nvl(request.getParameter("id"));
            String pw = CmmUtil.nvl(request.getParameter("pw"));


            log.info("id : " + id);
            log.info("pw : " + pw);

            ManagerDTO pDTO = new ManagerDTO();

            pDTO.setId(id);
            pDTO.setPw(EncryptUtil.encHashSHA256(pw));

            //로그인정보 체크
            ManagerDTO rDTO = managerService.getManagerLoginCheck(pDTO);

            log.info("rDTO:" + rDTO);

            if (rDTO == null){
                rDTO = new ManagerDTO();
                msg = "아이디 / 비밀번호를 확인해주세요";
                url = "managerLoginForm";

            }else {
                msg = "로그인 성공";
                url = "/Main";

                session.setAttribute("SS_ID", rDTO.getId());
                session.setAttribute("SS_PW", rDTO.getPw());
                session.setAttribute("SS_NAME", rDTO.getUser_name());

            }
            rDTO = null;

        }catch (Exception e){
            msg = "실패하였습니다 :" + e.toString();
            url = "managerLoginForm";
            System.out.println("오류로 인해 로그인이 실패하였습니다.");
            log.info(e.toString());
            e.printStackTrace();

        }finally {
            log.info(this.getClass().getName() + ".LOGIN_CHECK END!!!!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
        }

        return "/redirect";
    }


    //------회원탈퇴 (회원정보 삭제)------------
    @GetMapping(value = "/deleteManager")
    public String deleteManager(HttpSession session, HttpServletResponse response,HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".DELETE MANAGER START!!");

        String msg = "";
        String url = "";

        try{

            String id = CmmUtil.nvl((String) session.getAttribute("SS_ID"));

            log.info("id : " + id);

            ManagerDTO pDTO = new ManagerDTO();
            pDTO.setId(id);

            //회원정보 삭제
            int res = managerService.deleteManager(pDTO);

            log.info("res : " + res);
            msg = "회원탈퇴가 완료되었습니다.";
            url = "/managerLoginForm";

            session.invalidate();; //session clear

        }catch (Exception e){
            msg = "회원탈퇴 실패 : " + e.toString();
            url = "/Main";
            log.info(e.toString());
            e.printStackTrace();

        }finally {

            log.info(this.getClass().getName() + ".DELETE MANAGERER END!");

            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        return "/redirect";
    }

    //---------- 회원정보 수정 ---------------
    @RequestMapping(value = "/updateManager")
    public String updateManager(HttpSession session, HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".managerUpdate START!!!");

        String msg = "";
        String url = "";

        try {

            String id = CmmUtil.nvl(request.getParameter("id"));
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));

            log.info("id" + id);
            log.info("user_name" + user_name);

            ManagerDTO pDTO = new ManagerDTO();

            pDTO.setId(id);
            pDTO.setUser_name(user_name);

            managerService.updateManager(pDTO);

            msg = "수정되었습니다";
            url = "/mypage";

            session.setAttribute("SS_BUSINESS_NAME", pDTO.getId());
            session.setAttribute("SS_TEL", pDTO.getUser_name());

        }catch (Exception e){

            msg = "수정 실패";
            url = "/mypage";
            log.info("수정 실패 :" + e.toString());
            e.printStackTrace();

        }finally {
            log.info(this.getClass().getName() + ".ManagerUpdate END!!!");

            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }

        return "/redirect";
    }

    //------------- 로그아웃 -----------
    @RequestMapping(value = "/Logout")
    public String Logout(HttpServletRequest request, ModelMap model){
        log.info(this.getClass().getName() + ".LOGOUT START!!!");
        HttpSession session = request.getSession();

        String url = "/Main";
        String msg = "로그아웃 성공";

        session.invalidate(); // session clear

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "/redirect";
    }


    //마이페이지 이동
    @RequestMapping(value = "/mypage")
    public String mypage(HttpSession session, HttpServletResponse response,HttpServletRequest request, ModelMap model){

        log.info(this.getClass().getName() + ".MyPage GO!!!! ");

        return "/manager/mypage";
    }

}
