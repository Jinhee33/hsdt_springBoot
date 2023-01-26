<%@ page import="HSDT.pos.util.CmmUtil" %>
<%@ page import="HSDT.pos.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    OrderDTO oDTO = (OrderDTO)request.getAttribute("oDTO");

    if(oDTO == null){
        oDTO = new OrderDTO();
    }

    int access = 1;
    if (CmmUtil.nvl((String)session.getAttribute("SS_ID")).equals(
            CmmUtil.nvl(oDTO.getId()))){
        access = 2;
    }

    //사용자 로그인 여부 확인
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
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>게시글 작성</title>
</head>

<!--게시판 글쓰기 css-->
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link href="../css/OrderView.css" rel="stylesheet" type="text/css" />

<!--게시판 css -->
<link href="../css/OrderList.css" rel="stylesheet" type="text/css"/>

<!-- JS파일로 독립시키기-->
<script type="text/javascript">
    function contentIsInsert(){
        document.frm.submit();
    }
    function goToOrderList(){
        location.href="/OrderList";
    }
</script>

<body>
<div class=mainDiv>

    <!--BoardInputForm area-->
    <header> [ 게시글 작성 ] </header>

    <form name='frm' id="form" class="topBefore" action="/OrderInsert" method="post" enctype="multipart/form-data">
        <input id="name" name="title" type="text" placeholder="제목">
        <textarea id="message" name="contents" type="text" placeholder="내용" required></textarea>
        <input type='reset' value='취소' onclick="javascript:goToOrderList()">
        <input id="submit" type="submit" value="작성하기" onclick="javascript:contentIsInsert()">
    </form>
</div>

</body>
</html>