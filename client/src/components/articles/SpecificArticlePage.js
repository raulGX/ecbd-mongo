import React from 'react';
import { API_URL } from '../common/constants'

class SpecificArticlePage extends React.Component {
  constructor(props) {
    super(props)
    this.state = { loaded: false, article: {} }
  }

  componentDidMount() {
    let id = this.props.match.params.articleId
    fetch(API_URL + 'articles/' + id)
      .then(res => res.json())
      .then(data => this.setState({ article: data, loaded: true }))
      .catch(err => alert('error connecting to the server'))
  }

  render() {
    let { loaded, article } = this.state
    return (
      <div className='container'>
        {
          loaded ?
            (
              <article>
                <h1>{article.name}</h1>
                <p>{article.body}</p>
              </article>
            ) : (
              <p>Loading...</p>
            )
        }
      </div>
    );
  }
}

export default SpecificArticlePage;
