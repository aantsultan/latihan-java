function move(currentData, totalData) {
    let elem = document.getElementById("myBar");
    let width = (currentData/totalData) * 100;
    width++;
    elem.style.width = width + "%";
    elem.innerHTML = width  + "%";
}