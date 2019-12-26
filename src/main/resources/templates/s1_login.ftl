<html>
<head>
    <link rel="stylesheet" href="/public/css/style.css">
    <#include "s1_header.html">
</head>
<body>
<div class="s1_login_body">
    <span class="student-login">Prihlasenie studenta</span>

    <form action="/s1/test" method="post" class="s1_login_form">
        <input class="s1_text_input" placeholder="Meno" name="formularMeno" value="" required>
        <input class="s1_text_input" placeholder="Priezvisko" name="formularPriezvisko" value="">
        <br />
        <input class="s1_submit_button" type="submit" name="Vstupit" value="Vstupit">
    </form>
</div>
<div id="footer">current server: <strong>s1</strong>. Go to <a href="/s2/login">server s2</a></div>
</body>
</html>