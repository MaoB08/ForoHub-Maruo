document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('Debes iniciar sesión primero.');
        window.location.href = '/login/index.html';
        return;
    }

    cargarTopicos(token);
});

async function cargarTopicos(token) {
    try {
        const response = await fetch("http://localhost:8080/foro", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.status === 401 || response.status === 403) {
            alert("Tu sesión ha expirado. Por favor, inicia sesión de nuevo.");
            localStorage.removeItem('token');
            window.location.href = '/login/index.html';
            return;
        }

        if (!response.ok) {
            throw new Error(`Error al obtener tópicos: ${response.status}`);
        }

        const data = await response.json();
        console.log("Respuesta del servidor:", data);
        mostrarTopicos(data);
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudieron cargar los tópicos.");
    }
}

function mostrarTopicos(data) {
    const lista = document.getElementById('listaTopicos');
    lista.innerHTML = '';

    const topicos = Array.isArray(data) ? data : data.content;

    if (!topicos || topicos.length === 0) {
        lista.innerHTML = '<li>No hay tópicos disponibles</li>';
        return;
    }

    topicos.forEach(t => {
        const li = document.createElement('li');
        li.textContent = `${t.titulo} - ${t.autor} (${t.status})`;
        lista.appendChild(li);
    });
}

// Función para cerrar sesión
function logout() {
    localStorage.removeItem('token');
    window.location.href = '/login/index.html';
}

