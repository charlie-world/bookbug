import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Search extends Component {
    constructor(){
		super(...arguments);
		this.state = {
            query:'',
            target: ''
		};
	}

    render() {
		return (
            <div name="search">
                <ul>
                    <h2>Search</h2>
                    
                    <h3><input type="text" name="searchForm" placeholder="searchId" value={this.state.query} onChange={this.query}/></h3>
                </ul>
            </div>
		);
	}
}

export default Search;