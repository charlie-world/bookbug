import React, { Component } from 'react';
import Config from './config.js';

class Book extends Component {
    state = {
      result: null,
      update: true
    };

    getBook(bookId) {
      let apiPath = `${Config.host}/api/v1/books/${bookId}`;
      fetch(apiPath, {
        method: 'GET',
        headers:{
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'X-USER-AUTH': sessionStorage.getItem('token')
        },
        }).then((response)=> response.json())
        .then((responseData)=> {
        if (responseData.meta.result_code === 200) {
          this.setState({ 
            result: responseData.data,
            update: false
          });
        } else if (responseData.meta.result_code === 401) {
          sessionStorage.removeItem('token');
          this.props.history.push('/');
        } else {
          alert(responseData.meta.result_msg);
        }
      });
    }

    render() {
      const { update } = this.state;
      if (!update) {
        const { result } = this.state;
        return (
          <div clasName = "bookDetail">
              <img src={result.thumbnail} alt="책 이미지"/>
              <div>제목: {result.name}<br/></div>
              <div>내용: {result.contents}</div>
              <div>ISNB: {result.isbn}<br/></div>
              <div>작가: {result.authors}<br/></div>
              <div>출판사: {result.publisher}</div>
              <div>출판일: {result.publish_datetime}</div>
              <div>정가: {result.price}</div>
              <div>할인가: {result.sale_price}</div>
          </div>
        );
      } else {
        this.getBook(this.props.match.params.bookId);
        return null;
      }
    }
}

export default Book;