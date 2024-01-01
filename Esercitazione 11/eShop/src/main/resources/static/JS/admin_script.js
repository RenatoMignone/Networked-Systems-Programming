function deleteBook(isbn){
    const url = "http://localhost:8080/eShop/Admin/"+isbn;

    fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("renato.mignone@gmail.com:ciao")
        }
    }).then(response => console.log(response))

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

        .then(response => response.json())
        .then(data => {
            console.log("Server response:", data);
            // Handle the server response as needed
        })
        .catch(error => {
            console.log()
            console.error("Error sending JSON:", error);
        })
}

