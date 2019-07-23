import React, { Component } from 'react';
import { Switch, Route, BrowserRouter as Router } from 'react-router-dom';
import Search from './Search.js';
import Login from './Login.js';
import Join from './Join.js';
import History from './History.js';
import Rank from './Rank.js';

class App extends Component {
  onLogout() {
    sessionStorage.removeItem('token');
  }

  render() {
    return (
      <Router>
        <div>
          <Switch>
            <Route path = "/search" component = {Search} />
            <Route path = "/join" component = {Join} />
            <Route path = "/login" component = {Login} />
            <Route path = "/book/:bookId" component = {Book} />
            <Route path = "/history" component = {History} />
            <Route path = "/rank" component = {Rank} />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
