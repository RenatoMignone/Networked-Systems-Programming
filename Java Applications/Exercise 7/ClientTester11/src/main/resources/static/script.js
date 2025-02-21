import "XMLHttpRequest";
function handleBookRequest(isbn){
    console.log("c")
    var xhttp = new XMLHttpRequest();
    var file = "localhost:8080/Books/" + isbn;
    xhttp.open("GET", file, false);
    xhttp.send()

    var res = JSON.parse(xhttp.responseText);
    // var response = "Book found: <br>ISBN: " + res.isbn;
    document.getElementById("book").innerHTML = xhttp.responseText;
}

function handleBookCreation(isbn,title,author){
    var msg = new Object();
    msg.isbn = isbn;
    msg.author = author;
    msg.title = title;

    var xhttp = new XMLHttpRequest();
    var uri = "localhost:8080/Books/" + isbn;

    xhttp.open("POST",uri,false);
    xhttp.setRequestHeader("Content-Type","application/json");
    xhttp.send(JSON.stringify(msg));
}

function ListAllBooks(){

}