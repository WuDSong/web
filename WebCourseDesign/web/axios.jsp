<%--
  Created by IntelliJ IDEA.
  User: Episode 33
  Date: 2023/12/22
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    .box {
      margin: 0 auto;
      width: 400px;
      height: 300px;
      background-color: azure;
      box-shadow: 10px 5px rgb(195, 217, 210);
      text-align: center;
      padding: 10px;
    }

    .btn {
      width: 100px;
      height: 40px;
      border: 5px black solid;
      border-radius: 10px;
    }

    #wds {
      margin: 20px auto;
      width: 70%;
      height: 30px;
      /* background-color: aliceblue; */
      border: 1px brown solid;
      line-height: 30px;
    }

    .input {
      height: 20px;
      display: inline-block;
    }

    .right {
      background-color: aquamarine;
    }

    .error {
      background-color: red;
    }
  </style>
</head>
<body>
<div class="box">
  <div id="wds">登录试试密码？</div>
  <input type="text" class="input">
  <button class="btn">登录</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  axios({
    url: 'https://hmajax.itheima.net/api/register',
    method: 'POST',
    data: {
      username: 'wdswdswds',
      password: '123456789'
    }
  }).then(function (result) {

            console.log(result)
          }
  ).catch(error => {
    console.log('犯错误拉');
    console.log(error);
  })

  let div = document.querySelector('#wds')
  let input = document.querySelector('.input')
  let btn = document.querySelector('.btn')

  btn.addEventListener('click', function () {
    console.log(input.value);
    axios({
      url: 'https://hmajax.itheima.net/api/login',
      method: 'POST',
      data: {
        username: 'wdswdswds',
        password: input.value
      }

    }).then(result => {
      console.log('结果：');
      console.log(result.data.message)
      div.innerText = result.data.message
      div.style.backgroundColor = "aquamarine";
      div.classList.add('right')
    }).catch(error => {
      console.log('出错误：');
      console.log(error.response.data.message);
      div.innerText = error.response.data.message
      div.style.backgroundColor = "red";
    })
  })
</script>
</body>
</html>
