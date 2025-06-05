function completarTarea(id) {
    fetch(`/api/tareas/${id}/completar`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            actualizarListaConAnimacion();
        } else {
            alert('Error al completar la tarea');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al completar la tarea');
    });
}

function editarTarea(id, tituloActual, descripcionActual) {
    // Decodificar los valores para manejar caracteres especiales
    tituloActual = decodeURIComponent(tituloActual);
    descripcionActual = decodeURIComponent(descripcionActual);

    const nuevoTitulo = prompt('Editar título:', tituloActual);
    if (nuevoTitulo === null) return; // Usuario canceló

    const nuevaDescripcion = prompt('Editar descripción:', descripcionActual);
    if (nuevaDescripcion === null) return; // Usuario canceló

    const tareaActualizada = {
        titulo: nuevoTitulo,
        descripcion: nuevaDescripcion
    };

    fetch(`/api/tareas/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tareaActualizada)
    })
    .then(response => {
        if (response.ok) {
            actualizarListaConAnimacion();
        } else {
            alert('Error al editar la tarea');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al editar la tarea');
    });
}

function actualizarListaConAnimacion() {
    const icono = document.getElementById('iconoActualizar');
    const boton = icono.parentElement;

    icono.classList.add('rotate');
    boton.disabled = true;

    fetch('/api/tareas')
        .then(response => response.json())
        .then(tareas => {
            const contenedor = document.querySelector('tbody');
            contenedor.innerHTML = '';

            tareas.forEach(tarea => {
                const titulo = encodeURIComponent(tarea.titulo);
                const descripcion = encodeURIComponent(tarea.descripcion);
                
                const fila = `
                    <tr>
                        <td>${tarea.id}</td>
                        <td>${tarea.titulo}</td>
                        <td>${tarea.descripcion}</td>
                        <td>${tarea.completada ? 'Completada' : 'Pendiente'}</td>
                        <td>${new Date(tarea.fechaCreacion).toLocaleDateString()}</td>
                        <td>
                            <div class="btn-group gap-2" role="group">
                                ${!tarea.completada ? 
                                    `<button onclick="completarTarea(${tarea.id})" class="btn btn-success btn-sm">Completar</button>` 
                                    : ''}
                                <button onclick="editarTarea(${tarea.id}, '${titulo}', '${descripcion}')" class="btn btn-primary btn-sm">Editar</button>
                                <form action="/tareas/${tarea.id}/eliminar" method="post" class="d-inline">
                                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                `;
                contenedor.innerHTML += fila;
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al actualizar la lista');
        })
        .finally(() => {
            icono.classList.remove('rotate');
            boton.disabled = false;
        });
}