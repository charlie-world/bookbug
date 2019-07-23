import React, { Component } from 'react';
import logo from './logo.png';
import Search from './Search.js';
import Login from './Login.js';

class App extends Component {
  onLogout() {
    localStorage.removeItem('token');
  }

  render() {
    if (localStorage.getItem('token') != null) {
      return (
        <h2>Hello</h2>
      );
    } else {
      return (
        <Login/>
      );
    }
  }
}

export default App;
