<%-- 
    Document   : index
    Created on : Dec 8, 2015, 7:00:42 PM
    Author     : hatu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"><head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Album example for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <!-- Bootstrap core CSS -->
        <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <!-- Custom styles for this template -->
        <link href="assets/css/album.css" rel="stylesheet">

    </head>

    <body>

        <div class="navbar-collapse inverse collapse" id="navbar-header" aria-expanded="false" style="height: 0px;">
            <div class="container-fluid">
                <div class="about">
                    <h4>About</h4>
                    <p class="text-muted">This is the image file upload example.</p>
                </div>
                <div class="social">
                    <h4>Contact</h4>
                    <ul class="list-unstyled">
                        <li><a href="#">Follow on Twitter</a></li>
                        <li><a href="#">Like on Facebook</a></li>
                        <li><a href="#">Email me</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">
                <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbar-header" aria-expanded="false">
                    ☰
                </button>
                <a href="#" class="navbar-brand">Album</a>
            </div>
        </div>

        <section class="jumbotron text-center">
            <form class="container" method="post" action="home" enctype="multipart/form-data">
                <h1 class="jumbotron-heading">Image Upload example</h1>
                <p class="lead text-muted">This is the image management sample web site.</p>
                <div class="form-group">
                    <label></label>
                    <input type="file" id="images" name="images" class="form-control" multiple onchange="onChangedFiles()"/>
                </div>
                <p id="preview-container">
                    
                </p>
                <p>
                    <button type="submit" class="btn btn-primary">Upload</button>
                    <button type="button" class="btn btn-secondary">Upload AJAX</button>
                </p>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <strong>Error </strong>${error}
                    </div>
                </c:if>
            </form>
        </section>

        <div class="album text-muted">
            <div class="container">

                <div class="row">
                    <c:forEach items="${images}" var="image">
                        <div class="card">
                            <img src="${image}" width="300" height="200">
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>

        <footer class="text-muted">
            <div class="container">
                <p class="pull-right">
                    <a href="#">Back to top</a>
                </p>
                <p>Album example is © Bootstrap, but please download and customize it for yourself!</p>
                <p>New to Bootstrap? <a href="../../">Visit the homepage</a> or read our <a href="../../getting-started/">getting started guide</a>.</p>
            </div>
        </footer>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="bower_components/holderjs/holder.js"></script>
        <script>
            $(function () {
                Holder.addTheme("thumb", {background: "#55595c", foreground: "#eceeef", text: "Thumbnail"});
            });
        </script>
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="assets/js/home-index.js" type="text/javascript"></script>
        <script src="bower_components/jquery/dist/jquery.min.js" type="text/javascript"></script>
    </body></html>