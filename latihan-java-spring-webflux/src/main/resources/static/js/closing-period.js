'use strict';

const url = "[[@{/closing-period/close}]]";
let eventSource = null;
const SSE_NAME = "closing-period";
const SSE_CLOSE = "close";


function onOpen() {
    const status = "CLOSE";
    eventSource = new EventSource(`${url}/${status}`);
    console.log("connection opening ...")
    eventSource.addEventListener("open", function (event) {
        console.log("connection opened");
    })
    onMessage();
}

function onClose() {
    if (eventSource) {
        console.info("connection closing ...");
        eventSource.close();
        console.info("connection closed");
    }
}

function onMessage() {
    eventSource.addEventListener(SSE_NAME, function (event) {
        let result = event.data;
        if (result === SSE_CLOSE) {
            onClose();
        } else {
            const dto = JSON.parse(result);
            const currentData = dto.currentData;
            const totalData = dto.totalData
            const elementLi = document.createElement("span");
            elementLi.textContent = `${currentData} of ${totalData}`;
            document.getElementById("result").innerHTML = '';
            document.getElementById("result").appendChild(elementLi);
        }
    })

    eventSource.onerror = (err) => {
        console.error("error : ", err);
        eventSource.close();
    }
}