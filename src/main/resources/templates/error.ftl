<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
  Error!
  <br />
  <#if msg??>
    <h3 style="color: brown ">
      ${msg}
    </h3>
  <#else>
    <h3 style="color: brown ">
      no msg for error！
    </h3>
  </#if>
</body>
</html>