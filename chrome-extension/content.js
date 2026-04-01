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

        // Disable button + show loading state
        button.disabled = true;
        const originalText = button.innerText;
        button.innerText = "Generating...";
        button.style.opacity = "0.7";
        button.style.cursor = "not-allowed";

        try {

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

            // If backend returns error status
            if (!response.ok) {
                throw new Error("Backend error");
            }

            const reply = await response.text();

            composeBox.innerText = reply;

        } catch (error) {

            console.error("AI Reply Error:", error);

            composeBox.innerText =
                "⚠️ Unable to generate email reply. Backend service may not be running properly.";

        } finally {

            // Restore button state
            button.disabled = false;
            button.innerText = originalText;
            button.style.opacity = "1";
            button.style.cursor = "pointer";
        }
    };

    composeBox.parentElement.appendChild(button);
}

function observeGmail() {

    const observer = new MutationObserver(() => {

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