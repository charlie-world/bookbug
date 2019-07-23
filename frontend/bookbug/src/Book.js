import React, { Component } from 'react';
import Config from './config.js';

class Book extends Component {
    getBook(props) {
      let apiPath = `${Config.host}/api/v1/books/${props.bookId}`;
      fetch(apiPath, {
        method: 'GET',
        headers:{
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'X-USER-AUTH': localStorage.getItem('token')
        },
        }).then((response)=> response.json())
        .then((responseData)=> {
        if (responseData.meta.result_code === 200) {
          alert('회원 가입 성공');
        } else {
          alert(responseData.meta.result_msg);
        }
        this.props.history.push('/login');
      });
    }


    render() {
      return 
    }
}

export default Book;