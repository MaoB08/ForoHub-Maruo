// Basic form handling
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('errorMessage');
            if (username === '' || password === '') {
                errorMessage.textContent = 'Please fill in all fields';
                errorMessage.classList.add('show');
                setTimeout(() => {
                    errorMessage.classList.remove('show');
                }, 3000);
            } else {
                console.log('Login attempt:', { username, password });
                fetch('http://localhost:8080/api/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ username, password })
                })
                errorMessage.textContent = 'Login successful!';
                errorMessage.style.color = '#27ae60';
                errorMessage.style.background = 'rgba(39, 174, 96, 0.1)';
                errorMessage.style.borderColor = 'rgba(39, 174, 96, 0.2)';
                errorMessage.classList.add('show');
            }
        });
        const inputs = document.querySelectorAll('input');
        inputs.forEach(input => {
            input.addEventListener('focus', function() {
                this.parentElement.classList.add('focused');
            });
            
            input.addEventListener('blur', function() {
                if (this.value === '') {
                    this.parentElement.classList.remove('focused');
                }
            });
        });