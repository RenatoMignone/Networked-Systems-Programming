function getBooks() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/eShop/Customer/all", true);
    xhr.send();
    xhr.onload = function () {
        if (xhr.status === 200) {
            let booksArray = JSON.parse(xhr.responseText);

            let element = document.getElementById("AllBooks");
            element.innerHTML = "";
            let newDiv = document.createElement("div");
            let text = document.createTextNode("Here is the full list of books: ");
            newDiv.appendChild(text);


            for (let i = 0; i < booksArray.length; i++) {
                let book = booksArray[i];

                let newBook = document.createTextNode("<br>"+"<br>" + i.toString() + ")" + "<br>" + "ISBN: " + book.isbn + "<br>" + "Titolo: " + book.title + "<br>" + "Autore: " + book.author+"<br>");
                newDiv.innerHTML += newBook.textContent;

            }

            element.appendChild(newDiv);
        }
    }
}
function getBookDetails(id) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/eShop/Customer/"+id, true);
    xhr.send();
    xhr.onload = function () {
        if (xhr.status === 200) {
            let book = JSON.parse(xhr.responseText);
            let element = document.getElementById("BookDetails");
            element.innerHTML = "";

            let newDiv = document.createElement("div");
            let text = document.createTextNode("Here are the details of the book specified: ");
            newDiv.appendChild(text);

            let details = document.createTextNode("<br>" + "ISBN: " + book.isbn + "<br>" + "Titolo: " + book.title + "<br>" + "Autore: " + book.author);
            newDiv.innerHTML += details.textContent;
            element.appendChild(newDiv);
        }
    }
}

function submitOrder(id){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/eShop/Customer/"+id, true);
    xhr.send();
    xhr.onload = function () {
        if (xhr.status === 201) {
            let element = document.getElementById("newOrder");
            let newDiv = document.createElement("div");
            let text = document.createTextNode("The order was successfully submitted");
            newDiv.appendChild(text);

            element.appendChild(newDiv);
        }
    }
}

function getOrderDetails(order_id){
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/eShop/Customer/orders/"+order_id;
    xhr.open("GET", url , true);
    xhr.send();
    xhr.onload = function () {
        if (xhr.status === 200) {
            let Order = JSON.parse(xhr.responseText);
            let libro = Order.libro;
            console.log(libro);

            let element = document.getElementById("OrderDetails");
            let newDiv = document.createElement("div");
            let text = document.createTextNode("Here are the details of the Order specified: ");
            newDiv.appendChild(text);

            let details = document.createTextNode("<br>" +"<br>" + "Order Id: "+Order.id +"<br>" +"Order Date: " +Order.data+"<br>"+ "Book's ISBN: " +libro.isbn +"<br>");
            newDiv.innerHTML += details.textContent;
            element.appendChild(newDiv);
        }
    }
}
