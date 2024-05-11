// logic.js

// Función para obtener la lista de libros desde la API y actualizar la tabla en HTML
function getAllBooks() {
    fetch('http://localhost:8080/apiBooks')
        .then(response => response.json())
        .then(books => {
            const tableBooks = document.getElementById('tableBooks');

            // Limpiar la tabla antes de agregar los nuevos datos
            tableBooks.innerHTML = '<tr><th>ID</th><th>Título</th><th>Autor</th><th>Año</th><th>Género</th><th>Acciones</th></tr>';

            books.forEach(book => {
                const row = `<tr>
                                    <td>${book.id}</td>
                                    <td>${book.title}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publicationYear}</td>
                                    <td>${book.genre}</td>
                                    <td>
                                        <button onclick="showFormEdit(${book.id})">Editar</button>
                                        <button onclick="deleteBook(${book.id})">Eliminar</button>
                                    </td>
                                </tr>`;
                tableBooks.innerHTML += row;
            });
        })
        .catch(error => console.error('Error al obtener la lista de libros:', error));
}

// Función para agregar un nuevo libro a la API
function addBook(event) {
    event.preventDefault();

    const newBook = {
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        publicationYear: parseInt(document.getElementById('publicationYear').value),
        genre: document.getElementById('genre').value
    };

    fetch('http://localhost:8080/apiBooks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newBook),
    })
        .then(response => response.json())
        .then(book => {
            console.log('Libro agregado:', book);
            // Limpiar campos y ocultar formulario
            cleanForm();
            hideFormAdd();
            // Actualizar la lista de libros
            getAllBooks();
        })
        .catch(error => console.error('Error al agregar el libro:', error));
}

// Función para mostrar el formulario de agregar
function showFormAdd() {
    const formAdd = document.getElementById('formAdd');
    formAdd.style.display = 'block';
}

// Función para ocultar el formulario de agregar
function hideFormAdd() {
    const formAdd = document.getElementById('formAdd');
    formAdd.style.display = 'none';
}

// Función para limpiar los campos del formulario de agregar
function cleanForm() {
    document.getElementById('title').value = '';
    document.getElementById('author').value = '';
    document.getElementById('publicationYear').value = '';
    document.getElementById('genre').value = '';
}

// Función para mostrar el formulario de edición con los datos del libro seleccionado
function showFormEdit(id) {
    fetch(`http://localhost:8080/apiBooks/${id}`)
        .then(response => response.json())
        .then(book => {
            var formEdit = document.getElementById('formEdit');

            // Preencher el formulario con los datos del libro
            document.getElementById('titleEdit').value = book.title;
            document.getElementById('authorEdit').value = book.author;
            document.getElementById('publicationYearEdit').value = book.publicationYear;
            document.getElementById('genreEdit').value = book.genre;

            // Mostrar el formulario en la interfaz gráfica
            formEdit.style.display = 'block';

            // Agregar un evento al botón "Guardar cambios"
            var saveChangesBtn = formEdit.querySelector('button[type="submit"]');
            saveChangesBtn.addEventListener('click', function (event) {
                // Llamada a la función para guardar cambios
                saveChangesBook(event, id);
            });
        })
        .catch(error => console.error('Error al obtener datos del libro:', error));
}

// Función para guardar los cambios de un libro en la API
function saveChangesBook(event, id) {
    event.preventDefault();

    // Obtener los nuevos valores del formulario
    var newTitle = document.getElementById('titleEdit').value;
    var newAuthor = document.getElementById('authorEdit').value;
    var newPublicationYear = document.getElementById('publicationYearEdit').value;
    var newGenre = document.getElementById('genreEdit').value;

    // Lógica para enviar los cambios a la API y actualizar el libro
    fetch(`http://localhost:8080/apiBooks/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: newTitle,
            author: newAuthor,
            publicationYear: newPublicationYear,
            genre: newGenre,
        }),
    })
        .then(response => response.json())
        .then(updateBook => {
            console.log('Update Book:', updateBook);
            // Puedes ocultar el formulario después de la actualización si es necesario
            document.getElementById('formEdit').style.display = 'none';
            // Actualizar la lista de libros
            getAllBooks();
        })
        .catch(error => console.error('Error al actualizar el libro:', error));
}

// Función para eliminar un libro de la API
function deleteBook(id) {
    fetch(`http://localhost:8080/apiBooks/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                console.log('Libro eliminado con éxito.');
                // Actualizar la lista de libros después de eliminar
                getAllBooks();
            } else {
                console.error('Error al eliminar el libro. Código de estado:', response.status);
            }
        })
        .catch(error => console.error('Error al procesar la solicitud:', error));
}

// Cargar la lista de libros al cargar la página
window.onload = getAllBooks();