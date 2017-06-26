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
    #pan .form-group{
      margin:30px;
    }
  </style>
</head>

<body>

<div class="container">
  <div>
    <nav class="navbar navbar-default container navbar-fixed-top" role="navigation">
      <ul class="nav navbar-nav">
        <li>
          <a href="user_center_admin.html">用户中心</a>
        </li>
        <li>
          <a href="book_center_admin.html">书库中心</a>
        </li>
        <li>
          <a href="checkout_center_admin.html">借阅中心</a>
        </li>
      </ul>
    </nav>
  </div>
  <div style="padding-top:100px">
    <h2>用户中心</h2>
    <hr />
  </div>
  <div class="row clearfix" id="pan">
    <nav class="navbar navbar-default container" role="navigation">
      <form class="navbar-form" role="search" action="user_center_admin.html">
        <div class="form-group col-md-8">
          <input type="text" class="form-control" style="width:100%" placeholder="用户名" name="username"/>
        </div>
        <div class="form-group col-md-2">
          <input type="submit" class="form-control" style="width:100%;background-color:#88cff0" value="搜索"/>
        </div>
      </form>
    </nav>
    <div class="panel panel-info">
      <div class="panel-heading">
        <h3 class="panel-title">
          查询结果
        </h3>
      </div>
      <div class="panel-body">
      <#if search_condition??>
        <p>${search_condition}</p>
      <#else>
        <p>未知条件</p>
      </#if>
      </div>
      <table class="table table-hover">
        <th>用户名</th><th>密码</th><th>操作</th>
      <#list list as user>
        <tr><td>${user.username}</td><td>${user.password}</td>
          <td>
            <a href="/view/change_user_info.html?username=${user.username}">修改</a>
          </td>
        </tr>
      </#list>
      </table>
    </div>
  </div>
</div>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>