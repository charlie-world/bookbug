import React, { Component } from 'react';
import Config from './config.js';
import Join from './Join.js';
import 'whatwg-fetch';

class Login extends Component {
	constructor(){
		super(...arguments);
		this.state ={
			requestID:'',
			requestPW:''
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

	onJoin() {

	}

	onSubmit() {
		let userInfo = {
			'id':this.state.requestID,
			'password':this.state.requestPW
		};
		let apiPath = `${Config.host}/api/v1/users/login`;
		fetch(apiPath, {
			method: 'POST',
			headers:{
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin': '*'
			},
			body: JSON.stringify(userInfo)
	    }).then((response)=> response.json())
	    .then((responseData)=> {
			if (responseData.meta.result_code === 200) {
				localStorage.setItem('token', responseData.data.token);
			} else {
				alert(responseData.meta.result_msg);
			}
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
			<div className="loginpanel">
				<div className="loginwindow">
					<ul>
						<li className="title">Login</li>
						<li><input type="text" name="requestID" placeholder="Id" value={this.state.requestID} onChange={this.requestIDChange}/></li>
						<li><input type="password" name="requestPW" placeholder="Password" value={this.state.requestPW} onChange={this.requestPWChange}/></li>
						<li><button className="loginwindowbutton" onClick={this.onSubmit.bind(this)}>로그인</button></li>
						<li><button className="joinwindowsbutton" onClick={Join}>회원가입</button></li>
					</ul>
				</div>
			</div>
		);
	}
}

export default Login;