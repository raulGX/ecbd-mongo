import React from 'react';
import { Link } from 'react-router-dom'
import { API_URL } from '../common/constants'

class Articles extends React.Component {
  constructor(props) {
    super(props)
    this.state = { loaded: false, articles: [] }
  }
  componentDidMount() {
    fetch(API_URL + 'articles')
      .then(res => res.json())
      .then(data => this.setState({ articles: data, loaded: true }))
      .catch(err => alert('error connecting to the server'))
  }
  render() {
    let { loaded, articles } = this.state
    return (
      <div className='container'>
        <h1>Articles page</h1>
        {articles.map((article, index) => (
          <div key={article.id}>
            <Link to={`/articles/${article.id}`}><h2>{article.name}</h2></Link>
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

export default Articles;
