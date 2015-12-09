<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>複数APIの実行パターン</title>
  <link href="//cdn.jsdelivr.net/semantic-ui/2.1.6/semantic.min.css" rel="stylesheet" type="text/css"/>
  <script src="//cdn.jsdelivr.net/jquery/1.11.3/jquery.min.js"></script>
  <script src="//cdn.jsdelivr.net/semantic-ui/2.1.6/semantic.min.js"></script>
</head>
<body>
  <div class="ui fixed inverted menu">
    <div class="ui container">
      <a href="#" class="header item">複数APIの実行パターン</a>
    </div>
  </div>
  <div class="ui container" style="margin-top: 80px">
    <ol>
      <li><a href="#" id="link-sequentialsync">シーケンシャルに実行して、結果をまとめて返す</a></li>
      <li><a href="#" id="link-parallelsync">並列実行して、結果をまとめて返す</a></li>
      <li><a href="#" id="link-sse">並列実行して、返ってきたものから逐次返す(Server Sent Event)</a></li>
    </ol>
    <div class="ui segment">
      <div class="ui horizontal statistics">
        <div class="statistic">
          <div id="elapse" class="value"></div>
          <div class="label">msec</div>
        </div>
      </div>
      <form class="ui form">
        <textarea id="response" rows="40" readonly></textarea>
      </form>
      <div class="ui dimmer">
        <div class="ui large text loader">Running api...</div>
      </div>
    </div>
  </div>

  <script src="/js/api.js"></script>
</body>
</html>