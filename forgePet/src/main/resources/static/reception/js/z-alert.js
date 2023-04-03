(function () {
    const myAlert = document.createElement("div");
    myAlert.style.position = "absolute";
    myAlert.style.width = "40%";
    myAlert.style.top = "30%";
    myAlert.style.left = "30%"
    myAlert.style.zIndex = "1061";
    window.showAlert = (text, color = "alert-warning") => {
        myAlert.innerHTML = `
            <div class="alert ${color} alert-dismissible fade show" role="alert">
            <strong>${text}</strong>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" id="myAlert"></button>
            </div>`;
        setTimeout(() => {
            document.getElementById('myAlert').click();
        }, 2000);
    }
    document.body.appendChild(myAlert);
}());

//发送邮件冷却30s
function setTime(val) {
    let countDown = 20;
    val.setAttribute("disabled", "disabled");
    let interval = setInterval(function () {
        if (countDown === 0) {
            clearInterval(interval)
            val.removeAttribute("disabled");
            val.value = "发送验证码";
            return
        }
        val.value = "重新发送(" + countDown + ")";
        countDown--;
    }, 1000)
}