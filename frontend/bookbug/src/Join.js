import React, { Component } from 'react';
import Config from './config.js';
import 'whatwg-fetch';

class Join extends Component {
  constructor() {
    super(...arguments);
		this.state = {
			requestID: '',
			requestPW: ''
		};
		this.alertOptions = {
			offset: 14,
			position: 'top right',
			theme: 'dark',
			time: 5000,
			transition: 'scale'
		};
		this.requestIDChange = this.requestIDChange.bind(this);
		this.requestPWChange = this.requestPWChange.bind(this);
	}

  onSubmit() {
		let userInfo = {
			'id':this.state.requestID,
			'password':this.state.requestPW
		};
		let apiPath = `${Config.host}/api/v1/users/join`;
		fetch(apiPath, {
			method: 'POST',
			headers:{
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*'
			},
			body: JSON.stringify(userInfo)
	    }).then((response)=> response.json())
	    .then((responseData)=> {
			if (responseData.meta.result_code === 201) {
        alert('회원 가입 성공');
			} else {
        alert(responseData.meta.result_msg);
      }
      this.props.history.push('/login');
		});
	}

	requestIDChange(event) {
		this.setState({requestID: event.target.value});
	}
	requestPWChange(event) {
		this.setState({requestPW: event.target.value});
	}

	render() {
		return (
			<div className="joinpanel">
				<div className="joinwindow">
					<ul>
						<h2 className="title">Join</h2>
						<h3><input type="text" name="requestID" placeholder="Id" value={this.state.requestID} onChange={this.requestIDChange}/></h3>
						<h3><input type="password" name="requestPW" placeholder="Password" value={this.state.requestPW} onChange={this.requestPWChange}/></h3>
						<h3><button className="joinwindowbutton" onClick={this.onSubmit.bind(this)}>가입 신청</button></h3>
					</ul>
				</div>
			</div>
		);
	}
}

export default Join;