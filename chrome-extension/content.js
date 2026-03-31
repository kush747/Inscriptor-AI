console.log("Gmail AI Extension Loaded");

function createAIButton(composeBox) {

    if (composeBox.parentElement.querySelector(".ai-reply-btn")) return;

    const button = document.createElement("button");

    button.innerText = "AI Reply";
    button.className = "ai-reply-btn";

    button.style.marginLeft = "10px";
    button.style.padding = "6px 12px";
    button.style.background = "#1a73e8";
    button.style.color = "white";
    button.style.border = "none";
    button.style.borderRadius = "4px";
    button.style.cursor = "pointer";

    button.onclick = async () => {

        const emailText = composeBox.innerText;

        const response = await fetch("http://localhost:9090/api/email/generate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                emailContent: emailText,
                tone: "professional",
                language: "English"
            })
        });

        const reply = await response.text();

        composeBox.innerText = reply;
    };

    composeBox.parentElement.appendChild(button);
}

function observeGmail() {

    const observer = new MutationObserver((mutations) => {

        const composeBoxes = document.querySelectorAll('[role="textbox"]');

        composeBoxes.forEach((box) => {
            createAIButton(box);
        });

    });

    observer.observe(document.body, {
        childList: true,
        subtree: true
    });
}

observeGmail();