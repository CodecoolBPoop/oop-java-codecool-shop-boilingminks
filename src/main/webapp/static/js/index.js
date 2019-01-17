function animation() {
    var elem = document.getElementById("animate");
    var pos = 200;
    var id = setInterval(frame, 10);

    function frame() {
        if (pos == 1400) {
            pos = 200;
        } else {
            pos++;
            elem.style.left = pos + "px";
        }
    }
}