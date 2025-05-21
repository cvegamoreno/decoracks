const body = document.body;
const themeIcon = document.getElementById("themeIcon");
const themeItems = document.querySelectorAll(".dropdown-item");

// Cargar el tema guardado o según el sistema
const savedTheme = localStorage.getItem("theme") || "auto";
applyTheme(savedTheme);

if (themeItems) {
    themeItems.forEach(item => {
        item.addEventListener("click", () => {
            const selectedTheme = item.getAttribute("data-theme");
            applyTheme(selectedTheme);
        });
    });
}

function applyTheme(theme) {
    if (theme === "auto") {
        const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
        body.setAttribute("data-bs-theme", prefersDark ? "dark" : "light");
        if (themeIcon) themeIcon.className = prefersDark ? "bi bi-moon-stars" : "bi bi-sun";
    } else {
        body.setAttribute("data-bs-theme", theme);
        if (themeIcon) themeIcon.className = theme === "dark" ? "bi bi-moon-stars" : "bi bi-sun";
    }
    localStorage.setItem("theme", theme);
}