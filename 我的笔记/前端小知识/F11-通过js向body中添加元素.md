* `const myAlert = document.createElement("div");`
* `document.body.appendChild(myAlert);`

```js
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
        // TODO 这里应该定时关闭警告框
    }
    document.body.appendChild(myAlert);
}());
```