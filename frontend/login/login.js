document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const errorMessage = document.getElementById('errorMessage');

    if (username === '' || password === '') {
        showMessage('Please fill in all fields', 'error');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) {
            const errorText = response.status === 401 ? 'Invalid credentials' : 'Login failed';
            showMessage(errorText, 'error');
            return;
        }

        const data = await response.json(); // backend debe devolver { token: "..." }
        localStorage.setItem('token', data.token); // Guardar token JWT
        showMessage('Login successful!', 'success');

        console.log('Token JWT:', data.token);
        // Redirigir a otra página, por ejemplo tópicos
        window.location.href = "/topicos/topicos.html";

    } catch (err) {
        console.error('Error:', err);
        showMessage('Network error', 'error');
    }
});

// Función para mostrar mensajes
function showMessage(message, type) {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = message;

    if (type === 'success') {
        errorMessage.style.color = '#27ae60';
        errorMessage.style.background = 'rgba(39, 174, 96, 0.1)';
        errorMessage.style.borderColor = 'rgba(39, 174, 96, 0.2)';
    } else {
        errorMessage.style.color = '#c0392b';
        errorMessage.style.background = 'rgba(192, 57, 43, 0.1)';
        errorMessage.style.borderColor = 'rgba(192, 57, 43, 0.2)';
    }

    errorMessage.classList.add('show');
    setTimeout(() => errorMessage.classList.remove('show'), 3000);
}

// Animación de inputs
const inputs = document.querySelectorAll('input');
inputs.forEach(input => {
    input.addEventListener('focus', function () {
        this.parentElement.classList.add('focused');
    });
    input.addEventListener('blur', function () {
        if (this.value === '') this.parentElement.classList.remove('focused');
    });
});
