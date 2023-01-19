<%@ page import="HSDT.pos.util.CmmUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%

    String SS_BUSINESS_NUM = CmmUtil.nvl(String.valueOf((Integer) session.getAttribute("SS_BUSINESS_NUM")));
    String SS_BUSINESS_NAME = CmmUtil.nvl((String) session.getAttribute("SS_BUSINESS_NAME"));
    String SS_ID = CmmUtil.nvl((String) session.getAttribute("SS_ID"));
    String SS_NAME = CmmUtil.nvl((String) session.getAttribute("SS_NAME"));
    String SS_TEL = CmmUtil.nvl((String) session.getAttribute("SS_MEM_TEL"));

%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link href="../css/mypage.css" rel="stylesheet" type="text/css" />
    <script src="../4_jquery_class/lib/jquery-1.9.1.min.js"></script>
    <script src="https://kit.fontawesome.com/c49ea42a2f.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div id="logo"><h1 class="logo">CAMP US</h1>
        <button class="CTA" onclick="location.href='/cu/Main'">
            <h1>MAIN PAGE</h1>
        </button>
    </div>
    <div class="leftbox">
        <nav>
            <a id="profile" class="active"><i class="fa fa-user"></i></a>
            <a id="payment"><i class="fa-solid fa-pen"></i></a> <!-- 회원정보수정 아이콘 -->
            <a id="subscription" href="/cu/changeMem"><i class="fa-solid fa-user-plus"></i></a>   <!-- 사업자 전환 아이콘 -->
            <a id="privacy"><i class="fa-solid fa-key"></i></a> <!-- 비밀번호 변경 아이콘 -->
            <a id="settings" href="/cu/deleteMember"><i class="fa-solid fa-user-minus"></i></a> <!-- 회원탈퇴 아이콘 -->
        </nav>
    </div>
    <div class="rightbox">
        <div class="profile">
            <h1>관리자 정보</h1>
            <h2>사업자 번호</h2>
            <p><%=SS_BUSINESS_NUM%></p>
            <h2>매장 이름</h2>
            <p><%=SS_BUSINESS_NAME%><button class="btn">Change</button></p>
            <h2>이름</h2>
            <p><%=SS_NAME%></p>
            <h2>아이디</h2>
            <p><%=SS_ID%></p>
            <h2>전화번호</h2>
            <p><%=SS_TEL%><button class="btn">Change</button></p>
        </div>

        <div class="payment noshow">
            <h1>Payment Info</h1>
            <h2>Payment Method</h2>
            <p>Mastercard •••• •••• •••• 0000 <button class="btn">update</button></p>
            <h2>Billing Address</h2>
            <p>1234 Example Ave | Seattle, WA <button class="btn">change</button></p>
            <h2>Zipcode</h2>
            <p>999000</p>
            <h2>Billing History</h2>
            <p>2018<button class="btn">view</button></p>
            <h2>Redeem Gift Subscription </h2>
            <p><input type="text" placeholder="Enter Gift Code"></input> <button class="btn">Redeem</button></p>
        </div>

        <div class="subscription noshow">
            <h1>Your Subscription</h1>
            <h2>Payment Date</h2>
            <p>05-15-2018 <button class="btn">pay now</button></p>
            <h2>Your Next Charge</h2>
            <p>$8.48<span> includes tax</span></p>
            <h2>Hulu Base Plan</h2>
            <p>Limited Commercials <button class="btn">change plan</button></p>
            <h2>Add-ons</h2>
            <p>None <button class="btn">manage</button></p>
            <h2>Monthly Recurring Total </h2>
            <p>$7.99/month</p>
        </div>

        <div class="privacy noshow">
            <h1>Privacy Settings</h1>
            <h2>Manage Email Notifications<button class="btn">manage</button></h2>
            <p></p>
            <h2>Manage Privacy Settings<button class="btn">manage</button></h2>
            <p></p>
            <h2>View Terms of Use <button class="btn">view</button></h2>
            <p></p>
            <h2>Personalize Ad Experience <button class="btn">update</button></h2>
            <p></p>
            <h2>Protect Your Account <button class="btn">protect</button></h2>
            <p></p>
        </div>
        <div class="settings noshow">
            <h1>Account Settings</h1>
            <h2>Sync Watchlist to My Stuff<button class="btn">sync</button></h2>
            <p></p>
            <h2>Hold Your Subscription<button class="btn">hold</button></h2>
            <p></p>
            <h2>Cancel Your Subscription <button class="btn">cancel</button></h2>
            <p></p>
            <h2>Your Devices <button class="btn">Manage Devices</button></h2>
            <p></p>
            <h2>Referrals <button class="btn">get $10</button></h2>
            <p></p>
        </div>

    </div>
</div>
<script type="text/javascript" src= "../js/mypage.js"></script>
</body>
</html>