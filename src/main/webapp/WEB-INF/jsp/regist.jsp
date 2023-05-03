<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ page import="java.util.*" %>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/regist.css" rel="stylesheet" type="text/css" >
<title>登録画面</title>
</head>
<body>
<div id="form-main">
  <div id="form-div">
    <form class="form" id="form1">
      
      <h1 class="title" style="text-align: center">新規登録</h1>
      
      <p class="name">
        <input name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="名前" id="name" />
      </p>
      
      <p class="email">
        <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email" placeholder="メール" />
      </p>

      <p class="password">
        <input name="password" type="password" class="validate[required,custom[email]] feedback-input" id="password" placeholder="パスワード" />
      </p>
      
      <p class="text">
        <textarea name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="備考"></textarea>
      </p>
      
      
      <div class="submit">
        <input type="submit" value="登録する" id="button-blue"/>
        <div class="ease"></div>
      </div>
      
      <p></p>
       <div class="back">
        <input type="back" value="戻る" id="button-red"/>
        <div class="ease1"></div>
      </div>
      
    </form>
  </div>
</body>
</html>