import { BrowserRouter, Route } from 'react-router-dom';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import Login from './Login.js';
import Join from './Join.js';
import Search from './Search.js';
import Book from '.Book.js';

ReactDOM.render(
  <BrowserRouter>
    <Route extract path = "/" component = {Login} />
    <Route path = "/search" component = {Search} />
    <Route path = "/join" component = {Join} />
    <Route path = "/login" component = {Login} />
    <Route path = "/book/:bookId" component = {Book} />
  </BrowserRouter>, document.getElementById('root')
);

serviceWorker.unregister();




