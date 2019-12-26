<html>
<head>
    <link rel="stylesheet" href="/public/css/style.css">
    <#include "s2_header.html">
</head>
    <body>
        <p class="s2-question-title">Otazky s najmensim poctom spravnych odpovedi</p>
        <table>
            <thead>
                <td>id</td>
                <td>otazka</td>
                <td>oks</td>
            </thead>
        <#list questions as question>
            <tr>
                <td> ${question.id}</td>
                <td> ${question.content}</td>
                <td> ${question.number}</td>
            </tr>
        </#list>
        </table>



        <p class="s2-question-title">Otazky s najvacsim poctom nespavnych odpovedi</p>
        <table>
            <thead>
            <td>id</td>
            <td>otazka</td>
            <td>bads</td>
            </thead>
            <#list badsQuestions as question>
                <tr>
                    <td> ${question.id}</td>
                    <td> ${question.content}</td>
                    <td> ${question.number}</td>
                </tr>
            </#list>
        </table>






        <p class="s2-question-title">Otazka a zoznam najcastejsich odpovedi</p>
        <table>
            <thead>
            <td>id</td>
            <td>otazka</td>
            <td>odpoved</td>
            <td>pocet</td>
            </thead>
            <#list detailedQuestions as question>
                <tr>
                    <td> ${question.id}</td>
                    <td> ${question.content}</td>
                    <td> ${question.number}</td>
                    <td> ${question.number2}</td>
                </tr>
            </#list>
        </table>
        <br>
        <br>
        <br>
        <div id="footer">current server: <strong>s2</strong>. Go to <a href="/s1/login">server s1</a></div>
    </body>
</html>