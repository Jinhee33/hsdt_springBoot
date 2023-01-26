<%@ page import="HSDT.pos.util.CmmUtil" %>
<%@ page import="HSDT.pos.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%
    List<OrderDTO> oList = (List<OrderDTO>) request.getAttribute("oList");

    //게시판 조회 결과 보여주기
    if (oList == null) {
        oList = new ArrayList<OrderDTO>();
    }

    //사용자 로그인 여부 확인
    int id = 0;

    //Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
    if (session.getAttribute("SS_ID") != null) {
        //세션의 값을 가져오기
        id = 1;
    }

    //주석
    System.out.println("oList :" + oList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <title>자유게시판</title>

    <!--나중에 JS독립시키기-->
    <script type="text/javascript">
        //제목 누르면 게시글 보게 함
        function goToOrderView(table_num){
            location.href="/OrderView?table_num=" + table_num;
        }
    </script>
</head>

<!--게시판 css -->
<link href="../css/OrderList.css" rel="stylesheet" type="text/css"/>
<body>
<div class=mainDiv>

    <!--게시판 area-->
    <!--codepen board templet-->
    <div class="notice">
        <div class="page-title">
            <div class="container">
                <h3>게시판</h3>
            </div>
        </div>

        <!-- board list area -->
        <div id="board-list">
            <div class="container">
                <table class="board-table">
                    <thead>
                    <tr>
                        <th scope="col" class="th-num">테이블</th>
                        <th scope="col" class="th-title">메뉴 이름</th>
                        <th scope="col" class="th-id">메뉴 가격</th>
                        <th scope="col" class="th-date">담당직원</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (oList.isEmpty()) { %>
                    <tr>
                        <td colspan="5"> 등록된 게시물이 없습니다.</td>
                    </tr>
                    <% } else {%>
                    <%for (OrderDTO o : oList) {%>
                    <tr>
                        <td><%=o.getTableNum()%>
                        </td>
                        <td name = "title"><a href="javascript:goToBoardView('<%=o.getTableNum()%>')"><%=o.getTableNum()%>
                        </a></td>
                        <td><a><%=o.getMenuName()%>
                        </a></td>
                        <td><a><%=o.getMenuPrice()%>
                        </a></td>
                        <td><a><%=o.getId()%>
                        </a></td>
                    </tr>
                    <%}%>
                    <%}%>
                    <tr>
                        <td class = "insertOrder" colspan="5">
                            <a href="/OrderInsertForm">글쓰기</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>