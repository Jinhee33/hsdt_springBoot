package HSDT.pos.controller;

import HSDT.pos.dto.OrderDTO;
import HSDT.pos.service.IOrderService;
import HSDT.pos.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class OrderController {

    @Resource(name = "OrderService")
    private IOrderService orderService;
    private final static Logger logger= LoggerFactory.getLogger(OrderController.class);

    // 리스트 조회
    @GetMapping(value = "OrderList", produces = "application/json")
    @ResponseBody
    public List<OrderDTO> OrderList(HttpServletRequest request) throws Exception{

        log.info(this.getClass().getName() + ".OrderList start!");

        List<OrderDTO> oList = orderService.OrderList();

        log.info("oList :" + oList);

        log.info(this.getClass().getName() + ".OrderList End!");

        return oList;
    }

//    @GetMapping(value = "OrderList2", produces = "application/json")
//    @ResponseBody
//    public List<OrderDTO> OrderList2(HttpServletRequest request) throws Exception{
//
//        log.info(this.getClass().getName() + ".OrderList start!");
//
//        List<OrderDTO> oList = orderService.OrderList2();
//
//        for (OrderDTO oDTO : oList) {
//            // Retrieve the file path and name from the database
//            String filePath = oDTO.getImgAddress();
//
//            // Read the file from the local file system
//            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
//
//            // Create a new MultipartFile object from the file content
//            MultipartFile file = new MockMultipartFile("D:\\android_img", filePath, "image/jpg", fileContent);
//
//            // Add the file to the OrderDTO object
//            oDTO.setImgAddress(String.valueOf(file));
//        }
//
//        log.info("oList :" + oList);
//
//        log.info(this.getClass().getName() + ".OrderList End!");
//
//        return oList;
//    }
    @GetMapping(value = "/OrderList2")
    public ResponseEntity<byte[]> OrderList2(HttpServletRequest request) throws Exception {
        // Get the local address and image name from the database
        List<OrderDTO> imageName = orderService.OrderList2();

        // Load the image from the local address in the DB
        byte[] imageBytes = loadLocalImage(String.valueOf(imageName));

        // Create the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);

        // Return the image bytes as a response entity
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    private byte[] loadLocalImage(String imageName) throws Exception {
        // Load the image from the local address in the DB
        String localAddress = "D:\\android_img";
        File imageFile = new File(localAddress + "/" + imageName);
        FileInputStream inputStream = new FileInputStream(imageFile);
        byte[] imageBytes = new byte[(int) imageFile.length()];
        inputStream.read(imageBytes);
        inputStream.close();

        return imageBytes;
    }

    //작성할 수 있는 페이지
    @GetMapping(value = "OrderInsertForm")
    public String OrderInsert(ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".OrderInsert is here!");
        return "/order/OrderInput";
    }

    //작성 완료
    @PostMapping(value = "OrderInsert")
    public String OrderListInsert(HttpSession session, HttpServletRequest request, ModelMap model, MultipartHttpServletRequest req) {

        log.info(this.getClass().getName() + ".Order Insert start!");

        String msg = "";
        String url = "";

        try{
            String orderList_num = CmmUtil.nvl((String) session.getAttribute("SS_ORDERLIST_NUM"));
            String menu_num = CmmUtil.nvl((String)session.getAttribute("SS_MENU_NUM"));
            String table_num = CmmUtil.nvl((String)session.getAttribute("SS_TABLE_NUM"));
            String id = CmmUtil.nvl((String)session.getAttribute("SS_ID"));

            log.info("orderList_num : " + orderList_num);
            log.info("menu_num : " + menu_num);
            log.info("table_num : " + table_num);
            log.info("id : " + id);

            OrderDTO oDTO = new OrderDTO();

            oDTO.setOrderListNum(orderList_num);
            oDTO.setMenuNum(menu_num);
            oDTO.setTableNum(table_num);
            oDTO.setId(id);

            orderService.insertOrder(oDTO);

            msg = "등록되었습니다";
            url = "/OrderList";

        } catch (Exception e) {

            msg = "실패하였습니다 : " + e.getMessage();
            url = "/OrderList";

            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".Order Insert End!");

            model.addAttribute("url", url);
            model.addAttribute("msg", msg);

            log.info("model : " + model);
        }

        return "/redirect";
    }

    //내용 조회
    @GetMapping(value = "OrderView")
    public String OrderView(HttpSession session, HttpServletRequest request, ModelMap model)
            throws Exception{
        log.info(this.getClass().getName() + ".OrderView Start!");

        String msg = "";

        try{
            String table_num = CmmUtil.nvl(request.getParameter("table_num"));
            String orderList_num = CmmUtil.nvl(request.getParameter("orderList_num"));

            log.info("table_num : " + table_num);
            log.info("orderList_num : " + orderList_num);

            OrderDTO oDTO = new OrderDTO();
            oDTO.setTableNum(table_num);
            oDTO.setOrderListNum(orderList_num);

            OrderDTO boDTO = orderService.getOrderView(oDTO);

            if (boDTO == null){
                boDTO = new OrderDTO();
            }

            log.info("getOrderView success");

            model.addAttribute("boDTO", boDTO);

            log.info("bo.table_num : " + boDTO.getTableNum());
            log.info("bo.orderList_num : " + boDTO.getOrderListNum());

        }catch (Exception e) {
            msg = "실패하였습니다. : " +  e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName() + ".OrderView End!");
            model.addAttribute("msg", msg);
        }

        return "/order/OrderView";
    }

}




