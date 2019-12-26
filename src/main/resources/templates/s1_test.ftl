<html>
<head>
    <link rel="stylesheet" href="/public/css/style.css">
    <#include "s1_header.html">
</head>
<body>
<form action="/s1/results" method="post" class="s1_test_form">

    <#list questions as question>
        <fieldset id="question_${question.id}" class="s1_question">
            <span class="s1_question_title">
                ${question.id}.
                ${question.content}
            </span>
            <#if question.answer_1??>
                <div class="s1_question_answer">
                    <input type="radio" name="question_${question.id}" value=1> ${question.answer_1}
                </div>
            </#if>
            <#if question.answer_2??>
                <div class="s1_question_answer">
                    <input type="radio" name="question_${question.id}" value=2> ${question.answer_2}
                </div>
            </#if>
            <#if question.answer_3??>
                <div class="s1_question_answer">
                    <input type="radio" name="question_${question.id}" value=3> ${question.answer_3}
                </div>
            </#if>
            <#if question.answer_4??>
                <div class="s1_question_answer">
                    <input type="radio" name="question_${question.id}" value=4> ${question.answer_4}
                </div>
            </#if>
        </fieldset>


    </#list>
    <#if session??>
        <input type="hidden" id="session" name="session" value=${session}>
    </#if>

    <input class="s1_submit_button"  type="submit" name="Odovzdat" value="Odovzdat">
</form>
<div id="footer">current server: <strong>s1</strong>. Go to <a href="/s2/login">server s2</a></div>
</body>
</html>