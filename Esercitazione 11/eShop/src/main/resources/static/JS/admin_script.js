function deleteBook(isbn){
    const url = "http://localhost:8080/eShop/Admin/"+isbn;

    fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("renato.mignone@gmail.com:ciao")
        }
    }).then(response => {
        if (response.status === 200) {
            let element = document.getElementById("deleteBook");
            element.innerHTML="";
            let text = document.createTextNode("Element correctly deleted");
            element.appendChild(text);

        } else {
            let element = document.getElementById("deleteBook")
            element.innerHTML="";
            let text = document.createTextNode("This book does not exists");
            element.appendChild(text);
        }
    }).catch(error => {
        // Gestisci gli errori di rete o altre eccezioni
        console.error("Errore durante la chiamata API:", error);
    });

}

function createBook(isbn,Author,Title) {
    const jsonData = {
        isbn: isbn,
        author: Author,
        title: Title
    };

    const url = "http://localhost:8080/eShop/Admin";
    // xhr.setRequestHeader('Authorization', 'Basic ' "+ btoa("renato.mignone@gmail.com" + ':' + "ciao));

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization" : "Basic "+ btoa("renato.mignone@gmail.com:ciao")
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if (response.status === 201) {
                let element = document.getElementById("newBook");
                element.innerHTML="";
                let text = document.createTextNode("Book correctly Created");
                element.appendChild(text);

            }
            else {
                let element = document.getElementById("newBook")
                element.innerHTML="";
                let text = document.createTextNode("This ISBN already exists");
                element.appendChild(text);
            }
        }).catch(error => {
        // Gestisci gli errori di rete o altre eccezioni
        console.error("Errore durante la chiamata API:", error);
    });
}

