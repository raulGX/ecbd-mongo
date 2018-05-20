import React from 'react';
import { Link } from 'react-router-dom'
import { Button, Modal } from 'react-bootstrap';

import { API_URL } from '../common/constants'
import axios from 'axios';

class RecipePage extends React.Component {
  constructor(props) {
    super(props)
    this.state = { loaded: false, articles: [] }
  }
  componentDidMount() {
    let token = localStorage.getItem('token')
    axios.get(API_URL + 'articles/myArticles', { headers: { Authorization: token } })
      .then(res => this.setState({ articles: res.data, loaded: true }))
      .catch(err => alert('error connecting to the server'))
  }
  render() {
    let { loaded, articles } = this.state
    return (
      <div className='container'>
        <h1>Articles page</h1>
        {articles.map((article, index) => (
          <div key={article.id}>
            <Link to={`/myarticles/${article.id}`}><h2>{article.name}</h2></Link>
            <p>{article.body.substr(0, 200)}</p>
            {index !== articles.length - 1 && <hr style={{ marginBottom: "30px" }} />}
          </div>
        ))}
        {loaded
          ? (<div>
          </div>)
          : (<p>Loading</p>)
        }
      </div>
    );
  }
}

export default RecipePage;
