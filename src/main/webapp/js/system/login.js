$(function () {
    $("#login_sub").click(function () {
        $("#submitForm").submit();
    });
    //键盘回车触发登录
    $(document).keydown(function (event) {
        if(13==event.keyCode){
            $("#submitForm").submit();
        }
    })
})