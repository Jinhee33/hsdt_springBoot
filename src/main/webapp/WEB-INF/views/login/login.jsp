<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>로그인/회원가입</title>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">

    <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link href="../css/login.css" rel="stylesheet" type="text/css" />
    <script src="../4_jquery_class/lib/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        function doRegUserCheck(f) {
            if (f.user_name.value === "") {
                alert("이름을 입력하세요");
                f.user_name.focus();
                return false;
            }
            if (f.id.value === "") {
                alert("아이디를 입력하세요");
                f.id.focus();
                return false;
            }
            if (f.pw.value === "") {
                alert("비밀번호를 입력하세요");
                f.pw.focus();
                return false;
            }
            if (f.pw2.value === "") {
                alert("비밀번호를 확인하세요");
                f.pw2.focus();
                return false;
            }
        }

        function pw() {
            var p1 = document.getElementById('pw').value;
            var p2 = document.getElementById('pw2').value;
            if (p1 != p2){
                alert("비밀번호가 일치하지 않습니다");
                return false;
            }else{
                return true;
            }
        }

        function doLoginUserCheck(f){

            if (f.id.value == ""){
                alert("아이디를 입력하세요");
                f.id.focus();
                return false;
            }

            if (f.pw.value == ""){
                alert("비밀번호를 입력하세요");
                f.pw.focus();
                return false;
            }
        }
    </script>

</head>
<body>

<div class="cotn_principal">
    <div class="cont_centrar">

        <div class="cont_login">
            <div class="cont_info_log_sign_up">
                <!-- LOGIN -->
                <div class="col_md_login">
                    <div class="cont_ba_opcitiy">

                        <h2>로그인</h2>
                        <p>환영합니다.</p>
                        <button class="btn_login" onclick="cambiar_login()">로그인</button>
                    </div>
                </div>
                <!-- SIGNUP -->
                <div class="col_md_sign_up">
                    <div class="cont_ba_opcitiy">
                        <h2>회원가입</h2>


                        <p>혹시 저희 사이트가 처음이신가요?</p>

                        <button class="btn_sign_up" onclick="cambiar_sign_up()">회원가입</button>

                    </div>
                </div>
            </div>


            <div class="cont_back_info">
                <div class="cont_img_back_grey">
                    <img src="../img/fire.jpg" alt="" />
                </div>

            </div>
            <!-- Анимированные LOGIN и SIGNUP после нажатия на кнопку -->
            <div class="cont_forms" >
                <div class="cont_img_back_">
                    <img src="../img/fire.jpg" alt="" />
                </div>
                <form class="cont_form_login" method="post" onsubmit="return doLoginUserCheck(this);">
                    <a href="#" onclick="ocultar_login_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
                    <h2>LOGIN</h2>

                    <input type="text" placeholder="아이디" name="id" id="id"/>
                    <input type="password" placeholder="비밀번호" name="pw" id="pw"/>
                    <button type="submit" class="btn_login" formaction="/getManagerLoginCheck" onclick="cambiar_login()">관리자 로그인</button>

                </form>

                <form class="cont_form_sign_up" name="f" method="post" action="/insertManager" onsubmit="return doRegUserCheck(this);">

                    <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>

                    <h2>SIGN UP</h2>
                    <input type="text" placeholder="아이디" name="id"/>
                    <input type="password" placeholder="비밀번호" name="pw" />
                    <input type="password" placeholder="비밀번호 확인" name="pw2" />
                    <input type="text" placeholder="이름" name="user_name"/>
                    <button class="btn_sign_up" onclick="cambiar_sign_up()" type="submit">회원가입 완료</button>
                </form>

            </div>
        </div>
    </div>

    <script src="js/index.js"></script>
    <script type="text/javascript" src= "../js/login.js"></script>
</div>
</body>
</html>