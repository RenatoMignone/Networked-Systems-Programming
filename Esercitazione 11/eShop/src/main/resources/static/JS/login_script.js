function redirectToCustomer() {
    // Change the URL to your desired customer page

    window.location.href = "../customer.html";
}

function redirectToAdmin(username,password){
    let xhr = new XMLHttpRequest();
    xhr.open('GET','http://localhost:8080/admin.html',true)
    xhr.setRequestHeader('Authorization', 'Basic ' + btoa(username + ':' + password));
    window.location.href = "../admin.html";
}

function Login(email, password) {
    let xhr = new XMLHttpRequest();
    // xhr.open("GET", "http://localhost:8080/eShop/login/"+email+"/"+password, true);
    xhr.open("GET","http://localhost:8080/admin.html",true)
    xhr.setRequestHeader('Authorization', 'Basic ' + btoa(email + ':' + password));
    xhr.send();

    xhr.onload = function () {
        if (xhr.status === 200) {
            window.location.href = "../admin.html";
        }
        else{
            let div = document.getElementById("");
        }
    }
}
