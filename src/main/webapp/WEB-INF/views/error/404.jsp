<%@ page contentType="text/html;charset=UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(404);%>

    <!DOCTYPE html>
    <html>
    <head>
    <title></title>
    <meta charset="utf-8">
    <style type="text/css">
    .imgWrapper{
    width: 85%;
    background-color: #fff;
    margin: 65px auto;
    padding-top: 60px;
    padding-bottom: 60px;
    overflow: hidden;
    }
    .imgbox{
    width: 334px;
    min-height: 383px;
    margin: 0px auto;
    background-image:url("/wbalone/images/404.png") ;
    }
    </style>
    </head>
    <body>
    <div class="imgWrapper">
    <div class="imgbox">
    </div>
    </div>
    </body>
    </html>