import React from 'react';
import logo from './logo.png';
import './Home.css';
import Search from './Search.js';
import Login from './Login.js';

class Home extends Component {
  onLogout() {
    localStorage.removeItem('token');
  }

  render() {
    if (localStorage.getItem('token') != null) {
      return <Hello
    }
  }
}


function Home() {
  var token = localStorage.getItem('token');
  if (token == null) {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <form id="f1" method="post" name="login_form">
              <label>Id :</label>
              <input type="text" name="id" id="id"/>
              <label>Password :</label>
              <input type="password" name="password" id="password"/>
              <button onClick={Login}></button>
          </form>
        </header>
      </div>
    );
  } else {
    Search();
  }
}

export default Home;
