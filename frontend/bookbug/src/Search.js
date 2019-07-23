import React, { Component } from 'react';
import Config from './config.js';

class Search extends Component {
    constructor(){
		super(...arguments);
		this.state = {
            query:'',
            target: 'title',
            results: null,
            page: 1,
            total: null,
            isEnd: false
        };
        
        this.queryChange = this.queryChange.bind(this);
        this.targetChange = this.targetChange.bind(this);
        this.pageChange = this.pageChange.bind(this);
    }

    queryChange(event) {
		this.setState({query: event.target.value});
	}
	targetChange(event) {
		this.setState({target: event.target.value});
    }
    pageChange(event) {
        this.setState({target: event.target.value});
    }
    
    onQuery() {
        let target = `target-type=${this.state.target}`;
        let query = `query=${this.state.query}`;
        let page = `page=${this.state.page}`;
		let url = `${Config.host}/api/v1/books?${target}&${page}&${query}`;
		fetch(url, {
			method: 'GET',
			headers:{
				'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'X-USER-AUTH': localStorage.getItem('token')
			}
	    }).then((response)=> response.json())
	    .then((responseData)=> {
			if (responseData.meta.result_code === 200) {
				this.setState({
                    results: responseData.data.books,
                    isEnd: responseData.data.isEnd,
                    total: responseData.data.total,
                });
			} else if (responseData.meta.result_code === 401) {
                localStorage.removeItem('token');
                this.props.history.push('/login');
            } else {
				alert(responseData.meta.result_msg);
			}
		});
    }

    result() {
        const itemList = this.state.results.map((data) => 
            /**
             * private Long bookId;
    private String name;
    private String thumbnail;
    private List<String> authors;
    private int price;
    private int salePrice;
             */
            <tr>
                <th>{data.name}</th>
                <th>{data.authors}</th>
                <th>{data.price}원</th>
                <th></th>
            </tr>
        );
        return (
            <ul>{itemList}</ul>
        );
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
                </ul>
            </div>
		);
    }

    render() {
        if (this.state.results === null) {
            return this.searchTab();
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