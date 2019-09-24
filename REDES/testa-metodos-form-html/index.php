<?php

if (isset($_REQUEST['data']))
{
    var_dump($_REQUEST);
    die();
}

?>

<html>

<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function() {
            $('#get').click(  function () {
                console.log("click");
                var sendData = $('#data').val();

                $.ajax({
                    url: '/',    //Your api url
                    type: 'GET',   //type is any HTTP method
                    data: {
                        data: sendData
                    },      //Data as js object
                    success: function (r) {
                        console.log(r)
                    }
                })
                ;
            });

            $('#post').click(  function () {
                console.log("click");
                var sendData = $('#data').val();

                $.ajax({
                    url: '/',    //Your api url
                    type: 'POST',   //type is any HTTP method
                    data: {
                        data: sendData
                    },      //Data as js object
                    success: function (r) {
                        console.log(r)
                    }
                })
                ;
            });

            $('#put').click(  function () {
                console.log("click");
                var sendData = $('#data').val();

                $.ajax({
                    url: '/',    //Your api url
                    type: 'PUT',   //type is any HTTP method
                    data: {
                        data: sendData
                    },      //Data as js object
                    success: function (r) {
                        console.log(r)
                    }
                })
                ;
            });
        });

    </script>
</head>
<body>
<form>

    Data:<br>
    <input id="data" type="text" name="data" value="Mickey"><br>
    <input id="get" type="button"  value="GET">
    <input id="post" type="button"  value="POST">
    <input id="put" type="button"  value="PUT">
    
</form>
</body>
</html>