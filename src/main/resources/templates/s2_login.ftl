<html>
<head>
    <link rel="stylesheet" href="/public/css/style.css">
    <#include "s2_header.html">
</head>
<body>
<form action="/s2/results" method="post">
    <input class="s1_text_input" placeholder="MASTER_KEY" name="masterKey" value="" required>
    <input class="s1_submit_button" type="submit" name="Submit" value="Submit">
</form>
<div id="footer">current server: <strong>s2</strong>. Go to <a href="/s1/login">server s1</a></div>
</body>
</html>