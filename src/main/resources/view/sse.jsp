<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>SSE demo</title>
</head>
<body>

<div id="msgFrompPush"></div>

<script type="text/javascript">
    if (!!window.EventSource) {
        var source = new EventSource("push");
        var msg = "";
        source.addEventListener("message", function (e) {
            msg += e.data + "<br/>";
            document.getElementById("msgFrompPush").innerText = msg;
        })

        source.addEventListener("open", function (e) {
            console.log("sse connection opening...")
        }, false);

        source.addEventListener("error", function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("sse connection closed.")
            } else {
                console.log(e.readyState);
            }
        }, false);
    } else {
        console.log("not supported sse!")
    }

</script>

</body>
</html>
