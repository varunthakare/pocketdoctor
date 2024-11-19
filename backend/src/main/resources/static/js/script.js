function showAlert(message) {
    document.getElementById('alertMessage').textContent = message;
    document.getElementById('customAlert').style.display = 'block';
}

function closeAlert() {
    document.getElementById('customAlert').style.display = 'none';
}


document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();
    const password1 = document.getElementById('password1').value;
    const password2 = document.getElementById('password2').value;

    if (password1 && password2 && password1 === password2) {
        showAlert('Created Password Successfully');
    } else {
        showAlert('Passwords do not match or fields are empty. Please try again.');
    }
});

function togglePasswordVisibility() {
    const password1 = document.getElementById('password1');
    const password2 = document.getElementById('password2');
    const checkbox = document.getElementById('showPassword');

    if (checkbox.checked) {
        password1.type = 'text';
        password2.type = 'text';
    } else {
        password1.type = 'password';
        password2.type = 'password';
    }
}

