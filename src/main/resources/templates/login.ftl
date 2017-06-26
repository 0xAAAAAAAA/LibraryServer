<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Tiny图书馆管理系统-登录</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <#--<link href="signin.css" rel="stylesheet">-->
    <style>
        .reg{
           float:left;
        }
        .btn_bar .btn{
            width:200px!important;
            text-align:center;
        }
        #pan{
          padding-top:200px;
        }
        #pan .form-group{
          margin:30px;
        }
        .login{
          float:right;
        }
    </style>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container" id="pan">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h1 class="text-center muted" style="color: darkgoldenrod">
              Tiny图书馆管理系统
            </h1>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="doLoginOrRegister.html">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" placeholder="请输入用户名" class="form-control" id="username" name="username" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" required/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10 btn_bar">
                      <#--将注册放在右边使得Enter提交绑定到登录按钮上，使用CSS控制显示效果-->
                      <input type="submit" class="btn btn-primary login", value="登录", name="LoginOrRegister"/><input type="submit" class="btn btn-default reg", value="注册", name="LoginOrRegister"/>
                    </div>
                </div>
                <#if msg??>
                  <div>
                    <div class="alert alert-warning">${msg}</div>
                  </div>
                </#if>
            </form>
        </div>
    </div>
</div>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>