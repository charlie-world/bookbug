import React, { Component } from 'react';
import { Switch, Route, BrowserRouter as Router } from 'react-router-dom';
import Search from './Search.js';
import Login from './Login.js';
import Join from './Join.js';

class App extends Component {
  onLogout() {
    localStorage.removeItem('token');
  }

  render() {
    return (
      <Router>
        <div>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route path="/search" component={Search} />
            <Route path = "/join" component = {Join} />
            <Route path = "/login" component = {Login} />
            <Route path = "/book/:bookId" component = {Book} />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default App;
