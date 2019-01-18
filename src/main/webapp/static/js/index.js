function animation() {

    function generateRandomInteger(min, max) {
        return Math.floor(min + Math.random()*(max + 1 - min))
    }

    var elem = document.getElementById("animate");
    var pos = generateRandomInteger(0, 400);
    var id = setInterval(frame, 5);



    function frame() {
        if (pos == 1400) {
            pos = generateRandomInteger(0, 800);
        } else {
            pos++;
            elem.style.left = pos + "px";
        }
    }
}