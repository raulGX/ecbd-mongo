import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { API_URL } from './constants';
class Register extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      name: '',
      password: ''
    }
    this.login = this.login.bind(this)
  }
  onChange = name => e => this.setState({ [name]: e.target.value })
  login(e) {
    e.preventDefault()
    axios.post(API_URL + 'auth/register', this.state)
      .then(() => {
        alert('user created succesfully')
        this.props.history.push('/login')
      })
      .catch(error => alert(error.response.data.message || 'Network error'))
  }
  render() {
    return (
      <div className="container">
        <div className="row">
          <form onSubmit={this.login} className="col-xs-offset-4 col-xs-4">
            <div className="input-group">
              <label>Username</label>
              <input onChange={this.onChange('name')} value={this.state.name} type="text" className="form-control" placeholder="Username" required />
            </div>
            <div className="input-group">
              <label>Password</label>
              <input onChange={this.onChange('password')} value={this.state.password} type="password" className="form-control" placeholder="Password" required />
            </div>
            <button className="btn btn-default" type="submit" onClick={() => { }}>Register</button>
            <br />
            <Link to="/login">Login</Link>
          </form>
        </div>
      </div>
    );
  }

};

export default Register;