/*@Slf4j
@Controller
public class OrderController {

    @Resource(name = "OrderService")
    private IOrderService orderService;

    // 리스트 조회
    @GetMapping(value = "OrderList")
    public String OrderList(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName() + ".OrderList start!");

        List<OrderDTO> oList = orderService.OrderList();

        if (oList == null){
            oList = new ArrayList<>();
        }

        log.info("oList :" + oList);
        model.addAttribute("oList", oList);

        log.info(this.getClass().getName() + ".OrderList End!");

        return "/order/OrderList";
    }

    //작성할 수 있는 페이지
    @GetMapping(value = "OrderInsertForm")
    public String OrderInsert(ModelMap model) throws Exception{
        log.info(this.getClass().getName() + ".OrderInsert is here!");
        return "/order/OrderInput";
    }

    //작성 완료
    @PostMapping(value = "OrderInsert")
    public String OrderListInsert(HttpSession session, HttpServletRequest request, ModelMap model, MultipartHttpServletRequest req) {

        log.info(this.getClass().getName() + ".Order Insert start!");

        String msg = "";
        String url = "";

        try{
            String orderList_num = CmmUtil.nvl((String) session.getAttribute("SS_ORDERLIST_NUM"));
            String menu_num = CmmUtil.nvl((String)session.getAttribute("SS_MENU_NUM"));
            String table_num = CmmUtil.nvl((String)session.getAttribute("SS_TABLE_NUM"));
            String id = CmmUtil.nvl((String)session.getAttribute("SS_ID"));

            log.info("orderList_num : " + orderList_num);
            log.info("menu_num : " + menu_num);
            log.info("table_num : " + table_num);
            log.info("id : " + id);

            OrderDTO oDTO = new OrderDTO();

            oDTO.setOrderListNum(orderList_num);
            oDTO.setMenuNum(menu_num);
            oDTO.setTableNum(table_num);
            oDTO.setId(id);

            orderService.insertOrder(oDTO);

            msg = "등록되었습니다";
            url = "/OrderList";

        } catch (Exception e) {

            msg = "실패하였습니다 : " + e.getMessage();
            url = "/OrderList";

            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".Order Insert End!");

            model.addAttribute("url", url);
            model.addAttribute("msg", msg);

            log.info("model : " + model);
        }

        return "/redirect";
    }

    //내용 조회
    @GetMapping(value = "OrderView")
    public String OrderView(HttpSession session, HttpServletRequest request, ModelMap model)
            throws Exception{
        log.info(this.getClass().getName() + ".OrderView Start!");

        String msg = "";

        try{
            String table_num = CmmUtil.nvl(request.getParameter("table_num"));
            String orderList_num = CmmUtil.nvl(request.getParameter("orderList_num"));

            log.info("table_num : " + table_num);
            log.info("orderList_num : " + orderList_num);

            OrderDTO oDTO = new OrderDTO();
            oDTO.setTableNum(table_num);
            oDTO.setOrderListNum(orderList_num);

            OrderDTO boDTO = orderService.getOrderView(oDTO);

            if (boDTO == null){
                boDTO = new OrderDTO();
            }

            log.info("getOrderView success");

            model.addAttribute("boDTO", boDTO);

            log.info("bo.table_num : " + boDTO.getTableNum());
            log.info("bo.orderList_num : " + boDTO.getOrderListNum());

        }catch (Exception e) {
            msg = "실패하였습니다. : " +  e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            log.info(this.getClass().getName() + ".OrderView End!");
            model.addAttribute("msg", msg);
        }

        return "/order/OrderView";
    }

}*/
