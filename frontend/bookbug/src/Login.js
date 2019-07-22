import React from 'react';
import Search from './Search.js';

function Login() {
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;
  
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/api/v1/users/login";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            localStorage.setItem('token', json.data.token);
        }
    };
    var data = JSON.stringify({"id": id, "password": password});
    xhr.send(data);

    return (
        <div className="App">
            <header className="App-header">
                <h2>dfadfa</h2>
            </header>
        </div>
    );
}

export default Login;