function toggleForms(formToShow) {
    var registerForm = document.getElementById('registerForm');
    var loginForm = document.getElementById('loginForm');
    var signUpText = document.getElementById('two');
    var dontHaveAccountText = document.getElementById('dontHaveAccountText');
    var haveAccountText = document.getElementById('haveAccountText');

    if (formToShow === 'loginForm') {
        registerForm.style.display = 'none';
        loginForm.style.display = 'block';
        signUpText.innerText = "Welcome back! Sign in here:";
        dontHaveAccountText.style.display = 'block';
        haveAccountText.style.display = 'none';
    } else {
        registerForm.style.display = 'block';
        loginForm.style.display = 'none';
        signUpText.innerText = "Sign up today!";
        dontHaveAccountText.style.display = 'none';
        haveAccountText.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var signUpLink = document.querySelector('.signin-link');
    if (signUpLink) {
        signUpLink.addEventListener('click', function(e) {
            e.preventDefault();
            toggleForms('loginForm');
        });
    }
});