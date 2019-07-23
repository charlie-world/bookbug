import React, { Component } from 'react';
import Config from './config.js';

class History extends Component {

    state = {
        history: [],
        update: true
    };

    getHistory() {
        if (sessionStorage.getItem('token') === null) {
            this.props.history.push('/login');
        } else {
            let apiPath = `${Config.host}/api/v1/users/history`;
            fetch(apiPath, {
                method: 'GET',
                headers:{
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'X-USER-AUTH': sessionStorage.getItem('token')
                }
            }).then((response)=> response.json())
            .then((responseData)=> {
                if (responseData.meta.result_code === 200) {
                    this.setState({
                        history: responseData.data,
                        update: false
                    });
                } else {
                    alert(responseData.meta.result_msg);
                }
            });
        }
    }

    view() {
        const { history } = this.state;
        const items = history.map((data) => 
            <tr>
                <th>{data.keyword}</th>
                <th>{data.history_date_time}</th>
            </tr>
        );
        return (
            <ul>{items}</ul>
        );
    }

    render() {
        const { update } = this.state;
        if (!update) {
            return this.view();
        } else {
            this.getHistory();
            return this.view();
        }
    }
}

export default History;