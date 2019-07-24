import React, { Component } from 'react';
import Config from './config.js';
import { Link } from 'react-router-dom';

class Search extends Component {
    constructor() {
        super();
        this.state = {
            query:'',
            target: 'title',
            results: null,
            page: 1,
            total: 0,
            isEnd: false
        };
        this.queryChange = this.queryChange.bind(this);
        this.targetChange = this.targetChange.bind(this);
    }

    queryChange(event) {
		this.setState({query: event.target.value});
	}
	targetChange(event) {
		this.setState({target: event.target.value});
    }
    
    onQuery(queryString = null) {
        if (!queryString) {
            let target = `target-type=${this.state.target}`;
            let query = `query=${this.state.query}`;
            let page = `page=${this.state.page}`;
            let url = `${Config.host}/api/v1/books?${target}&${page}&${query}`;
            fetch(url, {
                method: 'GET',
                headers:{
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'X-USER-AUTH': CacheStorage.getItem('token')
                }
            }).then((response)=> response.json())
                .then((responseData) => {
                    if (responseData.meta.result_code === 200) {
                        this.setState({
                            results: responseData.data.books,
                            isEnd: responseData.data.isEnd,
                            total: responseData.data.total,
                        });
                        this.props.history.push(`/search?${target}&${page}&${query}`);
                    } else if (responseData.meta.result_code === 401) {
                        CacheStorage.removeItem('token');
                        this.props.history.push('/');
                    } else {
                        alert(responseData.meta.result_msg);
                    }
                });
        } else {
            let url = `${Config.host}/api/v1/books${queryString}`;
            fetch(url, {
                method: 'GET',
                headers:{
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'X-USER-AUTH': CacheStorage.getItem('token')
                }
            }).then((response)=> response.json())
                .then((responseData) => {
                    if (responseData.meta.result_code === 200) {
                        this.setState({
                            results: responseData.data.books,
                            isEnd: responseData.data.isEnd,
                            total: responseData.data.total,
                        });
                    } else if (responseData.meta.result_code === 401) {
                        CacheStorage.removeItem('token');
                        this.props.history.push('/');
                    } else {
                        alert(responseData.meta.result_msg);
                    }
                });
        }
    }

    logout() {
        sessionStorage.removeItem('token');
        this.props.history.push('/');
    }

    rank() {
        this.props.history.push('/rank');
    }

    history() {
        this.props.history.push('/history');
    }

    detail(bookId) {
        return `/book/${bookId}`;
    }

    result() {
        if (this.state.results === null) {
            return null;
        } else {
            const itemList = this.state.results.map((data) => 
                <tr>
                    <th>{data.name}</th>
                    <th>{data.authors}</th>
                    <th>{data.price}원</th>
                    <th><img src={data.thumbnail} alt="책 이미지"/></th>
                    <th><Link to={this.detail(data.book_id)}><button>상세보기</button></Link></th>
                </tr>
            );
            return (
                <div>
                    <ul>{itemList}</ul>
                    <ul>
                        <button onClick={this.first.bind(this)}>맨앞</button>
                        <button onClick={this.prev.bind(this)}>이전</button>
                        <button onClick={this.next.bind(this)}>다음</button>
                    </ul>
                </div>
            );
        }
    }

    first() {
        const { page } = this.state;
        if (page !== 1) {
            this.setState({ page : 1 }, function () {
                this.onQuery();
            });
        } else {
            alert("맨 앞 페이지 입니다.");
        }
    }

    prev() {
        var { page } = this.state;
        if (page !== 1) {
            this.setState({ page : page - 1 }, function() {
                this.onQuery();
            });
        } else {
            alert("맨 앞 페이지 입니다.");
        }
    }

    next() {
        var { page, total } = this.state;
        const endPage = total / 10;
        if (page <= endPage) {
            const nextPage = page + 1;
            this.setState({ page: nextPage }, function() {
                this.onQuery();
            });
        } else {
            alert("마지막 페이지 입니다.");
        }
    }

    searchTab() {
        return (
            <div name="search">
                <ul>
                    <h2>Search</h2>
                    <select name="target" value={this.state.target} onChange={this.targetChange}>
                        <option value="title" selected="selected">제목</option>
                        <option value="person">저자</option>
                        <option value="publisher">출판사</option>
                        <option value="isbn">ISBN</option>
                    </select>
                    <input type="text" name="searchForm" placeholder="검색어" value={this.state.query} onChange={this.queryChange}/>
                    <button className="queryButton" onClick={this.onQuery.bind(this)}>검색</button>
                    <button className="logout" onClick={this.logout.bind(this)}>로그아웃</button>
                    <button className="rank" onClick={this.rank.bind(this)}>Top10</button>
                    <button className="history" onClick={this.history.bind(this)}>나의 히스토리</button>
                </ul>
            </div>
		);
    }

    render() {
        const queryString = this.props.location.search;
        if (this.state.results === null) {
            if (queryString !== "") {
                return this.onQuery(queryString);
            } else {
                return this.searchTab();
            }
        } else {
            return (
                <div>
                    {this.searchTab()}
                    {this.result()}
                </div>
            );
        }
	}
}

export default Search;