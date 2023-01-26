<%@ page import="HSDT.pos.util.CmmUtil" %>
<%@ page import="HSDT.pos.dto.OrderDTO" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%
    OrderDTO boDTO = (OrderDTO) request.getAttribute("boDTO");
    String img = CmmUtil.nvl((String) request.getAttribute("file"));
    String vid = CmmUtil.nvl((String) request.getAttribute("file"));

    if (boDTO == null) {
        boDTO = new OrderDTO();
    }

    String SS_NUM = CmmUtil.nvl((String) session.getAttribute("SS_ID"));

    int access = 1;
    if (CmmUtil.nvl((String) session.getAttribute("SS_ID")).equals(
            CmmUtil.nvl(boDTO.getId()))) {
        access = 2;
    }

    //사용자 로그인 여부 확인
    int id = 0;

    //Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
    if (session.getAttribute("SS_ID") != null) {
        //세션의 값을 가져오기
        id = 1;
    }

    System.out.println("boDTO : " + boDTO);
    System.out.println("bo.ID : " + boDTO.getId());
    System.out.println("SS_NUM : " + SS_NUM);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>게시글 조회</title>
</head>

<!--게시판 글쓰기 css-->
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link href="../css/OrderView.css" rel="stylesheet" type="text/css"/>

<!--게시판 css -->
<link href="../css/OrderList.css" rel="stylesheet" type="text/css"/>

<body>
<div class="wrap show">

    <!--BoardViewForm area-->
    <header> [ 게시글 조회 ] </header>

    <form id="form" class="topBefore">
        <div class="align_rt">
            <div class="notice">
                <div class="tab_each">
                    <div>
                        제목 <input id="name" type="text" name="title" maxlength="100"
                                  value="<%=CmmUtil.nvl(boDTO.getMenuName()) %>"
                                  readonly/>
                    </div>
                    <div>
                        내용 <textarea id="message" name="contents" readonly><%=CmmUtil.nvl(boDTO.getMenuPrice()).replaceAll("\r\n", "<br/>")%></textarea>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>