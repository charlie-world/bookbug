import React, { Component } from 'react';
import Config from './config.js';

class Rank extends Component {

    state = {
        rank: [],
        update: true
    };

    getRank() {
        if (CacheStorage.getItem('token') === null) {
            this.props.history.push('/login');
        } else {
            let apiPath = `${Config.host}/api/v1/populars-keyword`;
            fetch(apiPath, {
                method: 'GET',
                headers:{
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'X-USER-AUTH': CacheStorage.getItem('token')
                }
            }).then((response)=> response.json())
            .then((responseData)=> {
                if (responseData.meta.result_code === 200) {
                    this.setState({ 
                        rank : responseData.data,
                        update: false
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

    view() {
        const { rank } = this.state;            
        const items = rank.map((data) => 
            <tr>
                <th>{data.query_string}</th>
                <th>----------></th>
                <th>{data.count}</th>
            </tr>
        );
        return (
            <ul>{items}</ul>
        );
    }

    render() {
        if (!this.state.update) {
            return this.view();
        } else {
            this.getRank();
            return this.view();
        }
    }
}

export default Rank;