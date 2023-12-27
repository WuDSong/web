<%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/19
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button class="btn">TEST</button>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    let btn = document.querySelector('.btn')
    btn.addEventListener('click', function () {
        let data = new URLSearchParams();
        data.append('username', 'wdswdswds');

        axios({
            url: 'http://localhost:8080/WCD/TSer1',
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: data
        }).then(result => {
            console.log('结果：');
            console.log(result.data.message)
        }).catch(error => {
            console.log('出错误：');
            console.log(error.response.data.message);
        })
    })
</script>
</body>

</html>
