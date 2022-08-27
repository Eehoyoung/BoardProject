let index= {
    init: function () {
        $("#btn-login").bind("click", () => {
            this.login();
        })
    },

    login: function() {

        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data) {
            // 통신 성공
            alert("로그인 완료")
            location.href="/"
            console.log(data);
        }).fail(function(error) {
            // 통신 실패
            alert("로그인 실패")
            console.log(error)
        });
    },

    save: function() {
        let data = {
            username:$("#username").val(),
            email:$("#email").val(),
            password:$("#password").val(),

        }
        console.log(data);

        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(data, textStatus, xhr) {
            // 통신 성공시
            console.log("xhr : " + xhr); // XMLHttpRequest 객체
            console.log(xhr);
            console.log("textStatus : " + textStatus);
            console.log("data : " + data);
            alert("회원가입이 완료되었습니다.");
            location.href = "/"; // 회원가입 완료 후 메인 페이지로 이동
        }).fail(function(error) {
            // 통신 실패시
            console.log(error);
            alert("회원가입에 실패하였습니다.");
        })
    }
}

index.init()