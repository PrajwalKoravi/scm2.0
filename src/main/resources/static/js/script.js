console.log("script loaded...");


// Change theme work..
let currentTheme = getTheme();
console.log(currentTheme);

// initial..
document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});

//TODO:
function changeTheme() {

    changePageTheme(currentTheme, currentTheme);

    // set the listener to change the theme button
    const changeThemeButton = document.querySelector('#theme_change_button');

    changeThemeButton.addEventListener("click", (event) => {

        let oldTheme = currentTheme;
        
        console.log("change theme button clicked");
        
        if (currentTheme === "dark") {

            // chaneg to light theme..
            currentTheme = "light";

        } else {

            // chaneg to dark theme..
            currentTheme = "dark";
        }

        changePageTheme(currentTheme, oldTheme);
        
    });
}

//set theme to localStorage
function setTheme(theme) {
localStorage.setItem("theme", theme);
}

//get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

// Change current page theme..
function changePageTheme(theme, oldTheme) {

    // Update in localStorage..
    setTheme(currentTheme);

    // Remove the current theme...
    document.querySelector('html').classList.remove(oldTheme);

    // Set the current theme..
    document.querySelector('html').classList.add(theme);

    // Change the text of button
    document.querySelector('#theme_change_button').querySelector("span").textContent = theme == "light" ? "Dark" : "Light";

}


// Change page change theme...
