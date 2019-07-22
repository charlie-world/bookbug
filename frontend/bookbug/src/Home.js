import React, { Component } from 'react';
import logo from './logo.png';
import './Home.css';
import Search from './Search.js';
import LoginPanel from './LoginPanel.js';

class Home extends Component {
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
        <LoginPanel/>
      );
    }
  }
}

export default Home;
