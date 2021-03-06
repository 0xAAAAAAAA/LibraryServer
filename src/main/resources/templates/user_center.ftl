<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- <link rel="icon" href="../../favicon.ico"> -->

    <title>Tiny图书馆管理系统-用户中心</title>

    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- // <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script> -->

    <!-- Bootstrap core CSS -->
    <!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> -->

    <!-- Custom styles for this template -->
    <!-- <link href="signin.css" rel="stylesheet"> -->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!-- [if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif] -->

     <!-- // <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        #pan{
            padding-top:25px;
        }
    </style>
</head>

<body>

<div class="container">
    <div>
        <nav class="navbar navbar-default container navbar-fixed-top" role="navigation">
            <ul class="nav navbar-nav">
                <li>
                    <a href="user_center.html">个人中心</a>
                </li>
                <li>
                    <a href="book_center.html">书库中心</a>
                </li>
                <li>
                    <a href="checkout_center.html">借书记录</a>
                </li>
            </ul>
        </nav>
    </div>
    <div style="padding-top:100px">
        <h2>个人中心</h2>
        <hr />
    </div>
    <div class="row clearfix" id="pan">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    账户信息
                </h3>
            </div>
            <table class="table table-hover">
                <th>用户名</th><th>密码</th>
                <tr><td>${user.username}</td><td>${user.password}</td></tr>
            </table>
        </div>
        <div style="float:right">
            <button type="button" class="btn btn-default">
               <a href="/view/change_user_info.html">修改信息</a>
            </button>
        </div>
    </div>
</div>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>