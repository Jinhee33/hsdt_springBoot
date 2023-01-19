<%@ page import="HSDT.pos.dto.MenuDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="HSDT.pos.util.CmmUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    List<MenuDTO> mList = (List<MenuDTO>) request.getAttribute("mList");

    //정보 조회 결과 보여주기
    if (mList == null){
        mList = new ArrayList<MenuDTO>();
    }

    //주석
    System.out.println("mList : " + mList);
    Object mDTO;
%>

<%
    String SS_ID = CmmUtil.nvl((String) session.getAttribute("SS_ID"));
    String SS_PASSWORD = CmmUtil.nvl((String) session.getAttribute("SS_PASSWORD"));

    System.out.println("SS_PASWORD : " + SS_PASSWORD);
%>

<%
    int id = 0;

    //Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
    if (session.getAttribute("SS_ID") != null) {
        //세션의 값을 가져오기
        id = 1;
    }
%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>

    <link href="../css/mainPage.css" rel="stylesheet" type="text/css" />
    <link href="../css/menu.css" rel="stylesheet" type="text/css" />
    <script src="../4_jquery_class/lib/jquery-1.9.1.min.js"></script>
</head>

<script type="text/javascript">
    function MenuDetail(Menu_num){
        location.href="/MenuDetail?Menu_num=" + Menu_num;
    }
</script>

<body>

<!--Google -Fonts-->
<link href='https://fonts.googleapis.com/css?family=Sintony:400,700&subset=latin-ext' rel='stylesheet' type='text/css'>

<!--Font-awsome-->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
    <header>
        <div style="text-align:center;margin-top:25px;font-weight:bold;texxxt-decoration:none;">test</div>
        <h2>Test Menu</h2>
    </header>

    <div class="xs-menu-cont">
        <a id="menutoggle"><i class="fa fa-align-justify"></i> </a>
        <nav class="xs-menu displaynone">
            <ul>
                <li class="active">
                    <a href="#">에스프레소</a>
                </li>
                <li>
                    <a href="#">프라푸치노</a>
                </li>
                <li>
                    <a href="#">블렌디드</a>
                </li>
                <li>
                    <a href="#">피지오</a>
                </li>
                <li>
                    <a href="#">티바나</a>
                </li>
                <li>
                    <a href="#">병음료</a>
                </li>
                <li>
                    <a href="#">디저트</a>
                </li>

            </ul>
        </nav>
    </div>
    <nav class="menu">
        <ul>
            <li class="active">
                <a href="#">에스프레소</a>
            </li>
            <li>
                <a href="#">프라푸치노</a>
            </li>
            <li>
                <a href="#">블렌디드</a>
            </li>
            <li>
                <a href="#">피지오</a>
            </li>
            <li>
                <a href="#">티바나</a>
            </li>
            <li>
                <a href="#">병음료</a>
            </li>
            <li>
                <a href="#">디저트</a>
            </li>
            <li>
                <a target="_blank" href="http://www.mywebtricks.com/">Contact</a>
            </li>
            <!-- 로그인 전 -->
            <%if (id == 0) {%>
            <li style="float:right;">
                <a href="/managerLoginForm" class="">로그인/회원가입</a>
            </li>
            <!-- 로그인 후 -->
            <%} else if (id > 0) {%>
            <li style="float:right;">
                <a href="/mypage" class="">내정보</a>
            </li>
            <li style="float:right;">
                <a href="/cu/Logout">로그아웃</a>
            </li>
            <%};%>
        </ul>
    </nav>

    <%for (MenuDTO m : mList) {%>
    <ul class="cards">
        <li class="cards__item">
            <div class="card">
                <div class="card__image card__image--fence"></div>
                <div class="card__content">
                    <div class="card__title"><a href="javascript:MenuDetail('<%=m.getMenu_num()%>')"><%=m.getMenu_name()%></a></div>
                    <p class="card__text"><a href="javascript:MenuDetail('<%=m.getMenu_num()%>')"><%=m.getMenu_price()%></a></p>
                    <button class="btn btn--block card__btn">구입하기</button>
                </div>
            </div>
        </li>
    </ul>
    <%}%>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript" src= "../js/mainPage.js"></script>

</body>
</html>